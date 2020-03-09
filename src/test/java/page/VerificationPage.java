package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helpers.DataHelper;
import helpers.SqlCommands;

import java.sql.SQLException;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private static final SelenideElement codeField = $(".input__control");
    private static final SelenideElement verifyButton = $("[data-test-id='action-verify']");
    private static final SelenideElement errorNotification = $(By.className("notification__content"));
    private static final SelenideElement errorNotificationTwo = $("[data-test-id=error-notification]");
    private static final SelenideElement emptyCodeField = $(".input__sub");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify() throws SQLException {
        codeField.setValue(SqlCommands.getVerificationCode(SqlCommands.getId(DataHelper.getCorrectAuthInfo().getLogin())));
        verifyButton.click();
        return new DashboardPage();
    }

    public void notValidVerify() {
        DataHelper.VerificationCode wrongVerificationCode = DataHelper.getWrongVerificationCodeFor();
        codeField.setValue(wrongVerificationCode.getCode());
        verifyButton.click();
        errorNotification.shouldHave(Condition.visible);
        errorNotificationTwo.shouldHave(text("Ошибка! "));
    }

    public void warningOfEmptyCodeField() {
        verifyButton.click();
        emptyCodeField.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    public void assertVerifyBtnAvailable() {
        verifyButton.shouldBe(visible);
    }

//    public void fourthTryToLogin() throws SQLException {
//        codeField.setValue(SqlCommands.getVerificationCode(SqlCommands.getId(DataHelper.getCorrectAuthInfo().getLogin())));
//        verifyButton.click();
//        errorNotification.waitUntil(Condition.visible,15000).shouldHave(text("Ошибка"));
//    }
}

