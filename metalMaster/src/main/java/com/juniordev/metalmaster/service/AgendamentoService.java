package com.juniordev.metalmaster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.juniordev.metalmaster.dto.AgendamentoRequestDTO;
import com.juniordev.metalmaster.entity.AgendamentoEntity;
import com.juniordev.metalmaster.entity.ClienteEntity;
import com.juniordev.metalmaster.repo.AgendamentoRepository;

@Service
public class AgendamentoService {

	@Autowired
	ClienteService clienteService;

	@Autowired
	AgendamentoRepository repo;

	public List<AgendamentoEntity> listarPorCliente(Long clienteId) {
		return clienteService.buscarPorId(clienteId).getListaAgendamento();
	}

	public AgendamentoEntity criar(Long clienteId, AgendamentoRequestDTO dto) {
		ClienteEntity cliente = clienteService.buscarPorId(clienteId);
		AgendamentoEntity agendamento = new AgendamentoEntity();
		agendamento.setTecnico(dto.tecnico());
		agendamento.setDescricao(dto.descricao());
		agendamento.setData(dto.data());
		agendamento.setHora(dto.hora());
		cliente.adicionarAgendamento(agendamento);
		return repo.save(agendamento);
	}

	public void remover(Long clienteId, Long agId) {
		ClienteEntity cliente = clienteService.buscarPorId(clienteId);
		AgendamentoEntity agendamento = repo.findById(agId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado"));
		if (!agendamento.getCliente().getId().equals(clienteId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agendamento não pertence a este cliente");
		}
		cliente.removerAgendamento(agendamento);
		repo.delete(agendamento);
	}
}
