package praktikum;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.Pages.AboutClientPage;
import praktikum.Pages.AboutRentPage;
import praktikum.Pages.HomePage;

@RunWith(Parameterized.class)
public class FieldsAboutRentPageTest {
    private final int indexOfRent;
    private final String comment;
    private final int indexOfCheckbox;


    @ClassRule
    public static DriverRule factory = new DriverRule();

    public FieldsAboutRentPageTest(int indexOfRent, String comment, int indexOfCheckbox) {
        this.indexOfRent = indexOfRent;
        this.comment = comment;
        this.indexOfCheckbox = indexOfCheckbox;

    }

    @BeforeClass
    public static void PastPages() {
        new AboutRentPage(factory.getDriver())
                .openDriver();
        new HomePage(factory.getDriver()).clickOnOrderButton(".//div[@class='Header_Nav__AGCXC']/button[1]");
        new AboutClientPage(factory.getDriver()).fieldsAboutClientPage("Афанасий",
                "Михайловский",
                "Поленова, 11",
                "Бульвар Рокоссовского",
                "+73245697973");
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {0, "не надо слов", 0},
                {5, "no more words", 1}
        };
    }

    @Test
    public void checkFieldDate()  {
        var rentpage = new AboutRentPage(factory.getDriver());
        rentpage.setDate();
        rentpage.isDateCorrect();

    }

    @Test
    public void checkFieldRent(){
        var rentpage = new AboutRentPage(factory.getDriver());
        rentpage.setFieldRent(indexOfRent);
        rentpage.isRentTimeCorrect();
    }

    @Test
    public void checkCheckbox(){
        var rentpage = new AboutRentPage(factory.getDriver());
        rentpage.setCheckboxColor(indexOfCheckbox);
        rentpage.isChosenCheckboxCorrect();
    }

    @Test
    public void checkComment(){
        var rentpage = new AboutRentPage(factory.getDriver());
        rentpage.setFieldComment(comment);
        rentpage.isFieldCommentCorrect();
    }

}