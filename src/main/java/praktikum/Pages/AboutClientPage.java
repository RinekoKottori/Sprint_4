package praktikum.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;
import static praktikum.EnvConfig.BASE_URL;
import static praktikum.EnvConfig.EXPLICIT_WAIT;


public class AboutClientPage {
    private final WebDriver driver;
    private final By nameOfOrderPageAboutClient = By.className("Order_Header__BZXOb");
    private final By fieldName = By.xpath(".//input[@placeholder='* Имя']");
    private final By errorMassageForName = By.xpath(".//div[text()='Введите корректное имя']");
    private final By errorMassageForSurname = By.xpath(".//div[text()='Введите корректную фамилию']");
    private final By errorMassageForAddress = By.xpath(".//div[text()='Введите корректный адрес']");
    private final By errorMassageForPhone = By.xpath(".//div[text()='Введите корректный номер']");
    private final By fieldSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By fieldAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By fieldMetro = By.className("select-search__input");
    private final By optionMetro = By.className("select-search__row");
    private final By fieldPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By buttonNext = By.className("Button_Middle__1CSJM");

    public AboutClientPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openDriver() {
        driver.get(BASE_URL);
    }

    public void checkIsUserPageIsOpen() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.visibilityOfElementLocated(nameOfOrderPageAboutClient));
        assertEquals("Page Rent is not open", "Для кого самокат", driver.findElement(nameOfOrderPageAboutClient).getText());
    }


    public void setUserName(String username) {
        driver.findElement(fieldName).clear();
        driver.findElement(fieldName).sendKeys(username);
    }

    public void isUserNameCorrect() {
        driver.findElement(fieldSurname).click();
        String actualName = driver.findElement(fieldName).getAttribute("value");
        assertFalse("Invalid user name: " + actualName, driver.findElement(errorMassageForName).getAttribute("class").contains("Input_Visible___syz6"));
    }

    public void setUserSurame(String userSurname) {
        driver.findElement(fieldSurname).clear();
        driver.findElement(fieldSurname).sendKeys(userSurname);
    }

    public void isUserSurnameCorrect() {
        driver.findElement(fieldAddress).click();
        String actualSurname = driver.findElement(fieldSurname).getAttribute("value");
        assertFalse("Invalid user surname: " + actualSurname, driver.findElement(errorMassageForSurname).getAttribute("class").contains("Input_Visible___syz6"));
    }

    public void setUserAddress(String userAddress) {
        driver.findElement(fieldAddress).clear();
        driver.findElement(fieldAddress).sendKeys(userAddress);
    }

    public void isUserAddressCorrect() {
        driver.findElement(fieldPhone).click();
        String actualAddress = driver.findElement(fieldAddress).getAttribute("value");
        assertFalse("Invalid user address: " + actualAddress, driver.findElement(errorMassageForAddress).getAttribute("class").contains("Input_Visible___syz6"));
    }

    public void selectFromDropDownListOfMetroStations(String metroStation) {
        driver.findElement(fieldMetro).clear();
        driver.findElement(fieldMetro).sendKeys(metroStation);
        driver.findElement(optionMetro).click();
    }

    public void isMetroStationSelected() {
        String actualMetroStation = driver.findElement(fieldMetro).getAttribute("value");
        assertFalse("Metro station don't selected", actualMetroStation.isEmpty());
    }


    public void setUserPhone(String userPhone) {
        driver.findElement(fieldPhone).clear();
        driver.findElement(fieldPhone).sendKeys(userPhone);
    }

    public void isUserPhoneCorrect() {
        driver.findElement(fieldAddress).click();
        String actualPhone = driver.findElement(fieldPhone).getAttribute("value");
        assertFalse("Invalid user phone: " + actualPhone, driver.findElement(errorMassageForPhone).getAttribute("class").contains("Input_Visible___syz6"));
    }

    public AboutRentPage clickNextButton() {
        driver.findElement(buttonNext).click();
        return new AboutRentPage(driver);
    }

    public void fieldsAboutClientPage(String name, String surname, String adress, String metro, String phone) {
        var clientPage = new AboutClientPage(driver);
        clientPage.setUserName(name);
        clientPage.setUserSurame(surname);
        clientPage.setUserAddress(adress);
        clientPage.selectFromDropDownListOfMetroStations(metro);
        clientPage.setUserPhone(phone);
        clientPage.clickNextButton();
    }
}