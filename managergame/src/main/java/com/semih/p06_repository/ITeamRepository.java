package com.semih.p06_repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semih.p02_entity.Team;


@Repository
public interface ITeamRepository extends JpaRepository<Team, Long> {
	Team findByName(String name); // sadece takım isimlerini çekmek için
	List<Team> findByLeauge2(String leauge2); // belirli bir lig ismine göre takımları çeker
	Page<Team> findAll(Pageable pageable);
}
