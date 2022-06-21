import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.bean.EventRecord;
import com.sensorsdata.analytics.javasdk.consumer.BatchConsumer;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;

/**
 * @author icetea
 */
public class DemoTest {

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
        // 模拟用户点击事件
        EventRecord lookRecord = EventRecord.builder()
                .setDistinctId(distinctId)
                .isLoginId(Boolean.FALSE)
                .setEventName("pageClick")
                .addProperty("page_url", "https://ischinese.cn/index/course")
                .addProperty("page_name", "首页-精品课程")
                .addProperty("button_name", "https://ischinese.cn/index/course")
                .addProperty("ip", "192.168.7.123")
                .build();
        // 记录用户浏览商品事件
        for (int i = 0; i < 100000; i++) {
            sa.track(lookRecord);
        }
        sa.flush();
    }

}
