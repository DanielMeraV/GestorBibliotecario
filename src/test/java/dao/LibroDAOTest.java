package dao;

import logica.ClaseLibro;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LibroDAOTest {

    private static ClaseLibro libro1, libro2;
    @BeforeClass
    public static void setUp(){
        libro1 = new ClaseLibro("998", "The Catcher in the Rye", "J.D. Salinger", "Novela", true);
        libro2 = new ClaseLibro("999", "To Kill a Mockingbird", "Harper Lee", "Ficción / Drama", true);
        LibroDAO.registrarLibro(libro2);
    }

    @Test(timeout = 50000)
    public void given_object_libro_when_register_then_true(){
        assertTrue(LibroDAO.registrarLibro(libro1));
    }

    @Test (timeout = 50000)
    public void given_object_libro_when_delete_then_true(){
        assertTrue(LibroDAO.eliminarLibro("999"));
    }

    @Test (timeout = 50000)
    public void given_list_when_view_history_then_true(){
        List<ClaseLibro> listaLibro = LibroDAO.listarLibros();
        assertTrue(listaLibro != null);
    }

    @AfterClass
    public static void tearDown() {
        LibroDAO.eliminarLibro(libro1.getIdLibro());
    }
}