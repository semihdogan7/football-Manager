package com.semih.p02_entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

	private Long id;
	private String name;
	private String surname;
	private String position;
	private String country;
	private String team2;

	// Team'ın id sini getId ile eşitleriz
	private String team_id;

	// service tarafında hesaplarız
	private Integer squad;
	private String role;

	// Triggerlar ile yönetilir
	private int agePlayer;
	private Integer strong;
	private float value;

	// kullanıcıya gösterilmez
	private Float potansiyel;

	// vücut bilgileri
	private Integer boy;
	private Integer agirlik;
	private String ayak;

	// Beceri ve pozisyonlar
	private PlayerPositionsDto positions;
	private PlayerAttributesDto attributes;
	
	//istatistikler
	private Integer sezonGol;
	private Integer sezonAsist;
	private Integer sezonMac;

}