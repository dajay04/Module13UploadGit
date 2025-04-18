package apidemo;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HTTPRequestHandling
{
    static int ID;
    @Test(priority = 1)
    public void getUsers()
    {
        given()

       .when()
                .get("https://reqres.in/api/users?page=2")
       .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .log().all();
    }

    @Test(priority = 2)
    public void createUsers()
    {
        HashMap payload = new HashMap();
        payload.put("name","Ajay Dahiya");
        payload.put("job","Educator at Geeks for Geeks and Also on Youtube");

      ID=  given()
                .contentType("application/json")
                .body(payload)
           .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");

    }

    @Test(priority = 3,dependsOnMethods = {"createUsers"})
    public void updateUsers()
    {
        HashMap payload = new HashMap();
        payload.put("name","Learn Testing By AD");
        payload.put("job","Geeks for Geeks Educator");

       given()
                .contentType("application/json")
                .body(payload)
      .when()
                .put("https://reqres.in/api/users/"+ID)
     .then()
               .statusCode(200)
               .log().all();
    }

    @Test(priority = 3,dependsOnMethods = {"updateUsers"})
    public void deleteUsers()
    {
        given()

       .when()
                .delete("https://reqres.in/api/users/"+ID)
      .then()
                .statusCode(204)
                .log().all();
    }

}
