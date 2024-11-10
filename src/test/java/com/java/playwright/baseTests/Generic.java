package com.java.playwright.baseTests;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Generic {
    private Base base;
    protected static ThreadLocal<Page> page = new ThreadLocal();

    public Generic() {
        Base var10001 = this.base;
        page.set(Base.getBasePage());
    }

    public Generic(String set) {
    }

    public static Page getPage() {
        return (Page)page.get();
    }

    public Locator getById(String selector) {
        return ((Page)page.get()).locator("#" + selector);
    }

    public Locator getByLocator(String selector) {
        return ((Page)page.get()).locator(selector);
    }

    public Locator getByText(String selector) {
        return ((Page)page.get()).getByText(selector, (new Page.GetByTextOptions()).setExact(true));
    }

    public Locator getByTitle(String selector) {
        return ((Page)page.get()).getByTitle(selector, (new Page.GetByTitleOptions()).setExact(true));
    }

    public Locator getByLabel(String selector) {
        return ((Page)page.get()).getByLabel(selector, (new Page.GetByLabelOptions()).setExact(true));
    }

    public Locator getByPlaceholder(String selector) {
        return ((Page)page.get()).getByPlaceholder(selector, (new Page.GetByPlaceholderOptions()).setExact(true));
    }

    public Locator getByAltTxt(String selector) {
        return ((Page)page.get()).getByAltText(selector, (new Page.GetByAltTextOptions()).setExact(true));
    }

    public Locator getByTestDataId(String id) {
        return ((Page)page.get()).getByTestId(id);
    }

    public void hardWait(int millis) {
        ((Page)page.get()).waitForTimeout((double)millis);
    }

    public void navigate(String url) {
        ((Page)page.get()).navigate(url);
    }

    public void fillAndEnter(Locator locator, String text) {
        locator.fill(text);
        locator.press("Enter");
    }

    public void fill(Locator locator, String text) {
        locator.fill(text);
    }

    public void click(Locator locator) {
        locator.click();
    }

    public String getInnerText(Locator locator) {
        return locator.innerText();
    }
}
