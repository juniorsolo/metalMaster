package com.juniordev.metalmaster.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.juniordev.metalmaster.entity.AgendamentoEntity;

public record AgendamentoResponseDTO(Long id, String tecnico, String descricao, LocalDate data, LocalTime hora) {

	public AgendamentoResponseDTO(AgendamentoEntity a) {
		this(a.getId(), a.getTecnico(), a.getDescricao(), a.getData(), a.getHora());
	}
}
