package org.example.MCorporate;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class afterLogin {
    WebDriver driver;
    ChromeDriver driver1;
    @BeforeClass
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().window().fullscreen();
        driver.navigate().to("https://mypursu.com/");
        // driver.manage().window().fullscreen();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Corporate Login']")).click();
        driver.findElement(By.xpath("//input[@id='emailID']")).sendKeys("tripti@yopmail.com");
        driver.findElement(By.xpath("//input[@id='termsConditions']")).click();
        driver.findElement(By.xpath("//button[text()='Continue']")).click();
        driver1 = new ChromeDriver();
        driver1.navigate().to("https://yopmail.com/");
        driver1.findElement(By.xpath("//input[@placeholder='Enter your inbox here']")).sendKeys("tripti@yopmail.com");
        Thread.sleep(25000);
        driver1.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();
        Thread.sleep(5000);
        WebElement iframe = driver1.findElement(By.xpath("//iframe[@id='ifmail']"));
        driver1.switchTo().frame(iframe);
        WebElement Path = driver1.findElement(By.xpath("//*[@id='mail']/div/table/tbody/tr[3]/td/table/tbody/tr/td/p[2]"));
        String OTP=Path.getText();
        String numericPart = OTP.replaceAll("\\D", "");
        numericPart = numericPart.substring(0, Math.min(numericPart.length(), 6));
        //long number = Long.parseLong(numericPart);
        driver1.quit();
        //String numberAsString = Long.toString(number);
        // driver.manage().window().fullscreen();
        Thread.sleep(3000);
        for (int i = 0; i < numericPart.length(); i++) {
            char digitChar = numericPart.charAt(i);
            String digit = Character.toString(digitChar);
            driver.findElement(By.xpath("//div[@id='inputs']/input[" + (i + 2) + "]")).sendKeys(digit);
        }
        driver.findElement(By.xpath("//button[text()='Verify']")).click();
        try {
            if (isAlertPresent()) {
                Thread.sleep(5000);
                Alert alert = driver.switchTo().alert();
                alert.accept();
            }
        } catch (UnhandledAlertException e) {
            Alert unexpectedAlert = driver.switchTo().alert();
            unexpectedAlert.accept();
        }
        Thread.sleep(3000);
    }
    @Test(priority=0)
    public void AddUser() throws InterruptedException {
        driver.findElement(By.xpath("//button[text()='Add user']")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("nameWallet")).sendKeys("Test-110");
        driver.findElement(By.id("emailIDWallet")).sendKeys("testp110@yopmail.com");
        driver.findElement(By.id("mobile_number")).sendKeys("9999905110");
        WebElement dropdownElement = driver.findElement(By.xpath("//select[@name='countries']"));
        Thread.sleep(3000);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue("226");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        driver1 = new ChromeDriver();
        driver1.navigate().to("https://yopmail.com/");
        driver1.findElement(By.xpath("//input[@placeholder='Enter your inbox here']")).sendKeys("testp110@yopmail.com");
        Thread.sleep(25000);
        driver1.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();
        Thread.sleep(5000);
        WebElement iframe = driver1.findElement(By.xpath("//iframe[@id='ifmail']"));
        driver1.switchTo().frame(iframe);
        Thread.sleep(3000);
        WebElement Path = driver1.findElement(By.xpath("//*[@id='mail']/div/table/tbody/tr[3]/td/table/tbody/tr/td/p[2]"));
        String OTP=Path.getText();
        System.out.println(OTP);
        driver1.quit();
        String numericPart = OTP.replaceAll("\\D", "");
        numericPart = numericPart.substring(0, Math.min(numericPart.length(), 6));
        //long number = Long.parseLong(numericPart);
        driver.findElement(By.id("otpWallet")).sendKeys(numericPart);
        driver.findElement(By.xpath("(//button[text()='Submit'])[2]")).click();
    }
       /*@Test
       public void verifyWalletUser() throws InterruptedException {
           Thread.sleep(10000);
           driver.findElement(By.xpath("//*[@id='UserListTable']/tbody/tr[2]/td[6]//a[@title='Verification']")).click();
           Thread.sleep(10000);
           WebElement ele = driver.findElement(By.xpath("//*[@id='UserListTable']/tbody/tr[2]//td[2]"));
           String mail = ele.getText();
           driver1 = new ChromeDriver();
           driver1.navigate().to("https://yopmail.com/");
           driver1.findElement(By.xpath("//input[@placeholder='Enter your inbox here']")).sendKeys(mail);
           Thread.sleep(15000);
           driver1.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();
           Thread.sleep(5000);
           WebElement iframe = driver1.findElement(By.xpath("//iframe[@id='ifmail']"));
           driver1.switchTo().frame(iframe);
           WebElement Path = driver1.findElement(By.xpath("//*[@id='mail']/div/table/tbody/tr[3]/td/table/tbody/tr/td/p[2]"));
           String OTP=Path.getText();
           System.out.println(OTP);
           driver1.quit();
           String numericPart = OTP.replaceAll("\\D", "");
           numericPart = numericPart.substring(0, Math.min(numericPart.length(), 6));
           //long number = Long.parseLong(numericPart);
           driver.findElement(By.id("otpWallet")).sendKeys(numericPart);
           driver.findElement(By.xpath("(//button[text()='Submit'])[2]")).click();
       }*/

    @Test(priority=1)
    public void formAddTotalAmountLoaded() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(By.xpath("//button[text()='Load money']")).click();
       /* WebElement Load_Amount= driver.findElement(By.xpath("//h1[@id='addTotalAmountLoadedLabel']"));
        String actualText = Load_Amount.getText().trim();
        System.out.println("Actual text is " + actualText );
        String expectedText = "Request For Load Amount";
        System.out.println("Expected text is " + expectedText );
        Assert.assertEquals(expectedText,actualText);*/
        Thread.sleep(3000);
        driver.findElement(By.xpath("//form[@id='formAddTotalAmountLoaded']/div/div[3]/input[@placeholder='Enter Amount']")).sendKeys("1");
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element =driver.findElement(By.xpath("(//small)[5]/div/button[2]"));
        driver.manage().window().fullscreen();
        Thread.sleep(3000);
        element.click();
        Thread.sleep(3000);
    }
    @Test(priority=2)
    public void employeeformAddTotalAmountLoaded() throws InterruptedException {
        Thread.sleep(8000);
        driver.findElement(By.xpath("//*[@id='UserListTable']/tbody/tr[1]/td[6]/span/a[2]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//form[@id='employeeformAddTotalAmountLoaded']/div[1]/div[7]/input[@placeholder='Enter Amount']")).sendKeys("1");
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element =driver.findElement(By.xpath("//form[@id='employeeformAddTotalAmountLoaded']/div[2]/button[2]"));
        driver.manage().window().fullscreen();
        Thread.sleep(3000);
        element.click();
        Thread.sleep(5000);
    }
    @Test(priority=3)
    public void formAddWithdrow() throws InterruptedException {
        Thread.sleep(25000);
        driver.findElement(By.xpath("//button[text()='Withdraw']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//form[@id='formAddWithdrow']/div/div[3]/input")).sendKeys("1");
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element =driver.findElement(By.xpath("//form[@id='formAddWithdrow']/small/div/button[2]"));
        driver.manage().window().fullscreen();
        Thread.sleep(3000);
        element.click();
        Thread.sleep(3000);
    }
    @Test(priority=4)
    public void editUserDetail() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id='UserListTable']/tbody/tr[1]/td[6]/span/a[1]")).click();
       /* WebElement dropdownElement = driver.findElement(By.xpath("(//select[@name='countries'])[1]"));
        Thread.sleep(8000);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue("226");*/
        Thread.sleep(5000);
        driver.findElement(By.xpath("(//small)[3]/div[2]/label")).click();
        Thread.sleep(10000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
      /*  WebElement Move_to_scroll = driver.findElement(By.xpath("//div[@id='EditForm']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(Move_to_scroll).perform();
        js.executeScript("window,scrollBy(0,5000)");*/
        WebElement element = driver.findElement(By.xpath("//form[@id='editformAddWalletUser']/small/div[@class='modal-footer justify-content-center']/button[2]"));
        driver.manage().window().fullscreen();
        Thread.sleep(3000);
        element.click();
    }
    @Test(priority=5)
    public void totalAmountLoadedCalculation() throws InterruptedException {
        Thread.sleep(5000);
        WebElement total = driver.findElement(By.xpath("//section[@class='pp-section pp-counter-section']/div/div/div/div[2]/div/div/div[2]/h3"));
        String totalAmountLoaded = total.getText();
        float totalAmountLoaded_float = Float.parseFloat(totalAmountLoaded);
        WebElement transaction = driver.findElement(By.xpath("//section[@class='pp-section pp-counter-section']/div/div/div/div[3]/div/div/div[2]/h3"));
        String totalTransactionAmount = transaction.getText();
        float totalTransactionAmount_float = Float.parseFloat(totalTransactionAmount);
        WebElement available = driver.findElement(By.xpath("//section[@class='pp-section pp-counter-section']/div/div/div/div[4]/div/div/div[2]/h3"));
        String availableBalance = available.getText();
        float availableBalance_float = Float.parseFloat(availableBalance);
        if(totalTransactionAmount_float + availableBalance_float == totalAmountLoaded_float){
            System.out.println("Toatl amount loaded is the sum of Total transaction amount and Available balance");
        }
        else {
            System.out.println("Toatl amount loaded is the not sum of Total transaction amount and Available balance");
        }
    }
    @Test(priority = 6)
    public void search() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Happy");
        Thread.sleep(8000);
    }
    @Test(priority = 7)
    public void contactUs() throws InterruptedException {
        String mainTabHandle = driver.getWindowHandle();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//nav[contains(@class, 'navbar navbar')]/div/div/ul/li[3]/a")).click();
        WebElement whatsapp = driver.findElement(By.xpath("//section[contains(@class,'p')]/div/div[2]/div/div[1]/a/div[1]"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean clickable1 = wait.until(ExpectedConditions.elementToBeClickable(whatsapp)) != null;
        System.out.println("Element clickable: " + clickable1);
        whatsapp.click();
        driver.switchTo().window(mainTabHandle);
        Thread.sleep(3000);
        WebElement mail = driver.findElement(By.xpath("//section[contains(@class,'p')]/div/div[2]/div/div[2]/a/div[1]"));
        boolean clickable2 = wait.until(ExpectedConditions.elementToBeClickable(mail)) != null;
        System.out.println("Element clickable: " + clickable2);
        mail.click();
        driver.switchTo().window(mainTabHandle);
        Thread.sleep(3000);
       /* WebElement phone = driver.findElement(By.xpath("//section[contains(@class,'p')]/div/div[2]/div/div[3]/a/div[1]"));
        boolean clickable3 = wait.until(ExpectedConditions.elementToBeClickable(phone)) != null;
        System.out.println("Element clickable: " + clickable3);
        phone.click();
        WebDriverWait time = new WebDriverWait(driver, 20);
        time.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert text: " + alertText);
        alert.accept();*/
        driver.switchTo().window(mainTabHandle);
        Thread.sleep(5000);
    }
    @Test(priority = 8)
    public void basicInformation() throws InterruptedException {
        driver.findElement(By.xpath("//nav[contains(@class, 'navbar navbar')]/div/div/ul/li[2]/a")).click();
        Thread.sleep(3000);
        WebElement inputElement2 = driver.findElement(By.xpath("(//div[contains(@class, 'pp-profile-form')])[1]/form/div/div[1]/div/input"));
        boolean isReadOnly2 = inputElement2.getAttribute("readonly") != null;
        boolean isEnabled2 = inputElement2.isEnabled();
        boolean isNotEditable2 = isReadOnly2 || !isEnabled2;
        System.out.println("Is input element not editable? " + isNotEditable2);
        inputElement2.clear();
        Thread.sleep(3000);
        inputElement2.sendKeys("Tripti");
        driver.findElement(By.xpath("(//div[contains(@class, 'pp-card-bottom')])[2]/button")).click();
        driver.findElement(By.xpath("(//div[contains(@class, 'pp-card-bottom')])[2]/button")).click();
    }
    @Test(priority = 9)
    public void basicInformationDisabled() throws InterruptedException {
        driver.findElement(By.xpath("//nav[contains(@class, 'navbar navbar')]/div/div/ul/li[2]/a")).click();
        Thread.sleep(3000);
        WebElement inputElement1 = driver.findElement(By.xpath("(//div[contains(@class, 'pp-profile-form')])[1]/form/div/div[2]/div/div/input"));
        boolean isReadOnly1 = inputElement1.getAttribute("readonly") != null;
        boolean isEnabled1 = inputElement1.isEnabled();
        boolean isNotEditable1 = isReadOnly1 || !isEnabled1;
        System.out.println("Is input element not editable? " + isNotEditable1);
        WebElement inputElement2 = driver.findElement(By.xpath("(//div[contains(@class, 'pp-profile-form')])[1]/form/div/div[3]/div/input"));
        boolean isReadOnly2 = inputElement2.getAttribute("readonly") != null;
        boolean isEnabled2 = inputElement2.isEnabled();
        boolean isNotEditable2 = isReadOnly2 || !isEnabled2;
        System.out.println("Is input element not editable? " + isNotEditable2);
    }
    @Test(priority = 10)
    public void addressDetails() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[contains(@class, 'nav ')]/button[2]")).click();
        WebElement inputElement1 = driver.findElement(By.xpath("(((//div[contains(@class, 'pp-profile-form')])[2]/form/div/div/div/div)[1])/input"));
        boolean addressline1 = inputElement1.getAttribute("readonly") != null;
        boolean isEnabled1 = inputElement1.isEnabled();
        boolean isNotEditable1 = addressline1 || !isEnabled1;
        System.out.println("Is addressline1 element not editable? " + isNotEditable1);
        if(!isNotEditable1){
            inputElement1.clear();
            Thread.sleep(3000);
            inputElement1.sendKeys("Manjeraa trinityy");
        }

        WebElement inputElement2 = driver.findElement(By.xpath("(((//div[contains(@class, 'pp-profile-form')])[2]/form/div/div/div/div)[2])/input"));
        boolean addressline2 = inputElement2.getAttribute("readonly") != null;
        boolean isEnabled2 = inputElement2.isEnabled();
        boolean isNotEditable2 = addressline2 || !isEnabled2;
        System.out.println("Is addressline2 element not editable? " + isNotEditable2);
        if(!isNotEditable2){
            inputElement2.clear();
            Thread.sleep(3000);
            inputElement2.sendKeys("Manjeraa trinityy");
        }
        WebElement dropdownElement = driver.findElement(By.xpath("(//div[@class='col-12 col-md-6'])[6]/div/select"));
        Thread.sleep(3000);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("AFGHANISTAN");

        WebElement inputElement4 = driver.findElement(By.xpath("(((//div[contains(@class, 'pp-profile-form')])[2]/form/div/div/div/div)[4])/input"));
        boolean state = inputElement4.getAttribute("readonly") != null;
        boolean isEnabled4 = inputElement4.isEnabled();
        boolean isNotEditable4 = state || !isEnabled4;
        System.out.println("Is state element not editable? " + isNotEditable4);
        if(!isNotEditable4){
            inputElement4.clear();
            Thread.sleep(3000);
            inputElement4.sendKeys("NY");
        }
        WebElement inputElement5 = driver.findElement(By.xpath("(((//div[contains(@class, 'pp-profile-form')])[2]/form/div/div/div/div)[5])/input"));
        boolean city = inputElement5.getAttribute("readonly") != null;
        boolean isEnabled5 = inputElement5.isEnabled();
        boolean isNotEditable5 = city || !isEnabled5;
        System.out.println("Is city element not editable? " + isNotEditable5);
        if(!isNotEditable5){
            inputElement5.clear();
            Thread.sleep(3000);
            inputElement5.sendKeys("NYC");
        }
        WebElement inputElement6 = driver.findElement(By.xpath("(((//div[contains(@class, 'pp-profile-form')])[2]/form/div/div/div/div)[6])/input"));
        boolean zipcode = inputElement6.getAttribute("readonly") != null;
        boolean isEnabled6 = inputElement6.isEnabled();
        boolean isNotEditable6 = zipcode || !isEnabled6;
        System.out.println("Is zipcode element not editable? " + isNotEditable6);
        if(!isNotEditable6){
            inputElement6.clear();
            Thread.sleep(3000);
            inputElement6.sendKeys("12345");
        }
        WebElement inputElement7 = driver.findElement(By.xpath("(((//div[contains(@class, 'pp-profile-form')])[2]/form/div/div/div/div)[7])/input"));
        boolean documentName = inputElement7.getAttribute("readonly") != null;
        boolean isEnabled7 = inputElement7.isEnabled();
        boolean isNotEditable7 = documentName || !isEnabled7;
        System.out.println("Is documentName element not editable? " + isNotEditable7);
        if(!isNotEditable7){
            inputElement7.clear();
            Thread.sleep(3000);
            inputElement7.sendKeys("DL");
        }
        WebElement inputElement8 = driver.findElement(By.xpath("(((//div[contains(@class, 'pp-profile-form')])[2]/form/div/div/div/div)[8])/input"));
        boolean documentNumber = inputElement8.getAttribute("readonly") != null;
        boolean isEnabled8 = inputElement8.isEnabled();
        boolean isNotEditable8 = documentNumber || !isEnabled8;
        System.out.println("Is documentNumber element not editable? " + isNotEditable8);
        if(!isNotEditable8){
            inputElement8.clear();
            Thread.sleep(3000);
            inputElement8.sendKeys("12345PG6789");
        }
        driver.manage().window().fullscreen();
        WebElement inputElement9 = driver.findElement(By.xpath("(((//div[contains(@class, 'pp-profile-form')])[2]/form/div/div/div/div)[9])/label/input"));
        inputElement9.sendKeys("/Users/praharshanigampa/Desktop/Black.png");
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[contains(@class, 'pp-card-bottom-btn text-end')])[2]/button")).click();
    }
    @Test(priority = 11)
    public void privacyPolicy() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[contains(@class, 'nav ')]/button[3]")).click();
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    @Test(priority = 12)
    public void termsAndConditions() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[contains(@class, 'nav ')]/button[4]")).click();
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    @Test(priority = 13)
    public void contactus() throws InterruptedException {
        String mainTabHandle = driver.getWindowHandle();
        driver.findElement(By.xpath("//nav[contains(@class, 'navbar navbar')]/div/div/ul/li[2]/a")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[contains(@class, 'pp-profile-form')])[1]/form/div[2]/p/a")).click();
        driver.switchTo().window(mainTabHandle);
    }
    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.xpath("//div[@ID='navbarSupportedContent']/div/a")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        Thread.sleep(10000);
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
//You have crossed verification code request limit. Please try again after 10 minutes.
