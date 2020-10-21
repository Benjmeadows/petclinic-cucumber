package com.microsoft.cucumber.runner;

import io.cucumber.junit.*;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"}, features = "src/test/features"
,glue= {"com.microsoft.cucumber.steps"})
public class RunCucumberTest {
}
