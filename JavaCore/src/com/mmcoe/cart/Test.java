package com.mmcoe.cart;

public class Test {
	public static void main(String[] args) throws ShoppingCartException {
		Cart cart = new Cart();
		cart.addToCart(new Product("Marker",25));
		cart.addToCart(new Product("Duster",75));
		cart.checkout();
	}
}