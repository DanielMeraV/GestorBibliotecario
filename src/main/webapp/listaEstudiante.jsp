<%@ page import="logica.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/solicitarPrestamo.css">
    <title>Lista de Estudiantes</title>
</head>

<body>
<div class="container">
    <h1>Lista de Estudiantes</h1>
    <p>Lista de todos los estudiantes registrados: </p>

    <table class="loans-table">
        <!-- Tabla de Estudiantes -->
        <caption>Estudiantes</caption>
        <thead>
        <tr>
            <th>Cedula</th>
            <th>Nombre</th>
            <th>Direccion</th>
            <th>Telefono</th>
            <th>Codigo Unico</th>
            <th>Correo Electrónico</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<ClaseEstudiante> listaEstudiantes = (List) request.getSession().getAttribute("listaEstudiantes");
            for (ClaseEstudiante estudiante : listaEstudiantes) {
        %>
        <tr>
            <td><%=estudiante.getCedula()%>
            </td>
            <td><%=estudiante.getNombre()%>
            </td>
            <td><%=estudiante.getDireccion()%>
            </td>
            <td><%=estudiante.getTelefono()%>
            </td>
            <td><%=estudiante.getCodigoUnico()%>
            </td>
            <td><%=estudiante.getCorreoElectronico()%>
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
