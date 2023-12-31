package controladores;

import java.io.*;

import dao.EstudianteDAO;
import dao.LibroDAO;
import dao.PrestamoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import logica.*;

@WebServlet(name = "PrestamoServlet", urlPatterns = {"/prestamo"})
public class PrestamoServlet extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "registrarPrestamo":{
                actualizarTablas(request, response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "registrarPrestamo": {
                registrarPrestamo(request, response);
                break;
            }

        }
    }
    public void registrarPrestamo (HttpServletRequest request, HttpServletResponse response)
    {
        try {
            HttpSession session = request.getSession();

            String cedula = request.getParameter("cedula");
            String idLibro = request.getParameter("idLibro");

            if (!PrestamoDAO.verificarExistenciaEstudianteYLibro(cedula, idLibro)) {
                session.setAttribute("errorMensaje", "Error: Cédula o ID de libro inválidos.");
                response.sendRedirect("registrarPrestamo.jsp");
            } else if (!LibroDAO.cambiarDisponibilidadLibro(idLibro)) {
                session.setAttribute("errorMensaje", "Error: No se pudo cambiar la disponibilidad.");
                response.sendRedirect("registrarPrestamo.jsp");
            } else {
                session.setAttribute("errorMensaje", null);

                Date fechaPrestamo = Date.valueOf(LocalDate.now());
                Date fechaDevolucion = Date.valueOf(LocalDate.now().plusDays(15));

                // Crear un nuevo objeto ClasePrestamo
                ClasePrestamo nuevoPrestamo = new ClasePrestamo(cedula, idLibro, fechaPrestamo, fechaDevolucion, false);

                if(PrestamoDAO.registrarPrestamo(nuevoPrestamo)){
                    doGet(request, response);
                }else {
                    session.setAttribute("errorMensaje", "Error: No ha sido posible registrar el préstamo.");
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarTablas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();

        sesion.setAttribute("listaEstudiantes", EstudianteDAO.listarEstudiantes());
        sesion.setAttribute("listaLibros", LibroDAO.listarLibrosDisponibles());
        sesion.setAttribute("listaPrestamos", PrestamoDAO.listarPrestamos());

        response.sendRedirect("registrarPrestamo.jsp");
    }
}