package praktikum.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static praktikum.EnvConfig.BASE_URL;
import static praktikum.EnvConfig.EXPLICIT_WAIT;

public class FAQSamokatPage {
    private final WebDriver driver;
    private final By cookies = By.className("App_CookieButton__3cvqF");

    public FAQSamokatPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openDriver() {
        driver.get(BASE_URL);
    }

    public void acceptCookies() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(cookies));
        driver.findElement(cookies).click();
    }

    String[] expectedAnswerInAFAQItem = {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."};


    public void FAQItemAnswer(String item, String textInItem) {
        driver.findElement(By.xpath(item)).click();
        driver.findElement(By.xpath(textInItem)).getText();
    }

    public void checkFAQItemAnswer(int i, String textInItem) {
        assertEquals("Incorrect answer text: ", expectedAnswerInAFAQItem[i], driver.findElement(By.xpath(textInItem)).getText());
    }
}
