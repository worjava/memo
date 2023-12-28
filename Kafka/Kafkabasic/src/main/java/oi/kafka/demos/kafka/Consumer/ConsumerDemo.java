package oi.kafka.demos.kafka.Consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class ConsumerDemo {
    private static final Logger log = LoggerFactory.getLogger(ConsumerDemo.class.getSimpleName());

    public static void main(String[] args) {
        log.info("Hello I am Consumer");

        String bootstrapServers = "127.0.0.1:9092";



        String groupId = "my-second-application";
        String topic = "TestTopic2";
//       настройки консьюмера

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); //latest" - новые сообщения, "earliest" - все включая старые.
        //создаем консюмера
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        kafkaConsumer.subscribe(Arrays.asList(topic));


        // .
        int maxIterations = 5;
        int currentIteration = 0;

        while (currentIteration < maxIterations) {
            log.info("Pooling");
            // Получение записей от Kafka Consumer
                    ConsumerRecords<String, String> records =
                            kafkaConsumer.poll(Duration.ofMillis(1000));

            if (!records.isEmpty()) {
                for (ConsumerRecord<String, String> record : records) {
                    // Обработка каждой записи
                    log.info("Received record: ");
                    log.info("Topic = " + record.topic());
                    log.info("Partition = " + record.partition());
                      log.info( "Offset = " + record.offset() +
                            ", Key = " + record.key() +
                            ", Value = " + record.value());

                    System.out.println(record.value() + " " + record.key());
                }

                currentIteration++;
            }
        }

        log.info("Finished reading messages. Closing Kafka consumer.");
        kafkaConsumer.close();

    }
}