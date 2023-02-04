package org.tutorialsNinjaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomepageAfterLogin {
    WebDriver driver;
    By Desktops = By.xpath("//a[normalize-space()='Desktops']");
    By Sorting = By.xpath("//select[@id='input-sort']");
    By SortItem = By.xpath("(//div[@class='image'])");
    By AllDesktops = By.xpath("//a[normalize-space()='Show All Desktops']");
    public HomepageAfterLogin(WebDriver driver) {
        this.driver = driver;
    }

    public void hoverOnDesktops(){
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(Desktops);
        action.moveToElement(element);
        action.perform();
        System.out.println("Hovering on Desktops!");
    }
    public void clickShowAllDesktops(){
        driver.findElement(AllDesktops).click();
        System.out.println("All Desktops clicked!");
    }
    public void sort(String str){
        Select select = new Select(driver.findElement(Sorting));
        select.selectByVisibleText(str);
        System.out.println("Items sorted according to " + str);
    }
    public void clickOnFirstItem(){
        List<WebElement> options = driver.findElements(SortItem);
        options.get(0).click();
        System.out.println("First Item is clicked!");
    }
}
