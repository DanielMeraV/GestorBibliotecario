package controladores;

import java.io.*;

import dao.DevolucionDAO;
import dao.EstudianteDAO;
import dao.LibroDAO;
import dao.PrestamoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import logica.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebServlet(name = "DevolucionServlet", urlPatterns = {"/devolucion"})
public class DevolucionServlet extends HttpServlet {
    private SessionFactory sessionFactory;

    @Override
    public void init() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "realizarDevolucion": {
                actualizarTablas(request, response);
                break;
            }
            case "listaDevolucion":{
                verListaDevolucion(request, response);
                break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "realizarDevolucion":{
                registrarPrestamo(request, response);
                break;
            }
        }
    }

    private void actualizarTablas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();

        sesion.setAttribute("listaPrestamos", PrestamoDAO.listarPrestamos());
        sesion.setAttribute("listaDevolucion", DevolucionDAO.listarDevolucion());

        response.sendRedirect("realizarDevolucion.jsp");
    }

    private void verListaDevolucion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();

        sesion.setAttribute("listaDevolucion", DevolucionDAO.listarDevolucion());

        response.sendRedirect("listaDevolucion.jsp");
    }

    public void registrarPrestamo (HttpServletRequest request, HttpServletResponse response)
    {
        try {
            HttpSession session = request.getSession();

            int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));

            ClasePrestamo prestamo = PrestamoDAO.consultarPrestamo(idPrestamo);

            if (prestamo == null) {
                session.setAttribute("errorMensaje", "Error: ID de préstamo inválido.");
                response.sendRedirect("realizarDevolucion.jsp");
            } else if (prestamo.getMulta() == true) {
                session.setAttribute("errorMensaje", "Error: No se puede realizar la devolucion de un prestamo con multa.");
                response.sendRedirect("realizarDevolucion.jsp");
            } else {
                session.setAttribute("errorMensaje", null);

                Date fechaDevolucion = Date.valueOf(LocalDate.now());

                // Crear un nuevo objeto ClaseDevolcuion
                ClaseDevolucion nuevaDevolucion = new ClaseDevolucion(prestamo.getIdPrestamo(), prestamo.getCedula(), fechaDevolucion);

                if(DevolucionDAO.registrarDevolucion(nuevaDevolucion)){
                    LibroDAO.cambiarDisponibilidadLibro(prestamo.getIdLibro());
                    PrestamoDAO.eliminarPrestamo(prestamo.getIdPrestamo());
                    doGet(request, response);
                }else {
                    session.setAttribute("errorMensaje", "Error: No ha sido posible registrar el préstamo.");
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}