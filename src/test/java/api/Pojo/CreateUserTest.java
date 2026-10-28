package api.Pojo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class CreateUserTest {

    @Test
    public void createUserTest(){
        UserRequest userRequest = new UserRequest("Sahil Kumar", "QA");

        UserResponse userResponse =
                given()
                        .baseUri("https://reqres.in/api")
                        .basePath("/users")
                        .contentType("application/json")
                        .body(userRequest)
                        .when()
                        .post()
                        .then()
                        .log().all()
                        .statusCode(201)
                        .extract()
                        .as(UserResponse.class);

        assertEquals(userResponse.getName(), "Sahil Kumar");
        assertEquals(userResponse.getJob(), "QA");
        assertNotNull(userResponse.getID());
    }
}
