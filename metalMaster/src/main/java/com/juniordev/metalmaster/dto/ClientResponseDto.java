package com.juniordev.metalmaster.dto;

import com.juniordev.metalmaster.entity.ClienteEntity;

public record ClientResponseDto(Long id, String cpf, String nome) {

	public ClientResponseDto(ClienteEntity c) {
		this(c.getId(), c.getCpf(), c.getName());
	}
}
