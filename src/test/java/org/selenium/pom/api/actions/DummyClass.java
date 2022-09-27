package org.selenium.pom.api.actions;

import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;

public class DummyClass {

    public static void main(String args[])
    {
        String username="demouser" + new FakerUtils().generateRandomNumber();
        User user=new User();
        user.setUsername(username).
                setPassword("demopwd").
                setEmail(username +"@askomdch.com");
        SignUpApi signupapi=new SignUpApi();
        signupapi.register(user);
        //System.out.println(signupapi.register(user));
        System.out.println("REGISTER COOKIES: "+ signupapi.getCookies());
        CartApi cartapi=new CartApi(signupapi.getCookies());
        cartapi.addToCart(1215,1);
        System.out.println("CART COOKIES: " + cartapi.getCookies());
    }
}
