package com.curso.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.curso.modelo.Categoria;
import com.curso.modelo.Producto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet que busca un producto en concreto
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 2.0 23/12/2024
 */
public class BuscarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Producto producto = null;
		String nombre = request.getParameter("nombre");

		DataSource ds;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/MiDataSource");
			try (Connection conexion = ds.getConnection()) {
				String sql = "SELECT * FROM productos WHERE nombre = ?;";
				try (PreparedStatement statement = conexion.prepareStatement(sql)) {
					statement.setString(1, nombre);
					try (ResultSet rs = statement.executeQuery()) {
						if (rs.next()) {
							Categoria categoria = Categoria.valueOf(rs.getString("categoria"));
							Double precio = rs.getDouble("precio");
							Integer stock = rs.getInt("stock");
							if (categoria == null | precio == null || stock == null) {
								request.setAttribute("errorMessage", "Producto inválido.");
								RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
								dispatcher.forward(request, response);
							}
							producto = new Producto(nombre, categoria, precio, stock);
						}
					}
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

		if (producto != null) {
			request.setAttribute("producto", producto);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/mostrarProducto.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Ese producto no existe, créalo o busque otro.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}
}
