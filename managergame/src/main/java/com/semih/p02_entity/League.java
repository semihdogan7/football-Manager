package com.semih.p02_entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "leauge")
public class League {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "namee")
	private String name;
	
	@Column(name = "countryy")
	private String country;
	
	@Column(name = "continentt")
	private String continent;
	
	@Column(name = "levell")
	private String level;
	
	@Column(name = "groupp")
	private String group;
	
	@Column(name = "total_values")
	private int value;
	
	@Column(name = "team_count")
	private int teamCount;
	
	@OneToMany(mappedBy = "leauge", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Team> teams;
	
	@Column(name = "seasonn")
	private Integer season;
	
	@Column(name = "weekk")
	private Integer week;
	
	
	
	
	
	
}
