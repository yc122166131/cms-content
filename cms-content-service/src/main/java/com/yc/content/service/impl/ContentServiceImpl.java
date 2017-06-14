package com.yc.content.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.content.mapper.TbproductMapper;
import com.yc.content.service.ContentService;
import com.yc.pojo.product.JDProduction;


/**
 * 内容Service
 * @author yuanchen
 *
 */
@Service(value="contentService")
public class ContentServiceImpl implements ContentService {

	
	@Autowired
	private  TbproductMapper productMapper;
	
	
	@Override
	public List<JDProduction> getProductInfo() {
		List<JDProduction>  list = productMapper.getAll();
		return list;
	}

}
