package com.semih.p02_entity.dto;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class StadiumDto {
	private Long id;
	private String name;
	private Integer kapasite;
	private String sehir;
	private Integer zemin;
	private TeamDto team;
}
