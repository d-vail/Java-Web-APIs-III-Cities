package com.lambdaschool.cities;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CitiesApplication {
  public static final String EXCHANGE_NAME = "LambdaServer";
  public static final String QUEUE_CONFIDENTIAL = "Confidential";
  public static final String QUEUE_LOW_AI = "LowAffordabilityIndex";
  public static final String QUEUE_HIGH_AI = "HighAffordabilityIndex";

  public static void main(String[] args) {
    SpringApplication.run(CitiesApplication.class, args);
  }

  // Instantiate message server and queues
  @Bean
  public TopicExchange appExchange() {
    return new TopicExchange(EXCHANGE_NAME);
  }

  @Bean
  public Queue appConfidentialQueue() {
    return new Queue(QUEUE_CONFIDENTIAL);
  }

  @Bean
  public Queue appLowAIQueue() {
    return new Queue(QUEUE_LOW_AI);
  }

  @Bean
  public Queue appHighAIQueue() {
    return new Queue(QUEUE_HIGH_AI);
  }

  // Bind queues to server
  @Bean
  public Binding declareBindingConfidential() {
    return BindingBuilder.bind(appConfidentialQueue()).to(appExchange()).with(QUEUE_CONFIDENTIAL);
  }

  @Bean
  public Binding declareBindingLowAIQueue() {
    return BindingBuilder.bind(appLowAIQueue()).to(appExchange()).with(QUEUE_LOW_AI);
  }

  @Bean
  public Binding declareBindingHighAIQueue() {
    return BindingBuilder.bind(appHighAIQueue()).to(appExchange()).with(QUEUE_HIGH_AI);
  }

  // Instantiate a Jackson2JsonMessageConverter for Spring's use
  @Bean
  public Jackson2JsonMessageConverter producerJackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}

