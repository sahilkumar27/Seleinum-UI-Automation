package api.CRUD;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class Authentication {

    @Test
    public void authenticate() {

        given()
                .param("page", "2")
                .auth().basic("sahilkumar", "Password@123")
//                .auth().digest("sahilkumar", "Password@123")
//                .auth().form("sahilkumar", "Password@123")
//                .auth().oauth2("your_access_token_here") // Replace with a valid access token
                .when().get("https://reqres.in/api/users")
                .then().log().all()
                .statusCode(200).body("page", equalTo(2));

    }

    @Test
    public void checkResponseTime(){
        Response getResponse = given()
                .param("page", "2")
                .auth().none()
                .when().get("https://reqres.in/api/users");

                getResponse.getTime();
                getResponse.getTimeIn(TimeUnit.SECONDS);
                getResponse.time();
                getResponse.timeIn(TimeUnit.SECONDS);


        System.out.println("1-Time is: " + getResponse.getTime());
        System.out.println("2-Time is: " + getResponse.getTimeIn(TimeUnit.SECONDS));
        System.out.println("3-Time is: " + getResponse.time());
        System.out.println("4-Time is: " + getResponse.timeIn(TimeUnit.SECONDS));
    }

}
