package com.curso.servlet;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet que busca los producto de la pagina
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 2.0 23/12/2024
 */
public class ListarProductos extends HttpServlet {
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

					if (nombre == null || nombre.isEmpty() || categoria == null | precio == null || stock == null) {
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
			request.setAttribute("errorMessage", "No hay productos que mostrar.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			request.setAttribute("productos", productos);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listadoProductos.jsp");
		dispatcher.forward(request, response);
	}
}
