package com.semih.p05_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semih.p02_entity.MatchDeatils;
import com.semih.p05_service.intr.IMatchDetailsService;
import com.semih.p06_repository.IMatchDetailsRepository;

@Service
public class MatchDetailsService implements IMatchDetailsService{

	@Autowired
	IMatchDetailsRepository matchDetailsRepository;
	
	@Override
	public void saveMatchDetails(List<MatchDeatils> matchDeatils) {
		matchDetailsRepository.saveAll(matchDeatils);
	}

	

}
