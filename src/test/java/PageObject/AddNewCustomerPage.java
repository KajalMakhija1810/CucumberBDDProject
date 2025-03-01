package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddNewCustomerPage {

    WebDriver ldriver;

    public AddNewCustomerPage(WebDriver rDriver){
        ldriver =rDriver;
        PageFactory.initElements(rDriver,this);
    }

    //WebElements  on Web Page

    @FindBy(xpath="//a[@href='#']//p[contains(text(),'Customers')]")
    WebElement lnkCustomers_menu;

    @FindBy(xpath="//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]")
    WebElement lnkCustomers_menuitem;

    @FindBy(xpath="//a[normalize-space()='Add new']")
    WebElement btnAddNew;

    @FindBy(id="Email")
    WebElement txtEmail;

    @FindBy(id="Password")
    WebElement txtPassword;

    @FindBy(id="FirstName")
    WebElement txtFirstName;

    @FindBy(id="LastName")
    WebElement txtLastName;

    @FindBy(id = "Gender_Female")
    WebElement FeMaleGender;

    @FindBy(id="Gender_Male")
    WebElement MaleGender;

    @FindBy(id="Company")
    WebElement txtCompanyname;

    @FindBy(id="VendorId")
    WebElement dropdownVendorMgr;

    @FindBy(id="AdminComment")
    WebElement txtAdminContent;

    @FindBy(xpath="//button[@name='save']")
    WebElement btnSave;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    WebElement btnAddnew;


    //Actions to be Performed

    public String getPageTitle(){

        return ldriver.getTitle();
    }
    public void  clickOnCustomerMenu(){
        lnkCustomers_menu.click();
    }
    public void clickOnCustomerMenuItem(){
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(30));
        //wait until menu is visible nd Clickable
        wait.until(ExpectedConditions.visibilityOf(lnkCustomers_menu));
        wait.until(ExpectedConditions.elementToBeClickable(lnkCustomers_menu));
        lnkCustomers_menu.click();
        //Wait until menu is visible
        wait.until(ExpectedConditions.visibilityOf(lnkCustomers_menuitem));
        wait.until(ExpectedConditions.elementToBeClickable(lnkCustomers_menuitem));
        lnkCustomers_menuitem.click();
    }

    public void clickOnAddnew(){
        btnAddNew.click();
    }
    public void enterEmail(String email){
        txtEmail.sendKeys(email);
    }
    public void enterPassword(String password){
        txtPassword.sendKeys(password);
    }
    public void enterFirstName(String firstName){
         txtFirstName.sendKeys(firstName);
    }
    public void enterLastName(String lastName){
        txtLastName.sendKeys(lastName);
    }
    public void entercompanyName(String coName){
        txtCompanyname.sendKeys(coName);
    }
    public void enterAdminContent(String content)
    {
        txtAdminContent.sendKeys(content);
    }
    public void enterManagerOfVendor(String value)
    {
        Select drp=new Select(dropdownVendorMgr);
        drp.selectByVisibleText(value);
    }

    public void enterGender(String gender)
    {
        if(gender.equals("Male"))
        {
            MaleGender.click();
        }
        else if(gender.equals("Female"))
        {
            FeMaleGender.click();
        }
        else//default set Male gender
        {
            MaleGender.click();
        }

    }

    public void clickOnSave()
    {
        btnSave.click();
    }


}
