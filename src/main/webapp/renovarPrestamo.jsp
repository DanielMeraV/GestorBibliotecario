<%@ page import="logica.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/solicitarPrestamo.css">
    <title>Renovar un préstamo</title>
</head>

<body>
<div class="container">
    <h1>Renovar un préstamo</h1>
    <p>Seleccione el id del préstamo del que desea renovar.</p>

    <div class="tables-container">
        <table class="loans-table">
            <!-- Tabla de Préstamos -->
            <caption>Prestamos</caption>
            <thead>
            <tr>
                <th>ID Préstamo</th>
                <th>Cédula Estudiante</th>
                <th>ID Libro</th>
                <th>Fecha de Préstamo</th>
                <th>Fecha de Devolución</th>
                <th>Multa</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<ClasePrestamo> listaPrestamos = (List) request.getSession().getAttribute("listaPrestamos");
                String multa;
                for (ClasePrestamo prestamo : listaPrestamos) {
                    multa = prestamo.getMulta() ? "Si" : "No";
            %>
            <tr>
                <td><%=prestamo.getIdPrestamo()%>
                </td>
                <td><%=prestamo.getCedula()%>
                </td>
                <td><%=prestamo.getIdLibro()%>
                </td>
                <td><%=prestamo.getFechaPrestamo()%>
                </td>
                <td><%=prestamo.getFechaDevolucion()%>
                </td>
                <td><%=multa%>
                </td>
            </tr>
            <% }%>
            </tbody>
        </table>
    </div>

    <tr>
        <th colspan="2">&nbsp;&nbsp;&nbsp;</th>
        <th colspan="2">&nbsp;&nbsp;&nbsp;</th>
        <th colspan="2">&nbsp;&nbsp;&nbsp;</th>
    </tr>

    <div class="formulario">
        <form action="prestamo" method="post">
            <label for="idPrestamo">ID del préstamo:</label>
            <input type="text" id="idPrestamo" name="idPrestamo" placeholder="Ingrese el ID del préstamo aquí">

            <label for="nuevaFecha">&nbsp;&nbsp;&nbsp;Nueva fecha:</label>
            <input type="date" id="nuevaFecha" name="nuevaFecha" placeholder="Ingrese la nueva fecha aquí">

            <button type="submit" name="action" value="renovarPrestamo">Renovar</button>
        </form>
    </div>

    <% String errorMensaje = (String) session.getAttribute("errorMensaje"); %>
    <% if (errorMensaje != null && !errorMensaje.isEmpty()) { %>
    <div class="error-message">
        <%= errorMensaje %>
    </div>
    <% } %>

    <p></p>

    <div class="buttons-container">
        <button type="button" class="return-button" onclick="window.location.href='index.jsp'">Regresar</button>
    </div>
</div>

</body>
</html>