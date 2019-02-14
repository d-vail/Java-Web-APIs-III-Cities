package com.lambdaschool.cities.city;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CityRepository is an interface that extends JpaRepository for JPA queries.
 */
public interface CityRepository extends JpaRepository<City, Long> {
}
