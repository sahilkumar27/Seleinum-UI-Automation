
package api.CRUD;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class ApiChaining {

    @Test
    public void createBookingAndGetBooking() {

        RestAssured.baseURI =
                "https://restful-booker.herokuapp.com";

        // 1. Create Booking API
        Response createResponse =
                given()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .body("{"
                                + "\"firstname\":\"Sahil\","
                                + "\"lastname\":\"Kumar\","
                                + "\"totalprice\":500,"
                                + "\"depositpaid\":true,"
                                + "\"bookingdates\":{"
                                + "\"checkin\":\"2026-11-01\","
                                + "\"checkout\":\"2026-11-05\""
                                + "},"
                                + "\"additionalneeds\":\"Breakfast\""
                                + "}")
                        .when()
                        .post("/booking")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("bookingid", notNullValue())
                        .body("booking.firstname", equalTo("Sahil"))
                        .body("booking.lastname", equalTo("Kumar"))
                        .extract()
                        .response();

        // 2. Extract generated booking ID
        int bookingId =
                createResponse.jsonPath().getInt("bookingid");

        assertTrue(
                bookingId > 0,
                "Booking ID should be greater than zero"
        );

        System.out.println("Created Booking ID: " + bookingId);

        // 3. Get Booking API using extracted ID
        Response getResponse =
                given()
                        .header("Accept", "application/json")
                        .pathParam("id", bookingId)
                        .when()
                        .get("/booking/{id}")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("firstname", equalTo("Sahil"))
                        .body("lastname", equalTo("Kumar"))
                        .body("totalprice", equalTo(500))
                        .body("depositpaid", equalTo(true))
                        .body("additionalneeds", equalTo("Breakfast"))
                        .extract()
                        .response();

        // 4. Additional Java assertions
        assertEquals(
                getResponse.jsonPath().getString("firstname"),
                "Sahil"
        );

        assertEquals(
                getResponse.jsonPath().getString("lastname"),
                "Kumar"
        );

        assertEquals(
                getResponse.jsonPath().getInt("totalprice"),
                500
        );

        System.out.println("API chaining test passed!");
    }
}
