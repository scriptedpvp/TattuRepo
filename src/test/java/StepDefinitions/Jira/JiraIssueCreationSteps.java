package StepDefinitions.Jira;

import Pages.JiraHomePage;
import Pages.JiraLoginPage;
import Utils.ConfigReader;
import Utils.Driver;
import Utils.PayLoadUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class JiraIssueCreationSteps {
    JiraLoginPage loginPage;
    JiraHomePage homePage;
    WebDriver driver;
    URIBuilder uriBuilder = new URIBuilder();
    HttpResponse response;
    ObjectMapper objectMapper = new ObjectMapper();
    List<String> keys;
    @Given("the user creates jiraStory by providing {string},{string}, {string}")
    public void the_user_creates_jiraStory_by_providing(String summary, String description, String issueType) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost("localhost").setPort(8080).setPath("rest/api/2/issue");
        HttpPost httpPost=  new HttpPost(uriBuilder.build());
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Cookie",PayLoadUtils.getJsessionCookie());
        HttpEntity entity = new StringEntity(PayLoadUtils.getJiraIssuePayLoad(summary,description,issueType));
        httpPost.setEntity(entity);
        HttpResponse response = client.execute(httpPost);
        Assert.assertEquals(HttpStatus.SC_CREATED,response.getStatusLine().getStatusCode());
        objectMapper = new ObjectMapper();
        JiraPojoApi responseParse = objectMapper.readValue(response.getEntity().getContent(),JiraPojoApi.class);



    }

    @When("the user verify key,{string}, {string}")
    public void the_user_verify_key(String summary, String description) {
        driver= Driver.getDriver();
        loginPage = new JiraLoginPage(driver);
        homePage = new JiraHomePage(driver);
        driver.get("http://localhost:8080");
        loginPage.logIn(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));
        homePage.boardButton.click();
        homePage.tecBoard.click();
        homePage.summaries.get(homePage.summaries.size()-1).click();
        Assert.assertEquals(keys,homePage.keys.get(homePage.keys.size()-1).getText());
        Assert.assertEquals(summary,homePage.summaries.get(homePage.summaries.size()-1).getText() );
        Assert.assertEquals(description,homePage.descrip.getText());




    }
    @Then("the user get key, {string},{string}")
    public void the_user_get_key(String summary, String description) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
//        http://localhost:8080/rest/api/2/issues
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost("localhost").setPort(8080).setPath("rest/api/2/search");
        HttpGet get = new HttpGet(uriBuilder.build());

        get.setHeader("Accept","application/json");
        get.setHeader("Cookie",PayLoadUtils.getJsessionCookie());
        HttpResponse response = client.execute(get);
        JsonNode deso = objectMapper.readTree(response.getEntity().getContent());
        JsonNode issues = deso.get("key");

        for(JsonNode issue : issues ){
            keys.add(issue.asText());
        }




    }

}
