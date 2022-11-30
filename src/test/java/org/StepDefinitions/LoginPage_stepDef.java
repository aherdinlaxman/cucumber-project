package org.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.ApplicationHook.ApplicationHook;
import org.Pages.DashboardPage;
import org.Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class LoginPage_stepDef {
    WebDriver driver = ApplicationHook.driver;
    LoginPage lp ;
    DashboardPage dp;
    @Given("User is on login page")
    public void user_is_on_login_page() {
        lp = new LoginPage(driver);
        Assert.assertEquals(driver.getTitle(), "Swag Labs");

    }

    @When("User enters valid {string} and {string}")
    public void user_enters_valid_and(String username, String pwd) {

        lp.inputCredentials(username,pwd);

    }

    @When("User clicks on login button")
    public void user_clicks_on_login_button() {

       dp =  lp.clickLoginBtn();

    }

    @Then("User should be able to login successfully")
    public void user_should_be_able_to_login_successfully() {
        Assert.assertTrue(dp.verifyLogo());
        Assert.assertEquals(dp.getTitle(driver), "Swag Labs");

    }
    @Then("User should get error message of {string}")
    public void user_should_get_error_message_of(String expectedMsg) {
    Assert.assertEquals(lp.errorMsg(),expectedMsg );
    }
    @Then("User should get Locked error message of {string}")
    public void user_should_get_locked_error_message_of(String expectedMsg) {
        Assert.assertEquals(lp.errorMsg(),expectedMsg );
    }
}
