package com.yc.content.service;

import java.util.List;

import com.yc.pojo.JDProduction;

public interface CrwalService {

	
	/**
	 * 为每一个商品 添加 一个redis生成的 productId
	 * @param list 
	 * @return
	 */
	public List<JDProduction> addProductId(List<JDProduction> list);
}
