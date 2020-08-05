package com.liy.Vivero.Model;


public class Usuario  {
	private Integer id;
	private String nombre;
	private String apellido;
	private String correo;
	private String usuario;
	private String contrasenia;
	
	public Usuario() {
	}
	
	public Usuario(String nombre, String apellido, String correo, String usuario, String contrasenia) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
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
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getUsuario() {
		return usuario;
	}
}
