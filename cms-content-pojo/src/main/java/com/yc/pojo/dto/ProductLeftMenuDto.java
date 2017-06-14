package com.yc.pojo.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.yc.pojo.product.SubType;

/**
 * 商品左菜单 Dto 
 * @author yuanchen
 *
 */
public class ProductLeftMenuDto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 356255885106913890L;
	
	private String    supCateId;  //大类
	private String  supCateName;  //大类名称
	private List<SubType> childType;  //小类(集合)
	
	
	public String getSupCateId() {
		return supCateId;
	}
	public void setSupCateId(String supCateId) {
		this.supCateId = supCateId;
	}
	public String getSupCateName() {
		return supCateName;
	}
	public void setSupCateName(String supCateName) {
		this.supCateName = supCateName;
	}
	public List<SubType> getChildType() {
		return childType;
	}
	public void setChildType(List<SubType> childType) {
		this.childType = childType;
	}
	
	
	
	
}
