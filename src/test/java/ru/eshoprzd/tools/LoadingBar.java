package ru.eshoprzd.tools;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;


public class LoadingBar {

    private int timeoutSeconds = 20;

    public void waitForLoad() {
        try {
            $("#loading-bar").should(appear, Duration.ofMillis(1500));
        } catch (Error error) {
            System.out.println("Ожидался прогресс бар загрузки, но он не появился!");
            return;
        }

        $("#loading-bar").should(disappear, Duration.ofSeconds(timeoutSeconds));
        sleep(100);
    }

    public LoadingBar setTimeout(int seconds) {
        timeoutSeconds = seconds;
        return this;
    }


}
