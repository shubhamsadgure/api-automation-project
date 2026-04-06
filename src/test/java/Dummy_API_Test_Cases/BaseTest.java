package Dummy_API_Test_Cases;

import org.testng.annotations.BeforeClass;
import endPoints.routes;
import io.restassured.RestAssured;

public class BaseTest {

	@BeforeClass
    public void setup() {
        RestAssured.baseURI = routes.base_url;
    }
}
