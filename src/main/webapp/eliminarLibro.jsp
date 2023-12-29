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

    <h1>Eliminar Libro</h1>
    <p>Ingrese el ID del libro a eliminar:</p>

    <form action="libro" method="post">
        <label for="idLibro">ID del libro:</label>
        <input type="text" id="idLibro" name="idLibro">

        <div class="botones">
            <button type="submit" name="action" value="eliminarLibro">Aceptar</button>
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


