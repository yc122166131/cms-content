package com.yc.pojo.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.yc.pojo.product.SubType;

/**
 * 商品左菜单 Dto 
 * parent_id : 1 subid1
 * 			   2 subid2
 * @author yuanchen
 *
 */
public class ProductLeftMenuDto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 356255885106913890L;
	
	private Long    supCateId;  //大类
	private String  supCateName;  //大类名称
	private Set<SubType> childType;  //小类(集合)
	
	
	public Long getSupCateId() {
		return supCateId;
	}
	public void setSupCateId(Long supCateId) {
		this.supCateId = supCateId;
	}
	public String getSupCateName() {
		return supCateName;
	}
	public void setSupCateName(String supCateName) {
		this.supCateName = supCateName;
	}
	public Set<SubType> getChildType() {
		return childType;
	}
	public void setChildType(Set<SubType> childType) {
		this.childType = childType;
	}
	
	
	
	
}
