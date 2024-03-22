package scooter.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static scooter.additions.EnvConfig.DEFAULT_TIMEOUT;

public class OrderResultModal {
    private final WebDriver driver;
    // Название модального окна "Заказ оформлен"
    private final By orderResultModalHeader = By.xpath(".//div[contains(@class, 'Order_ModalHeader') and text() = 'Заказ оформлен']");

    public OrderResultModal(WebDriver driver) {
        this.driver = driver;
    }

    public OrderResultModal checkUploadOrderResultModal() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderResultModalHeader));

        assert driver.findElement(orderResultModalHeader).isDisplayed();
        return this;
    }
}
