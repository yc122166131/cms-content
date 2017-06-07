package com.yc.pojo;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
	
	/**
	 * 商品实体类
	 */
	private static final long serialVersionUID = -1042916603079470972L;
	
	private Long   productId;   //商品id
	private String productName; //商品名称
	private String imgPath;       //图片地址
	private String price;         //商品价格
	private String commentAmount; //评论数量
	private String companyName; //公司名(xxx专营店)
	private String brandId;     //对应的商标
	private Long   subCateId;   // 商品所属小类
	private String enableFlag;  // 是否可用
	private Date   createTime;  //创建时间
	private Date   updateTime;  //更新时间
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCommentAmount() {
		return commentAmount;
	}
	public void setCommentAmount(String commentAmount) {
		this.commentAmount = commentAmount;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getSubCateId() {
		return subCateId;
	}
	public void setSubCateId(Long subCateId) {
		this.subCateId = subCateId;
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
	public String getBrand() {
		return brandId;
	}
	public void setBrand(String brandId) {
		this.brandId = brandId;
	}
	public String getEnableFlag() {
		return enableFlag;
	}
	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}
	

}
