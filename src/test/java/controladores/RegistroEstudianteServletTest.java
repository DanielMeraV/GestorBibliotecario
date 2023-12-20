package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.ClaseEstudiante;
import logica.ClaseLibro;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;

public class RegistroEstudianteServletTest {

    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;

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
    }

    @Test(timeout = 3000)
    public void given_ostudent_when_register_then_timeout() throws ServletException, IOException {
        RegistroEstudianteServlet registro = new RegistroEstudianteServlet();
        registro.init();
        registro.doPost(request, response);
    }

}