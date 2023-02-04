package org.tutorialsNinjaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationConfirmationPage {
    WebDriver driver;
    By confirmationMsg = By.xpath("//*[@id='content']/h1");
    By continueBtn = By.xpath("//a[contains(text(),'Continue')]");

    public RegistrationConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    String expectedMsg, actualMsg;
    public void checkConfirmationMsg() {
        expectedMsg = "Your Account has Been Created!";
        actualMsg = driver.findElement(confirmationMsg).getText();
        boolean rslt = expectedMsg.equalsIgnoreCase(actualMsg);
        if (rslt) {
            System.out.println("Perfect message is showed!");
            System.out.println("Message: " + actualMsg);
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
