package praktikum;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Pages.AboutClientPage;
import praktikum.Pages.HomePage;

@RunWith(Parameterized.class)
public class FieldsAboutClientPageTest {
        private final String name;
    private final String surname;
    private final String adress;
    private final String metro;
    private final String phone;

    @ClassRule
    public static DriverRule factory = new DriverRule();

    public FieldsAboutClientPageTest(String name, String surname, String adress, String metro, String phone) {
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.metro = metro;
        this.phone = phone;
    }

    @BeforeClass
    public static void closeCookies() {
        new AboutClientPage(factory.getDriver())
                .openDriver();
        new HomePage(factory.getDriver()).clickOnOrderButton(".//div[@class='Header_Nav__AGCXC']/button[1]");
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"Gilbert", "Adamson", "Bolshaya Lubyanka Street", "Сокольники", "83258"},
                {"Анджеллина", "Михайловская", "Академика Курчатова, 17", "Черкизовская", "83245698797"}
        };
    }

    @Test
    public void checkFillInNameField() {
        var clientpage = new AboutClientPage(factory.getDriver());
        clientpage.setUserName(name);
        clientpage.isUserNameCorrect();
    }

    @Test
    public void checkFillInSurnameField() {
        var clientpage = new AboutClientPage(factory.getDriver());
        clientpage.setUserSurame(surname);
        clientpage.isUserSurnameCorrect();
    }

    @Test
    public void checkFillInAddressField() {
        var clientpage = new AboutClientPage(factory.getDriver());
        clientpage.setUserAddress(adress);
        clientpage.isUserAddressCorrect();
    }

    @Test
    public void checkFillInMetroField() {
        var clientpage = new AboutClientPage(factory.getDriver());
        clientpage.selectFromDropDownListOfMetroStations(metro);
        clientpage.isMetroStationSelected();
    }

    @Test
    public void checkFillInPhoneField() {
        var clientpage = new AboutClientPage(factory.getDriver());
        clientpage.setUserPhone(phone);
        clientpage.isUserPhoneCorrect();
    }

}
