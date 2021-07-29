package life.icetea.test.nettysocketio.listener;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Map;

/**
 * 权限listener
 * 注意：该listener只有当connect首次连接时才会触发一次验证
 * 功能：
 * 1. 认证权限,此时session还没有建立，只能通过ip，url,query参数，headers,等条件判断权限,这些都存在HandshakeData中
 * 2. init当前会员用户的数据
 *
 * @author icetea
 */
@Slf4j
public class MyAuthorizationListener implements AuthorizationListener {

    @Override
    public boolean isAuthorized(HandshakeData data) {
        Date time = data.getTime();
        System.out.println(time);

        // ip等域名等信息获取
        InetSocketAddress inetSocketAddress = data.getAddress();
        InetAddress inetAddress = inetSocketAddress.getAddress();
        System.out.println("hostName:" + inetAddress.getHostName());
        System.out.println("hostName:" + inetSocketAddress.getHostName());
        System.out.println("hostAddress:" + inetAddress.getHostAddress()); // 客户端ip地址
        System.out.println("port:" + inetSocketAddress.getPort()); // 客户端的port
        System.out.println("hostString:" + inetSocketAddress.getHostString());

        // url 字符串
        System.out.println("url = " + data.getUrl());

        // url参数获取
        String username = data.getSingleUrlParam("username");
        if (username == null || "".equals(username)) {
            log.error("权限验证失败, url={}", data.getUrl());
            return false;
        }

        // request header 获取
        /**
         * 问题: socket.io java client 不知道怎么在header中传递参数
         */
        HttpHeaders headers = data.getHttpHeaders();
        System.out.println("=====http request headers======");
        for (Map.Entry<String, String> header : headers) {
            System.out.println(header.getKey() + ": " + header.getValue());
        }
        System.out.println("=====http request headers end======");


        log.info("权限验证成功, token={}", data.getSingleUrlParam("token"));
        return true;
    }

}
