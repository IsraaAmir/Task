package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Rest {

    String baseUrl = "https://api.genderize.io";

    @Test
    public void test01_verifyGenderFieldExists() {
        given()
                .baseUri(baseUrl)
                .queryParam("name", "Alice")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .body("gender", notNullValue());
    }

    @Test
    public void test02_verifyResponseTime() {
        given()
                .baseUri(baseUrl)
                .queryParam("name", "Emma")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(2000L));
    }

    @Test
    public void test03_verifyMultipleNames() {
        given()
                .baseUri(baseUrl)
                .queryParam("name[]", new String[]{"Alice", "John", "Michael"})
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void test04_verifyMissingParameterError() {
        given()
                .baseUri(baseUrl)
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(422); // Genderize returns 422 if name param missing
    }
    @Test
    public void test05_getGenderForValidName() {
        given()
                .baseUri(baseUrl)
                .queryParam("name", "Alice")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .body("gender", equalTo("female"))
                .body("probability", notNullValue());
    }
}


