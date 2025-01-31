package com.semih.p07_methods;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.semih.p02_entity.LeaderboardPlayer;
import com.semih.p02_entity.MatchDeatils;
import com.semih.p02_entity.MatchLeague;
import com.semih.p02_entity.Player;
import com.semih.p02_entity.Team;
import com.semih.p02_entity.dto.TeamDto;
import com.semih.p06_repository.ILeaderboardPlayerRepository;
import com.semih.p06_repository.IMatchDetailsRepository;
import com.semih.p06_repository.IPlayerRepository;
import com.semih.p06_repository.ITeamRepository;

@Component
@CrossOrigin(origins = "http://localhost:5000")
public class MatchSimulation {

	@Autowired
	private IPlayerRepository playerRepository;

	@Autowired
	private CalculateTeamStrong calculateTeamStrong;

	@Autowired
	private ITeamRepository teamRepository;

	@Autowired
	private FindActionPlayer actionPlayer;

	@Autowired
	private IMatchDetailsRepository matchDetailsRepository;

	@Autowired
	private ILeaderboardPlayerRepository leaderboardPlayerRepository;

	public MatchLeague matchSimulation(MatchLeague matchLeague) {

		Team homeName = teamRepository.findByName(matchLeague.getHomeName());
		Team awayName = teamRepository.findByName(matchLeague.getAvayName());

		TeamDto ht = new TeamDto();
		TeamDto at = new TeamDto();

		BeanUtils.copyProperties(homeName, ht);
		BeanUtils.copyProperties(awayName, at);

		List<Player> playersHome = playerRepository.findByTeam2(homeName.getName());
		List<Player> playersAway = playerRepository.findByTeam2(awayName.getName());

		List<Float> homeOverall = calculateTeamStrong.calculateOveral(playersHome, homeName.getTactic());
		List<Float> awayOverall = calculateTeamStrong.calculateOveral(playersAway, awayName.getTactic());

		ht.setDefStrong(homeOverall.get(0));
		ht.setMidStrong(homeOverall.get(1));
		ht.setForStrong(homeOverall.get(2));

		at.setDefStrong(awayOverall.get(0));
		at.setMidStrong(awayOverall.get(1));
		at.setForStrong(awayOverall.get(2));

		float hDEF = ht.getDefStrong() < 25 ? 10 : ht.getDefStrong() - 15;
		float hMID = ht.getMidStrong() < 25 ? 10 : ht.getMidStrong() - 15;
		float hFOR = ht.getForStrong() < 25 ? 10 : ht.getForStrong() - 15;
		float hStr = (hDEF + hMID + hFOR + 45) / 3;

		float aDEF = at.getDefStrong() < 25 ? 10 : at.getDefStrong() - 15;
		float aMID = at.getMidStrong() < 25 ? 10 : at.getMidStrong() - 15;
		float aFOR = at.getForStrong() < 25 ? 10 : at.getForStrong() - 15;
		float aStr = (aDEF + aMID + aFOR + 45) / 3;

		float hoPOS = (float) (1.1 * ((hStr - 15) / ((hStr - 15) + (aStr - 15))));

		float hoSUT = ((hFOR * 5 + hMID) / ((hFOR * 5 + hMID) + (aDEF * 5 + aMID))) * 2 * 17;
		float hoORT = ((hMID * 3 + hDEF * 3) / ((hMID * 3 + hDEF * 3) + (aDEF * 5 + aMID))) * 2 * 23;
		float hoFAU = ((aFOR * 5 + aMID) / ((aFOR * 5 + aMID) + (hDEF * 5 + hMID))) * 2 * 20;
		float hoKAY = ((aDEF * 5 + aMID) / ((aDEF * 5 + aMID) + (hFOR * 5 + hMID))) * 2 * 35;
		float hoOFF = ((aDEF * 5 + aMID) / ((aDEF * 5 + aMID) + (hFOR * 5 + hMID))) * 2 * 5;
		float HisRed1 = (hoSUT + hoORT) / (hoKAY + hoOFF);

		float awSUT = ((aFOR * 5 + aMID) / ((aFOR * 5 + aMID) + (hDEF * 5 + hMID))) * 2 * 17;
		float awORT = ((aMID * 3 + aDEF * 3) / ((aMID * 3 + aDEF * 3) + (hDEF * 5 + hMID))) * 2 * 23;
		float awFAU = ((hFOR * 5 + hMID) / ((hFOR * 5 + hMID) + (aDEF * 5 + aMID))) * 2 * 20;
		float awKAY = ((hDEF * 5 + hMID) / ((hDEF * 5 + hMID) + (aFOR * 5 + aMID))) * 2 * 35;
		float awOFF = ((hDEF * 5 + hMID) / ((hDEF * 5 + hMID) + (aFOR * 5 + aMID))) * 2 * 5;
		float AisRed1 = (awSUT + awORT) / (awKAY + awOFF);

		int toSEC = 0, hoSEC = 0, awSEC = 0;

		int hSutOnn = 0, hSutAut = 0, hSutEng = 0, aSutOnn = 0, aSutAut = 0, aSutEng = 0;
		int hOrtKor = 0, aOrtKor = 0, hOrtOnn = 0, aOrtOnn = 0, hOrtAut = 0, aOrtAut = 0;
		int hGol = 0, aGol = 0, homOfs = 0, awyOfs = 0;
		int homFau = 0, awyFau = 0, homFri = 0, awyFri = 0, homPen = 0, awyPen = 0;
		int homYel = 0, awyYel = 0, homRed = 0, awyRed = 0;
		float homPos = 0, awyPos = 0;

		float frHAL = 0, scHAL = 0;

		int rf = (int) (Math.random() * 100) + 1;
		int rs = (int) (Math.random() * 100) + 1;

		frHAL += rf <= 2 ? 6 * 60
				: rf <= 4 ? 1 * 60 : rf <= 15 ? 5 * 60 : rf <= 26 ? 2 * 60 : rf <= 63 ? 3 * 60 : 4 * 60;
		scHAL += rs <= 2 ? 6 * 60
				: rs <= 4 ? 1 * 60 : rs <= 15 ? 5 * 60 : rs <= 26 ? 2 * 60 : rs <= 63 ? 3 * 60 : 4 * 60;

		List<MatchDeatils> details = new ArrayList<>();

		while (toSEC < 90 * 60 + frHAL + scHAL) {

			float hoTOT = hoSUT + hoORT + hoFAU + hoKAY + hoOFF;
			float awTOT = awSUT + awORT + awFAU + awKAY + awOFF;

			MatchDeatils detail = new MatchDeatils();

			detail.setMatchId(matchLeague);
			Long scPOS = (long) ((Math.random() * 90) + 1);
			detail.setSeconds(scPOS);
			toSEC += scPOS;

			if (toSEC <= frHAL + 45 * 60) {
				detail.setDevre("1. Yarı");
			} else {
				detail.setDevre("2. Yarı");
			}

			if (toSEC / 60 + 1 <= 45) {
				detail.setDk(Integer.toString(toSEC / 60 + 1));
			} else if (toSEC / 60 + 1 <= 45 + frHAL / 60) {
				detail.setDk("45 +" + Integer.toString(toSEC / 60 + 1 - 45));
			} else if (toSEC / 60 + 1 <= 90 + frHAL / 60) {
				detail.setDk(Integer.toString(toSEC / 60 + 1 - (int) frHAL / 60));
			} else {
				detail.setDk("90 +" + Integer.toString(toSEC / 60 + 1 - 90 - (int) frHAL / 60));
			}

			int hoOLY = (int) (Math.random() * hoTOT) + 1;
			int awOLY = (int) (Math.random() * awTOT) + 1;

			int witchTeam = (int) (Math.random() * 100) + 1;

			if (witchTeam < hoPOS * 100) { // TODO pozisyon ev sahibinin mi
				detail.setTeam1(homeName.getId());
				detail.setTeam2(awayName.getId());
				hoSEC += scPOS;
				if (hoOLY <= hoOFF) {
					homOfs++;
					detail.setOlay("Ofsayt");
					detail.setAraSonuc("Yok");
					detail.setSonuc("Yok");
					detail.setPlayerId1(actionPlayer.golculer(playersHome));
					detail.setPlayerId2(actionPlayer.asistciler(playersHome));
					while (detail.getPlayerId1() == detail.getPlayerId2()) {
						detail.setPlayerId2(actionPlayer.asistciler(playersHome));
					}
					detail.setPlayerId3(actionPlayer.defansif(playersAway));
				} else if (hoOLY <= hoOFF + hoKAY) {
					detail.setOlay("Top Kaybı");
					detail.setAraSonuc("Yok");
					detail.setSonuc("Yok");
					detail.setPlayerId1(actionPlayer.golculer(playersHome));
					detail.setPlayerId2(actionPlayer.asistciler(playersHome));
					while (detail.getPlayerId1() == detail.getPlayerId2()) {
						detail.setPlayerId2(actionPlayer.asistciler(playersHome));
					}
					detail.setPlayerId3(actionPlayer.defansif(playersAway));
				} else if (hoOLY <= hoOFF + hoKAY + hoSUT) { // TODO Pozisyon Şut mu?
					detail.setOlay("Şut");
					int sutIsaRan = (int) (Math.random() * 100 + 1);
					if (sutIsaRan < ((hFOR + 20) / 2)) {
						hSutEng++;
						detail.setAraSonuc("Engellendi");
						detail.setSonuc("Yok");
						detail.setPlayerId1(actionPlayer.golculer(playersHome));
						detail.setPlayerId2(actionPlayer.asistciler(playersHome));
						while (detail.getPlayerId1() == detail.getPlayerId2()) {
							detail.setPlayerId2(actionPlayer.asistciler(playersHome));
						}
						detail.setPlayerId3(actionPlayer.defansif(playersAway));
					} else if (sutIsaRan < hFOR + 20) {
						hSutOnn++;
						detail.setAraSonuc("İsabetli");
						int sutGolMu = (int) (Math.random() * 100 + 1);
						if (sutGolMu < 25) {
							hGol++;
							detail.setSonuc("Gol");

							detail.setPlayerId1(actionPlayer.golculer(playersHome));
							detail.setPlayerId2(actionPlayer.asistciler(playersHome));
							while (detail.getPlayerId1() == detail.getPlayerId2()) {
								detail.setPlayerId2(actionPlayer.asistciler(playersHome));
							}
							detail.setPlayerId3(playerRepository.findByTeam2AndRole(awayName.getName(), "GK").getId());
						} else {
							detail.setSonuc("Kurtarış");
							detail.setPlayerId1(actionPlayer.golculer(playersHome));
							detail.setPlayerId2(actionPlayer.asistciler(playersHome));
							while (detail.getPlayerId1() == detail.getPlayerId2()) {
								detail.setPlayerId2(actionPlayer.asistciler(playersHome));
							}
							detail.setPlayerId3(playerRepository.findByTeam2AndRole(awayName.getName(), "GK").getId());
						}
					} else {
						hSutAut++;
						detail.setAraSonuc("Aut");
						detail.setSonuc("Yok");
						detail.setPlayerId1(actionPlayer.golculer(playersHome));
						detail.setPlayerId2(actionPlayer.asistciler(playersHome));
						while (detail.getPlayerId1() == detail.getPlayerId2()) {
							detail.setPlayerId2(actionPlayer.asistciler(playersHome));
						}
						detail.setPlayerId3(actionPlayer.defansif(playersAway));
					}
				} else if (hoOLY <= hoOFF + hoKAY + hoSUT + hoORT) { // TODO Pozisyon Orta mı
					int korOrOrt = (int) (Math.random() * 100) + 1;
					if (korOrOrt < 30) {
						hOrtKor++;
						detail.setOlay("Korner");
					} else {
						detail.setOlay("Orta");
					}
					int ortRand = (int) (Math.random() * 100) + 1;
					if (ortRand < (hStr - 40) * 0.7) {
						hOrtOnn++;
						detail.setAraSonuc("İsabetli");
						int ortGolmu = (int) (Math.random() * 100) + 1;
						if (ortGolmu <= 12) {
							hGol++;
							hSutOnn++;
							detail.setSonuc("Gol");

							detail.setPlayerId1(actionPlayer.golculer(playersHome));
							detail.setPlayerId2(actionPlayer.kanatlar(playersHome));
							while (detail.getPlayerId1() == detail.getPlayerId2()) {
								detail.setPlayerId2(actionPlayer.kanatlar(playersHome));
							}
							detail.setPlayerId3(playerRepository.findByTeam2AndRole(awayName.getName(), "GK").getId());
						} else {
							hSutAut++;
							detail.setSonuc("Aut");
							detail.setPlayerId1(actionPlayer.golculer(playersHome));
							detail.setPlayerId2(actionPlayer.kanatlar(playersHome));
							while (detail.getPlayerId1() == detail.getPlayerId2()) {
								detail.setPlayerId2(actionPlayer.kanatlar(playersHome));
							}
							detail.setPlayerId3(actionPlayer.defansif(playersAway));
						}
					} else {
						hOrtAut++;
						detail.setAraSonuc("İsabetsiz");
						detail.setSonuc("Yok");
						detail.setPlayerId1(actionPlayer.kanatlar(playersHome));
						detail.setPlayerId2(actionPlayer.asistciler(playersHome));
						while (detail.getPlayerId1() == detail.getPlayerId2()) {
							detail.setPlayerId2(actionPlayer.asistciler(playersHome));
						}
						detail.setPlayerId3(actionPlayer.defansif(playersAway));
					}

				} else if (hoOLY <= hoOFF + hoKAY + hoSUT + hoORT + hoFAU) { // TODO Pozisyon Faul mü
					awyFau++;
					int kart = (int) (Math.random() * 200) + 1;
					if (kart <= 52) {
						if (kart <= 3) {
							awyRed++;
							detail.setOlay("Kırmızı");
							awSUT = (int) (awSUT * 0.85);
							awORT = (int) (awORT * 0.85);
							awFAU = (int) (awFAU * 0.85);
							awOFF = (int) (awOFF * 1.15);
							awKAY = (int) (awKAY * 1.15);
						} else {
							awyYel++;
							detail.setOlay("Sarı");
						}

						detail.setAraSonuc("Yok");
						detail.setSonuc("Yok");

						detail.setPlayerId1(actionPlayer.golculer(playersHome));
						detail.setPlayerId2(actionPlayer.asistciler(playersHome));
						while (detail.getPlayerId1() == detail.getPlayerId2()) {
							detail.setPlayerId2(actionPlayer.asistciler(playersHome));
						}
						detail.setPlayerId3(actionPlayer.defansif(playersAway));
					} else {
						detail.setOlay("Faul");
						int fauRand = (int) (Math.random() * 185) + 1;
						if (fauRand <= 3) {
							detail.setAraSonuc("Penaltı");
							homPen++;
							int penSon = (int) (Math.random() * 100) + 1;
							if (penSon <= 82) {
								detail.setSonuc("Gol");

								hGol++;
								aSutOnn++;
								detail.setPlayerId1(actionPlayer.duranTop(playersHome));
								detail.setPlayerId2(actionPlayer.asistciler(playersHome));
								while (detail.getPlayerId1() == detail.getPlayerId2()) {
									detail.setPlayerId2(actionPlayer.asistciler(playersHome));
								}
								detail.setPlayerId3(actionPlayer.defansif(playersAway));
							} else {
								detail.setSonuc("Yok");
								hSutAut++;
								detail.setPlayerId1(actionPlayer.duranTop(playersHome));
								detail.setPlayerId2(actionPlayer.asistciler(playersHome));
								detail.setPlayerId2(actionPlayer.asistciler(playersHome));
								while (detail.getPlayerId1() == detail.getPlayerId2()) {
									detail.setPlayerId2(actionPlayer.asistciler(playersHome));
								}
								detail.setPlayerId3(actionPlayer.defansif(playersAway));
							}
						} else if (fauRand <= 36) {
							detail.setAraSonuc("Frikik");
							homFri++;
							int friSon = (int) (Math.random() * 100) + 1;
							if (friSon <= 7) {
								detail.setSonuc("Gol");

								hGol++;
								hSutOnn++;
								detail.setPlayerId1(actionPlayer.duranTop(playersHome));
								detail.setPlayerId2(actionPlayer.asistciler(playersHome));
								while (detail.getPlayerId1() == detail.getPlayerId2()) {
									detail.setPlayerId2(actionPlayer.asistciler(playersHome));
								}
								detail.setPlayerId3(actionPlayer.defansif(playersAway));
							} else {
								detail.setSonuc("Yok");
								hSutAut++;
								detail.setPlayerId1(actionPlayer.duranTop(playersHome));
								detail.setPlayerId2(actionPlayer.asistciler(playersHome));
								while (detail.getPlayerId1() == detail.getPlayerId2()) {
									detail.setPlayerId2(actionPlayer.asistciler(playersHome));
								}
								detail.setPlayerId3(actionPlayer.defansif(playersAway));
							}
						} else {
							detail.setAraSonuc("Yok");
							detail.setSonuc("Yok");
							detail.setPlayerId1(actionPlayer.golculer(playersHome));
							detail.setPlayerId2(actionPlayer.asistciler(playersHome));
							while (detail.getPlayerId1() == detail.getPlayerId2()) {
								detail.setPlayerId2(actionPlayer.asistciler(playersHome));
							}
							detail.setPlayerId3(actionPlayer.defansif(playersAway));
						}
					}

				} else {
					detail.setOlay("Yok");
					detail.setAraSonuc("Yok");
					detail.setSonuc("Yok");
					detail.setPlayerId1(actionPlayer.golculer(playersHome));
					detail.setPlayerId2(actionPlayer.asistciler(playersHome));
					while (detail.getPlayerId1() == detail.getPlayerId2()) {
						detail.setPlayerId2(actionPlayer.asistciler(playersHome));
					}
					detail.setPlayerId3(actionPlayer.defansif(playersAway));
				}

			} else { // TODO pozisyon deplasmanın mı
				detail.setTeam1(awayName.getId());
				detail.setTeam2(homeName.getId());
				awSEC += scPOS;

				if (awOLY <= awOFF) {
					awyOfs++;
					detail.setOlay("Ofsayt");
					detail.setAraSonuc("Yok");
					detail.setSonuc("Yok");
					detail.setPlayerId1(actionPlayer.golculer(playersAway));
					detail.setPlayerId2(actionPlayer.asistciler(playersAway));
					while (detail.getPlayerId1() == detail.getPlayerId2()) {
						detail.setPlayerId2(actionPlayer.asistciler(playersAway));
					}
					detail.setPlayerId3(actionPlayer.defansif(playersHome));
				} else if (awOLY <= awOFF + awKAY) {
					detail.setOlay("Top Kaybı");
					detail.setAraSonuc("Yok");
					detail.setSonuc("Yok");
					detail.setPlayerId1(actionPlayer.golculer(playersAway));
					detail.setPlayerId2(actionPlayer.asistciler(playersAway));
					while (detail.getPlayerId1() == detail.getPlayerId2()) {
						detail.setPlayerId2(actionPlayer.asistciler(playersAway));
					}
					detail.setPlayerId3(actionPlayer.defansif(playersHome));
				} else if (awOLY <= awOFF + awKAY + awSUT) { // TODO Pozisyon Şut mu?
					detail.setOlay("Şut");
					int sutIsaRan = (int) (Math.random() * 100 + 1);
					if (sutIsaRan < ((hFOR + 20) / 2)) {
						aSutEng++;
						detail.setAraSonuc("Engellendi");
						detail.setSonuc("Yok");
						detail.setPlayerId1(actionPlayer.golculer(playersAway));
						detail.setPlayerId2(actionPlayer.asistciler(playersAway));
						while (detail.getPlayerId1() == detail.getPlayerId2()) {
							detail.setPlayerId2(actionPlayer.asistciler(playersAway));
						}
						detail.setPlayerId3(actionPlayer.defansif(playersHome));
					} else if (sutIsaRan < hFOR + 20) {
						aSutOnn++;
						detail.setAraSonuc("İsabetli");
						int sutGolMu = (int) (Math.random() * 100 + 1);
						if (sutGolMu < 25) {
							aGol++;
							detail.setSonuc("Gol");

							detail.setPlayerId1(actionPlayer.golculer(playersAway));
							detail.setPlayerId2(actionPlayer.asistciler(playersAway));
							while (detail.getPlayerId1() == detail.getPlayerId2()) {
								detail.setPlayerId2(actionPlayer.asistciler(playersAway));
							}
							detail.setPlayerId3(playerRepository.findByTeam2AndRole(homeName.getName(), "GK").getId());
						} else {
							detail.setSonuc("Kurtarış");
							detail.setPlayerId1(actionPlayer.golculer(playersAway));
							detail.setPlayerId2(actionPlayer.asistciler(playersAway));
							while (detail.getPlayerId1() == detail.getPlayerId2()) {
								detail.setPlayerId2(actionPlayer.asistciler(playersAway));
							}
							detail.setPlayerId3(playerRepository.findByTeam2AndRole(homeName.getName(), "GK").getId());
						}
					} else {
						aSutAut++;
						detail.setAraSonuc("Aut");
						detail.setSonuc("Yok");
						detail.setPlayerId1(actionPlayer.golculer(playersAway));
						detail.setPlayerId2(actionPlayer.asistciler(playersAway));
						while (detail.getPlayerId1() == detail.getPlayerId2()) {
							detail.setPlayerId2(actionPlayer.asistciler(playersAway));
						}
						detail.setPlayerId3(actionPlayer.defansif(playersHome));
					}
				} else if (awOLY <= awOFF + awKAY + awSUT + awORT) { // TODO Pozisyon Orta mı
					int korOrOrt = (int) (Math.random() * 100) + 1;
					if (korOrOrt < 30) {
						aOrtKor++;
						detail.setOlay("Korner");
					} else {
						detail.setOlay("Orta");
					}
					int ortRand = (int) (Math.random() * 100) + 1;
					if (ortRand < (aStr - 40) * 0.7) {
						aOrtOnn++;
						detail.setAraSonuc("İsabetli");
						int ortGolmu = (int) (Math.random() * 100) + 1;
						if (ortGolmu <= 12) {
							aGol++;
							aSutOnn++;
							detail.setSonuc("Gol");
							detail.setPlayerId1(actionPlayer.golculer(playersAway));
							detail.setPlayerId2(actionPlayer.kanatlar(playersAway));
							while (detail.getPlayerId1() == detail.getPlayerId2()) {
								detail.setPlayerId2(actionPlayer.kanatlar(playersAway));
							}
							detail.setPlayerId3(playerRepository.findByTeam2AndRole(homeName.getName(), "GK").getId());
						} else {
							hSutAut++;
							detail.setSonuc("Aut");
							detail.setPlayerId1(actionPlayer.golculer(playersAway));
							detail.setPlayerId2(actionPlayer.kanatlar(playersAway));
							while (detail.getPlayerId1() == detail.getPlayerId2()) {
								detail.setPlayerId2(actionPlayer.kanatlar(playersAway));
							}
							detail.setPlayerId3(actionPlayer.defansif(playersHome));
						}
					} else {
						aOrtAut++;
						detail.setAraSonuc("İsabetsiz");
						detail.setSonuc("Yok");
						detail.setPlayerId1(actionPlayer.kanatlar(playersAway));
						detail.setPlayerId2(actionPlayer.asistciler(playersAway));
						while (detail.getPlayerId1() == detail.getPlayerId2()) {
							detail.setPlayerId2(actionPlayer.asistciler(playersAway));
						}
						detail.setPlayerId3(actionPlayer.defansif(playersHome));
					}
				} else if (awOLY <= awOFF + awKAY + awSUT + awORT + awFAU) { // TODO Pozisyon Faul mü
					homFau++;
					int kart = (int) (Math.random() * 200) + 1;
					if (kart <= 52) {
						if (kart <= 3) {
							homRed++;
							detail.setOlay("Kırmızı");
							hoSUT = (int) (hoSUT * 0.85);
							hoORT = (int) (hoORT * 0.85);
							hoFAU = (int) (hoFAU * 0.85);
							hoOFF = (int) (hoOFF * 1.15);
							hoKAY = (int) (hoKAY * 1.15);
						} else {
							homYel++;
							detail.setOlay("Sarı");
						}

						detail.setAraSonuc("Yok");
						detail.setSonuc("Yok");
						detail.setPlayerId1(actionPlayer.golculer(playersAway));
						detail.setPlayerId2(actionPlayer.asistciler(playersAway));
						while (detail.getPlayerId1() == detail.getPlayerId2()) {
							detail.setPlayerId2(actionPlayer.asistciler(playersAway));
						}
						detail.setPlayerId3(actionPlayer.defansif(playersHome));
					} else {
						detail.setOlay("Faul");
						int fauRand = (int) (Math.random() * 185) + 1;
						if (fauRand <= 3) {
							detail.setAraSonuc("Penaltı");
							awyPen++;
							int penSon = (int) (Math.random() * 100) + 1;
							if (penSon <= 82) {
								detail.setSonuc("Gol");

								aGol++;
								hSutOnn++;
								detail.setPlayerId1(actionPlayer.duranTop(playersAway));
								detail.setPlayerId2(actionPlayer.asistciler(playersAway));
								while (detail.getPlayerId1() == detail.getPlayerId2()) {
									detail.setPlayerId2(actionPlayer.asistciler(playersAway));
								}
								detail.setPlayerId3(actionPlayer.defansif(playersHome));
							} else {
								detail.setSonuc("Yok");
								aSutAut++;
								detail.setPlayerId1(actionPlayer.duranTop(playersAway));
								detail.setPlayerId2(actionPlayer.asistciler(playersAway));
								while (detail.getPlayerId1() == detail.getPlayerId2()) {
									detail.setPlayerId2(actionPlayer.asistciler(playersAway));
								}
								detail.setPlayerId3(actionPlayer.defansif(playersHome));
							}
						} else if (fauRand <= 36) {
							detail.setAraSonuc("Frikik");
							awyFri++;
							int friSon = (int) (Math.random() * 100) + 1;
							if (friSon <= 7) {
								detail.setSonuc("Gol");

								aGol++;
								aSutOnn++;
								detail.setPlayerId1(actionPlayer.duranTop(playersAway));
								detail.setPlayerId2(actionPlayer.asistciler(playersAway));
								while (detail.getPlayerId1() == detail.getPlayerId2()) {
									detail.setPlayerId2(actionPlayer.asistciler(playersAway));
								}
								detail.setPlayerId3(actionPlayer.defansif(playersHome));
							} else {
								detail.setSonuc("Yok");
								aSutAut++;
								detail.setPlayerId1(actionPlayer.duranTop(playersAway));
								detail.setPlayerId2(actionPlayer.asistciler(playersAway));
								while (detail.getPlayerId1() == detail.getPlayerId2()) {
									detail.setPlayerId2(actionPlayer.asistciler(playersAway));
								}
								detail.setPlayerId3(actionPlayer.defansif(playersHome));
							}
						} else {
							detail.setAraSonuc("Yok");
							detail.setSonuc("Yok");
							detail.setPlayerId1(actionPlayer.golculer(playersAway));
							detail.setPlayerId2(actionPlayer.asistciler(playersAway));
							while (detail.getPlayerId1() == detail.getPlayerId2()) {
								detail.setPlayerId2(actionPlayer.asistciler(playersAway));
							}
							detail.setPlayerId3(actionPlayer.defansif(playersHome));
						}
					}
				} else {
					detail.setOlay("Yok");
					detail.setAraSonuc("Yok");
					detail.setSonuc("Yok");
					detail.setPlayerId1(actionPlayer.golculer(playersAway));
					detail.setPlayerId2(actionPlayer.asistciler(playersAway));
					while (detail.getPlayerId1() == detail.getPlayerId2()) {
						detail.setPlayerId2(actionPlayer.asistciler(playersAway));
					}
					detail.setPlayerId3(actionPlayer.defansif(playersHome));
				}
			}
			detail.setSeason(matchLeague.getSeason());
			detail.setLeague(matchLeague.getLeague());
			details.add(detail);

		}
		matchDetailsRepository.saveAll(details);
		List<LeaderboardPlayer> leaderboardPlayers = leaderboardPlayerRepository
				.findBySeasonAndAndLeague(matchLeague.getSeason(), matchLeague.getLeague());

		List<Player> mactakiOnbirler = new ArrayList<>();
		mactakiOnbirler.addAll(playersHome);
		mactakiOnbirler.addAll(playersAway);

		for (Player player : mactakiOnbirler) {
			if (player.getSquad() <= 11) {
				LeaderboardPlayer findLeaderPlayer = leaderboardPlayerRepository.findBySeasonAndAndLeagueAndPlayerId(
						matchLeague.getSeason(), matchLeague.getLeague(), player.getId());
				if (findLeaderPlayer == null) {
					findLeaderPlayer = new LeaderboardPlayer();
					findLeaderPlayer.setSeason(matchLeague.getSeason());
					findLeaderPlayer.setLeague(matchLeague.getLeague());
					findLeaderPlayer.setPlayerId(player.getId());
					findLeaderPlayer.setTeamName(player.getTeam2());
					findLeaderPlayer.setTeamId(player.getTeam().getId());
					findLeaderPlayer.setFullName(player.getName() + " " + player.getSurname());
					findLeaderPlayer.setGoal(0);
					findLeaderPlayer.setAsist(0);
					findLeaderPlayer.setOnbir(0);
					findLeaderPlayer.setMevki(player.getPosition());
					findLeaderPlayer.setStrong(player.getStrong());
				}
				findLeaderPlayer.setOnbir(findLeaderPlayer.getOnbir() + 1);

				for (MatchDeatils md : details) {
					if (md.getSonuc().equals("Gol")) {
						if (findLeaderPlayer.getPlayerId().equals(md.getPlayerId1())) {
							findLeaderPlayer.setGoal(findLeaderPlayer.getGoal() + 1);
						}
						if (findLeaderPlayer.getPlayerId().equals(md.getPlayerId2())) {
							findLeaderPlayer.setAsist(findLeaderPlayer.getAsist() + 1);
						}
					}
				}

				leaderboardPlayers.add(findLeaderPlayer);
			}
		}

		leaderboardPlayerRepository.saveAll(leaderboardPlayers);

		int hSutTot = hSutOnn + hSutAut + hSutEng;
		int aSutTot = aSutOnn + aSutAut + aSutEng;

		int hOrtTot = hOrtOnn + hOrtAut;
		int aOrtTot = aOrtOnn + aOrtAut;

		homPos = ((float) hoSEC / ((float) hoSEC + awSEC));
		awyPos = (float) (1 - homPos);

		float HisRed2 = (hoSUT + hoORT) / (hoKAY + hoOFF);
		float AisRed2 = (awSUT + awORT) / (awKAY + awOFF);

		float hStaEnd = HisRed2 / HisRed1;
		float aStaEnd = AisRed2 / AisRed1;

		System.out.printf("%-7s %-10s %-27s %-15s %-15s %-13s %-13s %-13s %-13s %-20s %s%n",
				String.format("%d %d", hGol, aGol), // Takım maç sonuçlarını gösterir
				String.format("%.2f %.2f", hStaEnd, aStaEnd),
				String.format("(%.0f %.0f) (%.2f) (%.2f %.2f)", hStr, aStr, hoPOS, homPos, awyPos),
				String.format("%.0f %.0f %.0f %.0f %.0f", hoSUT, hoORT, hoFAU, hoKAY, hoOFF),
				String.format("%.0f %.0f %.0f %.0f %.0f", awSUT, awORT, awFAU, awKAY, awOFF),
				String.format("%d (%d %d %d)", hSutTot, hSutOnn, hSutEng, hSutAut),
				String.format("%d (%d %d %d)", aSutTot, aSutOnn, aSutEng, aSutAut),
				String.format("%d (%d %d %d)", hOrtTot, hOrtOnn, hOrtAut, hOrtKor),
				String.format("%d (%d %d %d)", hOrtTot, hOrtOnn, hOrtAut, hOrtKor),
				String.format("(%d %d %d) (%d %d %d)", homFau, homFri, homPen, awyFau, awyFri, awyPen),
				String.format("(%d %d) (%d %d) (%d %d)", homOfs, awyOfs, homRed, awyRed, homYel, awyYel));

		int macRes = 0;
		if (hGol > aGol) {
			macRes = 1;
		} else if (hGol < aGol) {
			macRes = 2;
		}

		matchLeague.setMacRes(macRes);
		matchLeague.setHSutOnn(hSutOnn);
		matchLeague.setHSutAut(hSutAut);
		matchLeague.setHSutEng(hSutEng);
		matchLeague.setHSutTot(hSutTot);
		matchLeague.setASutOnn(aSutOnn);
		matchLeague.setASutAut(aSutAut);
		matchLeague.setASutEng(aSutEng);
		matchLeague.setASutTot(aSutTot);

		matchLeague.setHOrtOnn(hOrtOnn);
		matchLeague.setHOrtAut(hOrtAut);
		matchLeague.setHOrtKor(hOrtKor);
		matchLeague.setHOrtTot(hOrtTot);
		matchLeague.setAOrtOnn(aOrtOnn);
		matchLeague.setAOrtAut(aOrtAut);
		matchLeague.setAOrtKor(aOrtKor);
		matchLeague.setAOrtTot(aOrtTot);

		matchLeague.setHomeGol(hGol);
		matchLeague.setAwayGol(aGol);
		matchLeague.setHomOfs(homOfs);
		matchLeague.setAwyOfs(awyOfs);
		matchLeague.setHomPos(homPos);
		matchLeague.setAwyPos(awyPos);

		matchLeague.setHomFau(homFau);
		matchLeague.setAwyFau(awyFau);
		matchLeague.setHomFri(homFri);
		matchLeague.setAwyFri(awyFri);
		matchLeague.setHomPen(homPen);
		matchLeague.setAwyPen(awyPen);
		matchLeague.setHomYel(homYel);
		matchLeague.setAwyYel(awyYel);
		matchLeague.setHomRed(homRed);
		matchLeague.setAwyRed(awyRed);

		matchLeague.setIsPlayed("true");

		return matchLeague;
	}
}
