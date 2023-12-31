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
        PrestamoDAO.actualizarMultas();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "registrarPrestamo":{
                actualizarTablas(request, response);
                response.sendRedirect("registrarPrestamo.jsp");
                break;
            }
            case "renovarPrestamo": {
                actualizarTablas(request, response);
                response.sendRedirect("renovarPrestamo.jsp");
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
            case "renovarPrestamo": {
                renovarPrestamo(request, response);
                break;
            }
        }
    }


    private void renovarPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String idPrestamoStr = request.getParameter("idPrestamo");
            String nuevaFechaStr = request.getParameter("nuevaFecha");

            if (idPrestamoStr != null && nuevaFechaStr != null && !idPrestamoStr.isEmpty() && !nuevaFechaStr.isEmpty()) {
                int idPrestamo = Integer.parseInt(idPrestamoStr);
                Date nuevaFechaDevolucion = Date.valueOf(LocalDate.parse(nuevaFechaStr));

                if (PrestamoDAO.renovarPrestamo(idPrestamo, nuevaFechaDevolucion)) {
                    response.sendRedirect("index.jsp");
                } else {
                    session.setAttribute("errorMensaje", "Error:  La renovación no fue exitosa");
                    response.sendRedirect("renovarPrestamo.jsp");
                }
            } else {
                session.setAttribute("errorMensaje", "Error:  Parámetros inválidos o faltantes");
                response.sendRedirect("renovarPrestamo.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
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
    }
}