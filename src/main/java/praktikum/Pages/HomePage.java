package praktikum.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static praktikum.EnvConfig.BASE_URL;


public class HomePage {
    private final WebDriver driver;
    private final By buttonOrderInHeader = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[1]");
    private final By buttonOrderInMiddle = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openDriver() {
        driver.get(BASE_URL);
    }

    public AboutClientPage clickOnOrderButton() {
        if (driver.findElement(buttonOrderInHeader).isDisplayed()) {
            driver.findElement(buttonOrderInHeader).click();
        } else {
            driver.findElement(buttonOrderInMiddle).click();
        }
        return new AboutClientPage(driver);
    }
}