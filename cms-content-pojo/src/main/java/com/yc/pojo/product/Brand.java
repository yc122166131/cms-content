package com.yc.pojo.product;

import java.io.Serializable;
import java.util.Date;

public class Brand implements Serializable {

	/**
	 *  品牌 实体类
	 */
	private static final long serialVersionUID = -8065383516052969254L;
	
	
	private Long brandId; //品牌id
	private String  brandName ;//品牌名称
	private String brandDesc; //品牌描述
	private String enableFlag; //是否可用
	private String sortNo; //排序(用于界面)
	private Date createTime; //创建时间
	private Date updateTime; //更新维护时间
	
	
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandDesc() {
		return brandDesc;
	}
	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}
	public String getEnableFlag() {
		return enableFlag;
	}
	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}
	public String getSortNo() {
		return sortNo;
	}
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
