package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.ClaseEstudiante;
import logica.ClaseLibro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;

public class EstudianteServletTest {

    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static Session sessionsave;
    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void setUp(){
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getParameter("cedula")).thenReturn("1234567890");
        Mockito.when(request.getParameter("nombre")).thenReturn("Daniel");
        Mockito.when(request.getParameter("direccion")).thenReturn("La Florida");
        Mockito.when(request.getParameter("telefono")).thenReturn("0999384473");
        Mockito.when(request.getParameter("codigo")).thenReturn("202010586");
        Mockito.when(request.getParameter("correo")).thenReturn("daniel.mera@epn.edu.ec");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(sessionFactory.openSession()).thenReturn(sessionsave);
    }

    @Test(timeout = 3000)
    public void given_ostudent_when_register_then_timeout() throws ServletException, IOException {
        EstudianteServlet registro = new EstudianteServlet();
        registro.init();
        registro.doPost(request, response);
    }

    /*
    @Test
    public void given_student_when_delete_then_ok() throws ServletException, IOException {
        sessionsave = sessionFactory.openSession();

        EstudianteServlet registro = new EstudianteServlet();
        Mockito.when(request.getParameter("action")).thenReturn("registroEstudiante");
        registro.init();
        registro.doPost(request, response);

        EstudianteServlet eliminarRegistro = new EstudianteServlet();
        Mockito.when(request.getParameter("action")).thenReturn("eliminarEstudiante");
        eliminarRegistro.init();
        eliminarRegistro.doPost(request, response);

        ClaseEstudiante estudianteEliminado = ClaseEstudiante.getEstudiante("1234567890", sessionsave);

        assertNull(estudianteEliminado);
    }
    */

}
