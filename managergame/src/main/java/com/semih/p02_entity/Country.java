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
@Table(name = "coutryy")
public class Country {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "namee")
	private String name;
	
	@Column(name = "confederationn")
	private String confederation;
	
	@Column(name = "minn")
	private Integer min;
	
	@Column(name = "maxx")
	private Integer max;
	
	@Column(name = "ihtimall")
	private Integer ihtimal;
	
	@Column(name = "nam_say")
	private Integer namSay;
	
	@Column(name = "sur_say")
	private Integer surSay;
}
