package com.semih.p06_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semih.p02_entity.PlayerPositions;

@Repository
public interface IPlayerPositionsRepository extends JpaRepository<PlayerPositions, Long> {
	
}
