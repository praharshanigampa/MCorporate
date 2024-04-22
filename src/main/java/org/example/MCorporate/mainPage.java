package org.example.MCorporate;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.LinkedHashSet;
import java.util.Set;

public class mainPage {
    WebDriver driver;
    ChromeDriver driver1;
    @BeforeClass
    public void launchBrowser() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://mypursu.com/");
        Thread.sleep(3000);
    }
    @Test(priority = 0)
    public void mainPage() throws InterruptedException {
        String mainTabHandle = driver.getWindowHandle();
        driver.findElement(By.xpath("//div[contains(@class,'pp-box-')]/a")).click();
        Thread.sleep(3000);
        Set<String> linkedHashSet = new LinkedHashSet<>();
        for (int i = 1; i <= 3; i++) {
            WebElement element = driver.findElement(By.xpath("(//div[contains(@class,'row')])[2]/div[" + i + "]/div/span[1]"));
            linkedHashSet.add(element.getText());
        }
        for (String element : linkedHashSet) {
            System.out.println(element);
        }
        Thread.sleep(5000);
        driver.manage().window().fullscreen();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[1]/div[2]/a[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[1]/div[2]/a[2]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[1]/div[2]/a[3]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[1]/div[2]/a[4]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[1]/div[2]/a[5]")).click();
        Thread.sleep(3000);
        driver.switchTo().window(mainTabHandle);
        Thread.sleep(3000);
    }
    @Test(priority = 1)
    public void install() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Download mypursu ']")).click();
        Thread.sleep(3000);
        WebElement link1 = driver.findElement(By.xpath("//section[@id='ppSuccessSection']/div/div/div[2]/a[1]"));
        Actions action = new Actions(driver);
        action.moveToElement(link1).click().perform();
        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(3000);
        WebElement link2 =driver.findElement(By.xpath("//section[@id='ppSuccessSection']/div/div/div[2]/a[2]"));
        action.moveToElement(link2).click().perform();
        driver.navigate().back();
    }

    @Test(priority = 2)
    public void importalntLinks() throws InterruptedException{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[2]/ul/li[2]/a")).click();
        Thread.sleep(3000);
        driver.navigate().back();
        driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[2]/ul/li[3]/a")).click();
        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(3000);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[2]/ul/li[4]/a")).click();
        Thread.sleep(3000);
        driver.navigate().back();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[2]/ul/li[1]/a")).click();
    }
    @Test(priority = 3)
    public void quickLinks() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[3]/ul/li[1]/a")).click();
        Thread.sleep(3000);
        driver.navigate().back();
        driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[3]/ul/li[2]/a")).click();
        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[3]/ul/li[3]/a")).click();
        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[3]/ul/li[4]/a")).click();
        Thread.sleep(3000);
    }
    @Test(priority = 4)
    public void contactUs() throws InterruptedException {
        String mainTabHandle = driver.getWindowHandle();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[4]/ul/li[1]/a")).click();
        Thread.sleep(3000);
        driver.switchTo().window(mainTabHandle);
            /*driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[4]/ul/li[2]/a")).click();
            Thread.sleep(3000);
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            alert.dismiss();*/
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@class='pp-footer-content'])[4]/ul/li[3]/a")).click();
        Thread.sleep(3000);
        driver.quit();
    }

}

