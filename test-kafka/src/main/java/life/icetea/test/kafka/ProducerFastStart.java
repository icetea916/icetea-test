package life.icetea.test.kafka;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Slf4j
public class ProducerFastStart {

    public static final String BROKER_LIST = "39.105.212.214:9092";
    public static final String TOPIC = "topic-demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        // 设定对应客户端id，不设置则会自动生成
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.demo");
        // 重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 10);

        // kafka producer instance
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // init message
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, "hello, kafka!");

        // send message
        try {
            RecordMetadata recordMetadata = producer.send(record).get();
            log.info("success, topic: {}, partition: {}, offset: {}", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
        } catch (InterruptedException | ExecutionException e) {
            // 消息发送未成功
            log.error("error", e);
            throw new RuntimeException(e);
        }

        producer.close();
    }

}
