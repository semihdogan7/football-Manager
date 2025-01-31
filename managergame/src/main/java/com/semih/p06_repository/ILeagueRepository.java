package com.semih.p06_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semih.p02_entity.League;

@Repository
public interface ILeagueRepository extends JpaRepository<League, Long> {
	League findByName(String name);
}
