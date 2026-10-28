package api.CRUD;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class GetUserTest {

    @Test
    public void testGetUser() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .header("Accept", "application/json")

                .when()
                .get("users/1")

                .then()
                .log().all()
                .statusCode(200)
                .contentType("application/json")
                .body("id", equalTo(1))
                .body("name", equalTo("Leanne Graham"))
                .body("username", equalTo("Bret"))
                .body("email", equalTo("Sincere@april.biz"));
    }
}
