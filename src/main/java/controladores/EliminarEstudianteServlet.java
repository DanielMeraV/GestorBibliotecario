package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.ClaseEstudiante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

@WebServlet(name = "EliminarEstudianteServlet", urlPatterns = {"/eliminarEstudiante"})
public class EliminarEstudianteServlet extends HttpServlet {
    private SessionFactory sessionFactory;

    @Override
    public void init() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cedula = request.getParameter("cedula");
        HttpSession session = request.getSession();

        if (cedula != null && cedula != ""){
            session.setAttribute("errorMensaje", null);
            // Eliminar el estudiante utilizando Hibernate
            try (Session sessionSave = sessionFactory.openSession()) {
                // Crear un nuevo objeto ClaseEstudiante
                ClaseEstudiante estudiante = ClaseEstudiante.getEstudiante(cedula, sessionSave);
                sessionSave.beginTransaction();
                sessionSave.delete(estudiante);
                sessionSave.getTransaction().commit();
            } catch (Exception e) {
                session.setAttribute("errorMensaje", "Error: no puede eliminar un estudiante con un prestamo pendiente.");
                response.sendRedirect("eliminarEstudiante.jsp");
                return;
            }
            // Redireccionar a una página de éxito después de guardar en la base de datos
            response.sendRedirect("index.jsp");
        }else {
            session.setAttribute("errorMensaje", "Error: dato invalido.");
            response.sendRedirect("eliminarEstudiante.jsp");
        }

    }

    @Override
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}