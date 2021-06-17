package life.icetea.test.nettysocketio.listener;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import lombok.extern.slf4j.Slf4j;

/**
 * 权限listener
 * 只有当connect首次连接时才会触发一次验证
 *
 * @author icetea
 */
@Slf4j
public class MyAuthorizationListener implements AuthorizationListener {

    @Override
    public boolean isAuthorized(HandshakeData data) {
        String token = data.getSingleUrlParam("token");
        if (token != null && !"".equals(token)) {
            log.info("权限验证成功, token={}", data.getSingleUrlParam("token"));
            return true;
        }
        log.error("权限验证失败, url={}", data.getUrl());
        return false;
    }

}
