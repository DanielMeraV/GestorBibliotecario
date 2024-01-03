package dao;

import logica.ClaseDevolucion;
import logica.ClasePrestamo;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class DevolucionDAOTest {

    private static ClasePrestamo prestamo;
    @BeforeClass
    public static void setUp(){
        Date fechaPrestamo = Date.valueOf(LocalDate.now());
        Date fechaDevolucion = Date.valueOf(LocalDate.now().plusDays(15));
        prestamo = new ClasePrestamo("1234567890", "123", fechaPrestamo, fechaDevolucion, true);
    }

    @Test(timeout = 50000)
    public void given_object_prestamo_when_register_then_true(){
        PrestamoDAO.registrarPrestamo(prestamo);
        List<ClasePrestamo> listaPrestamo = PrestamoDAO.listarPrestamos();
        for (ClasePrestamo prestamos : listaPrestamo){
            if(prestamos.getCedula().equals("1234567890") && prestamos.getIdLibro().equals("123"))
                prestamo = prestamos;
        }
        ClaseDevolucion devolucion = new ClaseDevolucion(prestamo.getIdPrestamo(), prestamo.getCedula(), prestamo.getFechaDevolucion());
        assertTrue(DevolucionDAO.registrarDevolucion(devolucion));
    }

    @Test(timeout = 50000)
    public void given_object_prestamo_when_delete_then_false(){
        PrestamoDAO.registrarPrestamo(prestamo);
        List<ClasePrestamo> listaPrestamo = PrestamoDAO.listarPrestamos();
        for (ClasePrestamo prestamos : listaPrestamo){
            if(prestamos.getCedula().equals("1234567890") && prestamos.getIdLibro().equals("005"))
                prestamo = prestamos;
        }
        ClaseDevolucion devolucion = new ClaseDevolucion(prestamo.getIdPrestamo(), prestamo.getCedula(), prestamo.getFechaDevolucion());
        DevolucionDAO.registrarDevolucion(devolucion);
        assertFalse(PrestamoDAO.verificarExistenciaPrestamo(prestamo.getIdPrestamo()));
    }

    @Test(timeout = 50000)
    public void given_list_when_view_history_then_true(){
        List<ClaseDevolucion> listaDevolucion = DevolucionDAO.listarDevolucion();
        assertTrue(listaDevolucion != null);
    }
}