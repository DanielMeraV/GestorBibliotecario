<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/registrarEstudiante.css">
    <title>Registro de Estudiante</title>
</head>
<body>

<div id="formulario">

    <h1>Eliminar Estudiante</h1>
    <p>Ingrese la cédula del estudiante a eliminar:</p>

    <form action="eliminarEstudiante" method="post">
        <label for="cedula">Cédula:</label>
        <input type="text" id="cedula" name="cedula">

        <div class="botones">
            <button type="submit">Aceptar</button>
            <button type="button" class="cancelar" onclick="window.location.href='index.jsp'">Cancelar</button>
        </div>
    </form>

    <% String errorMensaje = (String) session.getAttribute("errorMensaje"); %>
    <% if (errorMensaje != null && !errorMensaje.isEmpty()) { %>
    <div class="error-message">
        <%= errorMensaje %>
    </div>
    <% } %>
</div>

</body>
</html>


