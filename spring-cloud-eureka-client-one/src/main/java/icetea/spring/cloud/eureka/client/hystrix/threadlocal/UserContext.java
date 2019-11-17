package icetea.spring.cloud.eureka.client.hystrix.threadlocal;

public class UserContext {
    public static final String CORRELATIKON_ID = "tmx-correlation-id";
    public static final String USER_ID = "";
    public static final String AUTH_TOKEN = "";
    public static final String ORG_ID = "'";

    private String correlationId;
    private String userId;
    private String authToken;
    private String orgId;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
