package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private static final SelenideElement text = $("[data-test-id='dashboard']");

    public DashboardPage() {
        text.shouldHave(Condition.text("Личный кабинет"));
    }
}
