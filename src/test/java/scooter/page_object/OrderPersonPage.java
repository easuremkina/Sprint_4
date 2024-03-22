package scooter.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import scooter.page_object.enums.MetroStation;

import java.time.Duration;

import static scooter.additions.EnvConfig.DEFAULT_TIMEOUT;


public class OrderPersonPage {
    private final WebDriver driver;
    //Блок с названием страницы "Для кого самокат"
    private final By orderHeader = By.xpath(".//div[contains(@class, 'Order_Header__') and text() = 'Для кого самокат']");
    // Поле "Имя"
    private final By firstNameField = By.cssSelector("[placeholder*='Имя']");
    // Поле "Фамилия"
    private final By lastNameField = By.cssSelector("[placeholder*='Фамилия']");
    // Поле "Адрес"
    private final By addressField = By.cssSelector("[placeholder*='Адрес']");
    // Поле "Станция метро"
    private final By metroStationField = By.cssSelector("[placeholder*='Станция метро']");
    // Поле "Телефон"
    private final By phoneField = By.cssSelector("[placeholder*='Телефон']");
    //Кнопка "Далее"
    private final By nextButton = By.cssSelector("[class^='Order_NextButton'] > button");

    public OrderPersonPage(WebDriver driver) {
        this.driver = driver;
    }

    //проверка, что открыта страница оформления заказа с полями персональных данных заказчика
    public OrderPersonPage checkUploadOrderPersonPage() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderHeader));

        assert driver.findElement(orderHeader).isDisplayed();
        return this;
    }
    private void inputField(String fieldValue, By field) {
        driver.findElement(field).isEnabled();
        driver.findElement(field).clear();
        driver.findElement(field).sendKeys(fieldValue);

    }
    public OrderPersonPage inputFirstName(String fieldValue) {
        inputField(fieldValue, firstNameField);
        return this;
    }
    public OrderPersonPage inputLastName(String fieldValue) {
        inputField(fieldValue, lastNameField);
        return this;
    }
    public OrderPersonPage inputAddress(String fieldValue) {
        inputField(fieldValue, addressField);
        return this;
    }
    public OrderPersonPage inputPhone(String fieldValue) {
        inputField(fieldValue, phoneField);
        return this;
    }

    //Метод для получения локатора кнопки выбора конкретной станции метро
    private By metroStationButton(MetroStation metroStationValueId) {
        //кажется что так не хорошо, писать локатор не в поле класса, а в методе
        return By.cssSelector("[value='" + metroStationValueId.getMode() + "']");
    }

    public OrderPersonPage inputMetroStation(MetroStation metroStationValueId) {

        driver.findElement(metroStationField).isEnabled();
        driver.findElement(metroStationField).click();
        driver.findElement(metroStationButton(metroStationValueId)).click();
        return this;
    }

    public OrderPersonPage clickNexButton() {
        driver.findElement(nextButton).click();
        return this;
    }
}
