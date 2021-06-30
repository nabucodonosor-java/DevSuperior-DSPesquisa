package com.devsuperior.dspesquisa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dspesquisa.dto.RecordDto;
import com.devsuperior.dspesquisa.dto.RecordInsertDto;
import com.devsuperior.dspesquisa.services.RecordService;

@RestController
@RequestMapping("/records")
public class RecordController {
	
	@Autowired
	private RecordService service;
	
	@PostMapping
	public ResponseEntity<RecordDto> insert(@RequestBody RecordInsertDto dto) {
		RecordDto newDto = service.insert(dto);
		return ResponseEntity.ok().body(newDto);
	}
}
