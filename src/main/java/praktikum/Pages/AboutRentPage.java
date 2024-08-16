package praktikum.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;
import static praktikum.EnvConfig.EXPLICIT_WAIT;

public class AboutRentPage {
    private WebDriver driver;
    private final By nameOfRentPage = By.className("Order_Header__BZXOb");
    private final By fieldDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By monthContainer = By.className("react-datepicker__month-container");
    private final By todayDate = By.xpath(".//div[@tabindex='0']");
    private final By fieldRent = By.className("Dropdown-placeholder");
    private final By listofRentOptions = By.xpath(".//div[@class='Dropdown-menu']/div");

    private final By listOfCheckbox = By.xpath(".//label[@class='Checkbox_Label__3wxSf']/input");
    private final By checkBoxForm = By.xpath(".//div[@class='Order_Form__17u6u']/div[3]");

    private final By fieldComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By errorMassageComment = By.xpath(".//div[text()='Тут что-то не так']");
    private final By buttonOrder = By.xpath(".//button[text()='Заказать' and @class='Button_Button__ra12g Button_Middle__1CSJM']");


    public AboutRentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openDriver() {
        driver.get("https://qa-scooter.praktikum-services.ru/order");
    }

    public void checkIsRentPageIsOpen() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        assertEquals("Rent page is not open","Про аренду" , driver.findElement(nameOfRentPage).getText());
    }

    public void setDate() {
        driver.findElement(fieldDate).click();
        driver.findElement(monthContainer).findElement(todayDate).click();
    }

    public void isDateCorrect (){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = date.format(formatter);
        assertEquals("Date is incorrect: ",formattedDate, driver.findElement(fieldDate).getAttribute("value"));
    }

    public void setFieldRent(int i) {
        driver.findElement(fieldRent).click();
        List<WebElement> listOptions = driver.findElements(listofRentOptions);
        WebElement oneRentOption = listOptions.get(i);
        oneRentOption.click();
    }

    public void isRentTimeCorrect(){
        assertTrue("Rent time not chosen: ", driver.findElement(fieldRent).getAttribute("class").contains("is-selected"));
    }


    public void setCheckboxColor(int i) {
        List<WebElement> listOptions = driver.findElements(listOfCheckbox);
        WebElement oneCheckboxColor = listOptions.get(i);
        oneCheckboxColor.click();
    }

    public void isChosenCheckboxCorrect(){
        assertTrue("Chosen checkbox is incorrect ", driver.findElement(checkBoxForm).getAttribute("class").contains("Order_FilledContainer__2MKAk"));
    }

    public void setFieldComment(String comment) {
        driver.findElement(fieldComment).clear();
        driver.findElement(fieldComment).sendKeys(comment);
    }

    public void isFieldCommentCorrect(){
        assertFalse("Comment is incorrect: ", driver.findElement(errorMassageComment).getAttribute("class").contains("Input_Visible___syz6"));
    }

    public ConfirmOrderPage clickButtonOrder() {
        driver.findElement(buttonOrder).click();
        return new ConfirmOrderPage(driver);
    }

    public void fieldsAboutRentPage (int indexOfRent, int indexOfCheckbox,  String comment) {
        var rentPage = new AboutRentPage(driver);
        rentPage.setDate();
        rentPage.setFieldRent(indexOfRent);
        rentPage.setCheckboxColor(indexOfCheckbox);
        rentPage.setFieldComment(comment);
        rentPage.clickButtonOrder();
    }
}