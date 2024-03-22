package scooter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import scooter.page_object.enums.OrderButton;
import scooter.page_object.enums.ScooterColor;
import scooter.page_object.enums.RentalPeriod;
import scooter.model.TestUserPersData;
import scooter.page_object.*;

import static scooter.additions.EnvConfig.SCOOTER_HOME;

@RunWith(Parameterized.class)
public class OrderTest {
    private final OrderButton orderButton;

    public OrderTest(OrderButton orderButton) {
        this.orderButton = orderButton;
    }

    @Parameterized.Parameters(name = "Местонахождение кнопки Заказать: {0}")
    public static Object[] getStartOrderButton() {
        return  new Object[]{ OrderButton.IN_HEADER_ON_PAGE,
                OrderButton.IN_MIDDLE_ON_PAGE,
        };
    }

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    public void successCreatedOrderWithCorrectInputFields() {
        WebDriver driver = driverRule.getDriver();
        driver.get(SCOOTER_HOME);

        HomePage homePage = new HomePage(driver);
        homePage.closeCookieButton();
        homePage.clickOrderButton(orderButton);
        TestUserPersData testUser = new TestUserPersData();


        // Заполнение формы с личными данными заказчика
        new OrderPersonPage(driver)
                .checkUploadOrderPersonPage()
                .inputFirstName(testUser.getFirstName())
                .inputLastName(testUser.getLastName())
                .inputAddress(testUser.getAddress())
                .inputMetroStation(testUser.getMetroStation())
                .inputPhone(testUser.getPhone())
                .clickNexButton();

        // Заполнение формы "Про аренду"
        new OrderAboutRentPage(driver)
                .checkUploadOrderAboutRentPage()
                .selectCurrentDate()
                .selectRentalPeriod(RentalPeriod.SUTKI)
                .selectColorScooter(ScooterColor.BLACK)
                .inputComment("Некий комментарий")
                .clickOrderButton();

        // Подтверждение заказа
        new OrderConfirmModal(driver)
                .checkUploadOrderConfirmModal()
                .confirmOrder();

        // Проверка появления всплывающего окна
        new OrderResultModal(driver)
                // По условиям задачи - итоговой проверкой является проверка,
                // что появилось всплывающее окно с сообщением об успешном создании заказа
                .checkUploadOrderResultModal();
    }
}
