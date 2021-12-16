package ru.ibs.company.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static ru.ibs.company.framework.utils.PropConst.VERSION_APP;

public class ConnectPage extends BasePage {

    @FindBy(xpath = "//h2")
    private WebElement content;

    @FindBy(xpath = "//span[text()='Выйти из системы']/..")
    private WebElement buttonExit;


    public void checkOpenPage() {
        try {
            waitUtilElementToBeVisible(By.xpath("//h1[contains(.,'Проверка статуса v') and contains(.,'" +
                    props.getProperty(VERSION_APP) + "')]"));
        } catch (TimeoutException ex) {
            Assertions.fail("Страница 'Connect' не загрузилась");
        }
    }

    public void checkContentContainsTextOnPage(String text) {
        waitUtilElementToBeVisible(content);
        Assertions.assertTrue(waitUtilElementAttributeValue(content, "innerText", text),
                "Страница 'Connect' не содержит текст '" + text + "'");
    }

    public void clickBtn(String nameBtn) {
        WebElement btn = null;
        switch (nameBtn) {
            case "Выйти из системы":
                btn = buttonExit;
                break;
            default:
                Assertions.fail("Кнопки '" + nameBtn +"' на страницк 'Connect' не существует");
        }
        waitUtilElementToBeClickable(btn).click();
    }
}
