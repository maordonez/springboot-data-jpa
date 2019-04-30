package com.maog.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maog.app.models.dao.ClienteRepository;
import com.maog.app.models.dao.ProductoRepository;
import com.maog.app.models.entity.Cliente;
import com.maog.app.models.entity.Producto;


@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProductoRepository productoRepository;

	@Transactional(readOnly=true)
	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Transactional
	@Override
	public void save(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Transactional(readOnly=true)
	@Override
	public Cliente findOne(long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void delete(long id) {
		Optional<Cliente>  cliente = clienteRepository.findById(id);
		if(cliente.isPresent()) {
			clienteRepository.delete(cliente.get());
		}	
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Cliente> findAll(Pageable page) {
		return clienteRepository.findAll(page);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Producto> listarProductos(String term) {
		return productoRepository.findByNombre(term);
	}


}
