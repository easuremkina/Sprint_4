package scooter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import scooter.page_object.HomePageFAQ;
import scooter.page_object.HomePage;

import static org.junit.Assert.assertEquals;
import static scooter.additions.EnvConfig.SCOOTER_HOME;

@RunWith(Parameterized.class)
public class FAQTest {
    @Rule
    public DriverRule driverRule = new DriverRule();
    private final int questionId;
    private final String question;
    private final String answer;


    public FAQTest(int questionId, String question, String answer) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
    }

    @Parameterized.Parameters(name = "Вопрос: {1}")
    public static Object [][] getQuestion() {
        return new Object[][] {
                {0, "Сколько это стоит? И как оплатить?",
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?",
                        "Пока что у нас так: один заказ — один самокат. " +
                        "Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?",
                        "Допустим, вы оформляете заказ на 8 мая. " +
                        "Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, " +
                        "когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?",
                        "Пока что нет! " +
                        "Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010." },
                {5, "Вы привозите зарядку вместе с самокатом?",
                        "Самокат приезжает к вам с полной зарядкой. " +
                        "Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?",
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я живу за МКАДом, привезёте?",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Test
    public void faq() {

        WebDriver driver = driverRule.getDriver();
        driver.get(SCOOTER_HOME);

        HomePage homePage = new HomePage(driver);
        homePage.closeCookieButton();

        HomePageFAQ homePageFAQ = new HomePageFAQ(driver, questionId);
        homePageFAQ.clickOnQuestion();

        assertEquals(question, homePageFAQ.getQuestionText());
        assertEquals(answer, homePageFAQ.getAnswerText());
    }
}
