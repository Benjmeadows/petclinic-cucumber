package com.microsoft.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.But;

import static io.github.seleniumquery.SeleniumQuery.$;

public class StepDefinitionPetClinicPages {
	public static WebDriver driver;

	@Given("^I am on the home page of the petclinic web app")
	public void givenPetClinicHomePageNavigation() throws Throwable {
		System.getProperty("webdriver.chrome.driver", System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe"));
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/");
		$.driver().use(driver);
	}

	@And("^I can see the welcome message on the home screen")
	public void welcomeMessageScreenCheck() throws Throwable {
		assertEquals("Welcome", $("h2").text());
	}

	@When("^I click the Find Owners tab")
	public void findOwnerButtonClick() throws Throwable {
		$("a[title='find owners']").waitUntil().isPresent().then().click();
	}

	@Then("I should see the Last Name field")
	public void lastNameFieldCheck() throws Throwable {
		$("input[id='lastName']").waitUntil().isPresent();
	}

	@And("^I should see the Find Owner button")
	public void findOwnerButtonCheck() throws Throwable {
		$("form#search-owner-form :button").waitUntil().isPresent().and().html().contains("Owner");
	}
	
	@When("^I click the Add Owner button")
	public void addOwnerButtonclick() throws Throwable {
		$("a[href='/owners/new']").waitUntil().isPresent().then().click();
	}
	
	@Then("I should see the First Name field")
	public void firstNameFieldCheck() throws Throwable {
		$("input[id='firstName']").waitUntil().isPresent();
	}
	
	@And("^I should see the Address field")
	public void addressFieldNameCheck() throws Throwable {
		$("input[id='address']").waitUntil().isPresent();
	}
	
	@And("^I should see the City field")
	public void cityFieldNameCheck() throws Throwable {
		$("input[id='city']").waitUntil().isPresent();
	}
	
	@And("^I should see the Telephone field")
	public void telephoneFieldNameCheck() throws Throwable {
		$("input[id='telephone']").waitUntil().isPresent();
	}
	
	@And("^I should see the Add Owner button")
	public void addOwnerButtonCheck() throws Throwable {
		$("form#add-owner-form :button").waitUntil().isPresent().and().html().contains("Add Owner");
	}
	
	@When("^I fill out the First Name field with \"([^\"]*)\"$")
	public void firstNameFieldFill(String text) throws Throwable {
		$("input[id='firstName']").waitUntil().isPresent().then().val(text);
		$("input[id='firstName']").waitUntil().val().isEqualTo(text);
	}
	
	@And("^I fill out the Last Name field with \"([^\"]*)\"$")
	public void lastNameFieldFill(String text) throws Throwable {
		$("input[id='lastName']").waitUntil().isPresent().then().val(text);
		$("input[id='lastName']").waitUntil().val().isEqualTo(text);
	}
	
	@And("^I fill out the Address field with \"([^\"]*)\"$")
	public void addressFieldFill(String text) throws Throwable {
		$("input[id='address']").waitUntil().isPresent().then().val(text);
		$("input[id='address']").waitUntil().val().isEqualTo(text);
	}
	
	@And("^I fill out the City field with \"([^\"]*)\"$")
	public void cityFieldFill(String text) throws Throwable {
		$("input[id='city']").waitUntil().isPresent().then().val(text);
		$("input[id='city']").waitUntil().val().isEqualTo(text);
	}
	
	@And("^I fill out the Telephone field with \"([^\"]*)\"$")
	public void telephoneFieldFill(String text) throws Throwable {
		$("input[id='telephone']").waitUntil().isPresent().then().val(text);
		$("input[id='telephone']").waitUntil().val().isEqualTo(text);
	}
	
	@And("^I click the Add Owner button on the New Owner page")
	public void addOwnerButtonNewOwnerPageclick() throws Throwable {
		$("form#add-owner-form :button").waitUntil().isPresent().and().html().contains("Add Owner").then().click();
	}
	
	@Then("I should be presented with the Owner Information screen")
	public void ownerInformationPageCheck() throws Throwable {
		$("div.container.xd-container").find("a").attr("href").contains("edit");
		$("div.container.xd-container").find("a").attr("href").contains("pets/new");
	}
	
	@When("I click the Add New Pet button")
	public void newPetButtonCheck() throws Throwable {
		$("div.container.xd-container").find("a").get(1).click();
	}
	
	@Then("I should be presented with the New Pet screen")
	public void newPetScreenCheck() throws Throwable {
		$("select#type").waitUntil().isPresent();
	}
	
	@When("^I fill out the \"([^\"]*)\"$")
	public void petNameFieldFill(String text) throws Throwable {
		$("input[id='name']").waitUntil().isPresent().then().val(text);
	}
	
	@And("^I fill out the \"([^\"]*)\" in the calendar$")
	public void petBirthDayFieldFill(String text) throws Throwable {
		$("input[id='birthDate']").waitUntil().isPresent().then().val(text);
	}
	
	@And("^I select the Pet type \"([^\"]*)\" from the drop down$")
	public void petTypeSelect(String text) throws Throwable {
		$("#type").waitUntil().isPresent().then().val(text);
	}
	
	@And("I click the Add Pet button")
	public void addNewPetButtonclick() throws Throwable {
		$("form.form-horizontal :button").waitUntil().isPresent().and().html().contains("Add Pet").then().click();
	}
	
	@When("I click the Error Tab")
	public void errorTabClick() throws Throwable {
		$("a[title='trigger a RuntimeException to see how it is handled']").waitUntil().isPresent().then().click();
	}
	
	@Then("I should be on the Error Page")
	public void errorPageScreenCheck() throws Throwable {
		$("h2").waitUntil().isPresent();
	}
	
	@After()
	public void closeBrowser() {
		driver.quit();
	}

}
