package com.yc.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

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
	private String brandName;     //对应的商标
	private String supCateId;   // 商品所属大类
	private String subCateId;   // 商品所属小类
	private String enableFlag;  // 是否可用
	private Date   createTime;  //创建时间
	private Date   updateTime;  //更新时间
	
	
	
	public Product() {
		super();
	}
	public Product(JDProduction jd) {
		BeanUtils.copyProperties(jd, this);
		this.setSupCateId(jd.getProductType_1st());
		this.setSubCateId(jd.getProductType_2nd());
	}
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getSupCateId() {
		return supCateId;
	}
	public void setSupCateId(String supCateId) {
		this.supCateId = supCateId;
	}
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
	public String getSubCateId() {
		return subCateId;
	}
	public void setSubCateId(String subCateId) {
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
	public String getEnableFlag() {
		return enableFlag;
	}
	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}
	

}
