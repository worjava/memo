package com.example.producerkafkawikimedia.wikimedia;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WikiMediaHandler implements EventHandler { //реализация интерфейса чтобы слушать HTTP
    KafkaProducer<String, String> kafkaProducer;
    String topic;

    private final Logger logger = LoggerFactory.getLogger(WikiMediaHandler.class.getSimpleName());

    public WikiMediaHandler(KafkaProducer<String, String> kafkaProducer, String topic) {
        this.kafkaProducer = kafkaProducer;
        this.topic = topic;
    }


    @Override
    public void onOpen()  {
// здесь ничего
    }

    @Override
    public void onClosed()  {
        kafkaProducer.close();
    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent)  {
       logger.info(messageEvent.getData());
        //асинхронно
        kafkaProducer.send(new ProducerRecord<>(topic,messageEvent.getData()));
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {
        logger.error("error " + throwable);
    }
}

