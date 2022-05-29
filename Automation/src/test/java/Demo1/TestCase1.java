package Demo1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.Random;

public class TestCase1 {
    WebDriver driver;
    Random randomEmail = new Random();
    int randomInt = randomEmail.nextInt(1000);

    @BeforeTest
    public void setup()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Documents\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("http://automationpractice.com/index.php");
    }
        @Test
        public void Signup()
    {
            WebElement login = driver.findElement(By.xpath("//a[@class='login']"));
            login.click();

            WebElement email = driver.findElement(By.xpath("//input[@id='email_create']"));
            /************************Generate Random Email**********************/

            email.sendKeys("irfan" + randomInt + "@gmail.com");

            WebElement create_account = driver.findElement(By.xpath("//button[@id='SubmitCreate']"));
            create_account.click();

            /******************* Sign Up Form ********************************/
            /******************* YOUR PERSONAL INFORMATION ********************/
            WebElement title_radio_btn = driver.findElement(By.xpath("//input[@id='id_gender1']"));
            title_radio_btn.click();

            WebElement Customer_First_name = driver.findElement(By.xpath("//input[@id='customer_firstname']"));
            Customer_First_name.sendKeys("Irfan");

            WebElement Customer_Last_name = driver.findElement(By.xpath("//input[@id='customer_lastname']"));
            Customer_Last_name.sendKeys("Khan");

            WebElement Password = driver.findElement(By.xpath(" //input[@id='passwd']"));
            Password.sendKeys("Google@123");

            Select b_Date = new Select(driver.findElement(By.xpath("//select[@id='days']")));
            b_Date.selectByValue("2");


            Select b_Month = new Select(driver.findElement(By.xpath("//select[@id='months']")));
            b_Month.selectByValue("5");

            Select b_Year = new Select(driver.findElement(By.xpath("//select[@id='years']")));
            b_Year.selectByValue("1970");

            WebElement checkBox = driver.findElement(By.xpath("//input[@id='newsletter']"));
            checkBox.click();

            /******************* YOUR ADDRESS **********************/
            WebElement Company = driver.findElement(By.xpath("//input[@id='company']"));
            Company.sendKeys("Uforia");

            WebElement Address = driver.findElement(By.xpath("//input[@id='address1']"));
            Address.sendKeys("Islamabad");

            WebElement Address_2 = driver.findElement(By.xpath("//input[@id='address2']"));
            Address_2.sendKeys("Rawalpindi");

            WebElement City = driver.findElement(By.xpath("//input[@id='city']"));
            City.sendKeys("ISB");

            Select drpState = new Select(driver.findElement(By.xpath("//select[@id='id_state']")));
            drpState.selectByVisibleText("Hawaii");

            WebElement Post_code = driver.findElement(By.xpath("//input[@id='postcode']"));
            Post_code.sendKeys("00000");

            WebElement Add_info = driver.findElement(By.xpath("//textarea[@id='other']"));
            Add_info.sendKeys("None");

            WebElement Home_phone = driver.findElement(By.xpath("//input[@id='phone']"));
            Home_phone.sendKeys("123456789");

            WebElement Mob_phone = driver.findElement(By.xpath("//input[@id='phone_mobile']"));
            Mob_phone.sendKeys("123456");

            WebElement register=driver.findElement(By.xpath("//button[@id='submitAccount']"));
            register.click();

            WebElement Logout = driver.findElement(By.xpath("//a[@title='Log me out']"));
            Logout.click();
    }

          /**************************** Login Form ****************************/
    @Test(priority = 1)
    public void login()
    {
            WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
                          /****Assert True****/
            Assert.assertTrue(email.isDisplayed());
            System.out.println("Email field is present");
            email.sendKeys("irfankhansqa@gmail.com");

            WebElement password = driver.findElement(By.xpath("//input[@id='passwd']"));
            password.sendKeys("Google@123");

            WebElement login = driver.findElement(By.xpath("//button[@id='SubmitLogin']"));
            login.click();

                   /**************Verifying Username*****************/
            WebElement labeltxt=driver.findElement(By.xpath("//a[@class='account']"));
            String ExpectedText = "Irfan Khan";
            String actualtxt=labeltxt.getText();
            Assert.assertEquals(ExpectedText,actualtxt);
            System.out.println("User Name is:"+actualtxt);

                  /**************Verifying Searchbox is Enabled using Soft Assertion***************/
            WebElement searchBox=driver.findElement(By.xpath("//input[@id='search_query_top']"));
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(true, searchBox.isEnabled());
            System.out.println("Searchbox is enable");
            softAssert.assertAll();

    }
    @AfterTest
        public void terminateBrowser()
        {
        //driver.close();
        }
    }

