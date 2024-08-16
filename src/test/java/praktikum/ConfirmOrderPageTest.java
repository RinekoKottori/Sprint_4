package praktikum;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import praktikum.Pages.AboutClientPage;
import praktikum.Pages.AboutRentPage;
import praktikum.Pages.ConfirmOrderPage;
import praktikum.Pages.HomePage;


public class ConfirmOrderPageTest {
    private static WebDriver driver;

    @Rule
    public DriverRule factory = new DriverRule();

    @Before
    public void initDriver() {
        driver = factory.getDriver();
        new ConfirmOrderPage(driver).openDriver();
        new HomePage(driver).clickOnOrderButton();
        new AboutClientPage(factory.getDriver()).fieldsAboutClientPage("Афанасий",
                "Михайловский",
                "Поленова, 11",
                "Бульвар Рокоссовского",
                "+73245697973");
        new AboutRentPage(factory.getDriver()).fieldsAboutRentPage(0, 0, "не надо слов");
    }

    @Test
    public void checkConfirmMassage() {
        new ConfirmOrderPage(driver).clickButtonConfirmOrder();
        new ConfirmOrderPage(driver).checkIsCreatedOrderDisplayed();
    }
}
