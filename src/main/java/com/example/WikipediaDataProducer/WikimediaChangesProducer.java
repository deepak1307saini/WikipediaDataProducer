package com.example.WikipediaDataProducer;

import com.launchdarkly.eventsource.EventSource;
import lombok.Data;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventHandler;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.launchdarkly.eventsource.EventSource.DEFAULT_READ_TIMEOUT;


@Service
public class WikimediaChangesProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        // to read real time stream data from wikimedia, we use event source
        EventHandler eventHandler = new wikipediaChangesHandler(kafkaTemplate, "wikipedia");
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();
        TimeUnit.SECONDS.sleep(10);
        eventSource.close();
    }
}
