<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio</title>
<link rel="stylesheet" type="text/css" href="estilo.css">
</head>
<body>
	<h1>Bienvenido al sistema de gestión de productos</h1>
	<div class="links">
		<a href="CrearProducto">Dar de alta un producto</a><br> <a
			href="EliminarProducto">Eliminar un producto</a><br> <a
			href="ModificarProducto">Modificar un producto</a><br> <a
			href="ListarProductos">Ver listado de productos</a><br>
	</div>

	<h2>Introduce el nombre de un producto para buscarlo</h2>
	<form action="BuscarProducto" method="get">
		<label for="nombre">Nombre del producto:</label> <input type="text"
			id="nombre" name="nombre" required><br> <br> <input
			type="submit" value="Buscar producto">
	</form>

	<div class="error-message">
		<%
		String errorMessage = (String) request.getAttribute("errorMessage");
		if (errorMessage != null && !errorMessage.isEmpty()) {
		%>
		<p><%=errorMessage%></p>
		<%
		}
		%>
	</div>

	<div class="success-message">
		<%
		String successMessage = (String) request.getAttribute("successMessage");
		if (successMessage != null && !successMessage.isEmpty()) {
		%>
		<p><%=successMessage%></p>
		<%
		}
		%>
	</div>
</body>
</html>