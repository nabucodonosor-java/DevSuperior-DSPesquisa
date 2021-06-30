package com.devsuperior.dspesquisa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dspesquisa.dto.GameDto;
import com.devsuperior.dspesquisa.services.GameService;

@RestController
@RequestMapping("games")
public class GameController {
	
	@Autowired
	private GameService service;
	
	@GetMapping
	public ResponseEntity<List<GameDto>> findAll() {
		List<GameDto> listGames = service.findAll();
		return ResponseEntity.ok().body(listGames);
	}

}
