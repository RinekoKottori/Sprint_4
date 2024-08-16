package praktikum.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static praktikum.EnvConfig.BASE_URL;
import static praktikum.EnvConfig.EXPLICIT_WAIT;

public class FAQSamokatPage {
    private final WebDriver driver;
    private final By firstFAQItem = By.xpath(".//div[@id='accordion__heading-0' and @role='button']");
    private final By FAQItems = By.xpath(".//div[@class ='accordion__button']");
    private final By questionsInFAQItem = By.className("accordion__item");
    private final By answersInFAQItem = By.xpath(".//div[@data-accordion-component='AccordionItemPanel']/p");
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

    public void clickOnFAQItem() {
        List<WebElement> arrowList = driver.findElements(FAQItems);
        for (WebElement E : arrowList) {
            new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                    .until(ExpectedConditions.visibilityOfElementLocated(firstFAQItem));
            E.click();
            String valueOfAttribute = E.getAttribute("aria-disabled");
            assertEquals("Arrows not clickable: ", "true", valueOfAttribute);
        }
    }

    String[] expectedQuestionInFAQItem = {"Сколько это стоит? И как оплатить?",
            "Хочу сразу несколько самокатов! Так можно?",
            "Как рассчитывается время аренды?",
            "Можно ли заказать самокат прямо на сегодня?",
            "Можно ли продлить заказ или вернуть самокат раньше?",
            "Вы привозите зарядку вместе с самокатом?",
            "Можно ли отменить заказ?",
            "Я живу за МКАДом, привезёте?"};


    public void compareActualQuestionInFAQItem() {
        List<WebElement> FAQItemList = driver.findElements(FAQItems);
        String[] FAQItemArrayQuestion = new String[8];
        for (int i = 0; i < 9; i++) {
            new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                    .until(ExpectedConditions.visibilityOfElementLocated(firstFAQItem));
            FAQItemArrayQuestion[i] = FAQItemList.get(i).getText();
            assertEquals("Incorrect question text: ", expectedQuestionInFAQItem[i], FAQItemArrayQuestion[i]);
        }
    }

    String[] expectedAnswerInAFAQItem = {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."};

    public void compareActualAnswerInFAQItem() {
        List<WebElement> ListItem = driver.findElements(questionsInFAQItem);
        int index = 0;
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(firstFAQItem));
        for (WebElement item : ListItem) {
            item.findElement(FAQItems).click();
            WebElement description = item.findElement(answersInFAQItem);
            new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(D -> description.isDisplayed());
            String descriptionText = description.getText();
            assertEquals("Incorrect answer text: ", expectedAnswerInAFAQItem[index], descriptionText);
            index++;
        }
    }
}
