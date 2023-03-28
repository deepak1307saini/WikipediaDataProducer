package com.example.WikipediaDataProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WikipediaDataProducerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WikipediaDataProducerApplication.class, args);
	}

	@Autowired
	private WikimediaChangesProducer wikimediaChangesProducer;

	@Override
	public void run(String... args) throws Exception {
		wikimediaChangesProducer.sendMessage();
	}
}
