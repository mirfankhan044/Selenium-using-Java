package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class BaseClass {

    public WebDriver driver;
    String url = "http://demo1.folio3.com:9200/my-account/";
    String driverpath = "C:\\Users\\Lenovo\\Documents\\Downloads\\Automation\\drivers\\chromedriver.exe";


    @BeforeTest
    public void open_browser() {

        /*********************** Specify Browser Properties ****************************/
        System.setProperty("webdriver.chrome.driver", driverpath);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


    }

    @AfterTest
    public void close_browser() {
        /*report.endTest(test);
        report.flush( );*/
//        driver.close( );
//        driver.quit();
    }
}

