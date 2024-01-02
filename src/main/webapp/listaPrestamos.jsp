<%@ page import="logica.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="CSS/solicitarPrestamo.css">
  <title>Lista de Préstamos</title>
</head>

<body>
<div class="container">
  <h1>Lista de Préstamos</h1>
  <p>Lista de todos los préstamos registrados: </p>

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

  <br>
  <br>

  <div class="buttons-container">
    <button type="button" class="return-button" onclick="window.location.href='index.jsp'">Regresar</button>
  </div>
</div>
</body>
</html>
