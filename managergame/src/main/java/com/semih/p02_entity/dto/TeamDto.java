package com.semih.p02_entity.dto;

import java.util.List;

import com.semih.p02_entity.Tactic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

	private Long id;
	private String name;
	private String leauge2;
	private String country;
	private String continent;

	private Float totalValue;
	private Integer playerCount;

	private Float defStrong;
	private Float midStrong;
	private Float forStrong;
	private Float avrStrong;

	private List<PlayerDto> players;

	private StadiumDto stadium;
	private Integer altyapi;
	private Integer antreman;
	private Integer scout;
	private Integer taraftar;
	private Integer itibar;
	private List<Tactic> tactics; // Tüm taktik pozisyonlarını tutan liste

}
