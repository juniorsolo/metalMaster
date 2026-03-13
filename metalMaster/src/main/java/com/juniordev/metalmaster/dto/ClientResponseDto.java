package com.juniordev.metalmaster.dto;

import com.juniordev.metalmaster.entity.ClientEntity;

public record ClientResponseDto (Long id, String name){	
	
	public ClientResponseDto(ClientEntity client) {
		this(client.getId(), client.getName());
	}
}
