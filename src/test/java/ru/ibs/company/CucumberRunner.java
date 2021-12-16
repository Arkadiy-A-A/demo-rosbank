package ru.ibs.company;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"ru.ibs.company.framework.utils.Listener"},
        glue = {"ru/ibs/company/framework/steps"},
        features = {"src/test/resources/feature"},
        tags = "@all",
        monochrome = true
)
public class CucumberRunner {
}
