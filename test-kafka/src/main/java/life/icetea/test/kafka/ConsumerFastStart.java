package life.icetea.test.kafka;


import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Collections;
import java.util.Properties;

public class ConsumerFastStart {

    public static final String BROKER_LIST = "39.105.212.214:9092";
    public static final String TOPIC = "topic-demo";
    public static final String GROUP_ID = "group.demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("bootstrap.servers", BROKER_LIST);
        properties.put("group.id", GROUP_ID);

        // kafka producer instance
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singletonList(TOPIC));
        while (true) {
            consumer.poll(1000).forEach(record -> {
                System.out.println(record.value());
            });
        }
    }

}
