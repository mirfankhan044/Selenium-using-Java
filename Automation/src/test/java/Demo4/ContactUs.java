package Demo4;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Random;

public class ContactUs {
    WebDriver driver;
    Random randomEmail = new Random();
    int randomInt = randomEmail.nextInt(10000);

    @BeforeTest
    public void setup()
    {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Lenovo\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("http://automationpractice.com/index.php");
    }
    @Test
    public void Contact() throws AWTException {
        WebElement ContactUs = driver.findElement(By.xpath("//div[@id='contact-link']"));
        ContactUs.click();

        /**************Verifying the heading 'CUSTOMER SERVICE - CONTACT US'*****************/
        WebElement headingText=driver.findElement(By.xpath("//h1[@class='page-heading bottom-indent']"));
        String actualHeading = "CUSTOMER SERVICE - CONTACT US";
        String expectedHeading=headingText.getText();
        Assert.assertEquals(actualHeading,expectedHeading);
        System.out.println("The heading is:"+expectedHeading);

        Select SubHeading = new Select(driver.findElement(By.xpath("//select[@id='id_contact']")));
        SubHeading.selectByIndex(1);

        /************************Generate Random Email**********************/
        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        email.sendKeys("irfan" + randomInt + "@gmail.com");

        /************************Generate Random Order Reference**********************/
        WebElement orderRef = driver.findElement(By.xpath("//input[@id='id_order']"));
        orderRef.sendKeys("" + randomInt + "");

        /************************Upload File using robot class**********************/
        //WebElement fileUpload = driver.findElement(By.xpath("//input[@id='fileUpload']"));
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("document.getElementById('fileUpload').click()");
        Robot rb = new Robot();
        rb.delay(1000);

        //Copy the File to Clipboard
        StringSelection fileUpload = new StringSelection("\"C:\\Users\\Lenovo\\Desktop\\Smily.jpg\"");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fileUpload,null);

        //CTRL+V
        rb.keyPress(KeyEvent.VK_CONTROL);  //Press Control Key
        rb.keyPress(KeyEvent.VK_V); //Press 'V' Key
        rb.keyRelease(KeyEvent.VK_CONTROL);  //Release Control Key
        rb.keyRelease(KeyEvent.VK_V); //Release 'V' Key
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

        /************************Generate Random Number for Message*********************/
        WebElement Message = driver.findElement(By.xpath("//textarea[@id='message']"));
        Message.sendKeys("This is a test message" + randomInt + "");

        WebElement Send = driver.findElement(By.xpath("//button[@id='submitMessage']"));
        Send.click();

        /*******************Verifying the Message in green banner********************/
        WebElement msg=driver.findElement(By.xpath("//p[@class='alert alert-success']"));
        String actualMsg = "Your message has been successfully sent to our team.";
        String expectedMsg=msg.getText();
        Assert.assertEquals(actualMsg,expectedMsg);
        System.out.println("The Message is:"+expectedMsg);
    }

    @AfterTest
    public void terminateBrowser()
    {
        //driver.close();
    }
}
