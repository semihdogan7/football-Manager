package com.semih.p05_service.intr;

import java.util.List;

import com.semih.p02_entity.dto.MyDataDto;

public interface IMyDataService {
	public List<MyDataDto> getMyData();
	
	public void updateSeason(Integer id, String season);

}
