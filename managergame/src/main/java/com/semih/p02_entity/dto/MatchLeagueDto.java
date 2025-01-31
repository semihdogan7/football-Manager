package com.semih.p02_entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchLeagueDto {

	private int id;
	private String isPlayed;
	private String league;
	private Integer season;
	private Integer week;
	private String homeName;
	private Integer homeGol;
	private Integer awayGol;
	private Integer MacRes;
	private String avayName;
//	private List<MatchDeatils> matchDetails;
	private Integer hSutOnn;
	private Integer hSutAut;
	private Integer hSutEng;
	private Integer hSutTot;
	private Integer aSutOnn;
	private Integer aSutAut;
	private Integer aSutEng;
	private Integer aSutTot;
	private Integer hOrtKor;
	private Integer hOrtOnn;
	private Integer hOrtAut;
	private Integer hOrtTot;
	private Integer aOrtKor;
	private Integer aOrtOnn;
	private Integer aOrtAut;
	private Integer aOrtTot;
	private Float homPos;
	private Float awyPos;
	private Integer homOfs;
	private Integer awyOfs;
	private Integer homFau;
	private Integer awyFau;
	private Integer homFri;
	private Integer awyFri;
	private Integer homPen;
	private Integer awyPen;
	private Integer homYel;
	private Integer awyYel;
	private Integer homRed;
	private Integer awyRed;

}
