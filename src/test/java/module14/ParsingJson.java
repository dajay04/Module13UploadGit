package module14;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ParsingJson
{
    @Test
    public void JSONPathUnderstanding() throws Exception
    {
        File file = new File(System.getProperty("user.dir")+"\\payloads\\config.json");

        boolean browserheadless =JsonPath.read(file,"$.ExecutionEnvironment.EnvironmentDetails.Headless");
        System.out.println(browserheadless);

        String CLient_Id =JsonPath.read(file,"$.environments.Salesforce_Lightening_API.E2ESandBox.PARTNERACCOUNTCLIENTID");
        System.out.println(CLient_Id);
    }

    // Using Response Class Objet + JSONPath
    @Test(priority = 1)
    public void createUserParsingResponse()
    {
       Response obj= given()
                .contentType("application/json")
       .when()
                .get("https://reqres.in/api/users?page=2");

        int StatusCode_Returned =obj.getStatusCode();
        System.out.println(StatusCode_Returned);

        String headerRespone =obj.header("Transfer-Encoding");
        System.out.println(headerRespone);

        String completeResponse =obj.asString();
        System.out.println(completeResponse);

       io.restassured.path.json.JsonPath jsonPathEvaltoru= obj.jsonPath();
       String JobDataID= jsonPathEvaltoru.getString("data[5].first_name");
       System.out.println(JobDataID);
    }


    @Test(priority = 2)
    public void ParsingUsingJsonObject()
    {
        HashMap payload = new HashMap();
        payload.put("name","Ajay Dahiya");
        payload.put("job","Educator at Geeks for Geeks and Also on Youtube");

      Response response=  given()
                .contentType("application/json")
                .body(payload)
       .when()
                .post("https://reqres.in/api/users");

        String jsonResponse=response.asString();
        JSONObject obj = new JSONObject(jsonResponse);

        String job = obj.getString("job");
        System.out.println(job);
    }


}
