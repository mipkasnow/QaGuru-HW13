package ru.eshoprzd.tests;

import com.codeborne.selenide.selector.WithText;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.eshoprzd.methods.MainPage;
import ru.eshoprzd.tools.LoadingBar;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static ru.eshoprzd.data.Data.*;

@DisplayName("Проверка элементов открытой части сайта")
public class UnauthorizedTest extends BaseTest{

    @Tag("regress")
    @ValueSource(strings = {purchase1, purchase2})
    @DisplayName("Поиск закупки на открытой части сайта")
    @ParameterizedTest()
    public void searchPurchase(String searchValue){
        new MainPage().open223FzSection();
        new LoadingBar().waitForLoad();
        $("#filterInput").setValue(searchValue);
        $(".glyphicon-search").click();
        $(".ui-select-highlight.h_green").parent().$(byText(searchValue)).should(appear);
    }

    @DisplayName("Поиск новости на открытой части сайта")
    @Tag("regress")
    @Test
    public void checkSearchNews(){
        new MainPage().newsPageOpening();
        new MainPage().searchNews(news1);
        $$(".allNews").shouldHave(size(1));
        $(withText("Желаем успешной торговли на УЭТП!")).should(appear);
    }

    @DisplayName("Проверка работы пагинации в разделе закупок")
    @Tag("regress")
    @Test
    public void checkPagination(){
        new MainPage().open223FzSection();
        $$(".purchase-item").shouldHave(size(20));
        $("[ng-click='$ctrl.changeSelectPages(1)']").click();
        $$(".purchase-item").shouldHave(size(20));
        $(byXpath("//button[normalize-space()='50']")).click();
        $$(".purchase-item").shouldHave(size(50));
        $(byXpath("//button[normalize-space()='100']")).click();
        $$(".purchase-item").shouldHave(size(100));
    }

    @DisplayName("Проверка некорректного логина")
    @Tag("regress")
    @Test
    public void incorrectLogin(){
        new MainPage().login(fake_login, fake_pswd);
        $(byText("Не найдено имя пользователя или пароль")).should(appear);
    }

    @DisplayName("Проверка ссылок подвала")
    @Test
    public void checkFooterLinks(){
        new MainPage().openRemoteSupportFromFooter();
        $(withText("Скачайте программу TeamViewer по одной из следующих ссылок:")).should(appear);
        $("a[href*='.exe']").shouldBe(visible);

        new MainPage().openPrivacyFromFooter();
        $(withText("Настоящим в соответствии с Федеральным законом от 27 июля 2006 года № 152 «О персональных данных»")).should(appear);
        $("a[href*='privacy'").shouldBe(visible);
    }

    @Disabled
    @DisplayName("Пропущенный тест")
    @Test
    public void skippedTest(){
        System.out.println("Тест пропущен");
    }

}
