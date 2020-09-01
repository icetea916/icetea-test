package icetea.test.webSocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 开启WebSocket服务
 */
@Configuration
//开启使用STOMP协议来传输基于代理(message broker)的消息,此时浏览器支持使用@MessageMapping
@EnableWebSocketMessageBroker
public class WebSocketSTOMPConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 将"/hello"路径注册为STOMP端点，这个路径与发送和接收消息的目的路径有所不同，这是一个端点，客户端在订阅或发布消息到目的地址前，要连接该端点，
     * 即用户发送请求url="/applicationName/hello"与STOMP server进行连接。之后再转发到订阅url；
     * PS：端点的作用——客户端在订阅或发布消息到目的地址前，要连接该端点。
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册一个Stomp 协议的endpoint,并指定 SockJS协议
        registry.addEndpoint("/endpointWisely").withSockJS();
    }

    // 配置消息代理(message broker)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); //广播式应配置一个/topic 消息代理
    }
}
