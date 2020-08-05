package com.liy.Vivero.Model;

import java.util.List;

public class Tipo  {
	private Integer id;
	private String nombre;
	private List<Producto> Producto;
	
	public Tipo() {
	}
	
	public Tipo(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setProducto(List<Producto> producto) {
		Producto = producto;
	}
	
	public List<Producto> getProducto() {
		return Producto;
	}
	
}
