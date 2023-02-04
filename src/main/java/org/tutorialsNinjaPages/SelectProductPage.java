package org.tutorialsNinjaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectProductPage {
    WebDriver driver;
    By qty = By.xpath("//input[@id='input-quantity']");
    By addToCrtBtn = By.xpath("//button[@id='button-cart']");
    By blackCartBtn = By.xpath("//div[@id='cart']");
    By viewCartLink = By.xpath("//strong[contains(text(),' View Cart')]");
    public SelectProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ModifyQnty(String setQnty) {
        WebElement qnty =  driver.findElement(qty);
        qnty.clear();
        qnty.sendKeys(String.valueOf(setQnty));
        System.out.println("Quantity is modified!");
    }

    public void clickOnAddToCartBtn(){
        driver.findElement(addToCrtBtn).click();
        System.out.println("Add to cart button is clicked!");
    }

    public void clickOnBlackCartBox() {
        driver.findElement(blackCartBtn).click();
        System.out.println("Cart button is clicked!");
    }

    public void clickOnViewCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartLink)).click();
        System.out.println("View cart is clicked!");
    }
}
