package com.microsoft.cucumber.steps;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.microsoft.cucumber.runner.RunCucumberTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefinitionPetClinicPages extends RunCucumberTest {
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	@Before
	public void setUp() throws Exception {
    	System.getProperty("webdriver.chrome.driver", System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe"));
    	ChromeDriver chrmDriver = new ChromeDriver();
    	chrmDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
    	driver.set(chrmDriver);
	}
	
	@After
	public void closeBrowser() {
		driver.get().quit();
	}

	@Given("^I am on the home page of the petclinic web app")
	public void givenPetClinicHomePageNavigation() throws Throwable {
		System.out.print(driver.get().getWindowHandle().toString());
		driver.get().get("http://localhost:8080/");
		assertEquals("Welcome", driver.get().findElements(By.xpath("//h2")).get(0).getText());
	}

	@And("^I can see the welcome message on the home screen")
	public void welcomeMessageScreenCheck() throws Throwable {
		assertEquals("Welcome", driver.get().findElements(By.xpath("//h2")).get(0).getText());
	}

	@When("^I click the Find Owners tab")
	public void findOwnerButtonClick() throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"main-navbar\"]/ul/li[3]/a/span[2]")).click();
	}

	@Then("I should see the Last Name field")
	public void lastNameFieldCheck() throws Throwable {
		driver.get().findElement(By.name("lastName")).isDisplayed();
	}

	@And("^I should see the Find Owner button")
	public void findOwnerButtonCheck() throws Throwable {
		assertEquals("Find Owner",driver.get().findElement(By.xpath("//*[@id=\"search-owner-form\"]/div[2]/div/button")).getText());
	}

	@When("^I click the Add Owner button")
	public void addOwnerButtonclick() throws Throwable {
		driver.get().findElement(By.xpath("/html/body/div/div/a")).click();
	}

	@Then("I should see the First Name field")
	public void firstNameFieldCheck() throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"firstName\"]")).isDisplayed();
	}

	@And("^I should see the Address field")
	public void addressFieldNameCheck() throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"address\"]")).isDisplayed();
	}

	@And("^I should see the City field")
	public void cityFieldNameCheck() throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"city\"]")).isDisplayed();
	}

	@And("^I should see the Telephone field")
	public void telephoneFieldNameCheck() throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"telephone\"]")).isDisplayed();
	}

	@And("^I should see the Add Owner button")
	public void addOwnerButtonCheck() throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"add-owner-form\"]/div[2]/div/button")).isDisplayed();
	}

	@When("^I fill out the First Name field with \"([^\"]*)\"$")
	public void firstNameFieldFill(String text) throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"firstName\"]")).sendKeys(text);
	}

	@And("^I fill out the Last Name field with \"([^\"]*)\"$")
	public void lastNameFieldFill(String text) throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"lastName\"]")).sendKeys(text);
	}

	@And("^I fill out the Address field with \"([^\"]*)\"$")
	public void addressFieldFill(String text) throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"address\"]")).sendKeys(text);
	}

	@And("^I fill out the City field with \"([^\"]*)\"$")
	public void cityFieldFill(String text) throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"city\"]")).sendKeys(text);
	}

	@And("^I fill out the Telephone field with \"([^\"]*)\"$")
	public void telephoneFieldFill(String text) throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"telephone\"]")).sendKeys(text);
	}

	@And("^I click the Add Owner button on the New Owner page")
	public void addOwnerButtonNewOwnerPageclick() throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"add-owner-form\"]/div[2]/div/button")).click();
	}

	@Then("I should be presented with the Owner Information screen")
	public void ownerInformationPageCheck() throws Throwable {
		assertEquals("Edit Owner",driver.get().findElement(By.xpath("/html/body/div/div/a[1]")).getText());
		assertEquals("Add New Pet",driver.get().findElement(By.xpath("/html/body/div/div/a[2]")).getText());
	}

	@When("I click the Add New Pet button")
	public void newPetButtonCheck() throws Throwable {
		driver.get().findElement(By.xpath("/html/body/div/div/a[2]")).click();
	}

	@Then("I should be presented with the New Pet screen")
	public void newPetScreenCheck() throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"type\"]")).isDisplayed();
	}

	@When("^I fill out the \"([^\"]*)\"$")
	public void petNameFieldFill(String text) throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(text);
	}

	@And("^I fill out the \"([^\"]*)\" in the calendar$")
	public void petBirthDayFieldFill(String text) throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"birthDate\"]")).sendKeys(text);
	}

	@And("^I select the Pet type \"([^\"]*)\" from the drop down$")
	public void petTypeSelect(String text) throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"type\"]")).sendKeys(text);
	}

	@And("I click the Add Pet button")
	public void addNewPetButtonclick() throws Throwable {
		driver.get().findElement(By.xpath("/html/body/div/div/form/div[2]/div/button")).click();
	}

	@When("I click the Error Tab")
	public void errorTabClick() throws Throwable {
		driver.get().findElement(By.xpath("//*[@id=\"main-navbar\"]/ul/li[5]/a/span[2]")).click();
	}

	@Then("I should be on the Error Page")
	public void errorPageScreenCheck() throws Throwable {
		assertEquals("Something happened...", driver.get().findElement(By.xpath("/html/body/div/div/h2")).getText());
	}

}
