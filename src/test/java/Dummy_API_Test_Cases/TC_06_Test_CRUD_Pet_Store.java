package Dummy_API_Test_Cases;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import payload.user;
import payload.user_end_points;

public class TC_06_Test_CRUD_Pet_Store {

    Faker faker;
    user userPayload;
    public static Logger logger;

    @BeforeClass
    public void generateTestData() {

        logger = LogManager.getLogger(this.getClass());

        System.out.println("\n========== TEST DATA GENERATION START ==========");

        faker = new Faker();
        userPayload = new user();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        logger.info("Generated User Data: " + userPayload.getUsername());

        System.out.println("========== TEST DATA GENERATION END ==========\n");
    }

    @Test(priority = 1)
    public void testCreateUser() {

        System.out.println("\n========== CREATE USER TEST START ==========");

        logger.info("Sending POST request to create user...");
        Response response = user_end_points.createUser(userPayload);

        response.then().log().body();

        logger.info("Response Status Code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("✅ CREATE USER PASSED");

        System.out.println("========== CREATE USER TEST END ==========\n");
    }

    @Test(priority = 2)
    public void testGetUserData() {

        System.out.println("\n========== GET USER TEST START ==========");

        logger.info("Fetching user: " + userPayload.getUsername());

        Response response = user_end_points.GetUser(userPayload.getUsername());

        response.then().log().body();

        logger.info("Response Status Code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("✅ GET USER PASSED");

        System.out.println("========== GET USER TEST END ==========\n");
    }

    @Test(priority = 3)
    public void testUpdateUser() {

        System.out.println("\n========== UPDATE USER TEST START ==========");

        String updatedName = faker.name().firstName();
        userPayload.setFirstName(updatedName);

        logger.info("Updating user first name to: " + updatedName);

        Response response = user_end_points.UpdateUser(userPayload.getUsername(), userPayload);

        response.then().log().body();

        logger.info("Response Status Code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        // Verify update
        Response responsePostUpdate = user_end_points.GetUser(userPayload.getUsername());

        logger.info("Verifying updated data...");
        responsePostUpdate.then().log().body();

        logger.info("✅ UPDATE USER PASSED");

        System.out.println("========== UPDATE USER TEST END ==========\n");
    }

    @Test(priority = 4)
    public void testDeleteUser() {

        System.out.println("\n========== DELETE USER TEST START ==========");

        logger.info("Deleting user: " + userPayload.getUsername());

        Response response = user_end_points.DeleteUser(userPayload.getUsername());

        response.then().log().body();

        logger.info("Response Status Code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("✅ DELETE USER PASSED");

        System.out.println("========== DELETE USER TEST END ==========\n");
    }
}