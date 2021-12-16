package ru.ibs.company.framework.managers;

import org.junit.jupiter.params.aggregator.ArgumentAccessException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

import static ru.ibs.company.framework.utils.PropConst.*;

public class InitManager {


    private static final TestPropManager props = TestPropManager.getTestPropManager();
    private static final String appVersion = props.getProperty(VERSION_APP, "2");
    private static final String versionXpath = "//span[@class='MuiButton-label' and text()='V"+appVersion+"']/..";
    private static final DriverManager driverManager = DriverManager.getDriverManager();

    public static void initFramework() {
        if(!appVersion.equals("2") && !appVersion.equals("3") && !appVersion.equals("1")) {
            throw new ArgumentAccessException("Системное св-во '" + VERSION_APP + "' может принимать значение '1', '2' или '3'\n" +
                    "Было переданно некорректно значение: '" + appVersion + "'");
        }
        WebDriver driver = driverManager.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        driver.get(props.getProperty(BASE_URL));
        WebElement element = driverManager.getWait().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(versionXpath)));
        ((JavascriptExecutor) driverManager.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void quitFramework() {
        driverManager.quitDriver();
        PageManager.getPageManager().clearMapPages();
    }

}
