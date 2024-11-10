package com.java.playwright.page;


import com.microsoft.playwright.Locator;
import com.java.playwright.baseTests.Generic;

public class LoginPage {
    private Generic generic = new Generic("");
    private String login_btn = "login-button";
    private String username = "user-name";
    private String password = "password";

    public LoginPage() {
    }

    public Locator loc_LoginBtn() {
        return this.generic.getById(this.login_btn);
    }

    public Locator loc_Username() {
        return this.generic.getById(this.username);
    }

    public Locator loc_Password() {
        return this.generic.getById(this.password);
    }

    public void login(String username, String password) {
        this.generic.fill(this.loc_Username(), username);
        this.generic.fill(this.loc_Password(), password);
        this.generic.click(this.loc_LoginBtn());
    }
}
