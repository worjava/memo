package oi.kafka.demos.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProducerDemoWithCallback {
    private static final Logger log = LoggerFactory.getLogger(ProducerDemoWithCallback.class.getSimpleName());

    public static void main(String[] args) {
        log.info("Hello I am broker");

        // создаем настройки продюсер
        Properties properties = new Properties();
        //Указывают сервер Kafka и формат сериализации для ключей и значений (строки).
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");

        ;
        ;
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // создаем Продюсера
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        // отправляем данные

        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<>("httpRead", "new data" + i);
            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        log.info("Получены новые данные \n" +
                                "Тема " + recordMetadata.topic() + "\n" +
                                "Партиция " + recordMetadata.partition() + "\n" +
                                "Offset " + recordMetadata.offset() + "\n" +
                                "Timestamp " + recordMetadata.timestamp());
                        //когда сообщение успешно отправлено, мы получаем информацию о теме, партиции, смещении и времени
                    } else {
                        log.error("Ошибка продюсера " + e);
                    }
                }
            });
        }

// очистить закрыть Продюсера
        producer.flush();
        producer.close();

    }

}
