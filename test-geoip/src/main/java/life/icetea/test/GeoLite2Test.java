package life.icetea.test;

import com.maxmind.geoip2.DatabaseReader;

import java.io.InputStream;

/**
 * @author icetea
 */
public class GeoLite2Test {

    public static void main(String[] args) throws Exception {
        // 创建 GeoLite2 数据库
        InputStream resourceAsStream = GeoLite2Test.class.getClass().getResourceAsStream("/GeoLite2-City_20220315/GeoLite2-City.mmdb");
        // 读取数据库内容
        DatabaseReader reader = new DatabaseReader.Builder(resourceAsStream).build();

        // 创建工具类
        GeoLite2Utils geoLite2Utils = new GeoLite2Utils(reader);

        // 访问IP
        String ip = "14.5.6.2";
        String site = "国家：" + geoLite2Utils.getCountry(ip)
                + "\n省份：" + geoLite2Utils.getProvince(ip)
                + "\n城市：" + geoLite2Utils.getCity(ip) + "\n经度："
                + geoLite2Utils.getLongitude(ip) + "\n纬度："
                + geoLite2Utils.getLatitude(ip);
        System.out.println(site);
    }

}
