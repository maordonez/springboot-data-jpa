package com.maog.app.models.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="factura_items")
public class ItemFactura {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Integer cantidad;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name ="producto_id")
	private Producto producto;
	
	public Double calcularImporte() {
		return cantidad.longValue() * producto.getPrecio();
	}
	
	

}
