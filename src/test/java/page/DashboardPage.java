package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private static final SelenideElement text = $("[data-test-id='dashboard']");
    //checkPage было - стало loginSuccessful
    public void goToDashboardPage() {
        text.shouldHave(Condition.text("Личный кабинет"));
    }
}
