package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.Administrador;
import logica.ClaseEstudiante;
import logica.ClaseLibro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

@WebServlet(name = "RegistroLibroServlet", urlPatterns = {"/registroLibro"})
public class RegistroLibroServlet extends HttpServlet {
    private SessionFactory sessionFactory;

    @Override
    public void init() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idLibro = request.getParameter("idLibro");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String genero = request.getParameter("genero");
        String disponibilidad = request.getParameter("disponibilidad");
        boolean disponible = disponibilidad.equals("disponible") ? true : false;
        HttpSession session = request.getSession();

        // Crear un nuevo objeto ClaseLibro
        ClaseLibro libro = new ClaseLibro(idLibro, titulo, autor, genero, disponible);

        if(!ClaseLibro.ingresarLibro(libro)){
            libro = null;
        }

        if (libro != null){
            session.setAttribute("errorMensaje", null);
            session.setAttribute("libroRegistrado", libro);
            // Guardar el estudiante utilizando Hibernate
            try (Session sessionSave = sessionFactory.openSession()) {
                sessionSave.beginTransaction();
                sessionSave.save(libro); // Guardar el estudiante en la base de datos
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
            response.sendRedirect("registrarLibro.jsp");
        }

    }

    @Override
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}