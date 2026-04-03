package Dummy_API_Test_Cases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import endPoints.routes;
import io.restassured.RestAssured;

public class TC_02_Get_Specific_emp {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = routes.base_url;
    }

    @Test
    public void testGetSpecificEmployee() {

        given()
            .pathParam("id", 1)
        .when()
            .get(routes.GET_SINGLE_EMPLOYEE)
        .then()
            .statusCode(200)
            .time(lessThan(5000L))

            
            .body("status", equalTo("success"))
            .body("message", equalTo("Successfully! Record has been fetched."))

            
            .body("data.id", equalTo(1))
            .body("data.employee_name", equalTo("Tiger Nixon"))
            .body("data.employee_salary", equalTo("320800"))
            .body("data.employee_age", equalTo("61"))
            .body("data.profile_image", equalTo(""))

            .log().all();
    }
}