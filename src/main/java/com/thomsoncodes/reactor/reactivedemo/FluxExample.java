package com.thomsoncodes.reactor.reactivedemo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thomsoncodes.reactor.reactivedemo.domain.ToDo;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Configuration
public class FluxExample {
	static private Logger LOG = LoggerFactory.getLogger(FluxExample.class);
	
	@Bean
	public CommandLineRunner runFluxExample() {
		return args -> {
			
			EmitterProcessor<ToDo> stream = EmitterProcessor.create();
			
			Mono<List<ToDo>> promise = stream
					.filter( s -> s.isCompleted())
					.doOnNext( s -> LOG.info("FLUX >> ToDo: {}", s.getDescription()))
					.collectList()
					.subscribeOn(Schedulers.single());
			
			stream.onNext(new ToDo("read a Book", true));
			stream.onNext(new ToDo("Listen classical music", true));
			stream.onNext(new ToDo("Workout in the morning", true));
			stream.onNext(new ToDo("Organize my room", true));
			stream.onNext(new ToDo("Go to Car Wash", true));
			stream.onNext(new ToDo("SP1 2018 is coming", true));
			stream.onNext(new ToDo("Study Angular", true));
			stream.onNext(new ToDo("Revise spring boot", true));
			
			stream.onComplete();
			
			promise.block();
		};
	}

}
