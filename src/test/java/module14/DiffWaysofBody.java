package module14;

import PojoClass.Methods;
import PojoClass.PojoPostRequesr;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DiffWaysofBody
{

    // Using HashMap
    @Test
    public void createUsingHashMap()
    {
        HashMap data = new HashMap<>();
        data.put("name","Ajay");
        data.put("gender","Male");
        data.put("email","Ajay005@test.com");
        data.put("status","Active");

        given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Authorization","Bearer "+"5426e376dade3dd6578a2b824afd93842c899e2fb392b4ae5a76ea45b4236baf")
                .body(data)
       .when()
                .post("https://gorest.co.in/public/v2/users")
      .then()
                .statusCode(201)
                .body("name",equalTo("Ajay"))
                .body("gender",equalTo("male"))
                .log().all();
    }

    // Using Org.Json : JSONObject class
    @Test
    public void createUsingOrgJson()
    {
        JSONObject data = new JSONObject();
        data.put("name","ajay");
        data.put("gender","Male");
        data.put("email","Ajay021@test.com");
        data.put("status","Active");

        given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Authorization","Bearer "+"2b0e66fbbfb15309727e15475e9a209890fc01caaf963e45355b4f5214c03113")
                .body(data.toString())
       .when()
                .post("https://gorest.co.in/public/v2/users")
      .then()
                .statusCode(201)
                .body("name",equalTo("ajay"))
                .body("gender",equalTo("male"))
                .log().all();
    }

    //Using Pojo Approach -> Depreciated Approach right now
    @Test
    public void createUsingPOJOApproach()
    {
        PojoPostRequesr data = new PojoPostRequesr();
        data.setName("dahiya");
        data.setGender("male");
        data.setEmail("test001901@test.com");
        data.setStatus("active");

        given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Authorization","Bearer "+"2b0e66fbbfb15309727e15475e9a209890fc01caaf963e45355b4f5214c03113")
                .body(data)
       .when()
                .post("https://gorest.co.in/public/v2/users")
       .then()
                .statusCode(201)
                .body("name",equalTo(data.getName()))
                .body("gender",equalTo(data.getGender()))
                .log().all();
    }

    //Using Json File ::: Most Coommon Approach in Any Framework
    @Test
    public void createUsingJSONFILE() throws Exception {
        System.out.println(System.getProperty("user.dir"));
        Methods methods = new Methods();
        String data =methods.readJsonAsString(System.getProperty("user.dir")+"\\payloads\\create_user\\happy_path.json");
        JsonObject obj= methods.createJSONOBJ(data);


       Response response = given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Authorization","Bearer "+"2b0e66fbbfb15309727e15475e9a209890fc01caaf963e45355b4f5214c03113")
                .body(obj.toString())
       .when()
                .post("https://gorest.co.in/public/v2/users");


        System.out.println("STatsu Code Fucntin:"+response.getStatusCode());

        System.out.println( "STatsu Line Fucntin:"+response.getStatusLine());



    }
}
