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

    @Test(timeout = 10)
    public void given_object_libro_when_register_then_timeout(){

        libro = new ClaseLibro("012", "Divina comedia",
                "Dante Alighieri", "Poesía", true);

        assertTrue(ClaseLibro.ingresarLibro(libro));
    }

    @Test
    public void given_null_when_register_then_false(){

        libro = new ClaseLibro(null, null,
                "Dante Alighieri", "Poesía", true);

        assertFalse(ClaseLibro.ingresarLibro(libro));
    }

    @Test
    public void given_letters_ID_when_register_then_false(){

        libro = new ClaseLibro("abc", "Divina comedia",
                "Dante Alighieri", "Poesía", true);

        assertFalse(ClaseLibro.ingresarLibro(libro));
    }

    @Test
    public void given_two_objects_when_register_then_ok(){

        ClaseLibro libroEsperado = new ClaseLibro("012", "Divina comedia",
                "Dante Alighieri", "Poesía", true);

        ClaseLibro libroResultante = createLibro("012", "Divina comedia",
                "Dante Alighieri", "Poesía", true);

        assertEquals(libroEsperado.hashCode(), libroResultante.hashCode());
    }

    private ClaseLibro createLibro(String idLibro, String titulo, String autor, String genero, boolean disponibilidad) {
        ClaseLibro libro = new ClaseLibro();
        libro.setIdLibro(idLibro);
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setGenero(genero);
        libro.setDisponibilidad(disponibilidad);
        return libro;
    }

    @Test(timeout = 250)
    public void given_many_objects_when_register_then_timeout() {
        int numberOfObjects = 10000;

        for (int i = 0; i < numberOfObjects; i++) {
            libro = new ClaseLibro("012", "Divina comedia",
                    "Dante Alighieri", "Poesía", true);
            assertTrue(ClaseLibro.ingresarLibro(libro));
        }
    }

}