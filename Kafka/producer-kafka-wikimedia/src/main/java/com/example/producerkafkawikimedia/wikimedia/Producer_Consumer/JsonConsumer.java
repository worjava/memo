package com.example.producerkafkawikimedia.wikimedia.Producer_Consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class JsonConsumer {
    public static void main(String[] args) {
        String bootstrapServers = "127.0.0.1:9092";
        String groupId = "MyConsumerJson"; // Замените на уникальный идентификатор группы

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        Consumer<String, String> consumer = new KafkaConsumer<>(properties);

        // Подписываемся на топик
        String topic = "httpRead";
        consumer.subscribe(Collections.singletonList(topic));
         List<String>messagesList = new ArrayList<>();
        while (true) {
            // Периодически проверяем новые сообщения
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            // Обрабатываем полученные записи (ваш код обработки сообщений)
            records.forEach(record -> {
                System.out.println("Received message: " + record.value());
                messagesList.add(record.value());
                // Ваш код обработки сообщений здесь

            });

        }

    }

}
