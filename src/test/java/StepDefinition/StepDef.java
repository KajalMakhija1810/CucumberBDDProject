package StepDefinition;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utilities.ReadConfig;
import Utilities.Utility;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jshell.execution.Util;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//Child Class of BaseClass
public class StepDef extends BaseClass{

    @Before
    public void setUp(){
       readConfig = new ReadConfig();
        log=LogManager.getLogger(StepDef.class);
        System.out.println("SetUp Method Executed");

        String browser = readConfig.getBrowser();
        //Launch Browser
        switch(browser.toLowerCase()){

            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                driver = null;
                break;
        }
        log.info("SetUp Executed....");
    }

    @Given("User Launchs  browser")
    public void user_launchs_browser() {
        loginPg = new LoginPage(driver);
        addCustomerPg = new AddNewCustomerPage(driver);
        searchCustomerPage= new SearchCustomerPage(driver);

        log.info("Chrome Browser Launched");
    }

    @When("User opens URL {string}")
    public void user_opens_url(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        log.info("Url Opened");
    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String emailadd, String password) {
        loginPg.enterEmail(emailadd);
        loginPg.enterPassword(password);
        log.info("Email Address and Pswd entered");
    }

    @When("Click on Login")
    public void click_on_login() {
        loginPg.solveCaptcha();
        loginPg.clickOnLoginButton();
        log.info("Login Button Clicked");
    }

    /// //////////////// Login Page Features ///////////////////////
    @Then("Page Title should be {string}")
    public void page_title_should_be(String expectedTitle) {

        Utility.handleAlert(driver);
        String actualTitle = driver.getTitle();
        //Assert.assertEquals(actualTitle, expectedTitle);
        if(actualTitle.equals(expectedTitle)){
            log.warn("Test passed: Login feature : Page title matched ");
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
            log.warn("Test Failed :Login Feature-page title not matched");

        }
    }

    @When("User click on Log out link")
    public void user_click_on_log_out_link() {
        loginPg.clickOnLogoutButton();
        log.info("User clicked on logout link");
    }



    /// ////////////////// Add New Customer /////////////////////////

    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
        String actualTitle = addCustomerPg.getPageTitle();
        String expectedTitle = "Dashboard / nopCommerce administration";

        if(actualTitle.equals(expectedTitle)){
            log.info("User Can View Dashboard-Test passed");
            Assert.assertTrue(true);
        }
        else{
            Assert.assertTrue(false);
            log.warn("user can view dashboard test failed");
        }
    }

    @When("User click on customer Menu")
    public void user_click_on_customer_menu() {
        addCustomerPg.clickOnCustomerMenu();
        log.info("Customer Menu Clicked");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @When("click on customer Menu Item")
    public void click_on_customer_menu_item() {
        addCustomerPg.clickOnCustomerMenuItem();
        log.info("Customer Menu-Item Clicked");
    }

    @When("click on Add new button")
    public void click_on_add_new_button() {
        addCustomerPg.clickOnAddnew();
        log.info("Clicked on Add New Button");

    }

    @Then("user can view Add customer page")
    public void user_can_view_add_customer_page() {
        String actualTitle = addCustomerPg.getPageTitle();
        String expectedTitle = "Add a new customer / nopCommerce administration";

        if(actualTitle.equals(expectedTitle)){
            log.info("User can view Add customer page-Passed");
            Assert.assertTrue(true);
        }
        else{
            log.info("User can view Add new Customer page-Failed");
            Assert.assertTrue(false);
        }

    }

    @When("User enters customer info")
    public void user_enters_customer_info() {

        //addCustomerPg.enterEmail("Test78@gmail.com");
        addCustomerPg.enterEmail(generateEmailId() + "@gmail.com");
        addCustomerPg.enterPassword("test1");
        addCustomerPg .enterFirstName("K");
        addCustomerPg.enterLastName("M");
        addCustomerPg.enterGender("Female");
        addCustomerPg.entercompanyName("Amazon");
        addCustomerPg.enterAdminContent("Admin content");
        addCustomerPg.enterManagerOfVendor("Vendor 1");

        log.info("Customer information entered");

    }

    @When("click on Save button")
    public void click_on_save_button() {
        addCustomerPg.clickOnSave();
        log.info("clicked on Save Button");

    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String expectedConfirmationMsg) {
        String bodyTagText = driver.findElement(By.tagName("Body")).getText();
       //Fetching Confirmation from body tag
        if(bodyTagText.contains(expectedConfirmationMsg)) {
            Assert.assertTrue(true);
            log.info("User can view confirmation message-Passed");
        }
        else{
            Assert.assertTrue(false);
            log.warn("User can view confirmation message-Failed");

        }

    }
    /// ////////// Search Customer ////////////////////
    @When("Enter customer Email")
    public void enter_customer_email() {
        searchCustomerPage.enterEmailAdd("victoria_victoria@nopCommerce.com");
        log.info("Email address entered");
    }
    @When("Click on search button")
    public void click_on_search_button() {
        searchCustomerPage.clickOnSearchButton();
        log.info("Clicked on Search Button");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    @Then("User should find Email in the Search table")
    public void user_should_find_email_in_the_search_table() {
        String expectedEmail = "victoria_victoria@nopCommerce.com";
       // Assert.assertTrue(searchCustomerPage.searchCustomerByEmail(expectedEmail));

        if(searchCustomerPage.searchCustomerByEmail(expectedEmail)== true){
            Assert.assertTrue(true);
            log.info("User should find Email in the Search Table-passed");
        }
        else {
            Assert.assertTrue(false);
            log.warn("User should find Email in the Search Table-Failed");

        }


    }

    //Search Customer By Name
    @When("Enter customer FirstName")
    public void enter_customer_first_name() {
        searchCustomerPage.enterFirstName("Victoria");

    }
    @When("Enter customer LastName")
    public void enter_customer_last_name() {
        searchCustomerPage.enterLastName("Terces");

    }
    @Then("User should find Name in the Search table")
    public void user_should_find_name_in_the_search_table() {
       String expectedName = "Victoria Terces";
        //Assert.assertTrue(searchCustomerPage.searchCustomerByName(expectedName));
        if(searchCustomerPage.searchCustomerByName(expectedName) ==true)
        {
            Assert.assertTrue(true);
        }
        else
            Assert.assertTrue(false);

    }

    //@After
    /*public void tearDown(Scenario sc) {
        System.out.println("Tear Down Method Executed");
        if (sc.isFailed() == true) {
            String fileWithPath = "C://Users//10692639//IdeaProjects//DemoCucumber//Screenshot//failedSS.png";
            TakesScreenshot srcShot = ((TakesScreenshot) driver);
            File SrcFile = srcShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(fileWithPath);

            //Copy File at destination
            try {
                FileUtils.copyFile(SrcFile, DestFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

     */

        @AfterStep
        public void addScreenshot(Scenario sc){
            if(sc.isFailed()){
                final byte[] screenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                sc.attach(screenshot,"image/png",sc.getName());
            }

        }


    }



