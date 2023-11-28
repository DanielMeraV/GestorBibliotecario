<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/index.css">
    <title>Gestor Bibliotecario</title>
</head>
<body>

<div class="container">
    <h1>Gestor Bibliotecario</h1>

    <div class="section">
        <h2>Registrar Estudiante</h2>
        <p class="description">En esta sección se puede ingresar un nuevo estudiante al sistema de la biblioteca.</p>
        <button class="btn" onclick="window.location.href='registrarEstudiante.jsp'">Registrar</button>
    </div>

    <div class="section">
        <h2>Registrar Libro</h2>
        <p class="description">En esta sección se puede ingresar un nuevo libro al sistema de la biblioteca.</p>
        <button class="btn" onclick="window.location.href='registrarLibro.jsp'">Registrar</button>
    </div>

    <div class="section">
        <form action="solicitarPrestamo" method="get">
            <h2>Solicitud de Préstamo</h2>
            <p class="description">En esta sección se puede realizar un nuevo prestamo de un libro por estudiante.</p>
            <button class="btn" onclick="window.location.href='solicitarPrestamo.jsp'">Solicitar</button>
        </form>
    </div>
</div>

</body>
</html>

