package com.semih.p04_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semih.p02_entity.dto.MyDataDto;
import com.semih.p04_controller.intr.IMyDataController;
import com.semih.p05_service.intr.IMyDataService;

@RestController
@RequestMapping(path = "/api/database")
//@CrossOrigin(origins = "http://localhost:5000")
@CrossOrigin(origins = "*")
public class MyDataController implements IMyDataController{
	
	@Autowired IMyDataService myDataService;

	@Override
	@GetMapping(path = "/mydata")
	public List<MyDataDto> getMyData() {
		return myDataService.getMyData();
	}

	@Override
	@PutMapping(path = "/updateseason")
	public void updateSeason(Integer id,String season) {
		myDataService.updateSeason(id, season);
	}

}
