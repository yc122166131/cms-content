package com.yc.content.service;

import java.util.List;

import com.yc.pojo.dto.ProductLeftMenuDto;
import com.yc.pojo.product.JDProduction;

public interface ContentService {

	public List<JDProduction>  getProductInfo();
	
	/**
	 * 获取左菜单Info
	 * @return
	 */
	public List<ProductLeftMenuDto> getLeftMenuInfo();
	
	/**
	 * 根据 商品类型Id 获取 商品信息
	 * @param id
	 * @return
	 */
	public List<JDProduction> getProductInfoByProductTypeId(Long id);
	
}
