package com.maog.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maog.app.models.entity.Producto;

@Repository
public interface ProductoRepository  extends CrudRepository<Producto, Long>{
	
	@Query("select e from Producto e where e.nombre like %?1%")
	public List<Producto> findByNombre(String term);
}
