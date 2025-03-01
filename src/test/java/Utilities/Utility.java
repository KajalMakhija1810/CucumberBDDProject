package Utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class Utility {
    public static void handleAlert(WebDriver driver){
        try{
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
        catch (NoAlertPresentException e){
            System.out.println("No Alert present on page");

        }
    }
}
