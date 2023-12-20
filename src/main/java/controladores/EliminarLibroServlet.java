package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.ClaseLibro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

@WebServlet(name = "EliminarLibroServlet", urlPatterns = {"/eliminarLibro"})
public class EliminarLibroServlet extends HttpServlet {
    private SessionFactory sessionFactory;

    @Override
    public void init() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idLibro = request.getParameter("idLibro");
        HttpSession session = request.getSession();

        if (idLibro != null && idLibro != ""){
            session.setAttribute("errorMensaje", null);
            // Eliminar el libro utilizando Hibernate
            try (Session sessionSave = sessionFactory.openSession()) {
                // Crear un nuevo objeto ClaseLibro
                ClaseLibro libro = ClaseLibro.getLibro(idLibro, sessionSave);
                sessionSave.beginTransaction();
                sessionSave.delete(libro);
                sessionSave.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                // Manejar el error, por ejemplo, redirigir a una página de error
                response.sendRedirect("error.jsp");
                return;
            }
            // Redireccionar a una página de éxito después de guardar en la base de datos
            response.sendRedirect("index.jsp");
        }else {
            session.setAttribute("errorMensaje", "Error: dato invalido.");
            response.sendRedirect("eliminarLibro.jsp");
        }

    }

    @Override
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}