package com.juniordev.metalmaster.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ClienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cpf", nullable = true)
	private String cpf;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<EnderecoEntity> listaEndereco = new ArrayList<>();

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<AgendamentoEntity> listaAgendamento = new ArrayList<>();

	public void adicionarEndereco(EnderecoEntity endereco) {
		listaEndereco.add(endereco);
		endereco.setCliente(this);
	}

	public void removerEndereco(EnderecoEntity endereco) {
		listaEndereco.remove(endereco);
		endereco.setCliente(null);
	}

	public void adicionarAgendamento(AgendamentoEntity agendamento) {
		listaAgendamento.add(agendamento);
		agendamento.setCliente(this);
	}

	public void removerAgendamento(AgendamentoEntity agendamento) {
		listaAgendamento.remove(agendamento);
		agendamento.setCliente(null);
	}
}
