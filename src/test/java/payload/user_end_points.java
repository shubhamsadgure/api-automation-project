package payload;

import static io.restassured.RestAssured.given;

import endPoints.routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class user_end_points {
	
	public static Response createUser(user payload)
	{
		Response response = given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(payload)
		
		
		.when()
		.post(routes.post_url_pet);
		
		return response;
	}
	
	
	public static Response GetUser(String userName)
	{
		Response response = given()
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		
		.when()
		.get(routes.get_url_pet);
		
		return response;
	}
	
	
	public static Response UpdateUser(String userName, user payload)
	{
		Response response = given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		.body(payload)
		
		
		.when()
		.put(routes.put_url_pet);
		
		return response;
	}
	
	
	public static Response DeleteUser(String userName)
	{
		Response response = given()
		.accept(ContentType.JSON)
		.pathParam("username", userName)
			
		
		.when()
		.delete(routes.del_url_pet);
		
		return response;
	}

}
