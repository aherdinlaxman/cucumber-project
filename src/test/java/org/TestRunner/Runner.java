package org.TestRunner;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/resources/login.feature",
        glue = {"org/StepDefinitions","org/ApplicationHook"},
        monochrome=true,
        dryRun=false,
//		tags="",
        plugin = {"pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" ,
                "html:target/html_report/cucumber_reports.html",
                "junit:target/junit_report/junit_reports.xml",
                "json:target/json_report/json_reports.json",

        }
)

public class Runner {
}
