package Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverHelper {

    private static DriverHelper driverHelperClass;
    private static DriverHelper driverHelper;
    private WebDriver driver;

    public DriverHelper(){

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    }

    public static WebDriver getInstance(){

        if (driverHelper == null){
            driverHelper = new DriverHelper();
        }
        return driverHelper.driver;

    }

}