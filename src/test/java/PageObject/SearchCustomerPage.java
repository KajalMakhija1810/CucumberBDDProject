package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage {
    WebDriver ldriver;

    public SearchCustomerPage(WebDriver rDriver){
        ldriver = rDriver;
        PageFactory.initElements(rDriver,this);
    }

    @FindBy(id="SearchEmail")
    WebElement emailAdd;

    @FindBy(id="search-customers")
    WebElement searchBtn;

    @FindBy(id="SearchFirstName")
    WebElement firstName;

    @FindBy(id="SearchLastName")
    WebElement lastName;

    @FindBy(xpath="//table[@id='customers-grid']//tbody/tr")
    List<WebElement> tableRows;

    //action method to enter emailid
    public void enterEmailAdd(String email){
        emailAdd.sendKeys(email);
    }


    //Action method to click search button
    public void  clickOnSearchButton(){
        searchBtn.click();
    }

    public boolean searchCustomerByEmail(String email) {
        boolean found = false;

        //total no.of rows in grid
        int tt1Rows = tableRows.size();

        //For iterating all rows in grid
        for(int i=1;i<=tt1Rows;i++){
            System.out.println("Searching row :" +i);
            WebElement webElementEmail = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i  + "]/td[2]"));
            String actualEmailAdd=webElementEmail.getText();
            System.out.println(actualEmailAdd);

            if(actualEmailAdd.equals(email)){
                found = true;
            }
        }
        return found;
    }

    //Action method to enter First Name
    public void enterFirstName(String firstNameText){
        firstName.sendKeys(firstNameText);
    }

    //Action method to enter Last Name
    public void enterLastName(String lastNameText){
        lastName.sendKeys(lastNameText);
    }

    //SEarch Customer by Name
    public boolean searchCustomerByName(String name) {
        boolean found = false;

        //total no.of rows in grid
        int tt1Rows = tableRows.size();

        //For iterating all rows in grid
        for(int i=1;i<=tt1Rows;i++){
            System.out.println("Searching row :" +i);
            WebElement webElementName = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i  + "]/td[3]"));
            String actualName=webElementName.getText();
            System.out.println(actualName);

            if(actualName.equals(name)){
                found = true;
                break;
            }
        }
        return found;
    }
}
