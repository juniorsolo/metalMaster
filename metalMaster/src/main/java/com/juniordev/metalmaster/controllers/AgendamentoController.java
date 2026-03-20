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

import com.juniordev.metalmaster.dto.AgendamentoRequestDTO;
import com.juniordev.metalmaster.dto.AgendamentoResponseDTO;
import com.juniordev.metalmaster.service.AgendamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes/{clienteId}/agendamentos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AgendamentoController {

	@Autowired
	AgendamentoService service;

	@GetMapping
	public ResponseEntity<List<AgendamentoResponseDTO>> listar(@PathVariable Long clienteId) {
		List<AgendamentoResponseDTO> list = service.listarPorCliente(clienteId).stream()
				.map(AgendamentoResponseDTO::new).toList();
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<AgendamentoResponseDTO> criar(@PathVariable Long clienteId,
			@Valid @RequestBody AgendamentoRequestDTO dto) {
		AgendamentoResponseDTO response = new AgendamentoResponseDTO(service.criar(clienteId, dto));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(response.id()).toUri();
		return ResponseEntity.created(location).body(response);
	}

	@DeleteMapping("/{agId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId, @PathVariable Long agId) {
		service.remover(clienteId, agId);
		return ResponseEntity.noContent().build();
	}
}
