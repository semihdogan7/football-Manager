package com.semih.p06_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semih.p02_entity.MyData;

@Repository
public interface IMyDataRepository extends JpaRepository<MyData, Integer>{
	MyData findByName(String seasın);

}
