package ru.ibs.company.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.company.framework.data.DataStorage;
import ru.ibs.company.framework.managers.DriverManager;
import ru.ibs.company.framework.managers.PageManager;
import ru.ibs.company.framework.managers.TestPropManager;

/**
 * @author Arkadiy_Alaverdyan
 * Базовый класс всех страничек
 */
public class BasePage {

    protected final DriverManager driverManager = DriverManager.getDriverManager();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 30, 1000);
    protected final TestPropManager props = TestPropManager.getTestPropManager();
    protected final DataStorage dataStorage = DataStorage.getInstance();

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitUtilElementToBeVisible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected Boolean waitUtilElementAttributeValue(WebElement element, String attributeName, String value) {
        boolean flag;
        try {
            flag = wait.until(ExpectedConditions.attributeContains(element, attributeName, value));
        } catch (TimeoutException ex) {
            flag = false;
        }
        return flag;
    }


    protected void fillInputField(WebElement field, String value) {
        try {
            waitUtilElementToBeClickable(field).click();
            field.sendKeys(value);
        } catch (UnhandledAlertException e) {
            Assertions.fail("Alert text: " + e.getAlertText());
        }
    }

    protected String getValueWebElement(WebElement element) {
        return element.getText().trim().isEmpty() ? element.getAttribute("value") :
                element.getText().trim();
    }

    protected void setCheckBox(WebElement checkBox, Boolean select) {
        if (checkBox.isSelected() != select) {
            checkBox.click();
        }
    }

    public void checkOpenPage() {
    }
}
