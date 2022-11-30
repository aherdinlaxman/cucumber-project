import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class TC_001 {


        @Test(threadPoolSize = 1, priority =0, invocationCount = 5)
        void get_weather_Details()
        {
            //Specify base URI
            RestAssured.baseURI="https://docs.github.com/en/rest";

            //Request object
            RequestSpecification httpRequest=RestAssured.given();

            //Response object
            Response response=httpRequest.request(Method.GET,"/repos");

            //print response in console window

            String responseBody=response.getBody().asString();
//            System.out.println("Response Body is:" +responseBody);

            //status code validation
            int statusCode=response.getStatusCode();
            System.out.println("Status code is: "+statusCode);
            Assert.assertEquals(statusCode, 200);

            //status line verification
            String statusLine=response.getStatusLine();
            System.out.println("Status line is:"+statusLine);
            Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

        }
    @Test(invocationCount = 3, priority = -6)
    void RegistrationSuccessful()
    {

       try { //Specify base URI
           RestAssured.baseURI = "http://restapi.demoqa.com/customer";

           //Request object
           RequestSpecification httpRequest = RestAssured.given();


           //Request paylaod sending along with post request
           JSONObject requestParams = new JSONObject();

           requestParams.put("FirstName", "JohnXYZ");
           requestParams.put("LastName", "XYZJohn");
           requestParams.put("UserName", "JohnXYZ");
           requestParams.put("Password", "JohnXYZxyx");
           requestParams.put("Email", "JohnXYZ@gmail.com");

           httpRequest.header("Content-Type", "application/json");

           httpRequest.body(requestParams.toJSONString()); // attach above data to the request

           //Response object
           Response response = httpRequest.request(Method.POST, "/register");


           //print response in console window

           String responseBody = response.getBody().asString();
           System.out.println("Response Body is:" + responseBody);

           //status code validation
           int statusCode = response.getStatusCode();
           System.out.println("Status code is: " + statusCode);
           Assert.assertEquals(statusCode, 201);

           //success code validation
           String successCode = response.jsonPath().get("SuccessCode");
           Assert.assertEquals(successCode, "OPERATION_SUCCESS");
       }catch(Exception e){
           System.out.println("error occured in test 2");
       }

    }
    }



