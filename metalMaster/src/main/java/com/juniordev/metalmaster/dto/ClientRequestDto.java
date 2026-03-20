package com.juniordev.metalmaster.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientRequestDto(@NotBlank String cpf, @NotBlank String nome) {

}
