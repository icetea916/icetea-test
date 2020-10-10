package icetea.test.nettysocketio.config;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;

public class MyAuthorizationListener implements AuthorizationListener {

    @Override
    public boolean isAuthorized(HandshakeData data) {
        String token = data.getSingleUrlParam("token");
        System.out.println(token);
        return true;
    }
    
}
