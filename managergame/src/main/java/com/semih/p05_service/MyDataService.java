package com.semih.p05_service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semih.p02_entity.MyData;
import com.semih.p02_entity.dto.MyDataDto;
import com.semih.p05_service.intr.IMyDataService;
import com.semih.p06_repository.IMyDataRepository;

@Service
public class MyDataService implements IMyDataService {

	@Autowired
	private IMyDataRepository myDataRepository;

//	@Autowired
//	private ILeagueRepository leagueRepository;

//	@Autowired
//	private IMatchLeagueService matchLeagueService;

	@Override
	public List<MyDataDto> getMyData() {
		List<MyData> myDatas = myDataRepository.findAll();
		List<MyDataDto> myDataDtos = new ArrayList<>();
//		int season = 0;

		for (MyData myData : myDatas) {
			MyDataDto myDataDto = new MyDataDto();
			BeanUtils.copyProperties(myData, myDataDto);

//			if (myDataDto.getName().equals("season")) {
//				season = Integer.parseInt(myData.getDescription());
//			}

			myDataDtos.add(myDataDto);
		}

//		List<League> leagues = leagueRepository.findAll();
//		for (League league : leagues) {
//			if (league.getSeason() < season) {
//				matchLeagueService.generateFixture(league.getName(), season);
//			}
//		}

		return myDataDtos;
	}

	@Override
	public void updateSeason(Integer id, String season) {
		Optional<MyData> mydatas = myDataRepository.findById(id);

		if (mydatas.isPresent()) {
			MyData myData = mydatas.get();
			myData.setDescription(season);
			myDataRepository.save(myData);
		}
	}
}
