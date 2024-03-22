package scooter.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static scooter.additions.EnvConfig.DEFAULT_TIMEOUT;

public class HomePageFAQ {
    private final WebDriver driver;
    //блок вопроса
    // блок вопроса
    private final By question;

    //блок раскрытого ответа на вопрос
    private final By answer;

    public HomePageFAQ(WebDriver driver, int questionId) {
        this.driver = driver;
        question = By.id("accordion__heading-" + questionId);
        answer = By.id("accordion__panel-" + questionId);
    }

    public void clickOnQuestion() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(question));

        driver.findElement(question).click();

        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(answer));
    }

    public String getQuestionText() {
        return driver.findElement(question).getText();
    }

    public String getAnswerText() {
        return driver.findElement(answer).getText();
    }

}
