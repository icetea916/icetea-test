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

    /**
     * 压力测试is_pass队列
     */
    @GetMapping("isPass/{number}")
    public String publishIsPassMessage(@PathVariable Integer number) {
        // 消息构造
        JSONObject messageMap = new JSONObject();

        int i = 0;
        while (i < number) {
            for (int j = 2650501; j <= 2650536; j++) {
                messageMap.put("upid", j);
                rabbitTemplate.convertAndSend("zjtx.topic", "zjtx.plan.ispass.pc", messageMap);
            }

            i++;
        }

        return "OK";
    }

    /**
     * 压力测试pc观看记录
     *
     * @return
     */
    @GetMapping("pc/{number}")
    public String publishPcMessage(@PathVariable Integer number) {
        // {"errcode":200,
        // "upid":2650539,
        // "lookTime":548,
        // "csid":5219,
        // "ip":"111.204.5.6",
        // "sessionId":"ff7597a5-930d-448f-9ccc-c26ff70133e4",
        // "userid":2098569,
        // "token":"e1ed9ee6c10f7bce63a5c3feb02c2c91",
        // "startTime2":1571041528271,
        // "watchTime":8,
        // "startTime":1571041520302,
        // "allowTime":900,
        // "time":0,
        // "endTime":1571041529693,
        // "cid":2435}
        JSONObject messageMap = new JSONObject();
        messageMap.put("errcode", 200);
        messageMap.put("upid", 2650539);
        messageMap.put("lookTime", 548);
        messageMap.put("csid", 5219);
        messageMap.put("ip", "188.188.188.188");
        messageMap.put("sessionId", "ff7597a5-930d-448f-9ccc-c26ff70133e4");
        messageMap.put("userid", 2098569);
        messageMap.put("token", "e1ed9ee6c10f7bce63a5c3feb02c2c91");
        messageMap.put("startTime2", 1571041528271L);
        messageMap.put("watchTime", 8);
        messageMap.put("startTime", 1571041520302L);
        messageMap.put("allowTime", 900);
        messageMap.put("time", 0);
        messageMap.put("endTime", 1571041529693L);
        messageMap.put("cid", 2435);

        for (int j = 0; j < number; j++) {
            rabbitTemplate.convertAndSend("zjtx.topic", "zjtx.looktime.pc", messageMap);
        }


        return "ok";

    }
}
