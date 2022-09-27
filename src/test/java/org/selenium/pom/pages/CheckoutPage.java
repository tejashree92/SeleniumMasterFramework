package org.selenium.pom.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends BasePage {
    private final By firstNameFld=By.id("billing_first_name");
    private final By lastNameFld=By.id("billing_last_name");
    private final By addressLineOneFld=By.id("billing_address_1");
    private final By billingCityField=By.id("billing_city");
    private final By billingPostCodeFld=By.id("billing_postcode");
    private final By billingEmailFld=By.id("billing_email");
    private final By placeOrderBtn=By.id("place_order");
    private final By successNotice=By.cssSelector(".woocommerce-notice");

    private final By clickHereToLoginLink=By.className("showlogin");
    private final By userNameFld= By.id("username");
    private final By passwordFld=By.id("password");
    private final By loginBtn=By.name("login");
    private final By overLay=By.cssSelector((".blockUI.blockOverlay"));
    private final By countryDropDown=By.id("billing_country");
    private final By stateDropDown=By.id("billing_state");
    private final By alternateCountryDropDown = By.id("select2-billing_country-container");
    private final By alternateStateDropDown=By.id("select2-billing_state-container");
    private final By directBankTransferRadioBtn=By.id("payment_method_bacs");

    private final By productName=By.cssSelector("td[class='product-name']");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load()
    {
        load("/checkout");
        return this;
    }

    public CheckoutPage enterFirstName(String firstName)
    {

        WebElement e=wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFld));
        e.clear();
        e.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName)
    {
        WebElement e=wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameFld));
        e.clear();
        e.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName)
    {
        /*Select select =new Select(driver.findElement(countryDropDown));
        select.selectByVisibleText(countryName);*/

        wait.until(ExpectedConditions.elementToBeClickable(alternateCountryDropDown)).click();
        WebElement e=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + countryName + "']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",e);
        e.click();
        return this;
   }


    public CheckoutPage enterAddressLineOne(String addressLineOne)
    {
        WebElement e=wait.until(ExpectedConditions.visibilityOfElementLocated(addressLineOneFld));
        e.clear();
        e.sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city)
    {
        WebElement e=wait.until(ExpectedConditions.visibilityOfElementLocated(billingCityField));
        e.clear();
        e.sendKeys(city);
        return this;
    }

    public CheckoutPage selectState(String stateName)
    {
        /*Select select =new Select(wait.until(ExpectedConditions.visibilityOf(driver.findElement(stateDropDown))));
        select.selectByVisibleText(stateName);*/

        wait.until(ExpectedConditions.elementToBeClickable(alternateStateDropDown)).click();
        WebElement e=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + stateName + "']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",e);
        e.click();
        return this;
    }

    public CheckoutPage enterPostCode(String postCode)
    {
        WebElement e=wait.until(ExpectedConditions.visibilityOfElementLocated(billingPostCodeFld));
        e.clear();
        e.sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email)
    {
        WebElement e=wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailFld));
        e.clear();
        e.sendKeys(email);
        return this;
    }


    public CheckoutPage setBillingAddress(BillingAddress billingaddress)
    {
        return enterFirstName(billingaddress.getFirstName()).
                enterLastName(billingaddress.getLastName()).
                selectCountry(billingaddress.getCountry()).
                enterAddressLineOne(billingaddress.getAddressLineOne()).
                enterCity(billingaddress.getCity()).
                selectState(billingaddress.getState()).
                enterPostCode(billingaddress.getPostalCode()).
                enterEmail(billingaddress.getEmail());
    }


    public CheckoutPage placeOrder()
    {

        waitForOverlaysToDisappear(overLay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotice()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();

    }
    public CheckoutPage clickHereToLoginLink()
    {
        wait.until(ExpectedConditions.elementToBeClickable(clickHereToLoginLink)).click();
        return this;
    }

    public CheckoutPage enterUserName(String username)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameFld)).sendKeys(username);

        return this;
    }

    public CheckoutPage enterPassword(String password)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFld)).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn()
    {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }

    public CheckoutPage login(String username,String password)
    {
        return enterUserName(username).
                enterPassword(password).
                clickLoginBtn();
    }

    private CheckoutPage waitForLoginBtnToDisappear()
    {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn));
        return this;
    }

    public CheckoutPage login(User user)
    {
        return enterUserName(user.getUsername()).
                enterPassword(user.getPassword()).
                clickLoginBtn().waitForLoginBtnToDisappear();


    }

    public CheckoutPage selectDirectBankTransfer()
    {
        WebElement e=wait.until(ExpectedConditions.visibilityOfElementLocated(directBankTransferRadioBtn));
        if(!e.isSelected())
        {
            e.click();
        }
        return this;
    }

    public String getProductName() throws Exception {

        int i=5;
        while(i>0)
        {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();

            }
            catch(StaleElementReferenceException e)
            {
                System.out.println("NOT FOUND.TRYING AGAIN " +e);
            }
            Thread.sleep(5000);
            i--;
        }
        throw new Exception("Element not found");


    }
}
