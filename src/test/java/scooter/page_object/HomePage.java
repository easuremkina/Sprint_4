package scooter.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import scooter.page_object.enums.OrderButton;

public class HomePage {

    private final WebDriver driver;
    //Кнопка "Заказать" вверху страницы
    private final By orderButtonOnHeader = By.xpath(
            ".//div[contains(@class, 'Header_Nav')]/button[contains(@class, 'Button_Button__')]");
    //Кнопка "Заказать" в середине страницы, после блока "Как это работает"
    private final By orderButtonMiddle = By.xpath(".//button[contains(@class, 'Button_Middle') and text() = 'Заказать']");
    //Кнопка согласия с куки
    private final By cookieButton = By.id("rcc-confirm-button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeCookieButton() {
        if (driver.findElement(cookieButton).isDisplayed()) {
            driver.findElement(cookieButton).click();
        }
    }

    public void clickOrderButton(OrderButton orderButton) {
        if(orderButton == OrderButton.IN_HEADER_ON_PAGE) {
            clickOnOrderButtonInHeader();
        } else if (orderButton == OrderButton.IN_MIDDLE_ON_PAGE) {
            clickOrderButtonInMiddle();
        }
    }
    public void clickOnOrderButtonInHeader() {
        driver.findElement(orderButtonOnHeader).click();
    }
    public void  clickOrderButtonInMiddle() {
        WebElement element = driver.findElement(orderButtonMiddle);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(orderButtonMiddle).click();
    }

}
