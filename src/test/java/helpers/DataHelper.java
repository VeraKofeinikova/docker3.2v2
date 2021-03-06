package helpers;

import lombok.Value;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class DataHelper {
    public static AuthInfo getCorrectAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getWrongAuthInfoOne() {
        return new AuthInfo("vasya", "qwerty12");
    }

    public static AuthInfo getWrongAuthInfoTwo() {
        return new AuthInfo("", "qwerty1");
    }

    public static AuthInfo getWrongAuthInfoThree() {
        return new AuthInfo("", "qwerty");
    }

    public static AuthInfo getNotValidLoginValidPassword() {
        return new AuthInfo("petya", "qwerty123");
    }

    public static AuthInfo getValidLoginNotValidPassword() {
        return new AuthInfo("vasya", "123qwerty");
    }

    public static AuthInfo getEmptyLoginValidPassword() {
        return new AuthInfo("", "qwerty123");
    }

    public static AuthInfo getValidLoginEmptyPassword() {
        return new AuthInfo("vasya", "");
    }

    public static VerificationCode getWrongVerificationCodeFor() {
        return new VerificationCode("123456");
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }
}
