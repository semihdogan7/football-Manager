package com.semih.p02_entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerAttributesDto {

	private Long id;
	//savunma
	private Float topKesme;
	private Float topKapma;
	private Float markaj;
	
	//zihinsel
	private Float liderlik;
	private Float sogukkanlilik;
	private Float kararlilik;
	
	//fiziksel
	private Float guc;
	private Float dayaniklilik;
	private Float ceviklik;
	
	//hava
	private Float kafaIsabeti;
	private Float kafaVurusu;
	private Float ziplama;
	
	//sürat
	private Float dripling;
	private Float hiz;
	private Float aniHiz;
	
	//teknik
	private Float yetenek;
	private Float topKontrolu;
	private Float falso;
	
	//vizyom
	private Float orta;
	private Float pas;
	private Float gorus;
	
	//hücum
	private Float bitiricilik;
	private Float sut;
	private Float plase;
}
