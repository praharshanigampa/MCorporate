
package org.example.MCorporate;


import org.example.generics.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Signup extends BaseTest {
    @Test
    public void Signup() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://mypursu.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Corporate Login']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//a[@class='text-decoration-none'])[2]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("developer32");
        driver.findElement(By.xpath("//input[@id='companyName']")).sendKeys("developer32");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("developer32@yopmail.com");
        driver.findElement(By.xpath("//input[@id='confirm_email']")).sendKeys("developer32@yopmail.com");
        driver.findElement(By.xpath("//input[@id='mob_no']")).sendKeys("9999900032");
        driver.findElement(By.xpath("//input[@id='termsConditions']")).click();
        driver.findElement(By.xpath("//input[@id='privacyPolicy']")).click();
        driver.findElement(By.xpath("(//button[text()='Continue'])[1]")).click();
        driver.findElement(By.xpath("//input[@id='companyAddress']")).sendKeys("786 XYZ");
        WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='country']"));
        Thread.sleep(3000);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("AFGHANISTAN");
        driver.findElement(By.xpath("//input[@id='state']")).sendKeys("Den");
        driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Mark");
        driver.findElement(By.xpath("//input[@id='zipcode']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@id='registration']")).sendKeys("U67919");
        Thread.sleep(3000);
        WebElement imageEdit = driver.findElement(By.xpath("//span[@class='material-symbols-outlined']"));
        imageEdit.click();
        Thread.sleep(1000);
        WebElement chooseFile = driver.findElement(By.xpath("//input[@name='image']"));
        chooseFile.sendKeys("/Users/praharshanigampa/Desktop/Black.png");
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[text()='Continue'])[2]")).click();
        ChromeDriver driver1 = new ChromeDriver();
        driver1.navigate().to("https://yopmail.com/");
        driver1.findElement(By.xpath("//input[@placeholder='Enter your inbox here']")).sendKeys("developer32");
        Thread.sleep(25000);
        driver1.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();
        Thread.sleep(5000);
        WebElement iframe = driver1.findElement(By.xpath("//iframe[@id='ifmail']"));
        driver1.switchTo().frame(iframe);
        WebElement Path = driver1.findElement(By.xpath("//*[@id='mail']/div/table/tbody/tr[3]/td/table/tbody/tr/td/p[2]"));
        String OTP=Path.getText();
        String numericPart = OTP.replaceAll("\\D", "");
        numericPart = numericPart.substring(0, Math.min(numericPart.length(), 6));
        long number = Long.parseLong(numericPart);
        driver1.quit();
        Thread.sleep(3000);
        String numberAsString = Long.toString(number);
        for (int i = 0; i < numberAsString.length(); i++) {
            char digitChar = numberAsString.charAt(i);
            String digit = Character.toString(digitChar);
            driver.findElement(By.xpath("//div[@id='inputs']/input[" + (i + 2) + "]")).sendKeys(digit);
        }
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[text()='Verify']")).click();
        Thread.sleep(3000);
        driver.quit();
    }
}
