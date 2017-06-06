package com.yc.pojo;

import java.io.Serializable;

public class productType implements Serializable{

	/**
	 * 商品种类 实体
	 */
	private static final long serialVersionUID = 1229405998876404708L;
	
	private Long typeId;        //类型Id
	private String typeName;      //类型名称
	private String parentId;       //类型父Id
	private String enableFlag;     //是否可用
	
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getEnableFlag() {
		return enableFlag;
	}
	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}
}
