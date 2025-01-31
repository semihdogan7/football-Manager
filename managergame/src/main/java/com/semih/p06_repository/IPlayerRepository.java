package com.semih.p06_repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semih.p02_entity.Player;

@Repository
public interface IPlayerRepository extends JpaRepository<Player, Long> {
	Page<Player> findAll(Pageable pageable);
	List<Player> findByTeam2(String team);
	List<Player> findByTeam2AndPosition(String team,String position);
	Player findByTeam2AndRole(String team, String role);
}
