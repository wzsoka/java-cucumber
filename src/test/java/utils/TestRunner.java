package utils;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefs",
        tags = "@smoke",
        plugin = {"pretty", "html:target/reports/html-reports/html-report.html"})
public class TestRunner {
}