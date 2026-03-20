package com.juniordev.metalmaster.dto;

import com.juniordev.metalmaster.entity.EnderecoEntity;

public record EnderecoResponseDTO(Long id, String nome, String numero, String cidade, String cep) {

	public EnderecoResponseDTO(EnderecoEntity e) {
		this(e.getId(), e.getNome(), e.getNumero(), e.getCidade(), e.getCep());
	}
}
