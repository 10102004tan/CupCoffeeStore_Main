package Entiny;

import java.util.ArrayList;

public class Order {
	private int id;
	private Product product;
	private int quantity;
	private int price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Order(int id, Product product, int quantity, int price) {
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}
	
	
}
