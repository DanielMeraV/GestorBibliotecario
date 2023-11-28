package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.ClaseLibro;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;

public class RegistroLibroServletTest {

    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;

    @BeforeClass
    public static void setUp(){
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getParameter("idLibro")).thenReturn("012");
        Mockito.when(request.getParameter("titulo")).thenReturn("Divina comedia");
        Mockito.when(request.getParameter("autor")).thenReturn("Dante Alighieri");
        Mockito.when(request.getParameter("genero")).thenReturn("Poesía");
        Mockito.when(request.getParameter("disponibilidad")).thenReturn("disponible");
        Mockito.when(request.getSession()).thenReturn(session);
    }

    @Test
    public void given_object_libro_when_register_then_true() throws ServletException, IOException {
        RegistroLibroServlet registro = new RegistroLibroServlet();
        registro.init();
        registro.doPost(request, response);

        ClaseLibro libroRegistrado = (ClaseLibro) session.getAttribute("libroRegistrado");
        ClaseLibro libroEsperado = new ClaseLibro("012", "Divina comedia",
                "Dante Alighieri", "Poesía", true);
        assertEquals(libroEsperado.hashCode(), libroRegistrado.hashCode());
    }

    @Test(timeout = 3000)
    public void given_object_libro_when_register_then_timeout() throws ServletException, IOException {
        RegistroLibroServlet registro = new RegistroLibroServlet();
        registro.init();
        registro.doPost(request, response);
    }

}