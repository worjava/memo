package com.example.kafkaconsumeropensearch.consumer;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;

import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.index.IndexResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.indices.GetIndexRequest;
import org.opensearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.opensearch.client.indices.CreateIndexRequest;


import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class OpenSearchConsumer {

    public static RestHighLevelClient createOpenSearchClient() {
        //   String connString = "http://localhost:9200"; ниже управляемый сервис OpenSearch/Elasticsearch через интернет. абстрагируемя от образа докер
        String connString = "https://arqarbszhi:ooww8pismg@red-boy-search-8656124499.us-east-1.bonsaisearch.net:443";


        // Построение URI из строки подключения
        URI connUri = URI.create(connString);

        // Извлечение информации о входе, если она существует
        String userInfo = connUri.getUserInfo();

        RestHighLevelClient restHighLevelClient;

        if (userInfo == null) {
            // REST-клиент без безопасности
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(new HttpHost(connUri.getHost(), connUri.getPort(), connUri.getScheme()))
            );
        } else {
            // REST-клиент с безопасностью
            String[] auth = userInfo.split(":");

            CredentialsProvider cp = new BasicCredentialsProvider();
            cp.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(auth[0], auth[1]));

            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(new HttpHost(connUri.getHost(), connUri.getPort(), connUri.getScheme()))
                            .setHttpClientConfigCallback(httpAsyncClientBuilder ->
                                    httpAsyncClientBuilder.setDefaultCredentialsProvider(cp)
                                            .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                            )
            );
        }

        return restHighLevelClient;
    }

    private static KafkaConsumer<String, String> creatKafkaConsumer() {
        String bootstrapServers = "127.0.0.1:9092";


        String groupId = "consumer-open-search-demo";
        String topic = "httpRead";
//       настройки консьюмера

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//создаем консюмера
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        return kafkaConsumer;
    }

    public static void main(String[] args) throws IOException {

        Logger log = LoggerFactory.getLogger(OpenSearchConsumer.class.getSimpleName());

        // Используйте клиента OpenSearch для доступа бд OpenSearch
        RestHighLevelClient client = createOpenSearchClient();

        // creat client Kafka <----->
        KafkaConsumer<String, String> consumer = creatKafkaConsumer();


// this index is check exist
        try (client; consumer) {
            boolean exist = client.indices().exists(new GetIndexRequest("wikimedia"), RequestOptions.DEFAULT);
            if (!exist) {
                // Создаем индекс если он не готов
                CreateIndexRequest index = new CreateIndexRequest("wikimedia");// отправили запрос на тему из удаленого опенсерч
                client.indices().create(index, RequestOptions.DEFAULT);
                log.info("The Wikimedia Index has be created");
            } else {
                log.info("Index already exits");
            }
          //подписали косюмера на топик
            consumer.subscribe(Collections.singleton("wikimedia"));
            //механизм чтения данных консюмером
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(3000));// время для ожидания новых записей
                int recordCount = records.count(); // кол во полученных записей
                log.info("Received " + recordCount + "records");

                for (ConsumerRecord<String, String> record : records) {
                    IndexRequest indexRequest = new IndexRequest("wikimedia")
                            .source(record.value(), XContentType.JSON);

                    log.info("Inserted 1 document info OpenSearch");
             IndexResponse response = client.index(indexRequest,RequestOptions.DEFAULT);
                    log.info(response.getId());

                }


            }
        }


    }

}

