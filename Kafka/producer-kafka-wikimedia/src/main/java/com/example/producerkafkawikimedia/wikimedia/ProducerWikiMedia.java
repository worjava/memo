package com.example.producerkafkawikimedia.wikimedia;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Configuration;


import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
@Configuration
public class ProducerWikiMedia {

    public static void main(String[] args) throws InterruptedException {

        String bootstrapServers = "127.0.0.1:9092";

        // создаем настройки продюсер
        Properties properties = new Properties();
        //Указывают сервер Kafka и формат сериализации для ключей и значений (строки).
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //время ожидания для отпавки сообщения (накапливаем и отрправяляем)
        // увелич. размер пакета
        // метод сжатия для уменьшения размера
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG,"20");
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG,Integer.toString(32*1024));
        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy"); //сжатие
        // создаем Продюсера
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        String topic = "httpRead";
        EventHandler eventHandler = new WikiMediaHandler(producer,topic); // здесь реализация чтобы слушать http
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();


        //start
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);

    }
}
