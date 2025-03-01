package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginPage {
    WebDriver ldriver;
    //here ldriver is local driver

    public LoginPage(WebDriver rDriver)
    {
        ldriver = rDriver;
        PageFactory.initElements(rDriver,this);
    }

    @FindBy(id = "Email")
    WebElement email;

    @FindBy(id="Password")
    WebElement password;


    @FindBy(xpath="//button[normalize-space()='Log in']")
    WebElement LoginBtn;

    @FindBy(linkText = "Logout")
    WebElement logout;



    //Creating Methods For Actions
    public void enterEmail(String emailAdd){
        email.clear();
        email.sendKeys(emailAdd);
    }

    public void enterPassword(String pwd){
        password.clear();
        password.sendKeys(pwd);
    }
    public void clickOnLoginButton(){
        LoginBtn.click();
    }
    public void  clickOnLogoutButton(){
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(logout));
        logout.click();
    }
    public void solveCaptcha() {
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
        try {
            // Switch to the reCAPTCHA iframe
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='reCAPTCHA']")));
            // Find and click the reCAPTCHA checkbox
            WebElement captchaCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='recaptcha-checkbox-border']")));
            captchaCheckbox.click();
            // Switch back to the default content
            ldriver.switchTo().defaultContent();
            Thread.sleep(5000); // Wait for CAPTCHA to process
        } catch (Exception e) {
            System.out.println("CAPTCHA not present or already solved.");
        }
    }

}






