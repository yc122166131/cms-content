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

	private String subCateId;
	private String subCateName;
	
	public String getSubCateId() {
		return subCateId;
	}
	public void setSubCateId(String subCateId) {
		this.subCateId = subCateId;
	}
	public String getSubCateName() {
		return subCateName;
	}
	public void setSubCateName(String subCateName) {
		this.subCateName = subCateName;
	}
	
	
	
}
