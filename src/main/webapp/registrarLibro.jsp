<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/registrarLibro.css">
    <title>Registro de Estudiante</title>
</head>
<body>

<div id="formulario">

    <h1>Registrar Libro</h1>
    <p>Ingrese los datos del libro:</p>

    <form action="registroLibro" method="post">
        <label for="idLibro">ID libro:</label>
        <input type="text" id="idLibro" name="idLibro">

        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo">

        <label for="autor">Autor:</label>
        <input type="text" id="autor" name="autor">

        <label for="genero">Género:</label>
        <input type="text" id="genero" name="genero">

        <label>Disponibilidad:</label>
        <div class="radio-container">
            <label for="disponible">Disponible:</label>
            <input type="radio" id="disponible" name="disponibilidad" value="disponible">
            <label for="noDisponible">No disponible:</label>
            <input type="radio" id="noDisponible" name="disponibilidad" value="no_disponible">
        </div>

        <br>
        <br>

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


