package praktikum;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.Pages.AboutClientPage;
import praktikum.Pages.AboutRentPage;
import praktikum.Pages.HomePage;

@RunWith(Parameterized.class)
public class PositiveFlowClientPageTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String adress;
    private final String metro;
    private final String phone;

    @Rule
    public DriverRule factory = new DriverRule();

    public PositiveFlowClientPageTest(String name, String surname, String adress, String metro, String phone) {
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.metro = metro;
        this.phone = phone;
    }

    @Before
    public void initDriver() {
        driver = factory.getDriver();
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"Мариса", "Адамантовна", "Москва", "Невская", "5689875564582"},
                {"Анджеллина", "Михайловская", "Академика Курчатова, 17", "Черкизовская", "83245698797"}
        };
    }

    @Test
    public void fillInClientFields() {
        var clientpage = new AboutClientPage(driver);
        clientpage.openDriver();
        new HomePage(driver).clickOnOrderButton();
        clientpage.setUserName(name);
        clientpage.setUserSurame(surname);
        clientpage.setUserAddress(adress);
        clientpage.selectFromDropDownListOfMetroStations(metro);
        clientpage.setUserPhone(phone);

        AboutRentPage pageAboutRent = clientpage.clickNextButton();
        pageAboutRent.checkIsRentPageIsOpen();
    }
}
