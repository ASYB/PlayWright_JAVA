package com.java.playwright.page;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.microsoft.playwright.Locator;
import com.java.playwright.baseTests.Generic;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    Generic generic = new Generic("");
    private String cartItems_WE = "//div[@class='cart_item']";
    private String itemName_WE = "//div[@class='cart_item']//div[@class='inventory_item_name']";
    private String itemPrice_txt = "//div[text()='%s']/parent::a/following-sibling::div//div[@class='inventory_item_price']";
    private String checkout_btn = "checkout";

    public CartPage() {
    }

    public Locator loc_allCartItemsWE() {
        return this.generic.getByLocator(this.cartItems_WE);
    }

    public Locator loc_itemNamesInCart() {
        return this.generic.getByLocator(this.itemName_WE);
    }

    public Locator loc_itemPrice(String name) {
        return this.generic.getByLocator(String.format(this.itemPrice_txt, name));
    }

    public Locator loc_checkoutBtn() {
        return this.generic.getById(this.checkout_btn);
    }

    public int getTotalCartItemCount() {
        return this.loc_allCartItemsWE().count();
    }

    public List<String> getNameOfAllItemsInCart() {
        List<String> itemNames = new ArrayList();
        int totalCartItemCount = this.getTotalCartItemCount();

        while(totalCartItemCount > 0) {
            --totalCartItemCount;
            itemNames.add(this.generic.getInnerText(this.loc_itemNamesInCart().nth(totalCartItemCount)));
        }

        return itemNames;
    }

    public String getItemPrice(String itemName) {
        return this.generic.getInnerText(this.loc_itemPrice(itemName));
    }

    public void clickOnCheckout() {
        this.generic.click(this.loc_checkoutBtn());
    }
}
