package StepDefinitions.Jira;

import java.util.Map;

public class JiraPojoApi {
    private Map<String, String> session;

    public Map<String, Object> getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(Map<String, Object> loginInfo) {
        this.loginInfo = loginInfo;
    }

    private Map<String, Object> loginInfo;

    public Map<String, String> getSession() {
        return session;
    }

    public void setSession(Map<String, String> session) {
        this.session = session;
    }

}

