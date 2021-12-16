package ru.ibs.company.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import ru.ibs.company.framework.data.DataStorage;
import ru.ibs.company.framework.managers.PageManager;
import ru.ibs.company.framework.pages.RegistrationPage;

public class RegistrationPageSteps {

    PageManager pageManager = PageManager.getPageManager();

    @И("^заполняем поля на странице 'Регистрация':$")
    public void fillField(DataTable dataTable) {
        dataTable.asMap()
                .forEach((nameField, value)-> pageManager.getPage(RegistrationPage.class).fillField(nameField, value));
    }

    @И("^кликаем на кнопку \"([^\"]*)\" на странице 'Регистрация'$")
    public void clickBtn(String nameBtn) {
        pageManager.getPage(RegistrationPage.class).clickBtn(nameBtn);
    }

    @И("^установить чекбокс \"(.*)\" в значение \"(true|false)\" на странице 'Регистрация'$")
    public void установить_чекбокс_в_значение(String nameCheckBox, Boolean select) {
        pageManager.getPage(RegistrationPage.class).setCheckBoxByName(nameCheckBox, select);
    }

    @И("^в переменную \"(.*)\" сохранить значение из поля \"(.*)\" на странице 'Регистрация'$")
    public void createVariable(String variable, String nameField) {
        DataStorage.getInstance().addVariable(variable, pageManager.getPage(RegistrationPage.class).getValueFromField(nameField));
    }
}
