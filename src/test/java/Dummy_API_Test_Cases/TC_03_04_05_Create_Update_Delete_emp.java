package Dummy_API_Test_Cases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import endPoints.routes;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TC_03_04_05_Create_Update_Delete_emp {

    // ✅ Global ID
    public static int empId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = routes.base_url;
    }

    // ================= CREATE API =================
    @Test(priority = 1)
    public void testCreateEmployee() {

        String name = "AutoEmp_" + System.currentTimeMillis();
        String salary = String.valueOf((int)(Math.random() * 100000));
        String age = String.valueOf((int)(Math.random() * 50) + 20);

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("salary", salary);
        requestBody.put("age", age);

        Response response = sendCreateRequestWithRetry(requestBody);

        // ✅ Store ID
        empId = response.jsonPath().getInt("data.id");
        System.out.println("Created Employee ID: " + empId);

        response.then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("data.id", notNullValue())
                .log().all();
    }

    // ================= UPDATE API =================
    @Test(priority = 2)
    public void testUpdateEmployee() {

        if (empId == 0) {
            Assert.fail("Employee ID not created.");
        }

        String updatedName = "UpdatedEmp_" + System.currentTimeMillis();
        String updatedSalary = String.valueOf((int)(Math.random() * 100000));
        String updatedAge = String.valueOf((int)(Math.random() * 50) + 20);

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", updatedName);
        requestBody.put("salary", updatedSalary);
        requestBody.put("age", updatedAge);

        Response response = sendUpdateRequestWithRetry(requestBody, empId);

        response.then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("data.name", equalTo(updatedName))
                .body("data.salary", equalTo(updatedSalary))
                .body("data.age", equalTo(updatedAge))
                .log().all();
    }

    // ================= DELETE API =================
    @Test(priority = 3)
    public void testDeleteEmployee() {

        if (empId == 0) {
            Assert.fail("Employee ID not available for deletion.");
        }

        Response response = sendDeleteRequestWithRetry(empId);

        response.then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", containsString("successfully"))
                .log().all();
    }

    // ================= RETRY - CREATE =================
    public Response sendCreateRequestWithRetry(JSONObject requestBody) {

        int maxRetries = 3;
        Response response = null;

        for (int i = 0; i < maxRetries; i++) {

            response = given()
                    .contentType(ContentType.JSON)
                    .body(requestBody.toString())
                    .when()
                    .post(routes.CREATE_EMPLOYEE);

            if (response.getStatusCode() == 200) {
                return response;
            }

            System.out.println("Retrying Create... Attempt: " + (i + 1));
            sleep();
        }

        Assert.fail("Create API failed after retries");
        return response;
    }

    // ================= RETRY - UPDATE =================
    public Response sendUpdateRequestWithRetry(JSONObject requestBody, int empId) {

        int maxRetries = 3;
        Response response = null;

        for (int i = 0; i < maxRetries; i++) {

            response = given()
                    .contentType(ContentType.JSON)
                    .pathParam("id", empId)
                    .body(requestBody.toString())
                    .when()
                    .put(routes.UPDATE_EMPLOYEE);

            if (response.getStatusCode() == 200) {
                return response;
            }

            System.out.println("Retrying Update... Attempt: " + (i + 1));
            sleep();
        }

        Assert.fail("Update API failed after retries");
        return response;
    }
    
    

    // ================= RETRY - DELETE =================
    public Response sendDeleteRequestWithRetry(int empId) {

        int maxRetries = 3;
        Response response = null;

        for (int i = 0; i < maxRetries; i++) {

            response = given()
                    .pathParam("id", empId)
                    .when()
                    .delete(routes.DELETE_EMPLOYEE);

            if (response.getStatusCode() == 200) {
                return response;
            }

            System.out.println("Retrying Delete... Attempt: " + (i + 1));
            sleep();
        }

        Assert.fail("Delete API failed after retries");
        return response;
    }

    // ================= COMMON SLEEP =================
    public void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}