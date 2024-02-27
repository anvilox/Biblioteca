<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="modelo.Libro" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Biblioteca</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>

	<% String error = (String)request.getAttribute("error"); 
		if(error!=null){%>
			<p><%=error%></p>
	<%}%>
	
	<% String info = (String)request.getAttribute("info");
		if (info!=null){%>
			<p><%=info%></p>
	<%}%>
	<table>
		<tr>
			<th><b>ISBN</b></th>
			<th><b>Título</b></th>
			<th><b>Autor</b></th>
		</tr>
		
		<% ArrayList<Libro> libros=(ArrayList<Libro>)request.getAttribute("libros");
			if (libros != null){
				for (Libro l:libros){%>
					<tr>
						<td><%=l.getIsbn()%></td>
						<td><%=l.getTitulo()%></td>
						<td><%=l.getAutor()%></td>
					</tr>
				<%}
			}%>
	</table>

<div>
	<form action="insertar" method="post">
		<label for="isbn">ISBN</label>
		<input type="text" id="isbn" name="isbn" placeholder="ISBN..">
		
		<label for="titulo">Titulo</label>
		<input type="text" id="titulo" name="titulo" placeholder="Titulo..">
		
		<label for="autor">Autor</label>
		<input type="text" id="autor" name="autor" placeholder="Autor..">
		
		<input type="submit" value="Submit">
	</form>
</div>
</body>
</html>