package Utils;

import StepDefinitions.Jira.JiraPojoApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import java.io.IOException;
import java.net.URISyntaxException;

public class PayLoadUtils {
    public static String cookieAuthPayLoad(){
        return "{\n" +
                "    \"username\": \"scriptedpvp\",\n" +
                "    \"password\": \"Youaregoodboy.1\"\n" +
                "\n" +
                "\n" +
                "}";
    }
    public static String getJiraIssuePayLoad(String summary,String description,String issueType){
        return "{\n" +
                "  \"fields\": {\n" +
                "    \"project\":{\n" +
                "      \"key\": \"TEC\"\n" +
                "    },\n" +
                "    \"summary\": \""+summary+"\",\n" +
                "    \"description\": \""+description+"\",\n" +
                "    \"issuetype\":{\n" +
                "      \"name\": \""+issueType+"\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }
    public static String getJsessionCookie() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost("localhost").setPort(8080).setPath("rest/auth/1/session");
        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        HttpEntity entity = new StringEntity(PayLoadUtils.cookieAuthPayLoad());
        httpPost.setEntity(entity);
        HttpResponse response = client.execute(httpPost);
        Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(response.getStatusLine().getStatusCode());
        JiraPojoApi jiraPojoApi = objectMapper.readValue(response.getEntity().getContent(), JiraPojoApi.class);
        System.out.println(jiraPojoApi.getSession().get("name"));
        System.out.println(jiraPojoApi.getSession().get("value"));
        //rest/api/2/issue
        String cookieName = jiraPojoApi.getSession().get("name");
        String cookieValue = jiraPojoApi.getSession().get("value");
        return String.format("%s=%s", cookieName,cookieValue);
    }

}
