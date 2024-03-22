package scooter.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import scooter.page_object.enums.ScooterColor;
import scooter.page_object.enums.RentalPeriod;

import java.time.Duration;

import static scooter.additions.EnvConfig.DEFAULT_TIMEOUT;

public class OrderAboutRentPage {

    private final WebDriver driver;
    // Блок с названием страницы "Про аренду" (стоит ли добавлять тексты в локаторы?)
    private final By aboutRentHeader = By.xpath(".//div[contains(@class, 'Order_Header__') and text() = 'Про аренду']");
    // Поле "Когда привести самокат"
    private final By orderDateField = By.cssSelector("[class*='Order_MixedDatePicker']");
    //Блок календаря, отображающийся при клике на Поле "Когда привезти самокат"
    private final By monthDatepicker = By.className("react-datepicker__month-container");
    // Блок текущего дня в календаре выбора даты в поле "Когда привезти заказ"
    private final By monthDatepickerCurrentDate = By.cssSelector("[class*='react-datepicker__day--today']");
    // Поле "Срок аренды" (добавляю имя поля, т к нет уверенности что в будущем на странице не появится элемент с @class='Dropdown-control')
    private final By rentalPeriodField = By.xpath(".//div[@class='Dropdown-control']/div[contains(text(), 'Срок аренды')]");
    // Раскрытый список выбора срока аренды
    private final By rentalPeriodMenu = By.className("Dropdown-menu");

    // Поле "Комментарий для курьера"
    private final By commentField = By.cssSelector("[placeholder*='Комментарий']");
    // Кнопка "Заказать"
    private final By orderButton = By.xpath(".//div[contains(@class, 'Order_Button')]/button[ text() = 'Заказать']");

    public OrderAboutRentPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderAboutRentPage checkUploadOrderAboutRentPage() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(aboutRentHeader));
        assert driver.findElement(aboutRentHeader).isDisplayed();
        return this;
    }

    //Метод выбора текущего дня в календаре поля Когда привезти заказ
    public OrderAboutRentPage selectCurrentDate() {
        driver.findElement(orderDateField).click();
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(monthDatepicker));
        driver.findElement(monthDatepickerCurrentDate).click();
        // не уверена, нужно ли в каждом подобном методе прописывать еще ожидание, что поле действительно заполнилось
        return this;
    }

    private By rentalPerionOption(RentalPeriod rentalPeriod) {
        //кажется что так не хорошо, писать локатор не в поле класса, а в методе
        return By.xpath(".//div[@class='Dropdown-option' and text() ='" + rentalPeriod.getMode() + "']");
    }

    public OrderAboutRentPage selectRentalPeriod(RentalPeriod rentalPeriod) {
        driver.findElement(rentalPeriodField).click();
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodMenu));
        driver.findElement(rentalPerionOption(rentalPeriod)).click();
        return this;
    }

    private By scooterColorCheckbox(ScooterColor scooterColor) {
        return  By.xpath(".//div[contains(text(), 'Цвет')]/parent::div//input[@id='" +scooterColor.getMode() + "']");
    }


    public OrderAboutRentPage selectColorScooter(ScooterColor scooterColor) {
        //в условиях текущей постановки проектой задачи позволила себе оставить поля без предварительного очищения
        driver.findElement(scooterColorCheckbox(scooterColor)).click();
        return this;
    }

    public OrderAboutRentPage inputComment(String comment) {
        driver.findElement(commentField).isEnabled();
        driver.findElement(commentField).clear();
        driver.findElement(commentField).sendKeys(comment);
        return  this;
    }
    public OrderAboutRentPage clickOrderButton() {
        driver.findElement(orderButton).click();
        return this;
    }
}
