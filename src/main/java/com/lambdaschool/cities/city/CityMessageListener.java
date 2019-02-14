package com.lambdaschool.cities.city;

import com.lambdaschool.cities.CitiesApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * CityMessageListener is a Spring Service that watches the specified queues.
 */
@Slf4j
@Service
public class CityMessageListener {
  /**
   * Logs messages of type CityMessage as each specified queue receives them.
   *
   * @param msg A message of type CityMessage that was sent to a watched queue
   */
  @RabbitListener(queues = {CitiesApplication.QUEUE_CONFIDENTIAL,
                            CitiesApplication.QUEUE_LOW_AI,
                            CitiesApplication.QUEUE_HIGH_AI})
  public void receiveMessage(CityMessage msg) {
    log.info("Received Message: { " + msg.toString() + " }");
  }
}
