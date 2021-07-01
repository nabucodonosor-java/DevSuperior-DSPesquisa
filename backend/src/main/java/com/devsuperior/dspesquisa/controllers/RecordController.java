package com.devsuperior.dspesquisa.controllers;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/by-moment")
	public ResponseEntity<Page<RecordDto>> findByMoment(
			@RequestParam(value = "min", defaultValue = "moment") String min,
			@RequestParam(value = "max", defaultValue = "moment") String max,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "0") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "moment") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {

		if (linesPerPage == 0) {
			linesPerPage = Integer.MAX_VALUE;
		}

		Pageable pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Instant minDate = ("".equals(min)) ? null : Instant.parse(min);
		Instant maxDate = ("".equals(max)) ? null : Instant.parse(max);

		Page<RecordDto> listRecords = service.findByMoment(minDate, maxDate, pageRequest);
		return ResponseEntity.ok().body(listRecords);

	}

	@GetMapping
	public ResponseEntity<Page<RecordDto>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "0") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "moment") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {

		if (linesPerPage == 0) {
			linesPerPage = Integer.MAX_VALUE;
		}

		Pageable pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<RecordDto> listRecords = service.findAll(pageRequest);
		return ResponseEntity.ok().body(listRecords);

	}
}
