package com.semih.p06_repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.semih.p02_entity.PlayerName;

@Repository
public interface IPlayerNameRepository extends JpaRepository<PlayerName, Long> {

	@Query(value = "SELECT * FROM namee WHERE countryy = :countryy ORDER BY RAND() LIMIT 1", nativeQuery = true)
	PlayerName findRandomByCountryy(@Param("countryy") String country);

	List<PlayerName> findByCountry(String country);
}
