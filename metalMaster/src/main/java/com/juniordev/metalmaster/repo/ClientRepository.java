package com.juniordev.metalmaster.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniordev.metalmaster.entity.ClienteEntity;

public interface ClientRepository extends JpaRepository<ClienteEntity, Long>{

}
