package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


import java.util.HashMap;

public class ApiRequest extends SpecBuilder{

    public static Response post(String endPoint, Headers headers, HashMap<String,Object> formParams, Cookies cookies)
    {
        return given(getRequestSpec()).
                headers(headers).
                formParams(formParams).
                cookies(cookies).
                when().
                post(endPoint).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String endpoint,Cookies cookies)
    {

        return given(getRequestSpec()).
                cookies(cookies).
                when().
                get(endpoint).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

}
