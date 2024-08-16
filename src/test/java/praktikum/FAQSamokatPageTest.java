package praktikum;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.Pages.FAQSamokatPage;

public class FAQSamokatPageTest {
    private WebDriver driver;

    @ClassRule
    public static DriverRule factory = new DriverRule();

    @BeforeClass
    public static void closeCookies() {
        new FAQSamokatPage(factory.getDriver())
                .openDriver();
        new FAQSamokatPage(factory.getDriver()).acceptCookies();
    }

    @Test
    public void checkClickOnArrow()  {
        var samokatPage = new FAQSamokatPage(factory.getDriver());
        samokatPage.clickOnFAQItem();

    }

    @Test
    public void checkQuestionInArrow() {
        var samokatPage = new FAQSamokatPage(factory.getDriver());
        samokatPage.compareActualQuestionInFAQItem();
    }

    @Test
    public void checkAnswerInArrow() {
        var samokatPage = new FAQSamokatPage(factory.getDriver());
        samokatPage.compareActualAnswerInFAQItem();
    }
}