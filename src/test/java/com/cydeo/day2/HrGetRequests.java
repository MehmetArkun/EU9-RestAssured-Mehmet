package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequests {

    //BeforeAll is an annotation equals to @BeforeClass in testNg, we use with static method
    //it's gonna be executed at once before everything
    @BeforeAll
    public static void init(){

        //save baseurl inside this variable so that we don't need to type each http method
        RestAssured.baseURI = "http://54.234.237.17:1000/ords/hr"; //oracle database

    }


    //get request to regions table
    @Test
    public void test1(){

        Response response = RestAssured.get("/regions");
        //once you setup your Restassured.baseURl, you dont have to put your base url as a string variable inside get

        System.out.println("response.statusCode() = " + response.statusCode());
    }

    /*
    Given accept type is json
    When user sends get request to regions/2
    Then response status code must be 200
    And content type equals to application/json
    And response body contains Americas
     */

    /*
    one thing to remember: gerek RestAssured.given() orneginde olsun, gerek Assertions.assertEquals orneginde olsun
    her defasinda RestAssured ve Assertions library'lerini iismlerini yazarak bunlari cagirmaktan ziyade, tepeye import edersek
    butun kutuphaneye erisimimiz olur. RestAssured ve Assertions keyworlderi silip ampul'den static import yapmamiz lazim.
    static importu otomatik yaparsan secili methodu baz alarak yapiyor. yukarida static import line'da baz aldigini silip * koy ki hepsini alsin.
     */

    @DisplayName("GET request to /regions/2")
    @Test
    public void test2(){
        Response response = given().
                accept(ContentType.JSON).
                when().get("regions/2");

        int expectedStatusCode = 200;
        int actualStatusCode = response.statusCode();

        String expectedContentType = "application/json";
        String actualContentType = response.contentType();

        String expectedBody = "Americas";
        String actualBody = response.body().asString();

        assertEquals(expectedStatusCode,actualStatusCode);
        assertEquals(expectedContentType, actualContentType);
        assertTrue(actualBody.contains(expectedBody));

    }

}
