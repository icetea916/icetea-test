package icetea.test.nettysocketio.listener;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import lombok.extern.slf4j.Slf4j;

/**
 * 权限listener, 当connect首次连接时会触一次验证
 */
@Slf4j
public class MyAuthorizationListener implements AuthorizationListener {

    @Override
    public boolean isAuthorized(HandshakeData data) {
        String loginUserName = data.getSingleUrlParam("loginUserName");
        if (loginUserName != null && !"".equals(loginUserName)) {
            log.info("loginUsername={} authorization success", loginUserName);
            return true;
        }
        return false;
    }

}
