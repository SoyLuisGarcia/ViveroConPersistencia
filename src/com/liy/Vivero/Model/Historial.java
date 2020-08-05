package com.liy.Vivero.Model;


public class Historial {
	private Integer id;
	private String fecha;
	private String fotografia;
	private Producto producto;
	
	public Historial() {
	}
	
	public Historial(Integer id, String fecha, String fotografia) {
		this.id = id;
		this.fecha = fecha;
		this.fotografia = fotografia;
	}
	
	public Historial(String fecha, String fotografia) {
		this.fecha = fecha;
		this.fotografia = fotografia;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getFecha() {
		return fecha;
	}
	
	
	
	public void setFotografia(String fotografia) {
		this.fotografia = fotografia;
	}
	public String getFotografia() {
		return fotografia;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Producto getProducto() {
		return producto;
	}
}
