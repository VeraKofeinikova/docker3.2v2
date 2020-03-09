import com.codeborne.selenide.Selenide;
import helpers.SqlCommands;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class TestsLoginPage {
    @BeforeEach
    public void clearCookies() throws SQLException {
        open("http://localhost:9999");
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @AfterAll
    static void clearDataInTables() throws SQLException {
        SqlCommands.clearAllTables();
    }

    @Test
    @DisplayName("Успешный вход. Правильные логин-пароль-верификейшн код")
    void loginSuccessful() throws SQLException {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val verificationPage = loginPage.correctAuthInfo();
        verificationPage.assertVerifyBtnAvailable();
        val dashBoardPage = verificationPage.validVerify();
        dashBoardPage.goToDashboardPage();
    }

    @Test
    @DisplayName("Невозможно войти. Неправильный логин")
    void loginFailedWrongLogin() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        loginPage.notValidLoginValidPassword();
    }

    @Test
    @DisplayName("Невозможно войти. Неправильный пароль")
    void loginFailedWrongPassword() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        loginPage.validLoginNotValidPassword();
    }

    @Test
    @DisplayName("Невозможно войти. Незаполненный логин")
    void loginFailedEmptyLogin() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        loginPage.emptyLoginValidPassword();
    }

    @Test
    @DisplayName("Невозможно войти. Незаполненный пароль")
    void loginFailedEmptyPassword() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        loginPage.validLoginEmptyPassword();
    }

    @Test
    @DisplayName("Невозможно войти. Правильные логин-пароль. Незаполненный смс-код")
    void loginFailedEmptyVerificationCode() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val verificationPage = loginPage.correctAuthInfo();
        verificationPage.assertVerifyBtnAvailable();
        verificationPage.warningOfEmptyCodeField();
    }

    @Test
    @DisplayName("Невозможно войти. Правильные логин-пароль. Неправильный смс-код")
    void loginFailedWrongVerificationCode() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val verificationPage = loginPage.correctAuthInfo();
        verificationPage.assertVerifyBtnAvailable();
        verificationPage.notValidVerify();
    }

//    @Test
//    @DisplayName("Невозможно войти. 3 раза неправильно введен пароль")
//    void wrongPasswordThreeTimesLoginFailedOnFourthTry() throws SQLException {
//        SqlCommands.clearVerificationCodes();
//        open("http://localhost:9999");
//        val loginPage = new LoginPage();
//        loginPage.blockingAuth();
//        val verificationPage = loginPage.correctAuthInfo();
//        verificationPage.fourthTryToLogin();
//    }
}