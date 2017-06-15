package com.yc.pojo.product;

import java.io.Serializable;

public class JDProduction implements Serializable {

	/**
	 *  京东商品信息爬虫 临时存储实体 
	 */
	private static final long serialVersionUID = 8251082441796321557L;
	
	private Long   productId;   //商品id
	private String productName; //商品名称
	private String imgPath; //图片地址
	private String price; //商品价格
	private String commentAmount; //评论数量
	private String companyName; //公司名(xxx专营店)
	private String productType_1st; // 商品大类
	private String productType_2nd; // 商品小类(爬取下来的商品 绑定的是 商品小类)
	private String brandName;  // 商品 所属品牌名称
	private String typeId; //商品类别(小类Id)
	
	
	public JDProduction() {
		super();
	}
	
	public JDProduction(String productType_1st, String productType_2nd) {
		super();
		this.productType_1st = productType_1st;
		this.productType_2nd = productType_2nd;
	}
	
	
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
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
	public String getCommentNum() {
		return commentAmount;
	}
	public void setCommentNum(String commentAmount) {
		this.commentAmount = commentAmount;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCommentAmount() {
		return commentAmount;
	}
	public void setCommentAmount(String commentAmount) {
		this.commentAmount = commentAmount;
	}
	public String getProductType_1st() {
		return productType_1st;
	}
	public void setProductType_1st(String productType_1st) {
		this.productType_1st = productType_1st;
	}
	public String getProductType_2nd() {
		return productType_2nd;
	}
	public void setProductType_2nd(String productType_2nd) {
		this.productType_2nd = productType_2nd;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
}
