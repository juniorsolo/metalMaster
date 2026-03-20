package com.juniordev.metalmaster.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.juniordev.metalmaster.dto.EnderecoRequestDTO;
import com.juniordev.metalmaster.dto.EnderecoResponseDTO;
import com.juniordev.metalmaster.service.EnderecoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes/{clienteId}/enderecos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnderecoController {

	@Autowired
	EnderecoService service;

	@GetMapping
	public ResponseEntity<List<EnderecoResponseDTO>> listar(@PathVariable Long clienteId) {
		List<EnderecoResponseDTO> list = service.listarPorCliente(clienteId).stream()
				.map(EnderecoResponseDTO::new).toList();
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<EnderecoResponseDTO> adicionar(@PathVariable Long clienteId,
			@Valid @RequestBody EnderecoRequestDTO dto) {
		EnderecoResponseDTO response = new EnderecoResponseDTO(service.adicionar(clienteId, dto));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(response.id()).toUri();
		return ResponseEntity.created(location).body(response);
	}

	@DeleteMapping("/{endId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId, @PathVariable Long endId) {
		service.remover(clienteId, endId);
		return ResponseEntity.noContent().build();
	}
}
