package com.curso.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.curso.modelo.Categoria;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet que crea un producto indicado y redirige al inicio
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 2.0 23/12/2024
 */
public class CrearProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] categorias = Categoria.obtenerCategorias();
		request.setAttribute("categorias", categorias);

		RequestDispatcher dispatcher = request.getRequestDispatcher("crearProducto.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String categoria = request.getParameter("categoria");
		String precioStr = request.getParameter("precio");
		String stockStr = request.getParameter("stock");

		if (nombre == null || nombre.isEmpty() || categoria == null || precioStr == null || precioStr.isEmpty()
				|| stockStr == null || stockStr.isEmpty()) {
			request.setAttribute("errorMessage", "Todos los campos son obligatorios.");
			doGet(request, response);
			return;
		}

		double precio = Double.parseDouble(precioStr);
		int stock = Integer.parseInt(stockStr);

		if (precio < 0 || stock < 0) {
			request.setAttribute("errorMessage", "Los campos precio y stock deben ser números positivos.");
			doGet(request, response);
			return;
		}

		DataSource ds;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/MiDataSource");
			try (Connection conexion = ds.getConnection()) {
				String sql = "INSERT INTO productos (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";
				PreparedStatement statement = conexion.prepareStatement(sql);
				statement.setString(1, nombre);
				statement.setString(2, categoria);
				statement.setDouble(3, precio);
				statement.setInt(4, stock);
				int filasAfectadas = statement.executeUpdate();
				if (filasAfectadas == 0) {
					request.setAttribute("errorMessage", "Error, no pudo crear el producto.");
					doGet(request, response);
					return;
				}

			} catch (SQLException e) {
				request.setAttribute("errorMessage", "Hubo un error con la base de datos.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
		} catch (NamingException e) {
			request.setAttribute("errorMessage", "Hubo un error con la base de datos.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}

		response.sendRedirect("index.jsp");
	}
}