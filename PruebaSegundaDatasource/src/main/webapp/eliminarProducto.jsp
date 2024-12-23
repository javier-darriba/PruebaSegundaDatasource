<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.curso.modelo.Producto"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Eliminar Producto</title>
<link rel="stylesheet" type="text/css" href="estilo.css">
</head>
<body>
	<h1>Eliminar un producto</h1>
	<form action="EliminarProducto" method="post">
		<label for="nombre">Selecciona el producto a eliminar:</label> <select
			name="nombre" required>
			<%
			List<Producto> productos = (List<Producto>) request.getAttribute("productos");
			for (Producto p : productos) {
			%>
			<option value="<%=p.getNombre()%>"><%=p.getNombre()%></option>
			<%
			}
			%>
		</select><br>
		<br> <br> <input type="submit" value="Eliminar Producto">
	</form>
	<a href="index.jsp">Volver al inicio</a>
</body>
</html>