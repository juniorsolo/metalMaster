package com.juniordev.metalmaster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.juniordev.metalmaster.dto.EnderecoRequestDTO;
import com.juniordev.metalmaster.entity.ClienteEntity;
import com.juniordev.metalmaster.entity.EnderecoEntity;
import com.juniordev.metalmaster.repo.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	ClienteService clienteService;

	@Autowired
	EnderecoRepository repo;

	public List<EnderecoEntity> listarPorCliente(Long clienteId) {
		return clienteService.buscarPorId(clienteId).getListaEndereco();
	}

	public EnderecoEntity adicionar(Long clienteId, EnderecoRequestDTO dto) {
		ClienteEntity cliente = clienteService.buscarPorId(clienteId);
		EnderecoEntity endereco = new EnderecoEntity();
		endereco.setNome(dto.nome());
		endereco.setNumero(dto.numero());
		endereco.setCidade(dto.cidade());
		endereco.setCep(dto.cep());
		cliente.adicionarEndereco(endereco);
		return repo.save(endereco);
	}

	public void remover(Long clienteId, Long endId) {
		ClienteEntity cliente = clienteService.buscarPorId(clienteId);
		EnderecoEntity endereco = repo.findById(endId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
		if (!endereco.getCliente().getId().equals(clienteId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endereço não pertence a este cliente");
		}
		cliente.removerEndereco(endereco);
		repo.delete(endereco);
	}
}
