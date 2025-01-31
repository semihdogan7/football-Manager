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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "matchleague")
public class MatchLeague {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "isplayedd")
	private String isPlayed;

	@Column(name = "leaguee")
	private String league;

	@Column(name = "seasonn")
	private Integer season;

	@Column(name = "weekk")
	private Integer week;

	@Column(name = "homenamee")
	private String homeName;

	@Column(name = "home_gol")
	private Integer homeGol;

	@Column(name = "away_gol")
	private Integer awayGol;
	
	@Column(name = "mac_res")
	private Integer MacRes;

	@Column(name = "avaynamee")
	private String avayName;

	@OneToMany(mappedBy = "matchId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MatchDeatils> matchDetails;

	@Column(name = "hsut_onn")
	private Integer hSutOnn;

	@Column(name = "hsut_aut")
	private Integer hSutAut;

	@Column(name = "hsut_eng")
	private Integer hSutEng;

	@Column(name = "hsut_tot")
	private Integer hSutTot;

	@Column(name = "asut_onn")
	private Integer aSutOnn;

	@Column(name = "asut_aut")
	private Integer aSutAut;

	@Column(name = "asut_eng")
	private Integer aSutEng;

	@Column(name = "asut_tot")
	private Integer aSutTot;

	/////////////

	@Column(name = "hort_kor")
	private Integer hOrtKor;

	@Column(name = "hort_onn")
	private Integer hOrtOnn;

	@Column(name = "hort_aut")
	private Integer hOrtAut;

	@Column(name = "hort_tot")
	private Integer hOrtTot;

	@Column(name = "aort_kor")
	private Integer aOrtKor;

	@Column(name = "aort_onn")
	private Integer aOrtOnn;

	@Column(name = "aort_aut")
	private Integer aOrtAut;

	@Column(name = "aort_tot")
	private Integer aOrtTot;

	////////////

	@Column(name = "hom_pos")
	private Float homPos;

	@Column(name = "awy_pos")
	private Float awyPos;

	@Column(name = "hom_ofs")
	private Integer homOfs;

	@Column(name = "awy_ofs")
	private Integer awyOfs;

	////////////

	@Column(name = "hom_fau")
	private Integer homFau;

	@Column(name = "awy_fau")
	private Integer awyFau;

	@Column(name = "hom_fri")
	private Integer homFri;

	@Column(name = "awy_fri")
	private Integer awyFri;

	@Column(name = "hom_pen")
	private Integer homPen;

	@Column(name = "awy_pen")
	private Integer awyPen;

	@Column(name = "hom_yel")
	private Integer homYel;

	@Column(name = "awy_yel")
	private Integer awyYel;

	@Column(name = "hom_red")
	private Integer homRed;

	@Column(name = "awy_red")
	private Integer awyRed;

}
