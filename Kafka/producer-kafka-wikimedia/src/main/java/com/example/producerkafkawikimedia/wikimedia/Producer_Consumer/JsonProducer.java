package com.example.producerkafkawikimedia.wikimedia.Producer_Consumer;

import org.apache.kafka.common.serialization.StringSerializer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class JsonProducer {
    public static void main(String[] args) throws Exception {
        String bootstrapServers = "127.0.0.1:9092";
        String topic = "TestTopic2";
        String url = "http://localhost:8099/api/accounts/all";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        while (true) {
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new RuntimeException("Unexpected response code: " + response);
                }

                ResponseBody body = response.body();
                if (body != null) {
                    String jsonData = body.string();
                    producer.send(new ProducerRecord<>(topic, jsonData));
                }
            }

            TimeUnit.MINUTES.sleep(10);
        }
    }
}
