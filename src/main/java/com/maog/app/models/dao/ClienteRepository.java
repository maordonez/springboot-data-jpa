package com.maog.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maog.app.models.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
