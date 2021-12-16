package ru.ibs.company.framework.steps.base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ru.ibs.company.framework.managers.InitManager;

public class Hooks {

    @Before
    public void before() {
        InitManager.initFramework();
    }

    @After
    public void after() {
        InitManager.quitFramework();
    }
}
