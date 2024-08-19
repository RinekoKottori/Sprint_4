package praktikum;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.Pages.FAQSamokatPage;

@RunWith(Parameterized.class)
public class FAQSamokatPageTest {
    private final String item;
    private final String textInItem;
    private final int index;

    @ClassRule
    public static DriverRule factory = new DriverRule();

    public FAQSamokatPageTest(String item, String textInItem, int index) {
        this.item = item;
        this.textInItem = textInItem;
        this.index = index;
    }

    @BeforeClass
    public static void closeCookies() {
        new FAQSamokatPage(factory.getDriver())
                .openDriver();
        new FAQSamokatPage(factory.getDriver()).acceptCookies();
    }
    @Parameterized.Parameters
    public static Object[][] getAnswers() {
        return new Object[][]{
                {".//div[@id='accordion__heading-0']", ".//div[@id ='accordion__panel-0']", 0},
                {".//div[@id='accordion__heading-1']", ".//div[@id ='accordion__panel-1']", 1},
                {".//div[@id='accordion__heading-2']", ".//div[@id ='accordion__panel-2']", 2},
                {".//div[@id='accordion__heading-3']", ".//div[@id ='accordion__panel-3']", 3},
                {".//div[@id='accordion__heading-4']", ".//div[@id ='accordion__panel-4']", 4},
                {".//div[@id='accordion__heading-5']", ".//div[@id ='accordion__panel-5']", 5},
                {".//div[@id='accordion__heading-6']", ".//div[@id ='accordion__panel-6']", 6},
                {".//div[@id='accordion__heading-7']", ".//div[@id ='accordion__panel-7']", 7}
        };
    }
    @Test
    public void openAnswers(){
        var samokatPage = new FAQSamokatPage(factory.getDriver());
        samokatPage.FAQItemAnswer(item, textInItem);
        samokatPage.checkFAQItemAnswer(index, textInItem);
    }

}