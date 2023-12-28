package oi.kafka.demos.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {
    private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());

    public static void main(String[] args) {
        log.info("Hello I am broker");

        // создаем настройки продюсер
        Properties properties = new Properties();
        //Указывают сервер Kafka и формат сериализации для ключей и значений (строки).
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        // создаем Продюсера
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        // отправляем данные
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>("TestTopic2", "Hello Alexander");
        producer.send(producerRecord);
        //  очистить закрыть Продюсера
        producer.flush();
        producer.close();
    }
}
