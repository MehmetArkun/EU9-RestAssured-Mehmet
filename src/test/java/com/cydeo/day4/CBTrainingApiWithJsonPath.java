package com.cydeo.day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we don't need to type each http method
        RestAssured.baseURI = "http://api.training.cydeo.com";
    }

    @DisplayName("GET Request to individual student")
    @Test
    public void test1(){

        //send a get request to student id 23401 as a path parameter
        //verify status code 200 / content type application/json;charset=UTF-8 / Content-Encoding = gzip
        //Verify Date headear exists
        //assert that
        /*
        firstName Vera
        batch 14
        section 12
        emailAddress aaa@gmail.com
        companyName Cybertek
        state IL
        zipCode 60606
         */

        Response response = given().accept(ContentType.JSON)
                .param("id", 23401)
                .get("/student/{id}");

        JsonPath jsonPath = response.jsonPath();

        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertEquals("gzip",response.header("Content-Encoding"));
        assertTrue(response.headers().hasHeaderWithName("Date"));





    }


}
