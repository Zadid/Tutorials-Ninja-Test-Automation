package org.tutorialsNinjaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    By MyAccountTitle = By.xpath("//a[@title='My Account']");
    By RegisterTitle = By.xpath("//a[contains(text(),'Register')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnMyAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(MyAccountTitle)).click();
        System.out.println("My account dropdown is clicked!");
    }

    public void clickOnRegister() {
        driver.findElement(RegisterTitle).click();
        System.out.println("Register is selected!");
    }
}