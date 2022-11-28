package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .log().all()
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print hasmore result
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first country id
        System.out.println("response.path(\"items[0].country_id\") = " + response.path("items[0].country_id"));

        //print second country name
        System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));

        //print http://52.207.61.129:1000/ords/hr/countries/CA
        System.out.println("response.path(\"items[2].links[0].href\") = " + response.path("items[2].links[0].href"));

        //get me all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert all the regions id are 2
        List<Integer> allRegionIds = response.path("items.region_id");

        for (Integer eachRegionId : allRegionIds) {
            assertEquals(2, eachRegionId);
        }

    }

    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .log().all()
                .when().get("/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        List<String> allJobIDs = response.path("items.job_id");

        for (String allJobID : allJobIDs) {
            assertEquals("IT_PROG", allJobID);
        }

    }

}
