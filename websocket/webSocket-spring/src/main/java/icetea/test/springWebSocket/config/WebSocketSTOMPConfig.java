package icetea.test.springWebSocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.security.Principal;

/**
 * 使用stomp消息代理
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
        registry.addEndpoint("/hello").withSockJS();
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }

    /**
     * 配置消息代理(message broker)
     * 如果不重载它的话，将会自动配置一个简单的内存消息代理，用它来处理以"/topic"为前缀的消息
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //基于内存的STOMP消息代理 广播式应配置一个/topic 消息代理
        registry.enableSimpleBroker("/queue", "/topic");
        registry.setApplicationDestinationPrefixes("/app", "/foo");
        registry.setUserDestinationPrefix("/user");
    }

//    /**
//     * 1、设置拦截器
//     * 2、首次连接的时候，获取其Header信息，利用Header里面的信息进行权限认证
//     * 3、通过认证的用户，使用 accessor.setUser(user); 方法，将登陆信息绑定在该 StompHeaderAccessor 上，在Controller方法上可以获取 StompHeaderAccessor 的相关信息
//     */
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new ChannelInterceptorAdapter() {
//            @Override
//            public Message<?> preSend(Message<?> message, MessageChannel channel) {
//                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//                //1、判断是否首次连接
//                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//                    //2、判断用户名和密码
//                    String name = accessor.getNativeHeader("username").get(0);
//                    String pas = accessor.getNativeHeader("password").get(0);
//
//                    if ("admin".equals(name) && "admin".equals(pas)) {
//                        Principal principal = new Principal() {
//                            @Override
//                            public String getName() {
//                                return name;
//                            }
//                        };
//                        accessor.setUser(principal);
//                        return message;
//                    } else {
//                        return null;
//                    }
//                }
//                //不是首次连接，已经登陆成功
//                return message;
//            }
//
//        });
//    }

}
