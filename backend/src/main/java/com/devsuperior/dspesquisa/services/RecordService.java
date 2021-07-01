package com.devsuperior.dspesquisa.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dspesquisa.dto.RecordDto;
import com.devsuperior.dspesquisa.dto.RecordInsertDto;
import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.entities.Record;
import com.devsuperior.dspesquisa.repositories.GameRepository;
import com.devsuperior.dspesquisa.repositories.RecordRepository;

@Service
public class RecordService {

	@Autowired
	private RecordRepository repository;

	@Autowired
	private GameRepository gameRepository;

	@Transactional
	public RecordDto insert(RecordInsertDto dto) {

		Record entity = new Record();

		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		entity.setMoment(Instant.now());

		Game game = gameRepository.getOne(dto.getGameId());
		entity.setGame(game);

		entity = repository.save(entity);

		return new RecordDto(entity);
	}

	@Transactional(readOnly = true)
	public Page<RecordDto> findByMoment(Instant minDate, Instant maxDate, Pageable pageRequest) {

		return repository.findByMoment(minDate, maxDate, pageRequest).map(RecordDto::new);
	}

	@Transactional(readOnly = true)
	public Page<RecordDto> findAll(Pageable pageRequest) {

		return repository.findAll(pageRequest).map(RecordDto::new);

	}
}
