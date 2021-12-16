package ru.ibs.company.framework.utils;

import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestStepFinished;
import io.qameta.allure.Allure;
import io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.ibs.company.framework.managers.DriverManager;

import java.util.Date;

public class Listener  extends AllureCucumber7Jvm {

    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, testStepFinished -> {
            if(testStepFinished.getResult().getStatus().is(Status.FAILED)) {
                Allure.getLifecycle().addAttachment(
                        "Screnshot" + new Date(),
                        "image/png",
                        "png",
                        ((TakesScreenshot) DriverManager.getDriverManager().getDriver()).getScreenshotAs(OutputType.BYTES));
            }
        });
        super.setEventPublisher(publisher);
    }

}
