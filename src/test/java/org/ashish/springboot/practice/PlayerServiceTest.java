package org.ashish.springboot.practice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.ashish.springboot.practice.model.Player;
import org.junit.Ignore;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.util.Strings;

/**
 * Unit tests for service
 * WHen using Junit 4, add @RunWIth(SpringRunner.class)
 * If using Junit 5, this is not needed.
 *
 * The {@link BaseTest} class is annotated with
 * {@link org.springframework.boot.test.context.SpringBootTest} to
 * indicate that we want an embedded server to be started at a random
 * port (specified by SpringBootTest.WebEnvironment.RANDOM_PORT)
 *
 * We are using TestNG because it simplifies running tests in
 * order rather than running each test case independently.
 */
@Test
public class PlayerServiceTest extends BaseTest{

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    final ObjectMapper resultMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    final OkHttpClient client = new OkHttpClient();

    @Test
    void testPortValidity() {
        testLogger.info("Web service initialized on port "+port);
    }

    @Ignore
    @Test(dependsOnMethods = "testPortValidity")
    void testPlayerController() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:"+port+"/players/test",String.class);
        Assert.assertNotNull(responseEntity);
        String responseBody = responseEntity.getBody();
        Assert.assertFalse(Strings.isNullOrEmpty(responseBody));
        testLogger.info("Response from test API call : "+responseBody);
        Assert.assertEquals(responseBody, "API works!");
    }

    @Test(dependsOnMethods = "testPortValidity")
    void testCreatePlayer() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:"+port+"/players/create/json")
                .post(RequestBody.create(MediaType.parse("application/json"),"{\"firstName\":\"LeBron\",\"lastName\":\"James\",\"name\":\"LeBron James\",\"country\":\"USA\"}"))
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        ResponseBody responseBody = response.body();
        Assert.assertNotNull(responseBody);
        String responseBodyStr = responseBody.string();
        Assert.assertFalse(Strings.isNullOrEmpty(responseBodyStr));
        Player createdPlayer = resultMapper.readValue(responseBodyStr,Player.class);
        Assert.assertNotNull(createdPlayer);
    }
}
