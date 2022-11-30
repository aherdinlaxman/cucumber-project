package org.Pages;

import org.Utilities.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Utilities {
    public WebDriver driver;
    //Elements
    @FindBy(id = "user-name")
    WebElement username;
    @FindBy(id = "password")
    WebElement password;
    @FindBy(id = "login-button")
    WebElement lgnButton;
    @FindBy(xpath = "//h3[@data-test = 'error']")
    WebElement errorMsg;
    //Constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //Actions
    public void inputCredentials(String uName, String pwd){
        username.clear();
        username.sendKeys(uName);
        password.clear();
        password.sendKeys(pwd);
    }
    public DashboardPage clickLoginBtn(){
        lgnButton.click();
        return new DashboardPage(driver);

    }
    public void clickLoginBtn1(){
        lgnButton.click();
        ;
    }
    public String errorMsg(){
        return errorMsg.getText();
    }
}
