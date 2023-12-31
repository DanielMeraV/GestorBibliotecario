package logica;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClaseEstudianteTest {
    private static ClaseEstudiante estudiante;

    @BeforeClass
    public static void setUp(){
        estudiante = new ClaseEstudiante();
    }

    @Test
    public void given_object_estudiante_when_register_then_true(){
        estudiante = new ClaseEstudiante("1234567890", "Andres", "San Bartolo", "0956321478", "202010586", "andres@gmail.com");
        assertTrue(ClaseEstudiante.validarDatosRegistro(estudiante));
    }

    @Test
    public void given_blanc_strings_when_register_then_false(){

        estudiante = new ClaseEstudiante("1234567890", "", "San Bartolo", "0956321478", "", "andres@gmail.com");
        assertFalse(ClaseEstudiante.validarDatosRegistro(estudiante));
    }

    @Test(timeout = 10)
    public void given_object_estudiante_when_register_then_timeout(){

        estudiante = new ClaseEstudiante("1234567890", "Andres", "San Bartolo", "0956321478", "202010586", "andres@gmail.com");
        assertTrue(ClaseEstudiante.validarDatosRegistro(estudiante));
    }

    @Test
    public void given_null_when_register_then_false(){
        estudiante = new ClaseEstudiante("1234567890", null, null, "0956321478", "202010586", null);
        assertFalse(ClaseEstudiante.validarDatosRegistro(estudiante));
    }

    @Test(timeout = 200)
    public void given_many_objects_when_register_then_timeout() {
        int numberOfObjects = 10000;

        for (int i = 0; i < numberOfObjects; i++) {
            estudiante = new ClaseEstudiante("1234567890", "Andres", "San Bartolo", "0956321478", "202010586", "andres@gmail.com");
            assertTrue(ClaseEstudiante.validarDatosRegistro(estudiante));
        }
    }
}