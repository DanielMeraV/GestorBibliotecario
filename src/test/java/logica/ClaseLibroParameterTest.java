
/*package logica;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class ClaseLibroParameterTest extends TestCase {

    private String idLibro, titulo, autor, genero;
    private boolean disponibilidad;

    @Parameterized.Parameters
    public static Iterable<Object[]> parameters(){
        List<Object[]> objects = new ArrayList<Object[]>();
        objects.add(new Object[]{"020", "", "", "", true});
        objects.add(new Object[]{null, "titulo2", null, "genero2", null});
        objects.add(new Object[]{"0222", "titulo3", "autor3", "genero3", true});
        objects.add(new Object[]{"02ab", "titulo4", "autor4", "genero4", false});
        objects.add(new Object[]{"024", "titulo5", "autor5", "genero5", "true"});
        return objects;
    }

    public ClaseLibroParameterTest(String id, String titulo, String autor, String genero, boolean disponibilidad){
        this.idLibro = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.disponibilidad = disponibilidad;
    }

    @Test
    public void given_object_libro_when_register_then_true(){

        ClaseLibro libro = new ClaseLibro(this.idLibro, this.titulo,
                this.autor, this.genero, this.disponibilidad);

        boolean resultado = ClaseLibro.ingresarLibro(libro);

        assertTrue(resultado);
    }

    @Test
    public void given_object_libro_when_register_then_false(){

        ClaseLibro libro = new ClaseLibro(this.idLibro, this.titulo,
                this.autor, this.genero, this.disponibilidad);

        boolean resultado = ClaseLibro.ingresarLibro(libro);

        assertFalse(resultado);
    }


}
*/