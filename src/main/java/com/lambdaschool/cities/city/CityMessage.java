package com.lambdaschool.cities.city;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * The CityMessage class represents a JSON message for the messaging system..
 */
@Data
public class CityMessage implements Serializable {
  private final String DATA;
  private final int AFFORDABILITY;
  private final boolean CONFIDENTIAL;

  /**
   * Constructor
   *
   * @param data            A string representation of a City object
   * @param affordability   The affordability index
   * @param confidential    Whether the message is confidential
   */
  public CityMessage(@JsonProperty("data") String data,
                     @JsonProperty("affordability") int affordability,
                     @JsonProperty("confidential") boolean confidential) {
    this.DATA = data;
    this.AFFORDABILITY = affordability;
    this.CONFIDENTIAL = confidential;
  }
}
