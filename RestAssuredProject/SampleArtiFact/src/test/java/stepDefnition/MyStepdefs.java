package stepDefnition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import pojoclasses.UserListReponse;

import static io.restassured.RestAssured.*;


public class MyStepdefs {
    JSONObject mysjon= new JSONObject();
    Response response;
    @Given("User hits URL")
    public void userHitsURL() {
        mysjon.put("name","Vishwanath");
        mysjon.put("job","Engineer");
        System.out.println("Inside userHitsURL");
                response= given().log().all().header("Content-Type","application/json").accept(ContentType.JSON).
                body(mysjon.toJSONString()).
                when().post("https://reqres.in/api/users").
                then().statusCode(201).extract().response();
    }

    @Then("Verify the status code as {int}")
    public void verifyTheStatusCodeAs(int arg0) {
        System.out.println("Inside verifyTheStatusCodeAs");
        System.out.println("Status code is :"+response.statusCode());

    }

    @Given("User gets list of users")
    public void userGetsListOfUsers() {
        UserListReponse listReponse=given().
                expect().log().all().
                    defaultParser(Parser.JSON).
                when().
                    get("https://reqres.in/api/users?page=2").
                then().
                    statusCode(200).
                    extract().
                    as(UserListReponse.class);

        System.out.println("Page number is :-"+listReponse.getPage());
        System.out.println("Url is:-"+listReponse.support.getUrl());
       int iSize=listReponse.getData().size();
       System.out.println("Last first name is:-"+listReponse.getData().get(iSize-1).last_name);

    }
}
