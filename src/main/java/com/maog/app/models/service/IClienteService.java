package com.maog.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.maog.app.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	public Page<Cliente> findAll(Pageable page);
	public void save(Cliente cliente);
	public Cliente findOne(long id);
	public void delete(long id);

}
