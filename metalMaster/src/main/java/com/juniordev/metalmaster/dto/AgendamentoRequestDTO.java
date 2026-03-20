package com.juniordev.metalmaster.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AgendamentoRequestDTO(
		@NotBlank String tecnico,
		String descricao,
		@NotNull LocalDate data,
		@NotNull LocalTime hora) {
}
