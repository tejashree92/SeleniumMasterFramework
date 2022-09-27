package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {

    private final By productName= By.cssSelector("td[class='product-name'] a");
    private final By checkoutBtn=By.cssSelector(".checkout-button.button.alt.wc-forward");
    private final By cartHeading=By.cssSelector(".has-text-align-center");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }

    /*public Boolean isLoaded()++++++++++
    {
        return wait.until(ExpectedConditions.textToBe(cartHeading,"Cart"));
    }*/

    public CheckoutPage checkout()
    {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return new CheckoutPage(driver);
    }
}
