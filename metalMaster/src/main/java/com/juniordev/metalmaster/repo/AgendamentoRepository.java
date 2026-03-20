package com.juniordev.metalmaster.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniordev.metalmaster.entity.AgendamentoEntity;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {

}
