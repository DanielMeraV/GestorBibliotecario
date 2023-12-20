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

public class EliminarLibroServletTest {

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
        sessionsave = Mockito.mock(Session.class);
        sessionFactory = Mockito.mock(SessionFactory.class);

        Mockito.when(request.getParameter("idLibro")).thenReturn("123");
        Mockito.when(request.getParameter("titulo")).thenReturn("Libro prueba");
        Mockito.when(request.getParameter("autor")).thenReturn("Autor prueba");
        Mockito.when(request.getParameter("genero")).thenReturn("ficcion");
        Mockito.when(request.getParameter("disponibilidad")).thenReturn("disponible");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(sessionFactory.openSession()).thenReturn(sessionsave);
    }

    @Test
    public void given_book_when_delete_then_ok() throws ServletException, IOException {
        sessionsave = sessionFactory.openSession();

        RegistroLibroServlet registro = new RegistroLibroServlet();
        registro.init();
        registro.doPost(request, response);

        EliminarLibroServlet eliminarRegistro = new EliminarLibroServlet();
        eliminarRegistro.init();
        eliminarRegistro.doPost(request, response);

        ClaseLibro libroEliminado = ClaseLibro.getLibro("123", sessionsave);

        assertNull(libroEliminado);
    }

}