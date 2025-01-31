package com.semih.p06_repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semih.p02_entity.Tactic;

@Repository
public interface ITacticRepository extends JpaRepository<Tactic, Long> {
	List<Tactic> findByName(String tacticName);
}
