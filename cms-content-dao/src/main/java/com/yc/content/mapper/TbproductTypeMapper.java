package com.yc.content.mapper;

import java.util.List;

import com.yc.pojo.dto.ProductLeftMenuDto;
import com.yc.pojo.product.JDProduction;
import com.yc.pojo.product.productType;

/**
 * productType Mapper 
 * 商品类别 mapper
 * @author yuanchen
 *
 */
public interface TbproductTypeMapper  {

		/**
		 * 获取 商品类别 
		 * @return
		 */
		public List<ProductLeftMenuDto> getProductTypeList();
	
}
