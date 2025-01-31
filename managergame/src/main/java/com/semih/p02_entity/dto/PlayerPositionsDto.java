package com.semih.p02_entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPositionsDto {

	private Long id;
	private Float gk;
	private Float dc;
	private Float dl;
	private Float dr;
	private Float dm;
	private Float mc;
	private Float mr;
	private Float ml;
	private Float amc;
	private Float amr;
	private Float aml;
	private Float st;
}
