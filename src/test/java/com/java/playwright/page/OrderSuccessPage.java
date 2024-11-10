package com.java.playwright.page;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.microsoft.playwright.Locator;
import com.java.playwright.baseTests.Generic;
public class OrderSuccessPage {
    Generic generic = new Generic("");
    private String orderSuccessMessageHeader_txt = ".complete-header";
    private String orderSuccessMessageInfo_txt = ".complete-text";
    private String successImg_altTxt = "Pony Express";

    public OrderSuccessPage() {
    }

    public Locator loc_OrderSuccessMessageHeader() {
        return this.generic.getByLocator(this.orderSuccessMessageHeader_txt);
    }

    public Locator loc_OrderSuccessMessageInfo() {
        return this.generic.getByLocator(this.orderSuccessMessageInfo_txt);
    }

    public Locator loc_SuccessImg() {
        return this.generic.getByAltTxt(this.successImg_altTxt);
    }
}
