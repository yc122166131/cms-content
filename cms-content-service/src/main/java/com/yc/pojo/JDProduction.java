package com.yc.pojo;

import java.io.Serializable;

public class JDProduction implements Serializable {

	private String productName; //商品名称
	private String imgPath; //图片地址
	private double price; //商品价格
	private String commentAmount; //评论数量
	private String companyName; //公司名(xxx专营店)
	
	
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
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
	
	
	
	
	
}
