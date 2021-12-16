package ru.ibs.company.framework.managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.ibs.company.framework.utils.PropConst.TYPE_BROWSER;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для управления веб драйвером
 */
public class DriverManager {

    /**
     * Переменна для хранения объекта веб-драйвера
     *
     * @see WebDriver
     */
    private WebDriver driver = null;

    private WebDriverWait wait = null;


    /**
     * Переменна для хранения объекта DriverManager
     */
    private static DriverManager INSTANCE = null;


    /**
     * Менеджер properties
     *
     * @see TestPropManager#getTestPropManager()
     */
    private final TestPropManager props = TestPropManager.getTestPropManager();


    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see DriverManager#getDriverManager()
     */
    private DriverManager() {
    }

    /**
     * Метод ленивой инициализации DriverManager
     *
     * @return DriverManager - возвращает DriverManager
     */
    public static DriverManager getDriverManager() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    /**
     * Метод ленивой инициализации веб драйвера
     *
     * @return WebDriver - возвращает веб драйвер
     */
    public WebDriver getDriver() {
        if (this.driver == null) {
            initDriver();
            wait = new WebDriverWait(driver, 20, 1000);
        }
        return this.driver;
    }


    public WebDriverWait getWait() {
        return wait;
    }


    /**
     * Метод для закрытия сессии драйвера и браузера
     *
     * @see WebDriver#quit()
     */
    public void quitDriver() {
        if (this.driver != null) {
            this.driver.quit();
            this.driver = null;
        }
    }

    /**
     * Метод инициализирующий веб драйвер под любую ОС
     */
    private void initDriver() {
        switch (props.getProperty(TYPE_BROWSER)) {
            case "firefox":
                this.driver = WebDriverManager.firefoxdriver().create();
                break;
            case "chrome":
//                ChromeOptions ops = new ChromeOptions();
//                ops.addArguments("--disable-notifications");
//                WebDriverManager.chromedriver().setup();
//                this.driver = new ChromeDriver(ops);
                this.driver = WebDriverManager.chromedriver().create();
                break;
            case "opera":
                this.driver = WebDriverManager.operadriver().create();
                break;
            case "edge":
                this.driver = WebDriverManager.edgedriver().create();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                this.driver = new InternetExplorerDriver();
                break;
            default:
                Assert.fail("Типа браузера '" + props.getProperty(TYPE_BROWSER) + "' не существует во фреймворке");
        }
    }


}

