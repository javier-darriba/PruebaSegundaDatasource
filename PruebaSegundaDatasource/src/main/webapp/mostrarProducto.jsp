<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.curso.modelo.Producto"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalles Producto</title>
<link rel="stylesheet" type="text/css" href="estilo.css">
</head>
<body>
	<%
	Producto producto = (Producto) request.getAttribute("producto");
	%>

	<h1>
		Detalles del Producto:
		<%=producto.getNombre()%></h1>

	<p>
		<strong>Categoría:</strong>
		<%=producto.getCategoria()%></p>
	<p>
		<strong>Precio:</strong>
		<%=producto.getPrecio()%></p>
	<p>
		<strong>Stock:</strong>
		<%=producto.getStock()%></p>

	<a href="index.jsp">Volver al inicio</a>
</body>
</html>