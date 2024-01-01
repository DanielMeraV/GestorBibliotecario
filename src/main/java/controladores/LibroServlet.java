package controladores;

import dao.EstudianteDAO;
import dao.LibroDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.ClaseLibro;
import java.io.IOException;

@WebServlet(name = "LibroServlet", urlPatterns = {"/libro"})
public class LibroServlet extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "registrarLibro":{
                registrarLibro(request, response);
                break;
            }
            case "eliminarLibro":{
                eliminarLibro(request, response);
                break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "listaLibros":{
                actualizarTablas(request, response);
                break;
            }
        }
    }

    private void actualizarTablas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();

        sesion.setAttribute("listaLibros", LibroDAO.listarLibrosDisponibles());

        response.sendRedirect("listaLibros.jsp");
    }

    public void registrarLibro (HttpServletRequest request, HttpServletResponse response)
    {
        try {
            HttpSession session = request.getSession();

            String idLibro = request.getParameter("idLibro");
            String titulo = request.getParameter("titulo");
            String autor = request.getParameter("autor");
            String genero = request.getParameter("genero");
            String disponibilidad = request.getParameter("disponibilidad");
            boolean disponible = disponibilidad.equals("disponible");

            // Crear un nuevo objeto ClaseLibro
            ClaseLibro libro = new ClaseLibro(idLibro, titulo, autor, genero, disponible);

            if (LibroDAO.registrarLibro(libro)){
                session.setAttribute("errorMensaje", null);
                response.sendRedirect("index.jsp");
            } else {
                session.setAttribute("errorMensaje", "Error: No ha sido posible registrar el libro.");
                response.sendRedirect("registrarLibro.jsp");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarLibro (HttpServletRequest request, HttpServletResponse response)
    {
        try {
            HttpSession session = request.getSession();

            String idLibro = request.getParameter("idLibro");

            if (LibroDAO.eliminarLibro(idLibro)){
                session.setAttribute("errorMensaje", null);
                response.sendRedirect("index.jsp");
            } else {
                session.setAttribute("errorMensaje", "Error: No ha sido posible eliminar el libro.");
                response.sendRedirect("eliminarLibro.jsp");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}