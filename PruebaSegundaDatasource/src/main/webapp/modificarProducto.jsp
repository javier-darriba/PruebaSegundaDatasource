<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.curso.modelo.Categoria"%>
<%@ page import="com.curso.modelo.Producto"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Producto</title>
<link rel="stylesheet" type="text/css" href="estilo.css">
</head>
<body>
	<h1>Modificar un producto</h1>

	<%
	String errorMessage = (String) request.getAttribute("errorMessage");
	if (errorMessage != null && !errorMessage.isEmpty()) {
	%>
	<p><%=errorMessage%></p>
	<%
	}
	%>

	<form action="ModificarProducto" method="post">

		<label for="producto">Selecciona el producto a modificar:</label> <select
			name="producto" required>
			<%
			List<Producto> productos = (List<Producto>) request.getAttribute("productos");
			for (Producto p : productos) {
			%>
			<option value="<%=p.getNombre()%>"><%=p.getNombre()%></option>
			<%
			}
			%>
		</select><br> <br> <input type="hidden" id="nombreAnterior"
			name="nombreAnterior"
			value="<%=productos != null && !productos.isEmpty() ? productos.get(0).getNombre() : ""%>">
		<label for="nombre">Nombre:</label> <input type="text" name="nombre"
			required><br> <br> <label for="categoria">Categoría:</label>
		<select name="categoria" required>
			<%
			String[] categorias = (String[]) request.getAttribute("categorias");
			for (String categoria : categorias) {
			%>
			<option value="<%=categoria%>"><%=categoria%></option>
			<%
			}
			%>
		</select><br> <br> <label for="precio">Precio:</label> <input
			type="number" name="precio" required><br> <br> <label
			for="stock">Stock:</label> <input type="number" name="stock" required><br>
		<br> <input type="submit" value="Modificar Producto">
	</form>

	<a href="index.jsp">Volver al inicio</a>
</body>
</html>