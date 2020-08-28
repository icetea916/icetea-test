package icetea.test.webSocket.controller;


import icetea.test.webSocket.domain.Message;
import icetea.test.webSocket.domain.Response;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WebSocketSTOMPController {

    @MessageMapping("/welcome")//浏览器发送请求通过@messageMapping映射/welcome这个地址。
    @SendTo("/topic/getResponse")//服务器端有消息时,会给订阅@SendTo中的路径的浏览器发送消息。
    public Response say(Message message) throws Exception {
        Thread.sleep(1000);
        return new Response("Welcome, " + message.getName() + "!");
    }

    @RequestMapping("ws")
    public String wsView() {
        return "test-stomp";
    }

}
