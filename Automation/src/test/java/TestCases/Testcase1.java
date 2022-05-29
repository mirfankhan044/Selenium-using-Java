package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Testcase1 extends BaseClass {


    @Test(priority = 1)
    public void login(){
        String username = "AM Customer";
        String password = "sqa_123456";

        WebElement uname = driver.findElement(By.id("username"));
        uname.sendKeys(username);
        WebElement psswd = driver.findElement(By.name("password"));
        psswd.sendKeys(password);
        driver.findElement(By.name("login")).click();
        login();
    }
    //@Test(priority = 2)
   // public void logout()
    {

       // driver.findElement(By.xpath("//a[normalize-space()='Logout']\n")).click();

    }
}
