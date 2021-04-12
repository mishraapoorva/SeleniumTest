package seleniumTestProject;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SeleniumProjectBck {

    //public static File path = new File("/Users/apoorvamishra/Documents/SeleniumTest/Screenshots");
    ProjectConstants constants = new ProjectConstants();

    //-------------------Screenshots Function--------------------------
    public static void takeScreenShot(WebDriver webdriver, String snapshotPath) throws Exception {
        File scrFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(scrFile, new File(snapshotPath));
    }

    //-------------------Scenario 4 : Bookstore--------------------------
    @Test(priority = 2)
    public void testScenario4() throws Exception {
        File path = new File("/Users/apoorvamishra/Documents/SeleniumTest/Screenshots/Scenario_4/Scenario_4");
        System.setProperty("webdriver.chrome.driver", constants.DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //open website
        driver.get(constants.NU_BOOKSTORE_URL);

        driver.manage().window().maximize(); // maximizing browser window
        String actualTitle = "Apparel, Gifts & Textbooks | The Northeastern University Bookstore";
        Assert.assertEquals(driver.getTitle(), actualTitle);

        Thread.sleep(1000);
        takeScreenShot(driver, path + "-Welcome.png");

        driver.findElement(By.cssSelector("#login_user > div > span.guestUser > a")).click();
        Thread.sleep(2000);
        //login process
        takeScreenShot(driver, path + "Before Credentials-Scenario4.png");
        driver.findElement(By.cssSelector("#logonId")).sendKeys(constants.USER_NAME);
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,1000)");

        driver.findElement(By.cssSelector("#logonPassword")).sendKeys(constants.PASSWORD);
        Thread.sleep(2000);

        takeScreenShot(driver, path + "After Credentials-Scenario4.png");
        //driver.findElement(By.xpath("//input[@id='login']")).click();
        driver.findElement(By.name("login")).click();

        takeScreenShot(driver, path + "Search category selection-Scenario4.png");
        Thread.sleep(2000);

        //searching an item
        driver.findElement(By.cssSelector("#topNavSearch")).sendKeys(constants.FIND_OBJECT);
        Thread.sleep(2000);

        takeScreenShot(driver, path + "After Search category selection-Scenario4.png");
        driver.findElement(By.cssSelector("#searchbutton")).click();
        Thread.sleep(2000);

        //item selection
        js.executeScript("window.scrollBy(0,1000)");
        takeScreenShot(driver, path + "-item_selection.png");
        driver.findElement(By.cssSelector("#foo1 > li:nth-child(5) > h3 > a")).click();
        Thread.sleep(2000);

        //adding it to cart
        js.executeScript("window.scrollBy(0,200)");
        takeScreenShot(driver, path + "-AddToCart-Scenario4.png");
        driver.findElement(By.cssSelector("#addToCartId")).click();
        Thread.sleep(1000);

        //view cart
        takeScreenShot(driver, path + "-ViewCart-Scenario4.png");
        driver.findElement(By.cssSelector("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ajaxDialogProductAdd.ui-dialog-buttons.ui-draggable.modal-border > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button.view-cart-class.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-only")).click();
        js.executeScript("window.scrollBy(0,500)");
        takeScreenShot(driver, path + "-ViewCartItemConfirmation-Scenario4.png");

        Thread.sleep(2000);
        driver.quit();
    }

    //-------------------Scenario 3 : Course Registration--------------------------
    @Test(priority = 1)
    public void testScenario3() throws Exception {
        File filePath = new File("/Users/apoorvamishra/Documents/SeleniumTest/Screenshots/Scenario_3/Scenario_3");
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get(constants.MYNEU_PORTAL_URL);
        driver.manage().window().maximize();
        // Login
        driver.findElement(By.linkText("Go To Login")).click();
        takeScreenShot(driver, filePath + "-Login-Scenario3.png");
        Thread.sleep(1000);

        // User name & password
        driver.findElement(By.id("username")).sendKeys(constants.MYNEU_USER_NAME);
        driver.findElement(By.id("password")).sendKeys(constants.MYNEU_PASSWORD);
        takeScreenShot(driver, filePath + "-Username&Password-Scenario3.png");
        Thread.sleep(1000);
        driver.findElement(By.name("_eventId_proceed")).click();

        Thread.sleep(5000);

        // Duo Authentication
        driver.switchTo().frame("duo_iframe");
        driver.findElement(By.xpath("//*[@id=\"auth_methods\"]/fieldset/div[1]/button")).click();
        takeScreenShot(driver, filePath + "-Duo-Scenario3.png");
        Thread.sleep(5000);

        // Open Browse Courses page
        driver.findElement(By.xpath("//*[@id=\"layout_2\"]")).click();
        takeScreenShot(driver, filePath + "-Service&Link-Scenario3.png");
        driver.findElement(By.xpath("//*[@id=\"portlet_com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_VGn3ZknJvwnV\"]/div/div/div/div[5]/div[2]/div/div[1]/div[2]/a")).click();

        takeScreenShot(driver, filePath + "-courseRegistration-Scenario3.png");
        Thread.sleep(10000);

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
        takeScreenShot(driver, filePath + "-courseReg-Scenario3.png");
        // Click on browse class
        driver.findElement(By.xpath("//*[@id=\"classSearchLink\"]/span")).click();
        //driver.findElement(By.xpath("//a[contains(@href, 'mode=search')]")).click();
        takeScreenShot(driver, filePath + "-browse-Scenario3.png");

        // select term
        Thread.sleep(1000);

        driver.findElement(By.id("select2-chosen-1")).click();
        takeScreenShot(driver, filePath + "-before selectTerm-Scenario3.png");
        Thread.sleep(1000);
        driver.findElement(By.id("s2id_autogen1_search")).sendKeys(constants.NU_COURSE_TERM);
        Thread.sleep(1000);
        driver.findElement(By.id("202150")).click();
        takeScreenShot(driver, filePath + "-after selectTerm-Scenario3.png");
        driver.findElement(By.xpath("//*[@id=\"term-go\"]")).click();
        takeScreenShot(driver, filePath + "-searchPage-Scenario3.png");

        // add IS and CSYE
        driver.findElement(By.xpath("//*[@id=\"s2id_txt_subject\"]/ul")).click();
        driver.findElement(By.id("s2id_autogen1")).sendKeys(constants.NU_COURSE_IS);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#INFO")).click();

        //driver.findElement(By.id("INFO")).click();
        takeScreenShot(driver, filePath + "-addSubject1.png");
        driver.findElement(By.id("s2id_autogen1")).sendKeys(constants.NU_COURSE_CSYE);
        Thread.sleep(1000);
        driver.findElement(By.id("CSYE")).click();
        takeScreenShot(driver, filePath + "-addSubject2.png");
        driver.findElement(By.id("search-go")).click();
        Thread.sleep(1000);
        takeScreenShot(driver, filePath + "-browseClass.png");
        Thread.sleep(3000);
        driver.quit();
    }

    //        //-------------------Scenario 5 : Create a plan for course Registration--------------------------
    @Test(priority = 1)
    public void testScenario5() throws Exception {
        File filePath = new File("/Users/apoorvamishra/Documents/SeleniumTest/Screenshots/Scenario_5/Scenario_5");
        System.setProperty("webdriver.chrome.driver", constants.DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Open website
        driver.get("https://my.northeastern.edu");
        driver.manage().window().maximize();
        //take screen shots
        takeScreenShot(driver, filePath + "-Welcome-Scenario5.png");

        // Login
        driver.findElement(By.linkText("Go To Login")).click();
        takeScreenShot(driver, filePath + "-Login-Scenario5.png");
        Thread.sleep(1000);

        // User name & password
        driver.findElement(By.id("username")).sendKeys(constants.MYNEU_USER_NAME);
        driver.findElement(By.id("password")).sendKeys(constants.MYNEU_PASSWORD);
        takeScreenShot(driver, filePath + "-Username&Password-Scenario5.png");
        Thread.sleep(1000);

        driver.findElement(By.name("_eventId_proceed")).click();

        Thread.sleep(10000);
        //driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/label/input")).click();
        // Duo Authentication
        takeScreenShot(driver, filePath + "-Duo-Scenario5.png");
        Thread.sleep(5000);

        // Open course registration page
        driver.findElement(By.xpath("//*[@id=\"layout_2\"]")).click();
        takeScreenShot(driver, filePath + "-Service&Link-Scenario5.png");
        driver.findElement(By.xpath("//*[@id=\"portlet_com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_VGn3ZknJvwnV\"]/div/div/div/div[5]/div[2]/div/div[1]/div[2]/a")).click();
        takeScreenShot(driver, filePath + "-courseRegistration-Scenario5.png");
        Thread.sleep(2000);

        // Switch Windowhandle
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
        takeScreenShot(driver, filePath + "-Registration-Scenario5.png");
        Thread.sleep(2000);

        // Plan Ahead
        driver.findElement(By.xpath("//*[@id=\"worksheetPlanner\"]")).click();

        takeScreenShot(driver, filePath + "-PlanAhead-Scenario5.png");
        Thread.sleep(2000);

        // Select Term
        driver.findElement(By.xpath("//*[@id=\"s2id_txt_term\"]")).click();
        takeScreenShot(driver, filePath + "-SelectTerm-Scenario5.png");
        Thread.sleep(1000);

        driver.findElement(By.id("s2id_autogen1_search")).sendKeys(constants.NU_COURSE_TERM);
        Thread.sleep(1000);
        driver.findElement(By.id("202150")).click();
        //driver.findElement(By.xpath("//*[@id=\"select2-results-1\"]/li[3]")).click();
        takeScreenShot(driver, filePath + "-Summer2021Semester-Scenario5.png");
        Thread.sleep(1000);
        driver.findElement(By.id("term-go")).click();
        Thread.sleep(1000);

        // Create Plan
        driver.findElement(By.id("createPlan")).click();
        takeScreenShot(driver, filePath + "-CreatePlan-Scenario5.png");
        Thread.sleep(2000);

        // Add courses
        driver.findElement(By.xpath("//*[@id=\"txt_courseNumber\"]")).sendKeys(constants.NU_COURSE_NUMBER_1);
        takeScreenShot(driver, filePath + "-CourseId-Scenario5.png");
        driver.findElement(By.id("search-go")).click();
        takeScreenShot(driver, filePath + "-SearchClass-Scenario5.png");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[2]/td[6]/div/button[2]")).click();
        takeScreenShot(driver, filePath + "-AddClass-Scenario5.png");
        Thread.sleep(2000);

        // Save plan
        driver.findElement(By.id("saveButton")).click();
        driver.findElement(By.id("txt_planDesc")).sendKeys(constants.NU_PLAN_NAME);
        takeScreenShot(driver, filePath + "-SavePlan-Scenario5.png");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.course-details-dialog.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(2)")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#breadcrumbHeader > a:nth-child(7)")).click();

        takeScreenShot(driver, filePath + "-ViewSavedPlan-Scenario5.png");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#user")).click();
        driver.findElement(By.cssSelector("#signOut > span")).click();
        //back to parent window
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(1000);
        driver.quit();
    }
}

