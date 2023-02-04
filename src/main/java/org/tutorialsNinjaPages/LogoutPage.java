package org.tutorialsNinjaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {
    WebDriver driver;
    By confirmationMsg = By.xpath("//p[contains (text(),'You have been logged off your account. It is now safe to leave the computer.')]");
    By continueBtn = By.xpath("//a[contains(text(),'Continue')]");

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    String expectedMsg, actualMsg;
    public void checkConfirmationMsg() {
        expectedMsg = "You have been logged off your account. It is now safe to leave the computer.";
        actualMsg = driver.findElement(confirmationMsg).getText();
        boolean rslt = expectedMsg.equalsIgnoreCase(actualMsg);
        if (rslt) {
            System.out.println("Perfect message is showed for logout!");
        }
        else {
            System.out.println("Wrong message is shown!");
        }
    }
    public void clickOnContinue() {
        driver.findElement(continueBtn).click();
        System.out.println("Continue button is clicked!");
    }
}
