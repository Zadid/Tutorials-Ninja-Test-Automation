package org.tutorialsNinjaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.tutorialsNinjaPages.RegisterForm.*;

public class login {
    private WebDriver driver;
    By MyAccountTitle = By.xpath("//a[@title='My Account']");
    By LogoutBtn = By.xpath("//a[contains(text(), 'Logout')]");
    By LoginTitle = By.xpath("//a[contains(text(),'Login')]");
    By emailField = By.xpath("//input[@id= 'input-email']");
    By passwordField = By.xpath("//input[@id= 'input-password']");
    By loginBtn = By.xpath("//input[@value= 'Login']");
    public login(WebDriver driver) {
        this.driver = driver;
    }
    public void clickOnMyAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(MyAccountTitle)).click();
        System.out.println("My account dropdown is clicked!");
    }
    public void clickOnLogout() {
        driver.findElement(LogoutBtn).click();
        System.out.println("Logout button is clicked!");
    }
    public void clickOnLogin() {
        clickOnMyAccount();
        driver.findElement(LoginTitle).click();
        System.out.println("Login is selected!");
    }
    public void inputValueInfield(String email, String pass){
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        System.out.println("User email is entered!");
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(pass);
        System.out.println("Password is entered!");
    }
    public void clickOnLoginBtn(){
        driver.findElement(loginBtn).click();
        System.out.println("Login button is selected!");
//        if(driver.getCurrentUrl().equalsIgnoreCase("http://tutorialsninja.com/demo/index.php?route=account/login")){
//            System.out.println("Login unsuccessful.");
//        }
    }

}
