package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DriverActions {

    private static DriverActions driverActions;

    public DriverActions(){

    }

    public static DriverActions getInstance(){

        if (driverActions==null)
            driverActions=new DriverActions();
        return driverActions;

    }

    private WebElement findElement(By locator){

        return DriverHelper.getInstance().findElement(locator);

    }


    public void click(By locator){

        findElement(locator).click();

    }

    public void sendKeys (By locator,String value){

        findElement(locator).sendKeys(value);

    }

    public String getText(By locator){

        return findElement(locator).getText();

    }

}