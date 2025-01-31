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
@Table(name = "player_attributes")
public class PlayerAttributes {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "strongg")
	private Integer strong;

	@Column(name = "top_kesme")
	private Float topKesme;
	@Column(name = "top_kapma")
	private Float topKapma;
	@Column(name = "markajj")
	private Float markaj;
	@Column(name = "liderlikk")
	private Float liderlik;
	@Column(name = "sogukkanlilikk")
	private Float sogukkanlilik;
	@Column(name = "kararlilikk")
	private Float kararlilik;
	@Column(name = "gucc")
	private Float guc;
	@Column(name = "dayaniklilikk")
	private Float dayaniklilik;
	@Column(name = "ceviklikk")
	private Float ceviklik;	
	@Column(name = "kafa_isabeti")
	private Float kafaIsabeti;
	@Column(name = "kafa_vurusu")
	private Float kafaVurusu;
	@Column(name = "ziplamaa")
	private Float ziplama;
	@Column(name = "driplingg")
	private Float dripling;
	@Column(name = "hizz")
	private Float hiz;
	@Column(name = "ani_hiz")
	private Float aniHiz;
	@Column(name = "yetenekk")
	private Float yetenek;
	@Column(name = "top_kontrolu")
	private Float topKontrolu;
	@Column(name = "falsoo")
	private Float falso;
	@Column(name = "ortaa")
	private Float orta;
	@Column(name = "pass")
	private Float pas;
	@Column(name = "goruss")
	private Float gorus;
	@Column(name = "bitiricilikk")
	private Float bitiricilik;
	@Column(name = "sutt")
	private Float sut;
	@Column(name = "plasee")
	private Float plase;

	@OneToOne
	@JoinColumn(name = "player", referencedColumnName = "id", nullable = false)
	@JsonIgnore
	private Player player;
}
