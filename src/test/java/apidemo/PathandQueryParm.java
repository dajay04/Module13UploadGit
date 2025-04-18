package apidemo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathandQueryParm
{
    @Test
    public void testPathQueryParm()
    {
        // https://reqres.in/api/users?page=2&id=8
        given()
                .pathParam("Path_1","api")
                .pathParam("Path_2","users")
                .queryParam("page",1)
                .queryParam("id",3)
        .when()
                .get("https://reqres.in/{Path_1}/{Path_2}")
        .then()
                .statusCode(200)
                .log().all();

    }
}
