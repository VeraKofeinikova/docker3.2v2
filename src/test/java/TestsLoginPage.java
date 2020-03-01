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
    public void clearCookies() {
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
    void loginSuccessful() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getCorrectAuthInfo();
        val verificationPage = loginPage.happyPath(authInfo);
        verificationPage.assertVerifyBtnAvailable();
        val verificationCode = DataHelper.getVerificationCodeFor();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("Невозможно войти. Неправильный логин")
    void loginFailedWrongLogin() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val notValidLogin = DataHelper.getNotValidLoginValidPassword();
        loginPage.notValidLoginValidPassword(notValidLogin);
    }

    @Test
    @DisplayName("Невозможно войти. Неправильный пароль")
    void loginFailedWrongPassword() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val notValidPassword = DataHelper.getValidLoginNotValidPassword();
        loginPage.validLoginNotValidPassword(notValidPassword);
    }

    @Test
    @DisplayName("Невозможно войти. Незаполненный логин")
    void loginFailedEmptyLogin() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val emptyLogin = DataHelper.getEmptyLoginValidPassword();
        loginPage.emptyLoginValidPassword(emptyLogin);
    }

    @Test
    @DisplayName("Невозможно войти. Незаполненный пароль")
    void loginFailedEmptyPassword() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val emptyPassword = DataHelper.getValidLoginEmptyPassword();
        loginPage.validLoginEmptyPassword(emptyPassword);
    }

    @Test
    @DisplayName("Невозможно войти. Правильные логин-пароль. Неправильный смс-код")
    void loginFailedWrongVerificationCode() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getCorrectAuthInfo();
        val verificationPage = loginPage.happyPath(authInfo);
        verificationPage.assertVerifyBtnAvailable();
        val verificationCode = DataHelper.getWrongVerificationCodeFor();
        verificationPage.notValidVerify(verificationCode);
    }

    @Test
    @DisplayName("Невозможно войти. Правильные логин-пароль. Превышение количества попыток введения неправильного кода")
    void loginFailedWrongVerificationCodeTooMuchAttemptsToPutCode() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getCorrectAuthInfo();
        val verificationPage = loginPage.happyPath(authInfo);
        verificationPage.assertVerifyBtnAvailable();
        val verificationCode1 = DataHelper.getWrongVerificationCodeFor();
        verificationPage.notValidVerify(verificationCode1);
        val verificationCode2 = DataHelper.getWrongVerificationCodeFor();
        verificationPage.notValidVerify(verificationCode2);
        val verificationCode3 = DataHelper.getWrongVerificationCodeFor();
        verificationPage.notValidVerify(verificationCode3);
        val verificationCode4 = DataHelper.getWrongVerificationCodeFor();
        verificationPage.tooMuchAttemptsOfVerificationCode(verificationCode4);
    }
