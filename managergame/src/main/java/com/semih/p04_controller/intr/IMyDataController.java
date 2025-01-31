package com.semih.p04_controller.intr;

import java.util.List;

import com.semih.p02_entity.dto.MyDataDto;

public interface IMyDataController {
	public List<MyDataDto> getMyData();
	
	public void updateSeason(Integer id, String season);
}
