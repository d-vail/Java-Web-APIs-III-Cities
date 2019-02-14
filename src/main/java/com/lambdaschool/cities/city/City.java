package com.lambdaschool.cities.city;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The City class presents a city object with data regarding its affordability.
 */
@Data
@Entity
public class City {
  private @Id @GeneratedValue Long id;
  private String name;
  private int medianHomePrice;
  private int affordabilityIndex;

  /**
   * Default Constructor for JPA
   */
  public City() {
  }

  /**
   * Constructor
   *
   * @param name                City name and state
   * @param medianHomePrice     Median price for homes in city
   * @param affordabilityIndex  Affordability index
   */
  public City(String name, int medianHomePrice, int affordabilityIndex) {
    this.name = name;
    this.medianHomePrice = medianHomePrice;
    this.affordabilityIndex = affordabilityIndex;
  }
}
