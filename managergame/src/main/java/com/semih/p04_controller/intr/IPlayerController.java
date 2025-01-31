package com.semih.p04_controller.intr;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.semih.p02_entity.Player;
import com.semih.p02_entity.dto.PlayerDto;

public interface IPlayerController {

	public List<PlayerDto> getAllPlayerController();

	public void savePlayer(String position, String teamName);

	public Page<PlayerDto> getPlayerPage(int page, int size);

	public void updatePosition(String team, String position);

	public void updateAllPosition();

	public List<PlayerDto> getPlayerThisTeam(String team2);

	public Optional<Player> getPlayer(Long id);

	public void fillAllTeam();

}
