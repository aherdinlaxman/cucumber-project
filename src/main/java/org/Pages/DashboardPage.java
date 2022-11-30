package org.Pages;

import org.Utilities.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DashboardPage extends Utilities {
    public WebDriver driver;
    //Elements
    @FindBy(className = "app_logo")
    WebElement pageLogo;

    //Constructor
    DashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //Actions
    public boolean verifyLogo(){
        return pageLogo.isDisplayed();
    }
}
