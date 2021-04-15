package icetea.test.nettysocketio.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListenerAdapter;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 异常监听器
 */
@Slf4j
public class MyExceptionListener extends ExceptionListenerAdapter {

    @Override
    public void onEventException(Exception e, List<Object> args, SocketIOClient client) {
        log.info("sessionId={} disconnected", client.getSessionId());
        client.disconnect();
    }

    @Override
    public void onDisconnectException(Exception e, SocketIOClient client) {
        log.info("sessionId={} disconnected", client.getSessionId());
        client.disconnect();
    }

    @Override
    public void onConnectException(Exception e, SocketIOClient client) {
        log.info("sessionId={} disconnected", client.getSessionId());
        client.disconnect();
    }

    @Override
    public void onPingException(Exception e, SocketIOClient client) {
        log.info("sessionId={} ping", client.getSessionId());
        client.disconnect();
    }

    @Override
    public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        ctx.close();
        return true;
    }


}