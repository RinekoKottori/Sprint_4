package praktikum;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.Pages.AboutClientPage;
import praktikum.Pages.AboutRentPage;
import praktikum.Pages.ConfirmOrderPage;
import praktikum.Pages.HomePage;

@RunWith(Parameterized.class)
public class PositiveFlowRentPageTest {
    private WebDriver driver;
    private final int indexOfRent;
    private final String comment;
    private final int indexOfCheckbox;


    @ClassRule
    public static DriverRule factory = new DriverRule();

    public PositiveFlowRentPageTest(int indexOfRent, int indexOfCheckbox, String comment) {
        this.indexOfRent = indexOfRent;
        this.indexOfCheckbox = indexOfCheckbox;
        this.comment = comment;

    }

    @Before
    public void initDriver() {
        driver = factory.getDriver();
        new AboutRentPage(driver).openDriver();
        new HomePage(driver).clickOnOrderButton();
        new AboutClientPage(factory.getDriver()).fieldsAboutClientPage("Афанасий",
                "Михайловский",
                "Поленова, 11",
                "Бульвар Рокоссовского",
                "+73245697973");
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {0,  0, "не надо слов"},
                {5, 1, "no more words"}
        };
    }

    @Test
    public void fillInRentFields() {
        var rentpage = new AboutRentPage(driver);
        rentpage.setDate();
        rentpage.setFieldRent(indexOfRent);
        rentpage.setCheckboxColor(indexOfCheckbox);
        rentpage.setFieldComment(comment);

        ConfirmOrderPage confirmPage = rentpage.clickButtonOrder();
        confirmPage.checkIsConfirmPageIsDisplayed();
    }

}