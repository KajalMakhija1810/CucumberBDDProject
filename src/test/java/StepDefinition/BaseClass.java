package StepDefinition;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utilities.ReadConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.*;
import java.util.Properties;



//This Class acts parent class nd contains all common elemns
//This will be accessiable to all other classes
public class BaseClass {
    public  static WebDriver driver;
    public LoginPage loginPg;
    public AddNewCustomerPage addCustomerPg;
    public SearchCustomerPage searchCustomerPage;
    //public static final Logger log;
    public static Logger log;
    public ReadConfig readConfig;


    static {
        log = LogManager.getLogger(BaseClass.class);
    }

    //Creating Generic method for generating Random Email id
    public String generateEmailId(){
        return (RandomStringUtils.randomAlphabetic(5));

    }

}
