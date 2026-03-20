package com.juniordev.metalmaster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.juniordev.metalmaster.dto.ClientRequestDto;
import com.juniordev.metalmaster.entity.ClienteEntity;
import com.juniordev.metalmaster.repo.ClientRepository;

@Service
public class ClienteService {

	@Autowired
	ClientRepository repo;

	public List<ClienteEntity> listarTodos() {
		return repo.findAll();
	}

	public ClienteEntity buscarPorId(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	public ClienteEntity criar(ClientRequestDto dto) {
		ClienteEntity e = new ClienteEntity();
		e.setCpf(dto.cpf());
		e.setName(dto.nome());
		return repo.save(e);
	}

	public ClienteEntity atualizar(Long id, ClientRequestDto dto) {
		ClienteEntity e = buscarPorId(id);
		e.setCpf(dto.cpf());
		e.setName(dto.nome());
		return repo.save(e);
	}

	public void deletar(Long id) {
		buscarPorId(id);
		repo.deleteById(id);
	}
}
