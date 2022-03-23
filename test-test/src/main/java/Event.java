import lombok.Data;

/**
 * 事件点
 *
 * @author icetea
 */
@Data
public class Event {

    /**
     * 用户信息
     */
    private User user;
    /**
     * 平台信息
     */
    private Platform platform;
    /**
     * 事件戳
     */
    private Long time;
    /**
     * 事件名称:如加入购物车，点击等
     */
    private String eventName;
    /**
     * 事件信息
     */
    private EventProperties properties;

}
