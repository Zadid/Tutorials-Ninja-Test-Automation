package org.tutorialsNinjaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ViewCartPage {
    WebDriver driver;

    By myAccountBtn = By.xpath("//a[@title='My Account']");
    By LogoutBtn = By.xpath("//a[contains(text(), 'Logout')]");
    By Qty = By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[4]/div/input");
    By totalValue = By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[6]");
    By unitValue = By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[5]");

    public ViewCartPage(WebDriver driver) {
        this.driver = driver;
    }
    public void checkQuantity(String expectedQnty) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(Qty));
        int actualQty = Integer.parseInt(driver.findElement(Qty).getAttribute("value"));
        int expectedQntyInt = (int) Math.round(Double.parseDouble(expectedQnty));
//        if (Math.round(Double.parseDouble(expectedQnty)) == actualQty) {
//            System.out.println("Right quantity is showed!");
//        }
//        else {
//            System.out.println("Quantity is wrong!");
//        }
        Assert.assertEquals(actualQty, expectedQntyInt, "Expected Quantity is not matched.");
    }

    public void checkTotal(String expectedQnty) {

        double unitPrice = Double.parseDouble(driver.findElement(unitValue).getText().trim().replace("$", "").replace(",", ""));
        System.out.println("Unit price is " + unitPrice);

        double expectedTotal = (Double.parseDouble(expectedQnty) * unitPrice);
        System.out.println("Expected total price is " + expectedTotal);

        double actualTotal = Double.parseDouble(driver.findElement(totalValue).getText().trim().replace("$", "").replace(",", ""));
        System.out.println("Actual total price is " + actualTotal);

//        if (expectedTotal == actualTotal) {
//            System.out.println("Total value is correct!");
//        }
//        else {
//            System.out.println("Total value is wrong!");
//        }
        Assert.assertEquals(actualTotal, expectedTotal, "Expected total is not matched.");
    }
    public void clickOnMyAccount() {
        driver.findElement(myAccountBtn).click();
        System.out.println("My Account is clicked!");
    }
    public void clickOnLogout() {
        driver.findElement(LogoutBtn).click();
        System.out.println("Logout button is clicked!");
    }
}
