package com.devsuperior.dspesquisa.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.entities.enums.Platform;

public class GameDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private Platform platform;

	public GameDto() {
	}

	public GameDto(Game entity) {
		id = entity.getId();
		title = entity.getTitle();
		platform = entity.getPlatform();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public static List<GameDto> converter(List<Game> listGames) {
		return listGames.stream().map(GameDto::new).collect(Collectors.toList());
	}

}
