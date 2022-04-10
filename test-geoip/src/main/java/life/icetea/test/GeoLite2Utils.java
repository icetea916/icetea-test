package life.icetea.test;


import com.maxmind.geoip2.DatabaseReader;

import java.net.InetAddress;

/**
 * @author icetea
 */
public class GeoLite2Utils {

    private DatabaseReader reader;

    /**
     * @param ip ip地址
     * @return
     * @throws Exception
     * @description: 获得国家
     */
    public String getCountry(String ip) throws Exception {
        return reader.city(InetAddress.getByName(ip)).getCountry().getNames().get("zh-CN");
    }

    /**
     * @param ip ip地址
     * @return
     * @throws Exception
     * @description: 获得省份
     */
    public String getProvince(String ip) throws Exception {
        return reader.city(InetAddress.getByName(ip)).getMostSpecificSubdivision().getNames().get("zh-CN");
    }

    /**
     * @param ip ip地址
     * @return
     * @throws Exception
     * @description: 获得城市
     */
    public String getCity(String ip) throws Exception {
        return reader.city(InetAddress.getByName(ip)).getCity().getNames().get("zh-CN");
    }

    /**
     * @param ip ip地址
     * @return
     * @throws Exception
     * @description: 获得经度
     */
    public Double getLongitude(String ip) throws Exception {
        return reader.city(InetAddress.getByName(ip)).getLocation().getLongitude();
    }

    /**
     * @param ip ip地址
     * @return
     * @throws Exception
     * @description: 获得纬度
     */
    public Double getLatitude(String ip) throws Exception {
        return reader.city(InetAddress.getByName(ip)).getLocation().getLatitude();
    }

    public GeoLite2Utils(DatabaseReader reader) {
        this.reader = reader;
    }

}