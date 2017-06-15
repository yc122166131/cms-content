package com.yc.content.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.yc.utils.JsonUtils;
import com.yc.content.mapper.TbproductTypeMapper;
import com.yc.pojo.dto.ProductLeftMenuDto;
import com.yc.pojo.product.SubType;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring/applicationContext-dao.xml"})  
public class ContentTest {

	
	@Autowired
	private  TbproductTypeMapper productTypeMapper;
	
	/**
	 * 测试左菜单
	 */
	@Test
	public void testContent(){
		List list = productTypeMapper.getProductTypeList();
		/*for(int i = 0 ; i < list.size() ; i++){
			ProductLeftMenuDto pd = (ProductLeftMenuDto) list.get(i);
			System.out.println(pd.getSupCateName()+":");
			for(SubType sub : pd.getChildType()){
				System.out.println("  "+sub.getSubCateName());
			}
		}*/
		
		System.out.println(JsonUtils.objectToJson(list));
	}
	
	
	
}
