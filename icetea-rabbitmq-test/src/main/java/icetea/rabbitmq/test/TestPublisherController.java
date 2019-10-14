package icetea.rabbitmq.test;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestPublisherController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * app观看记录入队压力测试
     */
    @GetMapping("appLook/{number}")
    public String publishAppLookTimeMessage(@PathVariable Integer number) {
        // 消息构造
        JSONObject messageMap = new JSONObject();
        messageMap.put("upid", "2650529");
        messageMap.put("uid", "2098569");
        messageMap.put("lookTime", "40");
        messageMap.put("csid", "328");
        messageMap.put("ip", "188.188.188.188");
        messageMap.put("pid", "735");
        messageMap.put("startTime", "1570865993561");
        messageMap.put("endTime", "1570865993561");
        messageMap.put("time", "48");
        messageMap.put("cid", "37");

        for (int i = 0; i < number; i++) {
            rabbitTemplate.convertAndSend("zjtx.topic", "zjtx.looktime.app", messageMap);
        }

        return "OK";
    }

}
