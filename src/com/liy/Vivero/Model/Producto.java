package com.liy.Vivero.Model;

import java.util.List;

public class Producto {
	private Integer id;
	private String nombre;
	private String condicion;
	private String fecha_ingreso;
	private Tipo tipo;
	private List<Historial> historial;
	private String nameTipo;
	
	public Producto() {
	}
	
	public Producto(String nombre, String condicion, String fecha_ingreso) {
		this.nombre = nombre;
		this.condicion = condicion;
		this.fecha_ingreso = fecha_ingreso;
	}
	
	public Producto(Integer id, String nombre, String condicion, String fecha_ingreso, String nameTipo) {
		this.id = id;
		this.nombre = nombre;
		this.condicion = condicion;
		this.fecha_ingreso = fecha_ingreso;
		this.nameTipo = nameTipo;
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
	
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	public String getCondicion() {
		return condicion;
	}
	
	public void setFecha_ingreso(String fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
	public String getFecha_ingreso() {
		return fecha_ingreso;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setHistorial(List<Historial> historial) {
		this.historial = historial;
	}
	public List<Historial> getHistorial() {
		return historial;
	}
	
	public void setNameTipo(String nameTipo) {
		this.nameTipo = nameTipo;
	}
	
	public String getNameTipo() {
		return nameTipo;
	}
}
