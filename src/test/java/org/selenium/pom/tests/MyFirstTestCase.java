package org.selenium.pom.tests;

import org.openqa.selenium.By;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyFirstTestCase extends BaseTest {

     @Test
      public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
         String searchFor="Blue";
      BillingAddress billingAddress=JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product=new Product(1215);
         /* BillingAddress billingAddress=new BillingAddress().
          setFirstName("demo").
          setLastName("user").
          setAddressLineOne("San Francisco").
          setCity("San Francisco").
          setPostalCode("94188").
          setEmail("askomdch@gmail.com");*/
         /*BillingAddress billingAddress=new BillingAddress("demo","user","San Francisco","San Francisco",
                 "94188","askomdch@gmail.com");*/
          StorePage storePage= new HomePage(getDriver()).
                  load().
                  getMyHeader().navigateToStoreUsingMenu().
         search(searchFor);
         Thread.sleep(7000);
          Assert.assertEquals(storePage.getTitle(),"Search results: “" + searchFor + "”");
          storePage.getProductThumbnail().clickAddToCartButton(product.getName());
          CartPage cartpage=storePage.getProductThumbnail().clickViewCart();
          Assert.assertEquals(cartpage.getProductName(),product.getName());
          CheckoutPage checkoutPage=cartpage.
                  checkout().
                  setBillingAddress(billingAddress).
                  selectDirectBankTransfer().
                  placeOrder();
          Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");

      }
    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
         String searchFor="Blue";
        BillingAddress billingAddress=JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product=new Product(1215);
        User user=new User(ConfigLoader.getInstance().getUsername(), ConfigLoader.getInstance().getPassword());

//        BillingAddress billingAddress=new BillingAddress().
//                setFirstName("demo").
//                setLastName("user").
//                setAddressLineOne("San Francisco").
//                setCity("San Francisco").
//                setPostalCode("94188").
//                setEmail("askomdch@gmail.com");*//*
//    BillingAddress billingAddress=new BillingAddress("demo","user","San Francisco","San Francisco",
//            "94188","askomdch@gmail.com");

        StorePage storePage= new HomePage(getDriver()).
                load().getMyHeader().
                navigateToStoreUsingMenu().
                search(searchFor);
        Thread.sleep(7000);
        Assert.assertEquals(storePage.getTitle(),"Search results: “" + searchFor + "”");
        storePage.getProductThumbnail().clickAddToCartButton("Blue Shoes");
        CartPage cartpage=storePage.getProductThumbnail().clickViewCart();
        Assert.assertEquals(cartpage.getProductName(),"Blue Shoes");
        CheckoutPage checkoutPage=cartpage.checkout();
        checkoutPage.clickHereToLoginLink();
        checkoutPage.
                login("demouser2","demopwd").
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");

    }
}
