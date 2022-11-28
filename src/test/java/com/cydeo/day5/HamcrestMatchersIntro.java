package com.cydeo.day5;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {
    @DisplayName("")
    @Test
    public void simpleTest1(){
        //Hamcrest is a assertion coming from RestAssured library. Assertion in plain english.

        assertThat(5+5, is(10));
        MatcherAssert.assertThat(6-7, Matchers.is(-1));
        MatcherAssert.assertThat(2+2, Matchers.equalTo(4));
        //matchers has 2 overloaded version
        //first that accept actual value
        //second that accept another matchers
        //below example is method is accepting another matchers equal to make it readable
        MatcherAssert.assertThat(2+3, is(equalTo(5)));

        assertThat(5+5, not(9));
        assertThat(5+5, is(not(9)));
        assertThat(5+5, is(not(equalTo(9))));

        //number comparison
        MatcherAssert.assertThat(5+5, Matchers.is(greaterThan(9)));
    }

    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest(){

        String text = "B22 is learning Hamcrest";

        //checking for equality is same as numbers
        assertThat(text, is("B22 is learning Hamcrest"));
        assertThat(text, equalTo("B22 is learning Hamcrest"));
        assertThat(text, is(equalTo("B22 is learning Hamcrest")));

        //check if this text starts with B22
        assertThat(text, startsWith("B22"));

        //now do it in case insestive manner
        assertThat(text, startsWithIgnoringCase("b22"));

        //ends with
        assertThat(text, endsWith("rest"));

        //check if text contains String learning
        assertThat(text, containsString("learning"));
        assertThat(text, containsStringIgnoringCase("LEARNING"));

        String str = "  ";

        //check if above str is blank
        assertThat(str, blankString());

        //check if trimmed str is emoty string
        assertThat(str.trim(), emptyString());
    }

    @DisplayName("Hamcrest for Collection")
    public void testCollection(){
        List<Integer> listOfNumbers = Arrays.asList(1,4,5,6,32,54,66,77,45,23);

        //check size of the list
        assertThat(listOfNumbers, hasSize(10));

        //check if this list has item 77
        assertThat(listOfNumbers, hasItem(77));

        //check if this list hasItems 77, 54, 23
        assertThat(listOfNumbers, hasItems(77, 54, 23));

        //check all numbers are greater than 0
        assertThat(listOfNumbers, everyItem(greaterThan(0)));
    }



}
