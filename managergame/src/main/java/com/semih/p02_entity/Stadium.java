package com.semih.p02_entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "stadium")
public class Stadium {
	
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "namee")
	private String name;
	
	@Column(name = "kapasitee")
	private Integer kapasite;
	
	@Column(name = "sehirr")
	private String sehir;
	
	@Column(name = "zeminn")
	private Integer zemin;
	
	@OneToOne
	@JoinColumn(name = "team", referencedColumnName = "id", nullable = false)
	@JsonIgnore
	private Team team;

}
