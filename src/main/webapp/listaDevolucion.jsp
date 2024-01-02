<%@ page import="logica.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/solicitarPrestamo.css">
    <title>Lista de Devoluciones</title>
</head>

<body>
<div class="container">
    <h1>Lista de Devoluciones</h1>
    <p>Lista de todas las devoluciones registradas: </p>

    <table class="loans-table">
        <caption>Devoluciones</caption>
        <thead>
        <tr>
            <th>ID Devolución</th>
            <th>ID Préstamo</th>
            <th>Cédula Estudiante</th>
            <th>Fecha Devolución</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<ClaseDevolucion> listaDevolucion = (List) request.getSession().getAttribute("listaDevolucion");
            for (ClaseDevolucion devolucion : listaDevolucion) {
        %>
        <tr>
            <td><%=devolucion.getIdDevolucion()%>
            </td>
            <td><%=devolucion.getIdPrestamo()%>
            </td>
            <td><%=devolucion.getCedulaEstudiante()%>
            </td>
            <td><%=devolucion.getFechaDevolucion()%>
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
