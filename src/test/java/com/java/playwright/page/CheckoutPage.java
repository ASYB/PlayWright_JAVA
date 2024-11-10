package com.java.playwright.page;



import com.microsoft.playwright.Locator;
import com.java.playwright.baseTests.Generic;

public class CheckoutPage {
    Generic generic = new Generic("");
    private String checkoutInfo_txt = "//span[@class='title']";
    private String firstName = "first-name";
    private String lastName = "last-name";
    private String postalCode = "postal-code";
    private String continue_btn = "continue";
    private String allItemSubTotal_txt = ".summary_subtotal_label";
    private String totalTax_txt = ".summary_tax_label";
    private String totalPrice_txt = ".summary_total_label";
    private String finist_btn = "finish";

    public CheckoutPage() {
    }

    public Locator loc_checkoutPageTitle() {
        return this.generic.getByLocator(this.checkoutInfo_txt);
    }

    public Locator loc_getFirstName() {
        return this.generic.getById(this.firstName);
    }

    public Locator loc_getLastName() {
        return this.generic.getById(this.lastName);
    }

    public Locator loc_getPostalAdd() {
        return this.generic.getById(this.postalCode);
    }

    public Locator loc_getContinueButton() {
        return this.generic.getById(this.continue_btn);
    }

    public Locator loc_subTotalValue() {
        return this.generic.getByLocator(this.allItemSubTotal_txt);
    }

    public Locator loc_taxValue() {
        return this.generic.getByLocator(this.totalTax_txt);
    }

    public Locator loc_totalValue() {
        return this.generic.getByLocator(this.totalPrice_txt);
    }

    public Locator loc_finishButton() {
        return this.generic.getById(this.finist_btn);
    }

    public String getCheckoutPageTitle() {
        return this.generic.getInnerText(this.loc_checkoutPageTitle());
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        this.generic.fill(this.loc_getFirstName(), firstName);
        this.generic.fill(this.loc_getLastName(), lastName);
        this.generic.fill(this.loc_getPostalAdd(), postalCode);
    }

    public void clickOnContinue() {
        this.generic.click(this.loc_getContinueButton());
    }

    public String getPrice(String priceFor) {
        String price = "";
        switch (priceFor) {
            case "total":
                price = this.generic.getInnerText(this.loc_totalValue()).split(":")[1].trim().substring(1);
                break;
            case "tax":
                price = this.generic.getInnerText(this.loc_taxValue()).split(":")[1].trim().substring(1);
                break;
            case "subTotal":
                price = this.generic.getInnerText(this.loc_subTotalValue()).split(":")[1].trim().substring(1);
        }

        return price;
    }

    public void clickOnFinish() {
        this.generic.click(this.loc_finishButton());
    }
}
