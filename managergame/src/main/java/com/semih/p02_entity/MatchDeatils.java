package com.semih.p02_entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "matchdetails")
public class MatchDeatils {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "match_idd",nullable = false)
	private MatchLeague matchId;
		
	@Column(name = "dkk")
	private String dk;
	
	@Column(name ="leaguee")
	private String league;
	
	@Column(name ="seasonn")
	private Integer season;
	
	@Column(name ="secondss")
	private Long seconds;
	
	@Column(name = "team_one")
	private Long team1;
	
	@Column(name = "team_two")
	private Long team2;
	
	@Column(name = "devree")
	private String devre;
	
	@Column(name = "olayy")
	private String olay;
	
	@Column(name = "ara_sonuc")
	private String araSonuc;
	
	@Column(name = "sonucc")
	private String sonuc;
	
	@Column(name = "player_one")
	private Long playerId1;
	
	@Column(name = "player_two")
	private Long playerId2;
	
	@Column(name = "player_three")
	private Long playerId3;

}
