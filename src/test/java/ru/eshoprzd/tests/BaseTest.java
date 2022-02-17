package ru.eshoprzd.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.eshoprzd.tools.AllureHelpers;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @BeforeEach
    public void beforeEach(){
        String browser = System.getProperty("browser", "chrome");
        String size = System.getProperty("size", "1590x850");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.browserSize = size;
        Configuration.browser = browser;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        AllureHelpers.attachAsText("Browser: ", browser);
        AllureHelpers.attachAsText("Size: ", size);

        clearBrowserCookies();
        Configuration.baseUrl = "https://eshoprzd.ru/";
        open("");
    }

    @AfterEach
    void afterEach(){
        AllureHelpers.addVideo();
        AllureHelpers.screenshotAs("Last screen");
        AllureHelpers.pageSource();
        AllureHelpers.browserConsoleLogs();
        closeWebDriver();
    }
}
