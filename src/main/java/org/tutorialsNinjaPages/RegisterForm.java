package org.tutorialsNinjaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Random;

public class RegisterForm {
    WebDriver driver;
    By scrollDown;
    By Firstname = By.xpath("//input[@id='input-firstname']");
    By Lastname = By.xpath("//input[@id='input-lastname']");
    By email = By.xpath("//input[@id='input-email']");
    By telephone = By.xpath("//input[@id='input-telephone']");
    By password = By.xpath("//input[@id='input-password']");
    By passConfirm = By.xpath("//input[@id='input-confirm']");
    By subscribe = By.xpath("//input[@name='newsletter' and @value='1']");
    By privacyPolicy = By.xpath("//input[@name='agree' and @value='1']");
    By cont = By.xpath("//input[@value='Continue']");

    public RegisterForm(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollTo(By element) {
        scrollDown = element;
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView()", driver.findElement(scrollDown));
    }

    public void enterFirstName(String fName) {
        System.out.println(fName);
        //driver.findElement(Firstname).clear();
        driver.findElement(Firstname).sendKeys(fName);
        if (fName == " "){
            System.out.println("First Name is not entered!");
        }
        else System.out.println("First Name number is entered!");
    }

    public void enterLastName(String lName) {
        driver.findElement(Lastname).sendKeys(lName);
        System.out.println("Lastname is entered!");
    }

    public String enterEmail() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        String emailAdd = saltStr + "@gmail.com";
        driver.findElement(email).sendKeys(saltStr + "@gmail.com");
        System.out.println("Email is entered!");
        return emailAdd;
    }

    public void enterTelephone(String phoneNum) {
        driver.findElement(telephone).sendKeys(phoneNum);
        if (phoneNum == " "){
            System.out.println("Telephone number is not entered!");
        }
        else System.out.println("Telephone number is entered!");
    }

    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
        System.out.println("Password is entered!");
    }

    public void enterPassConfirm(String conPass) {
        driver.findElement(passConfirm).sendKeys(conPass);
        System.out.println("Confirm password is entered!");
    }

    public void clickOnNewsletterSubscribe(String sub) {
        if (sub.equalsIgnoreCase("yes")) {
            driver.findElement(subscribe).click();
            System.out.println("Newsletter subscribe radio button is clicked!");
        }
    }

    public void clickOnPrivacyPolicy(String pvPol) {
        scrollTo(privacyPolicy);
        if (pvPol.equalsIgnoreCase("TRUE")) {
            driver.findElement(privacyPolicy).click();
            System.out.println("Privacy policy checkbox is clicked!");
        }
    }

    public void clickOnContinue(String[] errArr, String phoneNum, String fName) {
        driver.findElement(cont).click();
        System.out.println("Continue button is clicked!");
        String crntURL = driver.getCurrentUrl();
        if(crntURL.equalsIgnoreCase("http://tutorialsninja.com/demo/index.php?route=account/register")){
            boolean flag = driver.findElement(privacyPolicy).isSelected();
            for (int i = 0; i < errArr.length; i++){
                if(!flag){
                    String errMsgForPrvcyPolcy = driver.findElement(By.xpath("//div[@class = 'alert alert-danger alert-dismissible']")).getText();
//                    if (errArr[i].equalsIgnoreCase(driver.findElement(By.xpath("//div[@class = 'alert alert-danger alert-dismissible']")).getText())){
//                        System.out.println("Error Message of Privacy Policy: " + errArr[i]);
//                    }
                    Assert.assertEquals(errMsgForPrvcyPolcy, errArr[i], "Error message for privacy policy is not matched.");
                    System.out.println(errArr[i]);
                }
                else if (fName == " ") {
                    String errMsg = driver.findElement(By.xpath("//*[@id=account]/div[2]/div/div")).getText();
                    System.out.println(errMsg);
                    if (errArr[i].equalsIgnoreCase(errMsg)){
                        System.out.println("Error Message of First Name: " + errArr[i]);
                    }
                }
                else if (phoneNum == " ") {
//                    if (errArr[i].equalsIgnoreCase(driver.findElement(By.cssSelector("#account > div.form-group.required.has-error > div > div")).getText())){
//                        System.out.println("Error Message of Telephone Number: " + errArr[i]);
//                    }
                    String actualMsgPhn = driver.findElement(By.cssSelector("#account > div.form-group.required.has-error > div > div")).getText();
                    //System.out.println(errArr[i]);
                    //System.out.println(actualMsgPhn);
                    Assert.assertEquals(actualMsgPhn, errArr[i], "Error message for phone number is not matched");
                    System.out.println(actualMsgPhn);
                }
            }
        }
    }
}
