package com.cydeo.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters {

    //BeforeAll is an annotation equals to @BeforeClass in testNg, we use with static method
    //it's gonna be executed at once before everything
    @BeforeAll
    public static void init(){

        //save baseurl inside this variable so that we don't need to type each http method
        baseURI = "http://54.234.237.17:8000"; //oracle database

    }

    /*
    Given accept tyoe is Json
    And Id parameter value is 5
    When user send Get request to /api.spartans/{id}
    Then response status code should be 200
    And response content-type: application/json
    And "Blythe" should be in response payload (payload is the body)
     */

    @DisplayName("")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParams("id", 5)
                .when().get("/api/spartans/{id}");
        //{id} inside get method will be dynamic, parameterized


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Blythe"));

    }


    /*
    task
    Given accept type is Json
    And Id parameter value is 500
    When user sends Get request to /api/spartans/{id}
    Then response status code sghould be 404
    And response content type: appliaciton/json
    And "Not found" message should be in response payload
     */

    @Test
    public void test2(){

        Response response =
                given().accept(ContentType.JSON)
                .and().pathParams("id", 500)
                .when().get("/api/spartans/{id}");

        assertEquals(404, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Not Found"));

    }


    /*
    task
    Given accept type is Json
    And query paramater values are:
    gender|Female
    nameCOntains|e
    When user sends GET request to /api/spartans/search
    Then response status code should be 200
    And response content-type: application/json
    And "Female" should be in response payload
    And "Janette" should be in response payload
     */

    @Test
    public void test3(){
        Response response = given().log().all().accept(ContentType.JSON)
                //log all methods are here working like printing what we write
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when().get("/api/spartans/search");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }



    @DisplayName("GET request to /api/spartans/search with Query Params (MAP")
    @Test
    public void test4(){
        //create a map and add query parameters

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");

        Response response = given().log().all().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/api/spartans/search");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

    }


}
