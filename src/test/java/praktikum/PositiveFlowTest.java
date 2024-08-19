package praktikum;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.Pages.*;

@RunWith(Parameterized.class)
public class PositiveFlowTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String adress;
    private final String metro;
    private final String phone;
    private final String buttonOrder;
    private final int indexOfRent;
    private final String comment;
    private final int indexOfCheckbox;


    @Rule
    public DriverRule factory = new DriverRule();

    public PositiveFlowTest(String buttonOrder, String name, String surname, String adress, String metro, String phone, int indexOfRent, int indexOfCheckbox, String comment) {
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.metro = metro;
        this.phone = phone;
        this.buttonOrder = buttonOrder;
        this.indexOfRent = indexOfRent;
        this.indexOfCheckbox = indexOfCheckbox;
        this.comment = comment;
    }

    @Before
    public void initDriver() {
        driver = factory.getDriver();
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {".//div[@class='Header_Nav__AGCXC']/button[1]", "Мариса", "Адамантовна", "Москва", "Невская", "5689875564582", 0,  0, "не надо слов"},
                {".//div[@class='Home_FinishButton__1_cWm']/button", "Анджеллина", "Михайловская", "Академика Курчатова, 17", "Черкизовская", "83245698797", 5, 1, "no more words"}
        };
    }

    @Test
    public void fillInFields() {
        var clientpage = new AboutClientPage(driver);
        clientpage.openDriver();
        new FAQSamokatPage(driver).acceptCookies();
        new HomePage(driver).clickOnOrderButton(buttonOrder);
        clientpage.checkIsUserPageIsOpen();

        clientpage.setUserName(name);
        clientpage.isUserNameCorrect();
        clientpage.setUserSurame(surname);
        clientpage.isUserSurnameCorrect();
        clientpage.setUserAddress(adress);
        clientpage.isUserAddressCorrect();
        clientpage.selectFromDropDownListOfMetroStations(metro);
        clientpage.isMetroStationSelected();
        clientpage.setUserPhone(phone);
        clientpage.isUserPhoneCorrect();

        AboutRentPage pageAboutRent = clientpage.clickNextButton();
        pageAboutRent.checkIsRentPageIsOpen();

        var rentpage = new AboutRentPage(driver);
        rentpage.setDate();
        rentpage.isDateCorrect();
        rentpage.setFieldRent(indexOfRent);
        rentpage.isRentTimeCorrect();
        rentpage.setCheckboxColor(indexOfCheckbox);
        rentpage.isChosenCheckboxCorrect();
        rentpage.setFieldComment(comment);
        rentpage.isFieldCommentCorrect();

        ConfirmOrderPage confirmPage = rentpage.clickButtonOrder();
        confirmPage.checkIsConfirmPageIsDisplayed();
    }
}
