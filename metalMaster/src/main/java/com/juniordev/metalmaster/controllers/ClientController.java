package com.juniordev.metalmaster.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juniordev.metalmaster.dto.ClientRequestDto;
import com.juniordev.metalmaster.dto.ClientResponseDto;
import com.juniordev.metalmaster.entity.ClientEntity;
import com.juniordev.metalmaster.repo.ClientRepository;

@RestController
@RequestMapping("client")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientController {
	
	@Autowired
	ClientRepository  clientRepo;
	
	@GetMapping
	public List<ClientResponseDto> getAll() {
		List<ClientResponseDto> list = clientRepo.findAll().stream().map(ClientResponseDto::new).toList();
		return list;
	}
	
	@PostMapping
	public void saveClient(@RequestBody ClientRequestDto client) {
		ClientEntity entity = new ClientEntity(null, client.name());
		clientRepo.save(entity);
		return;
	}
	
	
	
	
}
