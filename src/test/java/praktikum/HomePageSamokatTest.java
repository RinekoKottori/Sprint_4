package praktikum;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.Pages.AboutClientPage;
import praktikum.Pages.HomePage;

public class HomePageSamokatTest {
    private WebDriver driver;

    @Rule
    public DriverRule factory = new DriverRule();

    @Before
    public void initDriver() {
          driver = factory.getDriver();
    }

    @Test
    public void checkButtonOrder() {
        var checkIfOpenNewPage = new HomePage(driver);
        checkIfOpenNewPage.openDriver();

        AboutClientPage aboutClientPage = checkIfOpenNewPage.clickOnOrderButton();
        aboutClientPage.checkIsUserPageIsOpen();
    }
}