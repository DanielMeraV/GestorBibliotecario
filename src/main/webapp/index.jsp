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
        <h2>Estudiante</h2>
        <p class="description">En esta sección se puede ingresar un nuevo estudiante al sistema de la biblioteca.</p>
        <button class="btn" onclick="window.location.href='registrarEstudiante.jsp'">Registrar</button>
        <p class="description">En esta sección se puede eliminar un estudiante del sistema de la biblioteca.</p>
        <button class="btn" onclick="window.location.href='eliminarEstudiante.jsp'">Eliminar</button>
    </div>

    <div class="section">
        <h2>Libro</h2>
        <p class="description">En esta sección se puede ingresar un nuevo libro al sistema de la biblioteca.</p>
        <button class="btn" onclick="window.location.href='registrarLibro.jsp'">Registrar</button>
        <p class="description">En esta sección se puede eliminar un libro del sistema de la biblioteca.</p>
        <button class="btn" onclick="window.location.href='eliminarLibro.jsp'">Eliminar</button>
    </div>
<<<<<<< HEAD
<<<<<<< HEAD

    <div class="section">
        <form action="prestamo" method="get">
            <h2>Solicitud de Préstamo</h2>
            <p class="description">En esta sección se puede realizar un nuevo prestamo de un libro por estudiante.</p>
            <button class="btn" onclick="window.location.href='registrarPrestamo.jsp'" name="action" value="registrarPrestamo">Solicitar</button>
        </form>
    </div>

    <div class="section">
        <form action="devolucion" method="get">
            <h2>Devolución de un libro</h2>
            <p class="description">En esta sección se puede realizar la devolución de un libro prestado a un estudiante.</p>
            <button class="btn" onclick="window.location.href='realizarDevolucion.jsp'" name="action" value="realizarDevolucion">Registrar</button>
        </form>
    </div>
=======
>>>>>>> parent of 7070510 (feat: creación interfaz de renovar y asignación de  multas)
=======

    <div class="section">
        <form action="solicitarPrestamo" method="get">
            <h2>Solicitud de Préstamo</h2>
            <p class="description">En esta sección se puede realizar un nuevo prestamo de un libro por estudiante.</p>
            <button class="btn" onclick="window.location.href='solicitarPrestamo.jsp'">Solicitar</button>
        </form>
    </div>
>>>>>>> parent of 7070510 (feat: creación interfaz de renovar y asignación de  multas)
</div>

</body>
</html>

