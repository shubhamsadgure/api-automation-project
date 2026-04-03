package Dummy_API_Test_Cases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import endPoints.routes;
import io.restassured.RestAssured;

public class TC_01_Get_emp {
	
	@BeforeClass
    public void setup() {
        RestAssured.baseURI = routes.base_url;
    }
    

    @Test
    public void testGetAllEmployees() {

        given()
        .when()
            .get(routes.GET_ALL_EMPLOYEES)
        .then()
            .statusCode(200)
            .time(lessThan(5000L))
            .body("status", equalTo("success"))
            .body("data", notNullValue())
            .log().all();
    }

}
