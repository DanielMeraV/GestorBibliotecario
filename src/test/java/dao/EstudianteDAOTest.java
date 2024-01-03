package dao;

import logica.ClaseEstudiante;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EstudianteDAOTest {

    private static ClaseEstudiante estudiante1, estudiante2;
    @BeforeClass
    public static void setUp(){
        estudiante1 = new ClaseEstudiante("1032569875", "Joel Delgado", "Solanda", "0963524178", "20196321", "joel.delgado@epn.edu.ec");
        estudiante2 = new ClaseEstudiante("1723188293", "Daniel Mera", "La Rumiñahui", "0987456321", "20196322", "daniel.mera@epn.edu.ec");
        EstudianteDAO.registrarEstudiante(estudiante2); // Para borrarlo
    }

    @Test (timeout = 50000)
    public void given_object_estudiante_when_register_then_true(){
        assertTrue(EstudianteDAO.registrarEstudiante(estudiante1));
    }

    @Test (timeout = 50000)
    public void given_object_estudiante_when_delete_then_true(){
        assertTrue(EstudianteDAO.eliminarEstudiante("1723188293"));
    }

    @Test (timeout = 50000)
    public void given_list_when_view_history_then_true(){
        List<ClaseEstudiante> listaEstudiante = EstudianteDAO.listarEstudiantes();
        assertTrue(listaEstudiante != null);
    }

    @AfterClass
    public static void tearDown() {
        EstudianteDAO.eliminarEstudiante(estudiante1.getCedula());
    }
}