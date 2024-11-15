package com.java.playwright.baseTests;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public final class Base {
    private static String configFile = "./config.properties";
    private static ThreadLocal<Playwright> playwright = new ThreadLocal();
    private static ThreadLocal<Browser> browser = new ThreadLocal();
    private static ThreadLocal<BrowserContext> browserContext = new ThreadLocal();
    private static ThreadLocal<Page> page = new ThreadLocal();
    private static ThreadLocal<Base> base = new ThreadLocal();

    private static void setPlaywright() {
        playwright.set(Playwright.create());
    }

    private static void setBrowser() {
        String binary = ReadPropertyFile.getProperty("binary", configFile);
        String channel = ReadPropertyFile.getProperty("channel", configFile);
        String headless = ReadPropertyFile.getProperty("headless", configFile);
        switch (binary) {
            case "chromium":
                browser.set(getPlaywright().chromium().launch((new BrowserType.LaunchOptions()).setChannel(channel).setHeadless(Boolean.valueOf(headless))));
                break;
            case "webkit":
                browser.set(getPlaywright().webkit().launch((new BrowserType.LaunchOptions()).setChannel(channel).setHeadless(Boolean.valueOf(headless))));
                break;
            case "firefox":
                browser.set(getPlaywright().webkit().launch((new BrowserType.LaunchOptions()).setChannel(channel).setHeadless(Boolean.valueOf(headless))));
                break;
            default:
                browser.set(getPlaywright().chromium().launch((new BrowserType.LaunchOptions()).setChannel(channel).setHeadless(Boolean.valueOf(headless))));
        }

    }

    private static void setBrowserContext() {
        browserContext.set(getBrowser().newContext());
    }

    protected static void setPage() {
        page.set(getBrowserContext().newPage());
    }

    public static Playwright getPlaywright() {
        return (Playwright)playwright.get();
    }

    public static Browser getBrowser() {
        return (Browser)browser.get();
    }

    public static BrowserContext getBrowserContext() {
        return (BrowserContext)browserContext.get();
    }

    public static Page getPage() {
        return (Page)page.get();
    }

    public static Page getBasePage() {
        if (base.get() == null) {
            base.set(new Base());
        }

        Base var10000 = (Base)base.get();
        return getPage();
    }

    private Base() {
        setPlaywright();
        setBrowser();
        setBrowserContext();
        setPage();
    }
}
