package dao;

import logica.ClasePrestamo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class PrestamoDAO {

    private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();;

    public static boolean verificarExistenciaEstudianteYLibro(String cedula, String idlibro){
        try (Session sessionSave = sessionFactory.openSession()) {
            sessionSave.beginTransaction();

            // Verificar si el estudiante existe
            if (EstudianteDAO.consultarEstudiante(cedula) == null) {
                // El estudiante no existe
                return false;
            }

            // Verificar si el libro existe y si está o no está disponible
            if (LibroDAO.consultarLibro(idlibro) == null || !LibroDAO.consultarLibro(idlibro).getDisponibilidad()) {
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

    public static boolean eliminarPrestamo(int idPrestamo){
        try (Session sessionSave = sessionFactory.openSession()) {

            ClasePrestamo prestamo = consultarPrestamo(idPrestamo);

            sessionSave.beginTransaction();
            sessionSave.delete(prestamo);
            sessionSave.getTransaction().commit();

            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
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

    public static ClasePrestamo consultarPrestamo(int idPrestamo) {
        try (Session sessionSave = sessionFactory.openSession()) {
            Query<ClasePrestamo> prestamoQuery = sessionSave.createQuery ("FROM ClasePrestamo WHERE idPrestamo = :idPrestamo", ClasePrestamo.class);
            prestamoQuery.setParameter("idPrestamo", idPrestamo);
            return prestamoQuery.uniqueResult();
        }catch (Exception e) {
            return null;
        }
    }
}