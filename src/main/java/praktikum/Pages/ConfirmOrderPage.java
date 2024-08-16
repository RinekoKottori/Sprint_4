package praktikum.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static praktikum.EnvConfig.BASE_URL;
import static praktikum.EnvConfig.EXPLICIT_WAIT;

public class ConfirmOrderPage {
    private final WebDriver driver;
    private final By confirmMassage = By.className("Order_ModalHeader__3FDaJ");
    private final By messageAboutSuccessfulCreatedOrder = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div[1]");
    private final By buttonConfirmOrder = By.xpath(".//button[text()='Да' and @class='Button_Button__ra12g Button_Middle__1CSJM']");

    public ConfirmOrderPage(WebDriver driver) {
        this.driver = driver;
    }
    public void openDriver() {
        driver.get(BASE_URL);
    }

    public void checkIsConfirmPageIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.visibilityOfElementLocated(confirmMassage));
        assertTrue(driver.findElement(confirmMassage).isDisplayed());
    }

    public void clickButtonConfirmOrder() {
        driver.findElement(buttonConfirmOrder).click();
    }

    public void checkIsCreatedOrderDisplayed() {
        assertEquals("Order don't created", "Заказ оформлен", driver.findElement(messageAboutSuccessfulCreatedOrder).getText());
    }
}
