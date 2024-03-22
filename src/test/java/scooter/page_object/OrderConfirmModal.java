package scooter.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import scooter.additions.GlobalProperties;

import java.time.Duration;

import static scooter.additions.EnvConfig.DEFAULT_TIMEOUT;

public class OrderConfirmModal {
    private final WebDriver driver;
    // Название модального окна "Хотите оформить заказ?"
    private final By orderConfirmModalHeader = By.xpath(".//div[contains(@class, 'Order_ModalHeader') and text() = 'Хотите оформить заказ?']");
    // Общий блок кнопок для дальнейших действий с заказом (Да/Нет)
    private final String orderButtons  = ".//div[contains(@class, 'Order_Modal')]//div[contains(@class, 'Order_Buttons')]";
    // Кнопка подтверждения заказа - "Да"
    private final By confirmOrderButton = By.xpath(orderButtons + "/button[text() = '" + GlobalProperties.YES + "']");
    // Кнопка подтверждения заказа - "Нет"
    private final By refuseOrderButton = By.xpath(orderButtons + "/button[text() = '" + GlobalProperties.NO + "']");
    public OrderConfirmModal(WebDriver driver) {
        this.driver = driver;
    }

    public OrderConfirmModal checkUploadOrderConfirmModal() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderConfirmModalHeader));

        assert driver.findElement(orderConfirmModalHeader).isDisplayed();
        return this;
    }

    public OrderConfirmModal confirmOrder() {
        driver.findElement(confirmOrderButton).click();
        return  this;
    }

    public OrderConfirmModal refuseOrder() {
        driver.findElement(refuseOrderButton).click();
        return this;
    }
}
