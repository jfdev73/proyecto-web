<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, org.miranda.webapp.headers.models.*"%>

<%
List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
Producto producto = (Producto) request.getAttribute("producto");
String title = (String)request.getAttribute("title");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fromulario de Productos</title>
</head>
<body>
	<h1><%=(producto.getId()!=null && producto.getId()>0)? "Editar Producto": "Crear Producto"%></h1>

	<form action="<%=request.getContextPath()%>/productos/form"
		method="post">
		<div>
			<label for="nombre">Nombre:</label>
			<div>
				<input type="text" name="nombre" id="nombre" value="<%=producto.getNombre()%>">
			</div>

		</div>

		<%
		if (errores != null && errores.containsKey("nombre")) {
		%>
		<div style="color: red;"><%=errores.get("nombre")%></div>
		<%
		}
		%>

		<div>
			<label for="precio">Precio:</label>
			<div>
				<input type="number" name="precio" id="precio" value="<%= producto.getPrecio() != null ? producto.getPrecio() : "" %>">
			</div>

		</div>
		
		<%if (errores != null && errores.containsKey("precio")) {%>
		<div style="color: red;"><%=errores.get("precio")%></div>
		<%
		}
		%>

		<div>
			<label for="sku">Sku:</label>
			<div>
				<input type="text" name="sku" id="sku" value="<%= producto.getSku() !=null ? producto.getSku() : ""%>">
			</div>

		</div>
		<%
		if (errores != null && errores.containsKey("sku")) {
		%>
		<div style="color: red;"><%=errores.get("sku")%></div>
		<%
		}
		%>

		<div>
			<label for="sku">Categoria:</label>
			<div>
				<select name="categoria" id="categoria">
					<option value="">--- seleccionar ---</option>
					<%
					for (Categoria c : categorias) {
					%>
					<option value="<%=c.getId()%>" <%= c.getId().equals(producto.getCategoria().getId()) ? "selected" : "" %>> <%=c.getCategoria() %></option>
					<%
					}
					%>
				</select>
			</div>
		</div>
		<%
		if (errores != null && errores.containsKey("categoria")) {
		%>
		<div style="color: red;"><%=errores.get("categoria")%></div>
		<%
		}
		%>

		<div>
			<div>
				<input type="submit" value="<%=(producto.getId()!=null && producto.getId()>0)? "Editar": "Crear"%>">
			</div>
		</div>
		
		<input type="hidden" name="id" value="<%= producto.getId() %>">
	</form>


</body>
</html>