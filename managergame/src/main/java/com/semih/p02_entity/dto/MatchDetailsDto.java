package com.semih.p02_entity.dto;

import com.semih.p02_entity.MatchLeague;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDetailsDto {

	private Long id;
	private MatchLeague matchId;
	private String dk;
	private String league;
	private Integer season;
	private Long seconds;
	private Long team1;
	private Long team2;
	private String devre;
	private String olay;
	private String araSonuc;
	private String sonuc;
	private Long playerId1;
	private Long playerId2;
	private Long playerId3;
}
