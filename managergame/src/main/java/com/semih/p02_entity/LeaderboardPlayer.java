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
@Table(name = "leaderboard_player")
public class LeaderboardPlayer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "seasonn")
	private Integer season;
	
	@Column(name = "leaguee")
	private String league;
	
	@Column(name = "player_id")
	private Long playerId;
	
	@Column(name = "team_id")
	private Long teamId;
	
	@Column(name = "teamName")
	private String teamName;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "goall")
	private Integer goal;
	
	@Column(name = "asistt")
	private Integer asist;
	
	@Column(name = "onbirr")
	private Integer onbir;
	
	@Column(name = "mevkii")
	private String mevki;

	@Column(name = "strongg")
	private Integer strong;
	
}
