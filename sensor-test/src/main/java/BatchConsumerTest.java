import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.bean.EventRecord;
import com.sensorsdata.analytics.javasdk.consumer.BatchConsumer;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author icetea
 */
public class BatchConsumerTest {

    public static void main(String[] args) throws InvalidArgumentException {
        // 从神策分析获取的数据接收的 URL
//        final String SA_SERVER_URL = "http://172.16.0.74:8000/sensorsensor/test";
        final String SA_SERVER_URL = "http://localhost:8080/sa/icetea?token=123456";
        // 当缓存的数据量达到50条时，批量发送数据
        final int SA_BULK_SIZE = 50;
        // 数据同步失败不抛出异常
        final boolean THROW_EXCEPTION = false;
        // 内存中数据最大缓存条数，如此值大于0，代表缓存的数据会有条数限制，最小 3000 条，最大 6000 条。否则无条数限制。
        final int MAX_CACHE_SIZE = 0;
        // 使用 BatchConsumer 初始化 SensorsAnalytics
        // 不要在任何线上的服务中使用此 Consumer
        BatchConsumer batchConsumer = new BatchConsumer(SA_SERVER_URL, SA_BULK_SIZE, MAX_CACHE_SIZE, THROW_EXCEPTION);
        final SensorsAnalytics sa = new SensorsAnalytics(batchConsumer);

        String distinctId = "ABCDEF123456789";
        // 添加当前登录用户，会触发内置事件
        sa.profileAppend(distinctId, true, new HashMap<String, Object>());

        // 用户的 Distinct ID
        {
            EventRecord lookRecord = EventRecord.builder()
                    .setDistinctId(distinctId)
                    .isLoginId(Boolean.FALSE)
                    .setEventName("ViewProduct")
                    .addProperty("$time", new Date())  // '$time' 属性是系统预置属性，表示事件发生的时间，如果不填入该属性，则默认使用系统当前时间
                    // '$ip' 属性是系统预置属性，如果服务端中能获取用户 IP 地址，并填入该属性，神策分析会自动根据 IP 地址解析用户的省份、城市信息
                    .addProperty("$ip", "123.123.123.123")
                    .addProperty("ProductId", "123456")
                    .build();
            // 记录用户浏览商品事件
            sa.track(lookRecord);
        }

        // 用户订单付款
        {
            // 订单中的商品 ID 列表
            List<String> productIdList = new ArrayList<String>();
            productIdList.add("123456");
            productIdList.add("234567");
            productIdList.add("345678");
            EventRecord lookRecord = EventRecord.builder().setDistinctId(distinctId)
                    .isLoginId(Boolean.FALSE)
                    .setEventName("PaidOrder")
                    // '$ip' 属性是系统预置属性，如果服务端中能获取用户 IP 地址，并填入该属性，神策分析会自动根据 IP 地址解析用户的省份、城市信息
                    .addProperty("$ip", "123.123.123.123")
                    .addProperty("OrderId", "123456")
                    .addProperty("ProductIdList", productIdList)
                    .build();
            // 记录用户订单付款事件
            sa.track(lookRecord);
        }

        sa.flush();

    }

}
