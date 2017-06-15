package com.yc.content.mapper;

import java.util.List;

import com.yc.pojo.product.JDProduction;

/**
 * product Mapper 
 * 商品 mapper
 * @author yuanchen
 *
 */
public interface TbproductMapper {

	public List<JDProduction> getAll();
	
	public void insertJDItemInfo(List<JDProduction> Jd_INFO);
	
	public List<JDProduction> getProductInfoByProductTypeId(Long id);
	
}
