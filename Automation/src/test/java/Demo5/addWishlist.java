package Demo5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class addWishlist {
    WebDriver driver;
    String ProductTitle;
    int ProductQuantity;


    @BeforeTest
    public void setup()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Documents\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("http://automationpractice.com/index.php");
    }

         /**************************** Login Form ****************************/
    @Test
    public void login() {
        WebElement Signin = driver.findElement(By.xpath("//a[@class='login']"));
        Signin.click();

        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        email.sendKeys("irfankhansqa@gmail.com");

        WebElement password = driver.findElement(By.xpath("//input[@id='passwd']"));
        password.sendKeys("Google@123");

        WebElement login = driver.findElement(By.xpath("//button[@id='SubmitLogin']"));
        login.click();

        /**************Verifying Username*****************/
        WebElement userName=driver.findElement(By.xpath("//a[@class='account']"));
        String expectedUserName = "Irfan Khan";
        String actualUserName=userName.getText();
        Assert.assertEquals(expectedUserName,actualUserName);
        System.out.println("The User Name is:" + actualUserName);

        /**************** Search Product*****************/
        WebElement searchProduct = driver.findElement(By.xpath("//input[@id='search_query_top']"));
        searchProduct.sendKeys("Faded Short Sleeve"+ Keys.ENTER);

        /*WebElement Search = driver.findElement(By.xpath("//button[@class='btn btn-default button-search']"));
        Search.click();*/

        /**************** Verify Product with name*****************/
        WebElement productName=driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts'][normalize-space()='Faded Short Sleeve T-shirts']"));
        String expectedProductName = "Faded Short Sleeve T-shirts";
        String actualProductName = productName.getText();
        Assert.assertEquals(expectedProductName,actualProductName);
        System.out.println("Searched Product Name is:" + actualProductName);

        /****************Mouse hover and Verify Add to Wishlist link*****************/
        WebElement mouseHover = driver.findElement(By.xpath("//div[@class='right-block']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(mouseHover).perform();
        WebElement wishList = driver.findElement(By.xpath("//a[normalize-space()='Add to Wishlist']"));
        wishList.click();

        WebElement alertText=driver.findElement(By.xpath("//p[@class='fancybox-error']"));
        String expectedText = "Added to your wishlist.";
        String actualText=alertText.getText();
        Assert.assertEquals(expectedText,actualText);
        System.out.println("The Add to wishlist success Message is:" + actualText);
        WebElement close=driver.findElement(By.xpath("//a[@title='Close']"));
        close.click();

        /****************Verifying Page title and Heading of My Account**************/
        WebElement myAccount=driver.findElement(By.xpath("//a[@class='account']"));
        myAccount.click();
        String expectedTitle = "My account - My Store";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("The Account Page Title is:" + actualTitle);

        WebElement headingText=driver.findElement(By.xpath("//h1[@class='page-heading']"));
        String expectedHeading = "MY ACCOUNT";
        String actualHeading = headingText.getText();
        Assert.assertEquals(actualHeading,expectedHeading);
        System.out.println("The Account heading is:" + actualHeading);

        /****************Verifying Wishlist Page title and Heading**************/
        WebElement Wishlist=driver.findElement(By.xpath("//li[@class='lnk_wishlist']"));
        Wishlist.click();
        String wishlistExpectedTitle = "My Store";
        String wishlistActualTitle = driver.getTitle();
        Assert.assertEquals(wishlistActualTitle, wishlistExpectedTitle);
        System.out.println("The Wishlist Page Title is:" + wishlistActualTitle);

        WebElement wishlistHeading=driver.findElement(By.xpath("//h1[@class='page-heading']"));
        String wishlistExpectedHeading = "MY WISHLISTS";
        String wishlistActualHeading = wishlistHeading.getText();
        Assert.assertEquals(wishlistActualHeading,wishlistExpectedHeading);
        System.out.println("The Wishlist heading is:" + wishlistActualHeading);

        /****************Verifying Last Wishlist Item and Product Quantity**************/
        WebElement wishListItem=driver.findElement(By.xpath("//a[normalize-space()='My wishlist']"));
        wishListItem.click();

        WebElement productTitle=driver.findElement(By.xpath("//p[@id='s_title']"));
        String expectedProductTitle = "Faded Short Sleeve T-shirts";
        ProductTitle  = productTitle.getText();
        Assert.assertEquals(expectedProductTitle,ProductTitle);
        System.out.println("The Last Wishlist Item is:" + ProductTitle);

        WebElement productQuantity=driver.findElement(By.xpath("//input[@id='quantity_1_0']"));
        String actualQuantity = productQuantity.getAttribute("value");
        System.out.println("The Product Quantity is:"+ actualQuantity);

    }
    @AfterTest
    public void terminateBrowser()
    {
        //driver.close();
    }

}
