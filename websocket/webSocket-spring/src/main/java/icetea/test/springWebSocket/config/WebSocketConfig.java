package icetea.test.springWebSocket.config;

import icetea.test.springWebSocket.config.interceptor.WebSocketHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 开启WebSocket服务
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * 注册处理器
     *
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        MarcoHandler_2 marcoHandler_2 = new MarcoHandler_2();
        registry.addHandler(marcoHandler_2, "/marco2")
                //声明拦截器
                .addInterceptors(new WebSocketHandshakeInterceptor())
                //声明允许访问的主机列表
                .setAllowedOrigins("*");

        //声明启用SockJS连接，如果前端还用 new WebSocket(url); 会报：Error during WebSocket handshake: Unexpected response code: 200
        registry.addHandler(marcoHandler_2, "/marcoSockJS")
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
