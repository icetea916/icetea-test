package life.icetea.test.nettysocketio.listener;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import lombok.extern.slf4j.Slf4j;

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
        String token = data.getSingleUrlParam("token");
        if (token == null || "".equals(token)) {
            log.error("权限验证失败, url={}", data.getUrl());
            return false;
        }

        log.info("权限验证成功, token={}", data.getSingleUrlParam("token"));
        return true;
    }

}
