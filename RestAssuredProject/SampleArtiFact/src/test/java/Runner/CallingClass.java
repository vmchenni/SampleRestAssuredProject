package Runner;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import  static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
public class CallingClass {
    public static void main(String args[]){
        System.out.println("Hello World");
        RestAssured.baseURI="https://rahulshettyacademy.com";
      String myRespString= given().queryParam("key","qaclick123").header("Content-Type","application/json").log().all().
        body("{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383454,\n" +
                "    \"lng\": 33.427352\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house1\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}").
        when().post("/maps/api/place/add/json").
        then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).
        header("server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
      System.out.println("My Response String:-"+myRespString);

        JsonPath js= new JsonPath(myRespString);

        System.out.println("Place ID is "+js.getString("place_id"));
        System.out.println("Place ID is "+js.get("status"));
        System.out.println("Place ID is "+js.get("scope"));
        System.out.println("Place ID is "+js.get("reference"));
//Put API
        String UpddatePlace=given().queryParam("key","qaclick123").
        body("{\n" +
                "\t\"place_id\": \""+js.get("place_id")+"\",\n" +
                "\t\"address\": \"new updated address By VC31\",\n" +
                "\t\"key\":\"qaclick123\"\n" +
                "}").
         when().
                put("/maps/api/place/update/json").
                then().log().all().
                assertThat().
                statusCode(200).extract().response().asString();
        JsonPath js1=new JsonPath(UpddatePlace);
        System.out.println("Confirmation message is"+js1.getString("msg"));

//Get API to get updated address
        String sResponse=   given().
                                queryParam("key","qaclick123").
                                queryParam("place_id",""+js.get("place_id")).log().all().
                            when().
                                post("/maps/api/place/get/json").
                            then().
                                log().all().assertThat().statusCode(200).extract().response().asString();

    }

}
