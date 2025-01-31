package com.semih.p02_entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "team")
public class Team {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "namee", nullable = false, unique = true)
	private String name;

	@Column(name = "leaugee")
	private String leauge2;

	@Column(name = "countryy")
	private String country;

	@Column(name = "continentt")
	private String continent;

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Player> players;

	@ManyToOne
	@JoinColumn(name = "leauge_id", nullable = false)
	@JsonIgnore
	private League leauge;

	// trigger kontrol ediyor
	@Column(name = "total_value")
	private Float totalValue;

	@Column(name = "player_count")
	private Integer playerCount;

	@OneToOne(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Stadium stadium;

	@Column(name = "altyapii")
	private Integer altyapi;

	@Column(name = "antremann")
	private Integer antreman;

	@Column(name = "scoutt")
	private Integer scout;

	@Column(name = "taraftar")
	private Integer taraftar;

	@Column(name = "itibarr")
	private Integer itibar;

	// taktik ekranı

	@Column(name = "tacticc") // Formasyon adını tutan sütun
	private String tactic;
//
//	@OneToMany
//	@JoinColumn(name = "name", referencedColumnName = "tacticc", insertable = false, updatable = false)
//	private List<Tactic> tactics; // Tüm taktik pozisyonlarını tutan liste

}
