package dao;

import logica.ClasePrestamo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class PrestamoDAO {

    private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();;

    public static boolean verificarExistenciaEstudianteYLibro(String cedula, String idlibro){
        try (Session sessionSave = sessionFactory.openSession()) {
            sessionSave.beginTransaction();

            // Verificar si el estudiante existe
            if (EstudianteDAO.verificarExistenciaLibro(cedula) == null) {
                // El estudiante no existe
                return false;
            }

            // Verificar si el libro existe y si está o no está disponible
            if (LibroDAO.verificarExistenciaLibro(idlibro) == null || !LibroDAO.verificarExistenciaLibro(idlibro).getDisponibilidad()) {
                return false;
            }

            sessionSave.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean registrarPrestamo(ClasePrestamo prestamo) {
        try (Session sessionSave = sessionFactory.openSession()) {
            sessionSave.beginTransaction();
            sessionSave.save(prestamo);
            sessionSave.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    public static List<ClasePrestamo> listarPrestamos() {
        try (Session sessionSave = sessionFactory.openSession()){
            return sessionSave.createQuery("FROM ClasePrestamo", ClasePrestamo.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
