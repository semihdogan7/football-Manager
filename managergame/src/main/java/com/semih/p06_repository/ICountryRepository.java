package com.semih.p06_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.semih.p02_entity.Country;

@Repository
public interface ICountryRepository extends JpaRepository<Country, Long> {

	@Query("SELECT c FROM Country c WHERE :value BETWEEN c.min AND c.max")
	Country findCountryByValue(@Param("value") Integer value);
	
	Country findByName(String name);
}
