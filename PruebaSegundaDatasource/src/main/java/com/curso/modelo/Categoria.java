package com.curso.modelo;

/**
 * Enumerado que contiene las diferentes categorias de productos.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 17/12/2024
 */
public enum Categoria {
	JUGUETE, COMIDA, ELECTRONICA, ROPA, HERRAMIENTAS;

	public static String[] obtenerCategorias() {
		return new String[] { JUGUETE.name(), COMIDA.name(), ELECTRONICA.name(), ROPA.name(), HERRAMIENTAS.name() };
	}
}
