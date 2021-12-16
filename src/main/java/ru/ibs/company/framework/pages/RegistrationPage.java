package ru.ibs.company.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static ru.ibs.company.framework.utils.PropConst.VERSION_APP;

public class RegistrationPage extends BasePage {

    @FindBy(id = "uid")
    private WebElement uidField;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//span[text()='Зарегестрироваться']/..")
    private WebElement buttonReg;

    @FindBy(xpath = "//input[@value='createSessionId']")
    private WebElement checkBoxCreateSessionId;


    public void checkOpenPage() {
        try {
            waitUtilElementToBeVisible(By.xpath("//h1[contains(.,'Регистрация v') and contains(.,'" +
                    props.getProperty(VERSION_APP) + "')]"));
        } catch (TimeoutException ex) {
            Assertions.fail("Страница 'Регистрация' не загрузилась");
        }
    }


    public void fillField(String nameField, String value) {
        WebElement field = null;
        switch (nameField) {
            case "Уникальный идентификатор пользователя":
                field = this.uidField;
                break;
            case "Имя":
                field = this.nameField;
                break;
            case "Страна":
                field = this.countryField;
                break;
            case "Пароль":
                field = this.passwordField;
                break;
            default:
                Assertions.fail("Поле '" + nameField + "' на страницк 'Регистрация' не существует");
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
            case "Зарегистрироваться":
                btn = buttonReg;
                break;
            default:
                Assertions.fail("Кнопка '" + nameBtn + "' на страницк 'Регистрация' не существует");
        }
        waitUtilElementToBeClickable(btn).click();
    }

    public void setCheckBoxByName(String nameCheckBox, Boolean select) {
        WebElement checkBox = null;
        switch (nameCheckBox) {
            case "Создать идентификатор сессии автоматически":
                checkBox = checkBoxCreateSessionId;
                break;
            default:
                Assertions.fail("Чекбокс '" + nameCheckBox + "' на страницк 'Регистрация' не существует");
        }
        setCheckBox(checkBox, select);
    }

    public String getValueFromField(String nameField){
        WebElement field = null;
        switch (nameField) {
            case "Идентификатор сессии":
                field = checkBoxCreateSessionId;
                break;
            default:
                Assertions.fail("Поле '" + nameField + "' на страницк 'Регистрация' не существует");
        }
        return getValueWebElement(field);
    }
}
