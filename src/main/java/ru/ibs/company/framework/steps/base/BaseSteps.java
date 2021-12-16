package ru.ibs.company.framework.steps.base;

import io.cucumber.java.ru.И;
import ru.ibs.company.framework.managers.PageManager;
import ru.ibs.company.framework.pages.ConnectPage;
import ru.ibs.company.framework.pages.LoginPage;
import ru.ibs.company.framework.pages.RegistrationPage;

public class BaseSteps {

    private final PageManager pageManager = PageManager.getPageManager();

    @И("^страница \"([^\"]*)\" загружена$")
    public void stepLoadedPage(String pageName) {
        Class clazz = null;
        switch (pageName) {
            case "Авторизация":
                clazz = LoginPage.class;
                break;
            case "Connect":
                clazz = ConnectPage.class;
                break;
            case "Регистрация":
                clazz = RegistrationPage.class;
                break;
        }
        pageManager.getPage(clazz).checkOpenPage();
    }

}
