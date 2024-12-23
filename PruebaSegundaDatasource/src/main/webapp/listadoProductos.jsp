<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.curso.modelo.Producto"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado Productos</title>
<link rel="stylesheet" type="text/css" href="estilo.css">
</head>
<body>
<body>
	<h1>Listado de productos</h1>
	<table>
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Categoría</th>
				<th>Precio</th>
				<th>Stock</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<Producto> productos = (List<Producto>) request.getAttribute("productos");
			for (Producto p : productos) {
			%>
			<tr>
				<td><%=p.getNombre()%></td>
				<td><%=p.getCategoria()%></td>
				<td><%=p.getPrecio()%></td>
				<td><%=p.getStock()%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<a href="index.jsp">Volver al inicio</a>
</body>
</body>
</html>