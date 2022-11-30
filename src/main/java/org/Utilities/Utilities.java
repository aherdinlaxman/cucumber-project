package org.Utilities;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Utilities {
    /**
     * getCurrentURL
     *
     * @param driver
     * @return
     */
    public String getCurrentURL(WebDriver driver) {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String setValueToElement(String value, WebElement element, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return jse.executeScript("return arguments[0].value = '" + value + "'", element).toString();
    }

    public void executeJavaScript(String command, WebElement element, WebDriver driver) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0]." + command, element).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * scrollPageTo
     *
     * @param element
     * @param driver
     */
    public void scrollPageTo(WebElement element, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String bottom = jse.executeScript("return arguments[0].getBoundingClientRect().bottom;", element).toString();
        jse.executeScript("window.scrollTo(0," + bottom + ")", element);
    }

    /**
     * getPositionOfElement
     *
     * @param element
     * @param driver
     * @param position
     * @return
     */
    public int getPositionOfElement(WebElement element, WebDriver driver, String position) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return Integer.parseInt(jse.executeScript("return arguments[0].getBoundingClientRect()." + position + ";", element).toString());
    }

    /**
     * scrollPageByPixels
     *
     * @param px
     * @param driver
     */
    public void scrollPageByPixels(String px, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0," + px + ")");
    }

    /**
     * getTextValueOfElement JS By text content
     *
     * @param element
     * @param driver
     * @return
     */
    public String getTextContentOfElement(WebElement element, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return jse.executeScript("return arguments[0].textContent", element).toString();
    }

    /**
     * getTextValueOfElement JS By text content
     *
     * @param element
     * @param driver
     * @return
     */
    public String getTextValueOfElement(WebElement element, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return jse.executeScript("return arguments[0].value", element).toString();
    }

    public String getTitle(WebDriver driver){
        return driver.getTitle();
    }

    /**
     * getTextValueOfElement JS By text content
     *
     * @param element
     * @param driver
     * @return
     */
    public boolean isElementCheckedOrSelected(WebElement element, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (jse.executeScript("return arguments[0].checked", element).toString().contains("true"))
            return true;

        return false;
    }

    /**
     * Load Page by URL
     *
     * @param url
     */
    public void loadURL(WebDriver driver , String url) {
        driver.get(url);
    }

    /**
     * switchTab
     *
     * @param tabIndex
     * @return
     */
    public boolean switchTab(WebDriver driver , int tabIndex) {
        try {
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabIndex));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * click element JS
     *
     * @param element
     * @param driver
     * @return
     */
    public void clickOnElement(WebElement element, WebDriver driver) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("return arguments[0].click()", element).toString();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * click element JS
     *
     * @param element
     * @param driver
     * @return
     */
    public void clickOnElement(By element, WebDriver driver) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click()", driver.findElement(element)).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * clickElementByActions
     * @param element
     * @param driver
     */
    public void clickElementByActions(WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click();
        actions.build().perform();
    }

    /**
     * setElementValueByActions
     * @param text
     * @param element
     * @param driver
     */
    public void setElementTextValueByActions(String text, WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys(text);
        actions.sendKeys(Keys.ENTER);
        actions.build().perform();
    }

    /**
     * clearElementTextValueByActions
     * @param element
     * @param driver
     */
    public void clearElementTextValueByActions(WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        executeJavaScript("select()", element, driver);
        actions.sendKeys(Keys.DELETE);
        actions.build().perform();
    }

    /**
     * waitUntilElementDisplayed
     * @param TIMEOUT
     * @param implicitlyWait
     * @param webElement
     * @param driver
     */
    public void waitUntilElementDisplayed(int TIMEOUT, int implicitlyWait, final WebElement webElement, WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        ExpectedCondition<Boolean> elementIsDisplayed = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    webElement.isDisplayed();
                    return true;
                } catch (NoSuchElementException e) {
                    return false;
                } catch (StaleElementReferenceException f) {
                    return false;
                }
            }
        };
        wait.until(elementIsDisplayed);
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
    }

    /**
     * waitForLoaderElement
     * @param driver
     * @return
     */
    public boolean waitForLoaderElement(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner")));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * waitForInvisibilityOfElement
     *
     * @param classname
     * @param bySelector
     * @param driver
     * @return
     */
    public boolean waitForInvisibilityOfElement(String classname, By bySelector, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        try {
            wait.until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver arg0) {
                    try {
                        return (hasClass(classname, arg0.findElement(bySelector)));
                    } catch (Exception e) {
                        return true;
                    }
                }
            });

            if (!hasClass(classname, driver.findElement(bySelector)))
                return false;
            else
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * hasClass
     *
     * @param className
     * @param element
     * @return
     */
    public boolean hasClass(String className, WebElement element) {
        final String classNameLowerCase = className.toLowerCase();
        String classValue;

        try {
            classValue = element.getAttribute("class");
        } catch (Exception e) {
            return false;
        }

        if (StringUtils.isEmpty(classValue)) {
            return false;
        }

        classValue = classValue.toLowerCase();

        if (!classValue.contains(classNameLowerCase)) {
            return false;
        }

        for (String singleClass : classValue.split("\\s+")) {
            if (classNameLowerCase.equals(singleClass.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * hasAttribute
     *
     * @param attribute
     * @param element
     * @return
     */
    public boolean hasAttribute(String attribute, WebElement element) {

        String att = element.getAttribute(attribute);

        if (StringUtils.isEmpty(att)) {
            return false;
        }
        return true;
    }

    /**
     * hasAttributeEqualTo
     *
     * @param attribute
     * @param attributeString
     * @param element
     * @return
     */
    public boolean hasAttributeEqualTo(String attribute, String attributeString, WebElement element) {
        return element.getAttribute(attribute).equals(attributeString);
    }



    /**
     * clickLink
     *
     * @param link
     */
    public void clickLink(WebDriver driver , String link) {
        driver.findElement(By.linkText(link)).click();
    }

    /**
     * List all the folder under a directory
     *
     * @param directoryName
     *            to be listed
     */
    public List<String> listFolders(String directoryName) {
        File directory = new File(directoryName);
        List<String> filesList = new ArrayList<String>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                filesList.add(file.getName());
            }
        }
        return filesList;
    }

    /**
     * Refresh the page
     */
    public void refreshCurrentPage(WebDriver driver) {

        driver.navigate().refresh();
    }

    /**
     * getBrowserLogMessages
     * @param driver
     * @return
     */
    public LogEntries getBrowserLogMessages(WebDriver driver) {
        try {

            return driver.manage().logs().get(LogType.PERFORMANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * isTextDisplayed
     *
     * @param text
     * @return
     */
    public boolean isTextDisplayed(WebDriver driver , String text) {
        try {
            driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
