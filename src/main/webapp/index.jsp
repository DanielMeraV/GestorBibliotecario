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

    <h2>Estudiante</h2>
    <div class="section">
        <div class="section-item">
            <p class="description">En esta sección se puede ingresar un nuevo estudiante al sistema de la biblioteca.</p>
            <button class="btn" onclick="window.location.href='registrarEstudiante.jsp'">Registrar</button>
        </div>
        <div class="section-item">
            <p class="description">En esta sección se puede eliminar un estudiante del sistema de la biblioteca.</p>
            <button class="btn" onclick="window.location.href='eliminarEstudiante.jsp'">Eliminar</button>
        </div>
        <div class="section-item">
            <form action="estudiante" method="get">
                <p class="description">En esta sección se puede ver la lista de los estudiantes registrados.</p>
                <button class="btn" onclick="window.location.href='listaEstudiante.jsp'" name="action" value="listaEstudiantes">Ver lista</button>
            </form>
        </div>
    </div>

    <h2>Libro</h2>
    <div class="section">
        <div class="section-item">
            <p class="description">En esta sección se puede ingresar un nuevo libro al sistema de la biblioteca.</p>
            <button class="btn" onclick="window.location.href='registrarLibro.jsp'">Registrar</button>
        </div>
        <div class="section-item">
            <p class="description">En esta sección se puede eliminar un libro del sistema de la biblioteca.</p>
            <button class="btn" onclick="window.location.href='eliminarLibro.jsp'">Eliminar</button>
        </div>
        <div class="section-item">
            <form action="libro" method="get">
                <p class="description">En esta sección se puede ver la lista de los libros registrados.</p>
                <button class="btn" onclick="window.location.href='listaLibros.jsp'" name="action" value="listaLibros">Ver lista</button>
            </form>
        </div>

    </div>

    <h2>Solicitud de Préstamo</h2>
    <div class="section">
        <div class="section-item">
            <form action="prestamo" method="get">
                <p class="description">En esta sección se puede realizar un nuevo prestamo de un libro por estudiante.</p>
                <button class="btn" onclick="window.location.href='registrarPrestamo.jsp'" name="action" value="registrarPrestamo">Solicitar</button>
            </form>
        </div>
        <div class="section-item">
            <form action="prestamo" method="get">
                <p class="description">En esta sección se puede ver la lista de los préstamos realizados.</p>
                <button class="btn" onclick="window.location.href='listaPrestamos.jsp'" name="action" value="listaPrestamos">Ver Lista</button>
            </form>
        </div>
    </div>

    <h2>Devolución de un libro</h2>
    <div class="section">
        <div class="section-item">
            <form action="devolucion" method="get">
                <p class="description">En esta sección se puede realizar la devolución de un libro prestado a un estudiante.</p>
                <button class="btn" onclick="window.location.href='realizarDevolucion.jsp'" name="action" value="realizarDevolucion">Registrar</button>
            </form>
        </div>
        <div class="section-item">
            <form action="devolucion" method="get">
                <p class="description">En esta sección se puede ver la lista de las devoluciones registradas.</p>
                <button class="btn" onclick="window.location.href='listaDevolucion.jsp'" name="action" value="listaDevolucion">Ver Lista</button>
            </form>
        </div>

    </div>
</div>

</body>
</html>