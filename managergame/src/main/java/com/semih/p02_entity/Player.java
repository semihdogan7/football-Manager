package com.semih.p02_entity;

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
@Table(name = "player")
public class Player {

	// dto da kullanıcıya göstermiyoruz

	@Column(name = "birthh")
	private int birth;

	// genel propertyler

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "namee")
	private String name;

	@Column(name = "surnamee")
	private String surname;

	@Column(name = "positionn")
	private String position;

	@Column(name = "countryy")
	private String country;

	@Column(name = "teamm")
	private String team2;

	// Team
	@ManyToOne
	@JoinColumn(name = "team_id", nullable = false)
	@JsonIgnore // Sonsuz döngüyü önler
	private Team team;

	// service tarafında hesaplarız
	@Column(name = "squadd")
	private Integer squad;

	@Column(name = "rolee")
	private String role;

	// MySQL Triggerlar ile yönetilir
	@Column(name = "agee_player")
	private int agePlayer;

	@Column(name = "strong")
	private Integer strong;

	@Column(name = "valuee")
	private float value;

	// kullanıcıya gösterilmez
	@Column(name = "potansiyell")
	private Float potansiyel;

	// vücut özellikleri ve potansiyel
	@Column(name = "boyy")
	private Integer boy;
	@Column(name = "agirlikk")
	private Integer agirlik;
	@Column(name = "ayakk")
	private String ayak;

	// beceri ve pozisyonları
	@OneToOne(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private PlayerPositions playerPositions;

	@OneToOne(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private PlayerAttributes playerAttributes;

}
