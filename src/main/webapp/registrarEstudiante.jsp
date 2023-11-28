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

    <h1>Registrar Estudiante</h1>
    <p>Ingrese los datos del estudiante:</p>

    <form action="registroEstudiante" method="post"> <!-- El formulario envía datos al servlet 'registroEstudiante' -->
        <label for="cedula">Cedula:</label>
        <input type="text" id="cedula" name="cedula">

        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre">

        <label for="direccion">Dirección:</label>
        <input type="text" id="direccion" name="direccion">

        <label for="telefono">Teléfono:</label>
        <input type="text" id="telefono" name="telefono">

        <label for="codigo">Código Único:</label>
        <input type="text" id="codigo" name="codigo">

        <label for="correo">Correo Electrónico:</label>
        <input type="email" id="correo" name="correo">

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


