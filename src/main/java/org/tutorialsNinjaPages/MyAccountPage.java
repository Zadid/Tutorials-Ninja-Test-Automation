package org.tutorialsNinjaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    WebDriver driver;
    By yourStoreLink = By.xpath("//a[contains(text(),'Your Store')]");
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnYourStore() {
        driver.findElement(yourStoreLink).click();
        System.out.println("Your store link is clicked!");
    }
}
