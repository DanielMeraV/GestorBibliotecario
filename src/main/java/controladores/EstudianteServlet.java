package controladores;

import java.io.*;
import dao.EstudianteDAO;
import dao.LibroDAO;
import dao.PrestamoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import logica.ClaseEstudiante;

@WebServlet(name = "EstudianteServlet", urlPatterns = {"/estudiante"})
public class EstudianteServlet extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "registrarEstudiante": {
                registrarEstudiante(request, response);
                break;
            }
            case "eliminarEstudiante": {
                eliminarEstudiante(request, response);
                break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "listaEstudiantes":{
                actualizarTablas(request, response);
                break;
            }
        }
    }

    private void actualizarTablas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();

        sesion.setAttribute("listaEstudiantes", EstudianteDAO.listarEstudiantes());

        response.sendRedirect("listaEstudiante.jsp");
    }

    public void registrarEstudiante (HttpServletRequest request, HttpServletResponse response)
    {
        try {
            HttpSession session = request.getSession();

            String cedula = request.getParameter("cedula");
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String codigo = request.getParameter("codigo");
            String correo = request.getParameter("correo");

            // Crear un nuevo objeto ClaseEstudiante
            ClaseEstudiante nuevoEstudiante = new ClaseEstudiante(cedula, nombre, direccion, telefono, codigo, correo);

            if(EstudianteDAO.registrarEstudiante(nuevoEstudiante)){
                session.setAttribute("errorMensaje", null);
                response.sendRedirect("index.jsp");
            }else {
                session.setAttribute("errorMensaje", "Error: No ha sido posible registrar el estudiante.");
                response.sendRedirect("registrarEstudiante.jsp");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarEstudiante (HttpServletRequest request, HttpServletResponse response)
    {
        try {
            HttpSession session = request.getSession();

            String cedulaEstudiante = request.getParameter("cedula");

            if(EstudianteDAO.eliminarEstudiante(cedulaEstudiante)){
                session.setAttribute("errorMensaje", null);
                response.sendRedirect("index.jsp");
            }else {
                session.setAttribute("errorMensaje", "Error: No ha sido posible eliminar el estudiante.");
                response.sendRedirect("eliminarEstudiante.jsp");
            }

            response.sendRedirect("index.jsp");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}