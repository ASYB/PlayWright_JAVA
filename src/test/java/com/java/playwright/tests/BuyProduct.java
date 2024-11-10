package com.java.playwright.tests;


import com.java.playwright.baseTests.Generic;
import com.java.playwright.baseTests.ReadPropertyFile;
import com.java.playwright.page.AllProductsPage;
import com.java.playwright.page.CartPage;
import com.java.playwright.page.CheckoutPage;
import com.java.playwright.page.LoginPage;
import com.java.playwright.page.OrderSuccessPage;
import java.util.Arrays;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BuyProduct {
    Generic generic;
    LoginPage loginPage;
    AllProductsPage allProductsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrderSuccessPage orderSuccessPage;
    String loginURL;
    String loginPassword;
    String loginUsername;
    String config = "./config.properties";
    SoftAssert softAssert;

    public BuyProduct() {
    }

    @BeforeClass
    public void beforeClass() {
        this.loginURL = ReadPropertyFile.getProperty("loginURL", this.config);
        this.loginPassword = ReadPropertyFile.getProperty("password", this.config);
        this.loginUsername = ReadPropertyFile.getProperty("username", this.config);
        this.softAssert = new SoftAssert();
        this.loginPage = new LoginPage();
        this.allProductsPage = new AllProductsPage();
        this.cartPage = new CartPage();
        this.checkoutPage = new CheckoutPage();
        this.orderSuccessPage = new OrderSuccessPage();
    }

    @BeforeMethod
    public void beforeMethod() {
        this.generic = new Generic();
    }

    @Test
    public void test_Successful_Checkout() {
        this.generic.navigate(this.loginURL);
        this.loginPage.login(this.loginUsername, this.loginPassword);
        this.allProductsPage.addProduct("Sauce Labs Backpack");
        this.softAssert.assertEquals(this.generic.getInnerText(this.allProductsPage.loc_AddToCart("Sauce Labs Backpack")), "Remove", "[Failed]: Sauce Labs Backpack not added to cart");
        this.allProductsPage.addProduct("Sauce Labs Bike Light");
        this.softAssert.assertEquals(this.generic.getInnerText(this.allProductsPage.loc_AddToCart("Sauce Labs Bike Light")), "Remove", "[Failed]: Sauce Labs Bike Light not added to cart");
        String backpackPrice = this.allProductsPage.getProductPrice("Sauce Labs Backpack");
        String bikeLightPrice = this.allProductsPage.getProductPrice("Sauce Labs Bike Light");
        float totalPrice = Float.parseFloat(backpackPrice.substring(1)) + Float.parseFloat(bikeLightPrice.substring(1));
        this.softAssert.assertEquals(this.allProductsPage.getCartProductQuantity(), "2", "[Failed]: Product quantity not correctly updated in cart");
        this.allProductsPage.clickOnCartButton();
        this.softAssert.assertEquals(this.cartPage.getTotalCartItemCount(), 2, "[Failed]: Total item count is different from expected count in cart");
        this.softAssert.assertEquals(this.cartPage.getNameOfAllItemsInCart().containsAll(Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light")), true, "[Failed]: Items in cart does not match");
        this.softAssert.assertEquals(this.cartPage.getItemPrice("Sauce Labs Backpack"), backpackPrice, "[Failed]: Sauce Labs Backpack price does not match in cart");
        this.softAssert.assertEquals(this.cartPage.getItemPrice("Sauce Labs Bike Light"), bikeLightPrice, "[Failed]: Sauce Labs Bike Light price does not match in cart");
        this.cartPage.clickOnCheckout();
        this.softAssert.assertEquals(this.checkoutPage.getCheckoutPageTitle(), "Checkout: Your Information", "[Failed]: Title is not same as expected");
        this.checkoutPage.fillCheckoutInfo("test", "test", "518360");
        this.checkoutPage.clickOnContinue();
        this.softAssert.assertEquals(this.checkoutPage.getCheckoutPageTitle(), "Checkout: Overview", "[Failed]: Checkout overview page title is not as expected");
        this.softAssert.assertEquals(this.cartPage.getNameOfAllItemsInCart().containsAll(Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light")), true, "[Failed]: Items on checkout page does not match");
        this.softAssert.assertEquals(this.cartPage.getItemPrice("Sauce Labs Backpack"), backpackPrice, "[Failed]: Sauce Labs Backpack price does not match in cart");
        this.softAssert.assertEquals(this.cartPage.getItemPrice("Sauce Labs Bike Light"), bikeLightPrice, "[Failed]: Sauce Labs Bike Light price does not match in cart");
        this.softAssert.assertEquals(Float.parseFloat(this.checkoutPage.getPrice("subTotal")), totalPrice, "[Failed]: Calculates and displayed sub total value mismatched");
        Float tax = Float.parseFloat(this.checkoutPage.getPrice("tax"));
        Float subTotal = Float.parseFloat(this.checkoutPage.getPrice("subTotal"));
        Float calcultedTotal = tax + subTotal;
        Float actualTotal = Float.parseFloat(this.checkoutPage.getPrice("total"));
        this.softAssert.assertEquals(actualTotal, calcultedTotal, "[Failed]: Calculated and displayed total mismatched");
        this.checkoutPage.clickOnFinish();
        this.softAssert.assertEquals(this.generic.getInnerText(this.orderSuccessPage.loc_OrderSuccessMessageHeader()), "Thank you for your order!", "[Failed]: Success message header not present");
        this.softAssert.assertEquals(this.generic.getInnerText(this.orderSuccessPage.loc_OrderSuccessMessageInfo()), "Your order has been dispatched, and will arrive just as fast as the pony can get there!", "[Failed]: Success message body not present");
        this.softAssert.assertEquals(this.orderSuccessPage.loc_SuccessImg().isVisible(), true, "[Failed]: Success image not visible");
        this.softAssert.assertAll();
    }
}
