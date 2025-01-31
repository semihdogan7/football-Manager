package com.semih.p07_methods;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDetailsDto {
	
	private Long id;
	private String match_Id;
	private String dk;
	private String team;
	private String devre;
	private String olay;
	private String araSonuc;
	private String sonuc;
	private Long playerId1;
	private Long playerId2;
	private Long playerId3;

}
