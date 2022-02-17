package ru.eshoprzd.methods;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public static final String ENTER_SITE = "#login-btn";

    public void newsPageOpening() {$("#all-news").click();}

    public void open223FzSection(){
        $(withText("Закупки")).click();
        $(withText("Закупки ОАО «РЖД»")).click();
    }

    public void searchNews(String news){
        $("#filterInput").setValue(news);
        $(".headerSearch").click();
    }

    public void login(String loginName, String password) {
        $(ENTER_SITE).click();
        $("[ng-click='showLoginForm = !showLoginForm']").click();
        $("#login").setValue(loginName);
        $("#pass").setValue(password);
        $("#login-btn-form").click();
    }

    public void openRemoteSupportFromFooter(){
        $(".footerLinks a[href*='support']").click();
        $("[ng-model='descChecked']").click();
    }

    public void openPrivacyFromFooter() {$("a[href*='privacy']").click();}
}
