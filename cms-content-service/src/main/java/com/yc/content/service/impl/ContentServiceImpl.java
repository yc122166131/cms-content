package com.yc.content.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cn.yc.utils.JsonUtils;
import com.yc.content.jedis.JedisClient;
import com.yc.content.mapper.TbproductMapper;
import com.yc.content.mapper.TbproductTypeMapper;
import com.yc.content.service.ContentService;
import com.yc.pojo.dto.ProductLeftMenuDto;
import com.yc.pojo.product.JDProduction;
import com.yc.pojo.product.SubType;


/**
 * 内容Service
 * @author yuanchen
 *
 */
@Service(value="contentService")
public class ContentServiceImpl implements ContentService {

	Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private  TbproductMapper productMapper; //商品
	
	@Autowired
	private  TbproductTypeMapper productTypeMapper;  //商品类型
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value(value="${LEFTMENU_KEY}")
	private String LEFTMENU_KEY;
	
	@Override
	public List<JDProduction> getProductInfo() {
		List<JDProduction>  list = productMapper.getAll();
		return list;
	}
	
	@Override
	public List<ProductLeftMenuDto> getLeftMenuInfo(){
		
		//先redis 
		List<ProductLeftMenuDto> list = null;
		
		/**
		 * redis 操作必须try  不应该影响 正常业务!
		 */
		try{ 
			String leftMenuInfo = jedisClient.get(LEFTMENU_KEY);
			if(!StringUtils.isEmpty(leftMenuInfo)){
					String typeJSONStr = jedisClient.get(LEFTMENU_KEY);//redis
					list = JsonUtils.jsonToList(typeJSONStr, ProductLeftMenuDto.class);
					return list;
			}
		}catch(Exception e){
			log.info(" ========= O(∩_∩)O ===========> \n" + e);
			e.printStackTrace();
		}
		
		list = productTypeMapper.getProductTypeList();//db
		if( list!=null && list.size()>0 ){
			/**
			 * redis 操作必须try  不应该影响 正常业务!
			 */
			try{
				jedisClient.set(LEFTMENU_KEY, JsonUtils.objectToJson(list));
				jedisClient.expire(LEFTMENU_KEY, 300);//300秒后过期
			}catch(Exception e1){
				log.info(" O(∩_∩)O ===========> " + e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<JDProduction> getProductInfoByProductTypeId(Long id) {
		List<JDProduction> plist = productMapper.getProductInfoByProductTypeId(id);
		return plist;
	}
	

}
