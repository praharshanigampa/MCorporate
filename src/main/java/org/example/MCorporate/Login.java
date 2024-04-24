/*
package org.example.MCorporate;


import org.example.generics.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login extends BaseTest {

    @Test(priority = 0)
    public void loginWithoutMailId() throws InterruptedException {

        driver.findElement(By.xpath("//a[text()='Corporate Login']")).click();
        driver.findElement(By.xpath("//input[@id='termsConditions']")).click();
        driver.findElement(By.xpath("//button[text()='Continue']")).click();
        String actual_error = driver.findElement(By.xpath("//label[@id='emailID-error']")).getText();
        String expected_error = "Please provide email";
        Assert.assertEquals(actual_error,expected_error);
        Thread.sleep(2000);

    }
    @Test(priority = 1)
    public void loginWithoutTandC() throws InterruptedException {

        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Corporate Login']")).click();
        driver.findElement(By.xpath("//input[@id='emailID']")).sendKeys("developer17@yopmail.com");
        driver.findElement(By.xpath("//button[text()='Continue']")).click();
        String actual_error = driver.findElement(By.xpath("//label[@id='termsConditions-error']")).getText();
        String expected_error = "Please accept terms and conditions";
        Assert.assertEquals(actual_error,expected_error);

    }
    @Test(priority = 2)
    public void loginWithInvalidMailFormat() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Corporate Login']")).click();
        driver.findElement(By.xpath("//input[@id='emailID']")).sendKeys("yopmail.com");
        driver.findElement(By.xpath("//input[@id='termsConditions']")).click();
        driver.findElement(By.xpath("//button[text()='Continue']")).click();
        String actual_error = driver.findElement(By.xpath("//label[@id='emailID-error']")).getText();
        String expected_error = "Please enter valid email";
        Assert.assertEquals(actual_error,expected_error);
        Thread.sleep(2000);
    }
    @Test(priority = 3)
    public void loginWithInvalidMailFormatAndNoTandC() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Corporate Login']")).click();
        driver.findElement(By.xpath("//input[@id='emailID']")).sendKeys("yopmail.com");
        driver.findElement(By.xpath("//button[text()='Continue']")).click();
        String actual_error1 = driver.findElement(By.xpath("//label[@id='emailID-error']")).getText();
        String actual_error2 = driver.findElement(By.xpath("//label[@id='termsConditions-error']")).getText();
        String expected_error1= "Please enter valid email";
        String expected_error2="Please accept terms and conditions";
        Assert.assertEquals(actual_error1,expected_error1);
        Assert.assertEquals(actual_error2,expected_error2);
        Thread.sleep(2000);

    }
    @Test(priority = 4)
    public void loginWithNonRegisteredUser() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Corporate Login']")).click();
        driver.findElement(By.xpath("//input[@id='emailID']")).sendKeys("developer28@yopmail.com");
        driver.findElement(By.xpath("//input[@id='termsConditions']")).click();
        driver.findElement(By.xpath("//button[text()='Continue']")).click();
        String s=driver.findElement(By.xpath("//div[@class='toast-message']")).getText();
        System.out.println(s);
        Thread.sleep(2000);
    }
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

//need to handle when multiple login attempts fail
*/

package org.example.MCorporate;


import org.example.generics.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login extends BaseTest {
    @Test
    public void login() throws InterruptedException {
       // WebDriver driver = new ChromeDriver();
        options = new ChromeOptions();
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.navigate().to("https://mypursu.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Corporate Login']")).click();
        driver.findElement(By.xpath("//input[@id='emailID']")).sendKeys("developer50@yopmail.com");
        driver.findElement(By.xpath("//input[@id='termsConditions']")).click();
        driver.findElement(By.xpath("//button[text()='Continue']")).click();
        ChromeDriver driver1 = new ChromeDriver(options);
        driver1.navigate().to("https://yopmail.com/");
        driver1.findElement(By.xpath("//input[@placeholder='Enter your inbox here']")).sendKeys("developer50@yopmail.com");
        Thread.sleep(25000);
        driver1.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();
        Thread.sleep(5000);
        WebElement iframe = driver1.findElement(By.xpath("//iframe[@id='ifmail']"));
        driver1.switchTo().frame(iframe);
        WebElement Path = driver1.findElement(By.xpath("//*[@id='mail']/div/table/tbody/tr[3]/td/table/tbody/tr/td/p[2]"));
        String OTP=Path.getText();
        System.out.println(OTP);
        String numericPart = OTP.replaceAll("\\D", "");
        numericPart = numericPart.substring(0, Math.min(numericPart.length(), 6));
        System.out.println(numericPart);
        //long number = Long.parseLong(numericPart);
        driver1.quit();
        //String numberAsString = Long.toString(number);
        for (int i = 0; i < numericPart.length(); i++) {
            char digitChar = numericPart.charAt(i);
            String digit = Character.toString(digitChar);
            System.out.println(digit);
            driver.findElement(By.xpath("//div[@id='inputs']/input[" + (i + 2) + "]")).sendKeys(digit);
        }
        driver.findElement(By.xpath("//button[text()='Verify']")).click();
        try {
            if (isAlertPresent()) {
                Thread.sleep(5000);
                Alert alert = driver.switchTo().alert();
                String s=driver.findElement(By.xpath("//div[@class='toast-message']")).getText();
            }
        } catch (UnhandledAlertException e) {
            Alert unexpectedAlert = driver.switchTo().alert();
            unexpectedAlert.accept();
        }
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@class='dropdown-toggle text-uppercase']")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        driver.quit();
    }
    @Test(priority = 0)
    public void loginWithoutMailId() throws InterruptedException {

        driver = new ChromeDriver(options);
        driver.navigate().to("https://mypursu.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Corporate Login']")).click();
        driver.findElement(By.xpath("//input[@id='termsConditions']")).click();
        driver.findElement(By.xpath("//button[text()='Continue']")).click();
        String actual_error = driver.findElement(By.xpath("//label[@id='emailID-error']")).getText();
        String expected_error = "Please provide email";
        Assert.assertEquals(actual_error,expected_error);
        Thread.sleep(2000);
        driver.quit();
    }
    @Test(priority = 1)
    public void loginWithoutTandC() throws InterruptedException {

        options = new ChromeOptions();
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.navigate().to("https://mypursu.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Corporate Login']")).click();
        driver.findElement(By.xpath("//input[@id='emailID']")).sendKeys("developer17@yopmail.com");
        driver.findElement(By.xpath("//button[text()='Continue']")).click();
        String actual_error = driver.findElement(By.xpath("//label[@id='termsConditions-error']")).getText();
        String expected_error = "Please accept terms and conditions";
        Assert.assertEquals(actual_error,expected_error);
        System.out.println(actual_error);
    }
    @Test(priority = 2)
    public void loginWithInvalidMailFormat() throws InterruptedException {

        driver.navigate().to("https://mypursu.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Corporate Login']")).click();
        driver.findElement(By.xpath("//input[@id='emailID']")).sendKeys("yopmail.com");
        driver.findElement(By.xpath("//input[@id='termsConditions']")).click();
        driver.findElement(By.xpath("//button[text()='Continue']")).click();
        String actual_error = driver.findElement(By.xpath("//label[@id='emailID-error']")).getText();
        String expected_error = "Please enter valid email";
        Assert.assertEquals(actual_error,expected_error);
        System.out.println(actual_error);
        Thread.sleep(2000);
    }
    @Test(priority = 3)
    public void loginWithInvalidMailFormatAndNoTandC() throws InterruptedException {

        driver.navigate().to("https://mypursu.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Corporate Login']")).click();
        driver.findElement(By.xpath("//input[@id='emailID']")).sendKeys("yopmail.com");
        driver.findElement(By.xpath("//button[text()='Continue']")).click();
        String actual_error1 = driver.findElement(By.xpath("//label[@id='emailID-error']")).getText();
        String actual_error2 = driver.findElement(By.xpath("//label[@id='termsConditions-error']")).getText();
        String expected_error1= "Please enter valid email";
        String expected_error2="Please accept terms and conditions";
        Assert.assertEquals(actual_error1,expected_error1);
        Assert.assertEquals(actual_error2,expected_error2);
        System.out.println(actual_error1);
        System.out.println(actual_error2);
        Thread.sleep(2000);
    }
    @Test(priority = 4)
    public void loginWithNonRegisteredUser() throws InterruptedException {

        driver.navigate().to("https://mypursu.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Corporate Login']")).click();
        driver.findElement(By.xpath("//input[@id='emailID']")).sendKeys("developer1000@yopmail.com");
        driver.findElement(By.xpath("//input[@id='termsConditions']")).click();
        driver.findElement(By.xpath("//button[text()='Continue']")).click();
        String s=driver.findElement(By.xpath("//div[@class='toast-message']")).getText();
        System.out.println(s);
        Thread.sleep(2000);
        driver.quit();
    }
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

//need to handle when multiple login attempts fail
