import lombok.Data;

/**
 * 事件用户信息
 *
 * @author icetea
 */
@Data
public class User {

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名称
     */
    private Integer username;
    /**
     * 省名称
     */
    private Integer provinceName;
    /**
     * 市名称
     */
    private Integer cityName;
    /**
     * 区、县名称
     */
    private Integer countryName;

}
