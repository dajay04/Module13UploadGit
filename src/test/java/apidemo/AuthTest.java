package apidemo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AuthTest
{
    @Test
    public void testAuth()
    {
        // https://reqres.in/api/users?page=2&id=8
        given()
                .auth().basic("postman","password")
      .when()
                .get("https://postman-echo.com/basic-auth")
      .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();

    }
}
