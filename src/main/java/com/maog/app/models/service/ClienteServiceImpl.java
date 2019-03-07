package com.maog.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maog.app.models.dao.ClienteRepository;
import com.maog.app.models.entity.Cliente;


@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public void save(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Override
	public Cliente findOne(long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(long id) {
		Optional<Cliente>  cliente = clienteRepository.findById(id);
		if(cliente.isPresent()) {
			clienteRepository.delete(cliente.get());
		}	
	}

	@Override
	public Page<Cliente> findAll(Pageable page) {
		return clienteRepository.findAll(page);
	}


}
