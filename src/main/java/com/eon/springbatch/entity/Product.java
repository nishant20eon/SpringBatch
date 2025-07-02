package com.eon.springbatch.entity;

import java.util.Objects;

public class Product {
	
	private String productId;
	private String title;
	private String description;
	private String price;
	private String discount;
//	private String discountedPrice;
	private String discounted_price;

	public String getDiscountedPrice() {
		return discounted_price;
	}
	public void setDiscountedPrice(String discounted_price) {
		this.discounted_price = discounted_price;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", title=" + title + ", description=" + description + ", price="
				+ price + ", discount=" + discount + ", discountedPrice=" + discounted_price + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, discount, price, productId, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(description, other.description) && Objects.equals(discount, other.discount)
				&& Objects.equals(price, other.price) && productId == other.productId
				&& Objects.equals(title, other.title);
	}
	public Product(String productId, String title, String description, String price, String discount,
			String discounted_price) {
		super();
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.discounted_price = discounted_price;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
