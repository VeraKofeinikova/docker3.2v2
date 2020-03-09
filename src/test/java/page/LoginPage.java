package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helpers.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LoginPage {
    private static final SelenideElement loginField = $("[name='login']");
    private static final SelenideElement passwordField = $("[name='password']");
    private static final SelenideElement loginButton = $("[data-test-id='action-login']");
    private static final SelenideElement errorNotification = $("[data-test-id='error-notification']");
    private static final SelenideElement emptyLoginField = $$(".input__inner").get(0);
    private static final SelenideElement emptyPasswordField = $$(".input__inner").get(1);

    public VerificationPage correctAuthInfo() {
        DataHelper.AuthInfo correctAuthInfo = DataHelper.getCorrectAuthInfo();
        loginField.setValue(correctAuthInfo.getLogin());
        passwordField.setValue(correctAuthInfo.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void notValidLoginValidPassword() {
        DataHelper.AuthInfo notValidLogin = DataHelper.getNotValidLoginValidPassword();
        loginField.setValue(notValidLogin.getLogin());
        passwordField.setValue(notValidLogin.getPassword());
        loginButton.click();
        errorNotification.shouldHave(Condition.visible).shouldHave(text("Неверно указан логин или пароль"));
    }

    public void validLoginNotValidPassword() {
        DataHelper.AuthInfo notValidPassword = DataHelper.getValidLoginNotValidPassword();
        loginField.setValue(notValidPassword.getLogin());
        passwordField.setValue(notValidPassword.getPassword());
        loginButton.click();
        errorNotification.shouldHave(Condition.visible).shouldHave(text("Неверно указан логин или пароль"));
    }

    public void emptyLoginValidPassword() {
        DataHelper.AuthInfo emptyLogin = DataHelper.getEmptyLoginValidPassword();
        loginField.setValue(emptyLogin.getLogin());
        passwordField.setValue(emptyLogin.getPassword());
        loginButton.click();
        emptyLoginField.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    public void validLoginEmptyPassword() {
        DataHelper.AuthInfo emptyPassword = DataHelper.getValidLoginEmptyPassword();
        loginField.setValue(emptyPassword.getLogin());
        passwordField.setValue(emptyPassword.getPassword());
        loginButton.click();
        emptyPasswordField.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

//    public void blockingAuth() {
//        DataHelper.AuthInfo wrongAuthInfoOne = DataHelper.getWrongAuthInfoOne();
//        loginField.setValue(wrongAuthInfoOne.getLogin());
//        passwordField.setValue(wrongAuthInfoOne.getPassword());
//        loginButton.click();
//        errorNotification.shouldHave(Condition.visible);
//        loginField.setValue("\b\b\b\b\b\b\b\b\b\b\b");
//        passwordField.setValue("\b\b\b\b\b\b\b\b\b\b\b");
//
//        DataHelper.AuthInfo wrongAuthInfoTwo = DataHelper.getWrongAuthInfoTwo();
//        loginField.setValue(wrongAuthInfoTwo.getLogin());
//        passwordField.setValue(wrongAuthInfoTwo.getPassword());
//        loginButton.click();
//        errorNotification.shouldHave(Condition.visible);
//        loginField.setValue("\b\b\b\b\b\b\b\b\b\b\b");
//        passwordField.setValue("\b\b\b\b\b\b\b\b\b\b\b");
//
//        DataHelper.AuthInfo wrongAuthInfoThree = DataHelper.getWrongAuthInfoThree();
//        loginField.setValue(wrongAuthInfoThree.getLogin());
//        passwordField.setValue(wrongAuthInfoThree.getPassword());
//        loginButton.click();
//        errorNotification.shouldHave(Condition.visible);
//        loginField.setValue("\b\b\b\b\b\b\b\b\b\b\b");
//        passwordField.setValue("\b\b\b\b\b\b\b\b\b\b\b");
//    }
}
