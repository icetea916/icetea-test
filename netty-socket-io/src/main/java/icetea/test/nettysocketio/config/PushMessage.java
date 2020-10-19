package icetea.test.nettysocketio.config;


public class PushMessage {

    // 登录用户编号
    private Integer loginUserNum;
    // 消息内容
    private String content;


    public Integer getLoginUserNum() {
        return loginUserNum;
    }

    public void setLoginUserNum(Integer loginUserNum) {
        this.loginUserNum = loginUserNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PushMessage{" +
                "loginUserNum=" + loginUserNum +
                ", content='" + content + '\'' +
                '}';
    }

}
