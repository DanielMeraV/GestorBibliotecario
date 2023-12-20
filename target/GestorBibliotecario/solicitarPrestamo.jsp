<%@ page import="logica.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/solicitarPrestamo.css">
    <title>Solicitar Préstamo</title>
</head>

<body>
<div class="container">
    <h1>Solicitar Préstamo</h1>
    <p>Seleccione el estudiante registrado y los libros disponibles para realizar un préstamo.</p>

    <div class="tables-container">
        <table class="students-table">
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

        <table class="books-table">
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
                listaLibros = ClaseLibro.verificarLibrosDisponibles(listaLibros);
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
    </div>

    <tr>
        <th colspan="2">&nbsp;&nbsp;&nbsp;</th>
        <th colspan="2">&nbsp;&nbsp;&nbsp;</th>
        <th colspan="2">&nbsp;&nbsp;&nbsp;</th>
    </tr>

    <div class="formulario">
        <form action="solicitarPrestamo" method="post">
            <label for="cedula">Cédula del estudiante:</label>
            <input type="text" id="cedula" name="cedula" placeholder="Ingrese la cédula aquí">

            <label for="idLibro">&nbsp;&nbsp;&nbsp;ID del libro:</label>
            <input type="text" id="idLibro" name="idLibro" placeholder="Ingrese el ID aquí">

            <button type="submit">Aceptar</button>
        </form>
    </div>

    <% String errorMensaje = (String) session.getAttribute("errorMensaje"); %>
    <% if (errorMensaje != null && !errorMensaje.isEmpty()) { %>
    <div class="error-message">
        <%= errorMensaje %>
    </div>
    <% } %>

    <table class="loans-table">
        <!-- Tabla de Préstamos -->
        <caption>Prestamos</caption>
        <thead>
        <tr>
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

    <p></p>

    <div class="buttons-container">
        <button type="button" class="return-button" onclick="window.location.href='index.jsp'">Regresar</button>
    </div>
</div>
</body>
</html>
