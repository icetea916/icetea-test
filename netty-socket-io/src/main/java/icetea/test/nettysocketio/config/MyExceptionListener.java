package icetea.test.nettysocketio.config;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListenerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public class MyExceptionListener extends ExceptionListenerAdapter {

    @Override
    public void onEventException(Exception e, List<Object> args, SocketIOClient client) {

        client.disconnect();
    }

    @Override
    public void onDisconnectException(Exception e, SocketIOClient client) {
        client.disconnect();
    }

    @Override
    public void onConnectException(Exception e, SocketIOClient client) {
        client.disconnect();
    }

    @Override
    public void onPingException(Exception e, SocketIOClient client) {
        client.disconnect();
    }

    @Override
    public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        ctx.close();
        return true;
    }


}