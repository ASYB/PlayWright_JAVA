package com.java.playwright.page;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.microsoft.playwright.Locator;
import com.java.playwright.baseTests.Generic;


public class AllProductsPage {
    private Generic generic = new Generic("");
    private String addToCart_btn = "//div[text()='%s']//ancestor::div[@class='inventory_item_description']//button";
    private String price_lbl = "//div[text()='%s']//ancestor::div[@class='inventory_item_description']//div[@class='inventory_item_price']";
    private String shoppingCart_btn = "shopping_cart_container";
    private String shoppingCartCount_txt = "#shopping_cart_container>a>span";

    public AllProductsPage() {
    }

    public Locator loc_AddToCart(String productName) {
        return this.generic.getByLocator(String.format(this.addToCart_btn, productName));
    }

    public Locator loc_ProductPrice(String productName) {
        return this.generic.getByLocator(String.format(this.price_lbl, productName));
    }

    public Locator loc_ShoppingCart() {
        return this.generic.getById(this.shoppingCart_btn);
    }

    public Locator loc_ShoppingCartItemCount() {
        return this.generic.getByLocator(this.shoppingCartCount_txt);
    }

    public void addProduct(String productName) {
        this.generic.click(this.loc_AddToCart(productName));
    }

    public void clickOnCartButton() {
        this.generic.click(this.loc_ShoppingCart());
    }

    public String getCartProductQuantity() {
        return this.generic.getInnerText(this.loc_ShoppingCartItemCount());
    }

    public String getProductPrice(String productName) {
        return this.generic.getInnerText(this.loc_ProductPrice(productName));
    }
}
