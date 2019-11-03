package org.ashish.springboot.practice;

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

    @Test
    void testPortValidity() {
        testLogger.info("Web service initialized on port "+port);
    }

    @Test(dependsOnMethods = "testPortValidity")
    void testPlayerController() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:"+port+"/players/test",String.class);
        Assert.assertNotNull(responseEntity);
        String responseBody = responseEntity.getBody();
        Assert.assertFalse(Strings.isNullOrEmpty(responseBody));
        testLogger.info("Response from test API call : "+responseBody);
        Assert.assertEquals(responseBody, "API works!");
    }
}
