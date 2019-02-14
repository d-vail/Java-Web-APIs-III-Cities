package com.lambdaschool.cities.city;

import com.lambdaschool.cities.CitiesApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

/**
 * CityController class handles city endpoints.
 */
@Slf4j
@RestController
@RequestMapping("/cities")
public class CityController {
  private final CityRepository CITY_REPO;
  private final RabbitTemplate RABBIT_TEMPLATE;

  /**
   * Constructor
   *
   * @param cityRepo        A database interface for City
   * @param rabbitTemplate  A RabbitMQ helper object to simplify synchronous sending/receiving
   */
  public CityController(CityRepository cityRepo, RabbitTemplate rabbitTemplate) {
    this.CITY_REPO = cityRepo;
    this.RABBIT_TEMPLATE = rabbitTemplate;
  }

  /**
   * Create a message for each existing city object and send to appropriate queues:
   *  - Place in confidential queue if confidential
   *  - Place in cities1 queue if non confidential and an affordability index of < 6
   *  - Place all others in cities2 queue
   */
  @GetMapping("/afford")
  public void afford() {
    ArrayList<City> cities = new ArrayList<>(CITY_REPO.findAll());

    for (City c : cities) {
      boolean isConfidential = new Random().nextBoolean();
      final CityMessage CITY_MSG = new CityMessage(c.toString(), c.getAffordabilityIndex(),
              isConfidential);

      log.info("Sending message...");
      if(isConfidential) {
        RABBIT_TEMPLATE.convertAndSend(CitiesApplication.QUEUE_CONFIDENTIAL, CITY_MSG);
      } else {
        if(c.getAffordabilityIndex() < 6) {
          RABBIT_TEMPLATE.convertAndSend(CitiesApplication.QUEUE_CITIES1, CITY_MSG);
        } else {
          RABBIT_TEMPLATE.convertAndSend(CitiesApplication.QUEUE_CITIES2, CITY_MSG);
        }
      }
    }
  }

  /**
   * Create a message for each existing city object and send to appropriate queues:
   *  - Place in confidential queue if confidential
   *  - Place in cities1 queue if non confidential and an affordability index of < 6
   *  - Place all others in cities2 queue
   */
  @GetMapping("/homes")
  public void homes() {
    ArrayList<City> cities = new ArrayList<>(CITY_REPO.findAll());

    for(City c : cities) {
      boolean isConfidential = new Random().nextBoolean();
      final CityMessage CITY_MSG = new CityMessage(c.toString(), c.getAffordabilityIndex(),
              isConfidential);

      log.info("Sending message...");
      if(isConfidential) {
        RABBIT_TEMPLATE.convertAndSend(CitiesApplication.QUEUE_CONFIDENTIAL, CITY_MSG);
      } else {
        if(c.getMedianHomePrice() > 200000) {
          RABBIT_TEMPLATE.convertAndSend(CitiesApplication.QUEUE_CITIES1, CITY_MSG);
        } else {
          RABBIT_TEMPLATE.convertAndSend(CitiesApplication.QUEUE_CITIES2, CITY_MSG);
        }
      }
    }
  }

  /**
   * Create a message for each existing city object and send to appropriate queues:
   *  - Place in confidential queue if confidential
   *  - Place in cities1 queue if non confidential
   */
  @GetMapping("/names")
  public void names() {

  }
}
