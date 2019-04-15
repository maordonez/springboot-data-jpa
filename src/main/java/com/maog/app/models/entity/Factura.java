package com.maog.app.models.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="facturas")
public class Factura {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	private String observacion;
	
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;
	
	@ManyToOne(fetch= FetchType.LAZY)
	private Cliente cliente;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="factura_id")
	private List<ItemFactura> itemsFactura;
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	public void addItemFactura(ItemFactura item) {
		this.itemsFactura.add(item);
	}
	
	public Double getTotal() {
		Double total = 0.0;
		
		for(ItemFactura item: itemsFactura) {
			
			total += item.getCantidad() * item.getProducto().getPrecio();
		}
		
		return total;
	}

}
