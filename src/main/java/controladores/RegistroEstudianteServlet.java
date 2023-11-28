package controladores;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import logica.Administrador;
import logica.ClaseEstudiante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebServlet(name = "RegistroEstudianteServlet", urlPatterns = {"/registroEstudiante"})
public class RegistroEstudianteServlet extends HttpServlet {
    private SessionFactory sessionFactory;

    @Override
    public void init() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String codigo = request.getParameter("codigo");
        String correo = request.getParameter("correo");
        HttpSession session = request.getSession();

        // Crear un nuevo objeto ClaseEstudiante
        ClaseEstudiante estudiante = new ClaseEstudiante(cedula, nombre, direccion, telefono, codigo, correo);
        if(!Administrador.validarDatosRegistro(estudiante)){
            estudiante = null;
        }

        if (estudiante != null){
            session.setAttribute("errorMensaje", null);
            // Guardar el estudiante utilizando Hibernate
            try (Session sessionSave = sessionFactory.openSession()) {
                sessionSave.beginTransaction();
                sessionSave.save(estudiante); // Guardar el estudiante en la base de datos
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
            response.sendRedirect("registrarEstudiante.jsp");
        }

    }

    @Override
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}