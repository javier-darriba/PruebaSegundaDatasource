package com.curso.modelo;

/**
 * Clase que representa una producto de la aplicacion. Contiene su nombre,
 * categoria, precio y stock.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 17/12/2024
 */
public class Producto {
	private String nombre;
	private Categoria categoria;
	private double precio;
	private int stock;

	public Producto(String nombre, Categoria categoria, double precio, int stock) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
	}

	public String getNombre() {
		return nombre;

	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
