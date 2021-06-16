package icetea.test.springWebSocket.controller;


import icetea.test.springWebSocket.domain.HelloMessage;
import icetea.test.springWebSocket.domain.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//@Controller
public class WebSocketSTOMPController {

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/welcome")//浏览器发送请求通过@messageMapping映射/welcome这个地址。
    @SendTo("/topic/getResponse")//服务器端有消息时,会给订阅@SendTo中的路径的浏览器发送消息。
    @SendToUser("/welcome")
    public Greeting say(HelloMessage message) throws Exception {
        return new Greeting("Welcome, " + message.getName() + "!");
    }

    /**
     * 这里用的是@SendToUser，这就是发送给单一客户端的标志。本例中，
     * 客户端接收一对一消息的主题应该是“/user/” + 用户Id + “/message” ,这里的用户id可以是一个普通的字符串，只要每个用户端都使用自己的id并且服务端知道每个用户的id就行。
     */
    @MessageMapping("/message")
    @SendToUser("/message")
    public Greeting handleSubscribe() {
        System.out.println("this is the @SubscribeMapping('/marco')");
        return new Greeting("I am a msg from SubscribeMapping('/macro').");
    }

    /**
     * 测试对指定用户发送消息方法
     *
     * @return
     */
    @RequestMapping(path = "/send", method = RequestMethod.GET)
    public Greeting send() {
        simpMessageSendingOperations.convertAndSendToUser("1", "/message", new Greeting("I am a msg from SubscribeMapping('/macro')."));
        return new Greeting("I am a msg from SubscribeMapping('/macro').");
    }

    @RequestMapping("ws")
    public String wsView() {
        return "test-stomp";
    }

}
