package dao;

import logica.ClaseEstudiante;
import logica.ClasePrestamo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class PrestamoDAOTest {

    private static ClasePrestamo prestamo1, prestamo2, prestamo3;
    @BeforeClass
    public static void setUp(){
        Date fechaPrestamo = Date.valueOf(LocalDate.now());
        Date fechaDevolucion = Date.valueOf(LocalDate.now().plusDays(15));
        prestamo1 = new ClasePrestamo("1721294799", "011", fechaPrestamo, fechaDevolucion, false);
        prestamo2 = new ClasePrestamo("1721294799", "015", fechaPrestamo, fechaDevolucion, true);
        prestamo3 = new ClasePrestamo("1721294799", "012", fechaPrestamo, fechaDevolucion, true);
        PrestamoDAO.registrarPrestamo(prestamo2);  //Para borrarlo
        PrestamoDAO.registrarPrestamo(prestamo3); // Para renovar
    }

    @Test (timeout = 50000)
    public void given_object_prestamo_when_register_then_true(){
        assertTrue(PrestamoDAO.registrarPrestamo(prestamo1));
    }

    @Test (timeout = 50000)
    public void given_successful_prestamo_when_requestHistory_then_true(){
        List<ClasePrestamo> listaPrestamos = PrestamoDAO.listarPrestamos();
        assertTrue(listaPrestamos != null);
    }

    @Test (timeout = 50000)
    public void given_existing_prestamoID_when_consult_then_true(){
        assertTrue(PrestamoDAO.consultarPrestamo(20) != null);
    }

    @Test (timeout = 50000)
    public void given_object_prestamo_when_delete_then_true(){
        assertTrue(PrestamoDAO.eliminarPrestamo(prestamo2.getIdPrestamo()));
    }

    @Test (timeout = 50000)
    public void given_nonExisting_prestamo_when_delete_then_false() {
        assertFalse(PrestamoDAO.eliminarPrestamo(999));
    }

    @Test (timeout = 50000)
    public void given_existent_prestamo_when_renew_then_true() {
        assertTrue(PrestamoDAO.renovarPrestamo(prestamo3.getIdPrestamo(), Date.valueOf("2024-07-01")));
    }

    @Test (timeout = 50000)
    public void given_expired_prestamo_when_renew_then_false() {
        assertFalse(PrestamoDAO.renovarPrestamo(20, Date.valueOf(LocalDate.now().plusDays(100))));
    }

    @Test (timeout = 50000)
    public void given_existent_prestamo_when_renew_with_invalidDate_then_false(){
        assertFalse(PrestamoDAO.renovarPrestamo(21, Date.valueOf(LocalDate.now().minusDays(100))));
    }

    @Test (timeout = 50000)
    public void given_nonExistent_prestamo_when_renew_then_false() {
        assertFalse(PrestamoDAO.renovarPrestamo(999, Date.valueOf(LocalDate.now().plusDays(15))));
    }

    @AfterClass
    public static void tearDown() {
        PrestamoDAO.eliminarPrestamo(prestamo1.getIdPrestamo());
        PrestamoDAO.eliminarPrestamo(prestamo3.getIdPrestamo());
    }
}