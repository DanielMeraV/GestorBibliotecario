package dao;

import logica.ClaseEstudiante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class EstudianteDAO {

    private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();;

    public static boolean registrarEstudiante(ClaseEstudiante estudiante) {
        // Guardar el estudiante utilizando Hibernate
        try (Session sessionSave = sessionFactory.openSession()) {
            sessionSave.beginTransaction();
            sessionSave.save(estudiante); // Guardar el estudiante en la base de datos
            sessionSave.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    public static boolean eliminarEstudiante(String cedula){
        try (Session sessionSave = sessionFactory.openSession()) {

            ClaseEstudiante estudiante = verificarExistenciaLibro(cedula);

            sessionSave.beginTransaction();
            sessionSave.delete(estudiante);
            sessionSave.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    public static List<ClaseEstudiante> listarEstudiantes() {
        try (Session sessionSave = sessionFactory.openSession()){
            return sessionSave.createQuery("FROM ClaseEstudiante ", ClaseEstudiante.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static ClaseEstudiante verificarExistenciaLibro(String cedula) {
        try {
            Query<ClaseEstudiante> estudianteQuery = sessionFactory.openSession().createQuery ("FROM ClaseEstudiante WHERE cedula = :cedula", ClaseEstudiante.class);
            estudianteQuery.setParameter("cedula", cedula);
            return estudianteQuery.uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }
}
