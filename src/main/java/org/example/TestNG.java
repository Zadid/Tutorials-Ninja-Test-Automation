package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.tutorialsNinjaPages.*;

import java.io.IOException;
import java.util.Map;

public class TestNG {
    static WebDriver driver = null;
    static HomePage objOfHomePge;
    static RegisterForm objOfRegisterForm;
    static RegistrationConfirmationPage objOfRegistrationConfirmationPage;
    static MyAccountPage objOfMyAccountPage;
    static HomepageAfterLogin objOfHomepageAfterLogin;
    static SelectProductPage objOfSelectProductPage;
    static ViewCartPage objOfViewCartPage;
    static LogoutPage objOfLogoutPage;
    static login objOfLogin;

    static String emailAdd;

    public static void setBrowser(String browser) {

        switch (browser) {
            case "Chrome" -> {
                System.setProperty("web-driver.chrome.driver", "H:\\AutomationProjects\\Assignment6\\Drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                System.out.println(browser +" opened successfully!");
            }
            case "Firefox" -> {
                System.setProperty("web-driver.gecko.driver", "H:\\AutomationProjects\\Assignment6\\Drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                System.out.println(browser +" opened successfully!");
            }
            case "Edge" -> {
                System.setProperty("web-driver.edge.driver", "H:\\AutomationProjects\\Assignment6\\Drivers\\msedgedriver.exe");
                driver = new EdgeDriver();
                System.out.println(browser +" opened successfully!");
            }
            default -> System.out.println("This browser is out of scope!");
            //There is a chance to be null driver. So, default is not used and try NullPointerException using if-else.
        }
        if (driver != null) {
            String URL = "http://tutorialsninja.com/demo/";
            driver.get(URL);
            System.out.println("Website is opened successfully!");
        } else {
            System.out.println("Please select Chrome/Firefox/Edge to open browser!");
        }
    }
    public static void ClickOnMyAccount_Register(){
        objOfHomePge = new HomePage(driver);
        objOfHomePge.clickOnMyAccount();
        objOfHomePge.clickOnRegister();
    }

    public static void Register(String fname,String  lname,String  phoneNum,String  pass,String  conPass,String  sub,String  pvPol, String[] errArr){
        objOfRegisterForm = new RegisterForm(driver);
        objOfRegisterForm.enterFirstName(fname);
        objOfRegisterForm.enterLastName(lname);
        emailAdd = objOfRegisterForm.enterEmail();
        objOfRegisterForm.enterTelephone(phoneNum);
        objOfRegisterForm.enterPassword(pass);
        objOfRegisterForm.enterPassConfirm(conPass);
        objOfRegisterForm.clickOnNewsletterSubscribe(sub);
        objOfRegisterForm.clickOnPrivacyPolicy(pvPol);
        objOfRegisterForm.clickOnContinue(errArr, phoneNum, fname);
    }

    public static void CheckTheConfirmationRegister(){
        objOfRegistrationConfirmationPage = new RegistrationConfirmationPage(driver);
        objOfRegistrationConfirmationPage.checkConfirmationMsg();
        objOfRegistrationConfirmationPage.clickOnContinue();
    }

    public static void clickOnYourStore(){
        objOfMyAccountPage = new MyAccountPage(driver);
        objOfMyAccountPage.clickOnYourStore();
    }

    public static void clickOnFirstItem(){
        objOfHomepageAfterLogin = new HomepageAfterLogin(driver);
        objOfHomepageAfterLogin.hoverOnDesktops();
        objOfHomepageAfterLogin.clickShowAllDesktops();
        objOfHomepageAfterLogin.sort("Price (Low > High)");
        objOfHomepageAfterLogin.sort("Price (High > Low)");
        objOfHomepageAfterLogin.clickOnFirstItem();
    }

    public static void addToCart(String expectedQnty){
        objOfSelectProductPage = new SelectProductPage(driver);
        objOfSelectProductPage.ModifyQnty(expectedQnty);
        objOfSelectProductPage.clickOnAddToCartBtn();
        objOfSelectProductPage.clickOnBlackCartBox();
        objOfSelectProductPage.clickOnViewCart();
    }

    public static void clickForLogoutFromViewCart(String expectedQnty){
        objOfViewCartPage = new ViewCartPage(driver);
        objOfViewCartPage.checkQuantity(expectedQnty);
        objOfViewCartPage.checkTotal(expectedQnty);
        objOfViewCartPage.clickOnMyAccount();
        objOfViewCartPage.clickOnLogout();
    }

    public static void logoutConfirmation(){
        objOfLogoutPage = new LogoutPage(driver);
        objOfLogoutPage.checkConfirmationMsg();
        objOfLogoutPage.clickOnContinue();
    }
    public static void loginAftrSuccessflRgstr(){
        objOfLogin = new login(driver);
        objOfLogin.clickOnMyAccount();
        objOfLogin.clickOnLogout();
        objOfLogin.clickOnLogin();
    }
    public static void inputValueInLoginField(String email, String pass){
        objOfLogin.inputValueInfield(email, pass);
        objOfLogin.clickOnLoginBtn();
    }
    ReadData objReadData = new ReadData();
    String fileDir = new java.io.File(".").getCanonicalPath();

    public TestNG() throws IOException {
    }
    @DataProvider (name = "data-provider")
    public Object[][] dpMethod(){
        return new Object[][] {{emailAdd, "1234"},{emailAdd, "wxyz"}};
    }
    @BeforeTest(alwaysRun = true)
    public void openBrowser() {
        setBrowser("Chrome");
    }
    @Test(priority = 1, groups = "Register Successful")
    public void positiveTestRun() throws IOException {
        Map[] inputData = objReadData.readFromExcel(fileDir,"InputData.xlsx","PositiveTestCase");
        int numberOfIteration = inputData.length;
        for (int i = 1; i < numberOfIteration; i++) {
            ClickOnMyAccount_Register();
            String fname = (String) inputData[i].get("First Name"), lname = (String) inputData[i].get("Last Name"),
                    phoneNum = (String) inputData[i].get("Phone Number"), pass = (String) inputData[i].get("Password"),
                    conPass = (String) inputData[i].get("Confirm Password"), sub = (String) inputData[i].get("Subscribe Newsletter"),
                    pvPol = (String) inputData[i].get("Privacy Policy"), errMsg = (String) inputData[i].get("Error Message");
            String[] errArr = errMsg.split(",");
            Register(fname, lname, phoneNum, pass, conPass, sub, pvPol, errArr);
            System.out.println("Registration successful");
            CheckTheConfirmationRegister();
            loginAftrSuccessflRgstr();
        }
    }
    @Test(priority = 2,groups = "Process after login",dependsOnGroups = "Register Successful", dataProvider = "data-provider")
    public void runAfterSuccessfulRegister(String emailAdd, String pass) throws IOException {
        Map[] inputData = objReadData.readFromExcel(fileDir,"InputData.xlsx","PositiveTestCase");
        int numberOfIteration = inputData.length;
        for (int i = 1; i < numberOfIteration; i++) {
        String expectedQnty = (String) inputData[i].get("Quantity");
            inputValueInLoginField(emailAdd, pass);
            if(driver.getCurrentUrl().equalsIgnoreCase("http://tutorialsninja.com/demo/index.php?route=account/login")){
                String actualMsg = driver.findElement(By.xpath("//div[@class = 'alert alert-danger alert-dismissible']")).getText();
                String expectedMsg = "Warning: No match for E-Mail Address and/or Password.";
                Assert.assertEquals(actualMsg, expectedMsg, "Error message is not matched.");
                System.out.println(actualMsg);
            }
            else {
                clickOnYourStore();
                clickOnFirstItem();
                addToCart(expectedQnty);
                clickForLogoutFromViewCart(expectedQnty);
                logoutConfirmation();
            }
        }
    }
    @Test(priority = 3)
    public void negativeTestRun() throws IOException {
        Map[] inputData = objReadData.readFromExcel(fileDir,"InputData.xlsx","NegativeTestCase");
        int numberOfIteration = inputData.length;
        for (int i = 1; i < numberOfIteration; i++) {
            driver.get("http://tutorialsninja.com/demo/");
            ClickOnMyAccount_Register();
            String fname = (String) inputData[i].get("First Name"), lname = (String) inputData[i].get("Last Name"),
                    phoneNum = (String) inputData[i].get("Phone Number"), pass = (String) inputData[i].get("Password"),
                    conPass = (String) inputData[i].get("Confirm Password"), sub = (String) inputData[i].get("Subscribe Newsletter"),
                    pvPol = (String) inputData[i].get("Privacy Policy"), expectedQnty = (String) inputData[i].get("Quantity"),
                    errMsg = (String) inputData[i].get("Error Message");
            String[] errArr = errMsg.split(",");
            Register(fname, lname, phoneNum, pass, conPass, sub, pvPol, errArr);
        }
    }
    @AfterTest
    public void dismissRun(){
        driver.quit();
    }
}
