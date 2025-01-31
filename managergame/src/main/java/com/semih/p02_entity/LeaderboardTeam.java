package com.semih.p02_entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leaderbordd_team")
public class LeaderboardTeam {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "seasonn")
	private Integer season;
	
	@Column(name = "weekk")
	private Integer week;
	
	@Column(name = "leaguee")
	private String league;
	
	@Column(name = "team_name")
	private String teamName;
		
	@Column(name = "winnn")
	private Integer winn;
	
	@Column(name = "losss")
	private Integer loss;
	
	@Column(name = "draww")
	private Integer draw;
	
	@Column(name = "gol_score")
	private Integer golScore;
	
	@Column(name = "gol_cancel")
	private Integer golCon;
	
	@Column(name = "itibarr")
	private Integer itibar;
	
	
	
}
