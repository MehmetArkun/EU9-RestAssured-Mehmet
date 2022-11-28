package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to countries with JsonPath")
    @Test
    public void test1(){

        Response response = get("/countries");

        //get the second country name with jsonpath
        JsonPath jsonPath = response.jsonPath();
        String secondCountryName = jsonPath.get("items[1].country_name");

        //get all country ids
        List<String> country_ids = jsonPath.getList("items.country_id");

        //get all country names where region id is equal to 2
        List<Objects> countryNameWithRegionId2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        //it refers to each here -> each region id

    }

    @Test
    public void test2(){
        //we added limit query param to get 107 employees
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("limit", 107)
                .log().all()
                .when().get("/employees");

        //get me all email of employees who is working as IT_PROG
        JsonPath jsonPath = response.jsonPath();
        List<String> emails = jsonPath.getList("items.findAll {it.job_id == \"IT_PROG\"}.email");

        //get me first name of employees who is making more than 10.000
        List<String> firstNames = jsonPath.getList("items.findAll {it.salary > 10000}.first_name");
        System.out.println("firstNames = " + firstNames);

        //get the max salary first_name
        String kingFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);


    }


}
