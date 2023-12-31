package dao;

import logica.ClasePrestamo;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class PrestamoDAOTest {

    private static ClasePrestamo prestamo;
    @BeforeClass
    public static void setUp(){
        Date fechaPrestamo = Date.valueOf(LocalDate.now());
        Date fechaDevolucion = Date.valueOf(LocalDate.now().plusDays(15));
        prestamo = new ClasePrestamo("1721294799", "011", fechaPrestamo, fechaDevolucion, true);
    }

    @Test
    public void given_object_prestamo_when_register_then_true(){
        assertTrue(PrestamoDAO.registrarPrestamo(prestamo));
    }

    @Test
    public void given_existingPrestamoID_when_consult_then_true(){
        if(PrestamoDAO.consultarPrestamo(12) != null){
            assertTrue(true);
        }else{
            fail();
        }
    }

    @Test
    public void given_object_prestamo_when_delete_then_true(){
        assertTrue(PrestamoDAO.eliminarPrestamo(11));
    }

    @Test
    public void given_nonExistingPrestamo_when_delete_then_false() {
        assertFalse(PrestamoDAO.eliminarPrestamo(100));
    }


    @Test
    public void given_existentPrestamo_when_renew_then_true() {
        assertTrue(PrestamoDAO.renovarPrestamo(12, Date.valueOf(LocalDate.now().plusDays(100))));
    }

    @Test
    public void given_expiredPrestamo_when_renew_then_false() {
        assertFalse(PrestamoDAO.renovarPrestamo(12, Date.valueOf(LocalDate.now().plusDays(100))));
    }

    @Test
    public void given_existentPrestamo_when_renew_with_invalidDate_then_false(){
        assertFalse(PrestamoDAO.renovarPrestamo(12, Date.valueOf(LocalDate.now().minusDays(100))));
    }

    @Test
    public void given_nonExistentPrestamo_when_renew_then_false() {
        assertFalse(PrestamoDAO.renovarPrestamo(100, Date.valueOf(LocalDate.now().plusDays(15))));
    }

}