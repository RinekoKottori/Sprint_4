package praktikum.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {
    private final WebDriver driver;
//    private final By buttonOrderInHeader = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[1]");
//    private final By buttonOrderInMiddle = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickOnOrderButton(String button) {
        driver.findElement(By.xpath(button)).click();
        new AboutClientPage(driver);
    }
}