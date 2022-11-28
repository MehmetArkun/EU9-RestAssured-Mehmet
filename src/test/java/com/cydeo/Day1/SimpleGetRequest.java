package com.cydeo.Day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://54.234.237.17:8000/api/spartans";

    @Test
    public void test1(){

        //send a get request and save response inside the response object
        Response response = RestAssured.get(url);

        //print response satus code
        System.out.println(response.statusCode());

        //print response body
        response.prettyPrint();

    }


}
