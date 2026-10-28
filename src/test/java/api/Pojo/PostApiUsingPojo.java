package api.Pojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostApiUsingPojo {

    public static void main(String[] args) {
        EmployeePojo ep = new EmployeePojo("morpheus", "leader", new String[]{"Java", "C"}, "XYZ", "morpheus@xyz.com");

        //? Q9. How to validate the response of a POST API ?
        Response response = given()
                .auth().none()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .when()
                .body(ep).log().all()
                .post("https://reqres.in/api/users");
//                .then().log().all()
//                .body("name", equalTo("morpheus"),
//                        "job", equalTo("leader"),
//                        "skills[0]", equalTo("Java"),
//                        "skills[1]", equalTo("C"),
//                        "details.companyName", equalTo("XYZ"),
//                        "details.emailId", equalTo("morpheus@xyz.com"));

//        Assert.assertTrue(response.getStatusCode() == 201);
//        Assert.assertEquals(response.getBody().path("name"), "morpheus");
//        Assert.assertEquals(response.getBody().path("job"), "leader");
//        Assert.assertEquals(response.getBody().path("skills[0]"), "Java");
//        Assert.assertEquals(response.getBody().path("skills[1]"), "C");
//        Assert.assertEquals(response.getBody().path("details.companyName"), "XYZ");
//        Assert.assertEquals(response.getBody().path("details.emailId"), "morpheus@xyz.com1");


        //?Q.10 How to verify cookie, headers & content-type in API response
        response.getHeaders();
        System.out.println("Headers are: " + response.getHeaders());
        System.out.println("Headers-Content-Type is: " + response.header("Content-Type"));
        System.out.println("Headers-Date is: " + response.header("Date"));
        System.out.println("Headers-Server is: " + response.header("Server"));
        System.out.println("Headers-Content-Length is: " + response.header("Content-Length"));
        System.out.println("Headers-Connection is: " + response.header("Connection"));
        System.out.println("Headers-Content-Encoding is: " + response.header("Content-Encoding"));
        System.out.println("All the cookies are: " + response.getCookies());


    }
}
