package com.juniordev.metalmaster.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniordev.metalmaster.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>{

}
