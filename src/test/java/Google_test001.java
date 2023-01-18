import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

public class Google_test001 {
    public static WebDriver driver;
    @BeforeTest(enabled = false)
    public void launch_browser(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver1 = new ChromeDriver();
        driver=driver1;
    }
    @Test(enabled = false)
    private void language_Test(){
    driver.get("https://www.google.com/");
    List<WebElement> ele = driver.findElements(By.id("SIvCob"));
    Iterator<WebElement> it = ele.iterator();
    while(it.hasNext()){
        System.out.println(it.next().getText());
    }
        System.out.println("I am google");
    }


}
