package ru.ibs.company.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import ru.ibs.company.framework.managers.PageManager;
import ru.ibs.company.framework.pages.ConnectPage;

public class ConnectPageSteps {

    PageManager pageManager = PageManager.getPageManager();

    @И("^страница 'Connect' содержит следующее содержание:$")
    public void clickBtn(DataTable dataTable) {
        dataTable.asList()
                .forEach((text)-> pageManager.getPage(ConnectPage.class).checkContentContainsTextOnPage(text));
    }

    @И("^кликаем на кнопку \"([^\"]*)\" на странице 'Connect'$")
    public void clickBtn(String nameBtn) {
        pageManager.getPage(ConnectPage.class).clickBtn(nameBtn);
    }
}
