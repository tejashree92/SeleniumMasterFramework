package org.selenium.pom.tests;

import org.junit.Assert;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void LoginDuringCheckout() throws Exception {
        String username="demouser" + new FakerUtils().generateRandomNumber();
        User user=new User();
        user.setUsername(username).
                setPassword("demopwd").
                setEmail(username +"@askomdch.com");
        SignUpApi signupapi=new SignUpApi();
        signupapi.register(user);
        CartApi cartApi=new CartApi();
        Product product=new Product(1215);
        cartApi.addToCart(product.getId(),1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(5000);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();
        Thread.sleep(5000);

        checkoutPage.
                clickHereToLoginLink().
                login(user);
        Thread.sleep(5000);

        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));

    }
}
