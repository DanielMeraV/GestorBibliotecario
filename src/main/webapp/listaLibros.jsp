<%@ page import="logica.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/solicitarPrestamo.css">
    <title>Lista de Libros</title>
</head>

<body>
<div class="container">
    <h1>Lista de Libros</h1>
    <p>Lista de todos los libros registrados: </p>

    <table class="loans-table">
        <!-- Tabla de Libros -->
        <caption>Libros</caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Autor</th>
            <th>Género</th>
            <th>Disponibilidad</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<ClaseLibro> listaLibros = (List) request.getSession().getAttribute("listaLibros");
            String disponibilidad;
            for (ClaseLibro libro : listaLibros) {
                disponibilidad = libro.getDisponibilidad() ? "Si" : "No";
        %>
        <tr>
            <td><%=libro.getIdLibro()%>
            </td>
            <td><%=libro.getTitulo()%>
            </td>
            <td><%=libro.getAutor()%>
            </td>
            <td><%=libro.getGenero()%>
            </td>
            <td><%=disponibilidad%>
            </td>
        </tr>
        <% }%>
        </tbody>
    </table>

    <br>
    <br>

    <div class="buttons-container">
        <button type="button" class="return-button" onclick="window.location.href='index.jsp'">Regresar</button>
    </div>
</div>
</body>
</html>
