package com.example.WikipediaDataProducer;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.json.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;


public class wikipediaChangesHandler implements EventHandler {

    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic;

    public wikipediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        JSONObject json =new JSONObject(messageEvent.getData());
        String user=json.getString("user");
        String url=json.getJSONObject("meta").getString("uri");
         kafkaTemplate.send("wikipedia",String.format("User : %s\nUrl : %s\n",user,url));

    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
