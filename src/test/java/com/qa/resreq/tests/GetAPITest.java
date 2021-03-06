package com.qa.resreq.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.StatusCodes;
import com.qa.base.BaseTest;
import com.qa.utility.ConfigFileReader;
import com.qa.utility.Utility;

public class GetAPITest extends BaseTest {

	@BeforeClass
	public void initialize() {
		prop = ConfigFileReader.getConfig("/src/main/resources/com/qa/config/config.properties");
		serviceApiUrl = prop.getProperty("REQRESURL") + "/api/users";
	}

	@Test
	public void verifyGetAPIStatusCode() {
		Assert.assertEquals(statusCode, StatusCodes.getSuccessCode200());
	}
	
	@Test
	public void verifyGetAPIStatusCodeWithHeaders() {
		httpGet.addHeader("username", "shinu");
		httpGet.addHeader("password", "test@123");
		Assert.assertEquals(statusCode, StatusCodes.getSuccessCode200());
	}

	@Test
	public void verifyAPIResponsePerPage() {
		int perPage = Integer.parseInt(Utility.getValueByJPath(jsonObject, "/per_page"));
		Assert.assertEquals(perPage, 3);
	}

	@Test
	public void verifyAPIResponseTotal() {
		int perPage = Integer.parseInt(Utility.getValueByJPath(jsonObject, "/total"));
		Assert.assertEquals(perPage, 12);
	}

	@Test
	public void verifyAPIResponseArray() {
		int id = Integer.parseInt(Utility.getValueByJPath(jsonObject, "/data[0]/id"));
		String firstName = Utility.getValueByJPath(jsonObject, "/data[0]/first_name");
		String lastName = Utility.getValueByJPath(jsonObject, "/data[0]/last_name");
		Assert.assertEquals(id, 1);
		Assert.assertEquals(firstName, "George");
		Assert.assertEquals(lastName, "Bluth");
	}
}
