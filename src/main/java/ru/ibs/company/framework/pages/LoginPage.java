package ru.ibs.company.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static ru.ibs.company.framework.utils.PropConst.VERSION_APP;

public class LoginPage extends BasePage {

    @FindBy(id = "uid")
    private WebElement loginField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@name='sessionId']")
    private WebElement sessionIdField;

    @FindBy(xpath = "//input[@value='haveSessionId']")
    private WebElement checkBoxSessionId;

    @FindBy(xpath = "//span[text()='Подключиться' or text()='Войти']/..")
    private WebElement buttonConnect;

    @FindBy(xpath = "//a[text()='Зарегистрироваться']")
    private WebElement linkReg;


    public void checkOpenPage() {
        try {
            waitUtilElementToBeVisible(By.xpath("//h1[contains(.,'Тест v') and contains(.,'" +
                    props.getProperty(VERSION_APP) + "')]"));
        } catch (TimeoutException ex) {
            Assertions.fail("Страница 'Авторизация' не загрузилась");
        }
    }

    public void fillField(String nameField, String value) {
        WebElement field = null;
        value = value.startsWith("var#->") ?
                dataStorage.getVariableValue(value.replaceAll("var#->", "")) : value;
        switch (nameField) {
            case "Уникальный идентификатор пользователя":
                field = loginField;
                break;
            case "Пароль":
                field = passwordField;
                break;
            case "Идентификатор сессии":
                field = sessionIdField;
                break;
            default:
                Assertions.fail("Поле '" + nameField +"' на страницк 'Авторизация' не существует");
        }
        fillInputField(field, value);
        Assertions.assertTrue(waitUtilElementAttributeValue(field, "value", value),
                "Значение аттрибута 'value' несовпало у поля '" + nameField + "'\n" +
                        "Ожидалось: " + value + "\n" +
                        "Фактическое: " + field.getAttribute("value") + "\n");
    }

    public void clickBtn(String nameBtn) {
        WebElement btn = null;
        switch (nameBtn) {
            case "Войти":
                btn = buttonConnect;
                break;
            default:
                Assertions.fail("Кнопки '" + nameBtn +"' на страницк 'Авторизация' не существует");
        }
        waitUtilElementToBeClickable(btn).click();
    }

    public void clickLink(String nameLink) {
        WebElement link = null;
        switch (nameLink) {
            case "Зарегистрироваться":
                link = linkReg;
                break;
            default:
                Assertions.fail("Ссылки '" + nameLink +"' на страницк 'Авторизация' не существует");
        }
        waitUtilElementToBeClickable(link).click();
    }

    public String getValueFromField(String nameField) {
        WebElement field = null;
        switch (nameField) {
            case "Идентификатор сессии":
                field = sessionIdField;
                break;
            default:
                Assertions.fail("Поля '" + nameField + "' на страницк 'Авторизация' не существует");
        }
        return getValueWebElement(field);
    }

    public void setCheckBoxByName(String nameCheckBox, Boolean select) {
        WebElement checkBox = null;
        switch (nameCheckBox) {
            case "Войти по идентификатору":
                checkBox = checkBoxSessionId;
                break;
            default:
                Assertions.fail("Чекбокс '" + nameCheckBox + "' на страницк 'Авторизация' не существует");
        }
        setCheckBox(checkBox, select);
        Assertions.assertTrue(waitUtilElementAttributeValue(checkBox, "checked", select.toString()),
                "Значение аттрибута 'checked' несовпало у чекбокса '" + nameCheckBox + "'\n" +
                        "Ожидалось: " + select + "\n" +
                        "Фактическое: " + checkBox.getAttribute("checked") + "\n");
    }
}
