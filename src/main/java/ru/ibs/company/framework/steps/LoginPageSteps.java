package ru.ibs.company.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import ru.ibs.company.framework.data.DataStorage;
import ru.ibs.company.framework.managers.PageManager;
import ru.ibs.company.framework.pages.LoginPage;

public class LoginPageSteps {

    PageManager pageManager = PageManager.getPageManager();

    @И("^заполняем поля на странице 'Авторизация':$")
    public void fillField(DataTable dataTable) {
        dataTable.asMap()
                .forEach((nameField, value)-> pageManager.getPage(LoginPage.class).fillField(nameField, value));
    }

    @И("^кликаем на кнопку \"([^\"]*)\" на странице 'Авторизация'$")
    public void clickBtn(String nameBtn) {
        pageManager.getPage(LoginPage.class).clickBtn(nameBtn);
    }

    @И("^кликаем по ссылке \"([^\"]*)\" на странице 'Авторизация'$")
    public void clickLink(String nameLink) {
        pageManager.getPage(LoginPage.class).clickLink(nameLink);
    }

    @И("^в переменную \"(.*)\" сохранить значение из поля \"(.*)\" на странице 'Авторизация'$")
    public void createVariable(String variable, String nameField) {
        DataStorage.getInstance().addVariable(variable, pageManager.getPage(LoginPage.class).getValueFromField(nameField));
    }

    @И("^установить чекбокс \"(.*)\" в значение \"(true|false)\" на странице 'Авторизация'$")
    public void установить_чекбокс_в_значение(String nameCheckBox, Boolean select) {
        pageManager.getPage(LoginPage.class).setCheckBoxByName(nameCheckBox, select);
    }

}
