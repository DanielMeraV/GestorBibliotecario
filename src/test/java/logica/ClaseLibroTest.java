package logica;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClaseLibroTest {

    private static ClaseLibro libro;

    @BeforeClass
    public static void setUp(){
        libro = new ClaseLibro();
    }

    @Test
    public void given_object_libro_when_register_then_true(){

        libro = new ClaseLibro("012", "Divina comedia",
                "Dante Alighieri", "Poesía", true);

        assertTrue(ClaseLibro.ingresarLibro(libro));
    }

    @Test
    public void given_blanc_strings_when_register_then_false(){

        libro = new ClaseLibro("012", "",
                "", "", true);

        assertFalse(ClaseLibro.ingresarLibro(libro));
    }

    @Test(expected = NullPointerException.class)
    public void given_null_objects_when_register_then_exception(){

        libro = new ClaseLibro();

        assertTrue(ClaseLibro.ingresarLibro(libro));
    }

    @Test(timeout = 10)
    public void given_object_libro_when_register_then_timeout(){

        libro = new ClaseLibro("012", "Divina comedia",
                "Dante Alighieri", "Poesía", true);

        assertTrue(ClaseLibro.ingresarLibro(libro));
    }

}