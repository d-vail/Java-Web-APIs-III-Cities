package com.lambdaschool.cities.city;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class City {
  private @Id @GeneratedValue Long id;
  private String name;
  private String state;
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
   * @param name                City name
   * @param state               State city is located in
   * @param medianHomePrice     Median price for homes in city
   * @param affordabilityIndex  Affordability index
   */
  public City(String name, String state, int medianHomePrice, int affordabilityIndex) {
    this.name = name;
    this.state = state;
    this.medianHomePrice = medianHomePrice;
    this.affordabilityIndex = affordabilityIndex;
  }
}
