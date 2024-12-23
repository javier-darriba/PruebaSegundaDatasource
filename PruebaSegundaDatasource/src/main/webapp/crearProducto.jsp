<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta Producto</title>
<link rel="stylesheet" type="text/css" href="estilo.css">
</head>
<body>
	<h1>Dar de alta un producto</h1>

	<%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <p><%=errorMessage%></p>
    <%
        }
    %>


	<form action="CrearProducto" method="post">
		<label for="nombre">Nombre:</label> <input type="text" name="nombre"
			required><br> <br> <label for="categoria">Categoría:</label>
		<select name="categoria">
			<%
			String[] categorias = (String[]) request.getAttribute("categorias");
			for (String categoria : categorias) {
			%>
			<option value="<%=categoria%>"><%=categoria%></option>
			<%
			}
			%>
		</select><br> <br> <label for="precio">Precio:</label> <input
			type="number" name="precio" step="0.01" required><br> <br>
		<label for="stock">Stock:</label> <input type="number" name="stock"
			required><br> <br> <input type="submit"
			value="Crear Producto">
	</form>

	<a href="index.jsp">Volver al inicio</a>
</body>
</html>