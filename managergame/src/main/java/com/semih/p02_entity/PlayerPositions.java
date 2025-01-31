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
@Table(name = "player_positions")
public class PlayerPositions {

	// ek mevkiler

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "gkk")
	private Float gk;
	@Column(name = "dcc")
	private Float dc;
	@Column(name = "dll")
	private Float dl;
	@Column(name = "drr")
	private Float dr;
	@Column(name = "dmm")
	private Float dm;
	@Column(name = "mcc")
	private Float mc;
	@Column(name = "mrr")
	private Float mr;
	@Column(name = "mll")
	private Float ml;
	@Column(name = "amcc")
	private Float amc;
	@Column(name = "amrr")
	private Float amr;
	@Column(name = "amll")
	private Float aml;
	@Column(name = "stt")
	private Float st;

	@OneToOne
	@JoinColumn(name = "player", referencedColumnName = "id", nullable = false)
	@JsonIgnore
	private Player player;
}
