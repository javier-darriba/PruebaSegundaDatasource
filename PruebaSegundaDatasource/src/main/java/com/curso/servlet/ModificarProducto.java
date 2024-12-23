package com.curso.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet que modifica lo atributos de un producto
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 2.0 23/12/2024
 */
public class ModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Producto> productos = new ArrayList<Producto>();
		Producto producto = null;

		DataSource ds;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/MiDataSource");
			String sql = "SELECT * FROM productos;";

			try (Connection conexion = ds.getConnection();
					Statement statement = conexion.createStatement();
					ResultSet rs = statement.executeQuery(sql);) {

				while (rs.next()) {
					String nombre = rs.getString("nombre");
					Categoria categoria = Categoria.valueOf(rs.getString("categoria"));
					Double precio = rs.getDouble("precio");
					Integer stock = rs.getInt("stock");

					if (nombre == null || nombre.isEmpty() || categoria == null || precio == null || stock == null) {
						request.setAttribute("errorMessage", "Existe un producto inválido.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
						dispatcher.forward(request, response);
					}
					producto = new Producto(nombre, categoria, precio, stock);
					productos.add(producto);
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

		if (productos == null || productos.isEmpty()) {
			request.setAttribute("errorMessage", "No hay productos, primero cree uno.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			request.setAttribute("productos", productos);
			String[] categorias = Categoria.obtenerCategorias();
			request.setAttribute("categorias", categorias);
			RequestDispatcher dispatcher = request.getRequestDispatcher("modificarProducto.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombreAnterior = request.getParameter("nombreAnterior");
		String nombre = request.getParameter("nombre");
		String categoria = request.getParameter("categoria");
		String precioStr = request.getParameter("precio");
		String stockStr = request.getParameter("stock");

		if (nombreAnterior == null || nombreAnterior.isEmpty() || nombre == null || nombre.isEmpty()
				|| categoria == null || precioStr == null || precioStr.isEmpty() || stockStr == null
				|| stockStr.isEmpty()) {
			request.setAttribute("errorMessage", "Todos los campos son obligatorios.");
			doGet(request, response);
			return;
		}

		double precio = Double.parseDouble(precioStr);
		int stock = Integer.parseInt(stockStr);
		
		if(precio < 0 || stock < 0) {
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
				String sql = "UPDATE productos SET nombre = ?, categoria = ?, precio = ?, stock = ? WHERE nombre = ?";
				try (PreparedStatement statement = conexion.prepareStatement(sql)) {
					statement.setString(1, nombre);
					statement.setString(2, categoria);
					statement.setDouble(3, precio);
					statement.setInt(4, stock);
					statement.setString(5, nombreAnterior);

					int filasAfectadas = statement.executeUpdate();
					if (filasAfectadas == 0) {
						request.setAttribute("errorMessage", "Error, no pudo modificar el producto.");
						doGet(request, response);
						return;
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

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
