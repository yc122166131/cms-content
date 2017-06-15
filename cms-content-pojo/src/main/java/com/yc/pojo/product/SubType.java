package com.yc.pojo.product;

import java.io.Serializable;

/**
 * 商品子类 pojo  
 * @author yuanchen
 *
 */
public class SubType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3336658816928863945L;

	private Long subCateId;
	private String subCateName;
	
	public Long getSubCateId() {
		return subCateId;
	}
	public void setSubCateId(Long subCateId) {
		this.subCateId = subCateId;
	}
	public String getSubCateName() {
		return subCateName;
	}
	public void setSubCateName(String subCateName) {
		this.subCateName = subCateName;
	}
	
	
	
}
