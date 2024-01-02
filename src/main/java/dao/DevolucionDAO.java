package dao;

import logica.ClaseDevolucion;
import logica.ClasePrestamo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DevolucionDAO {

    private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();;

    public static List<ClaseDevolucion> listarDevolucion() {
        try (Session sessionSave = sessionFactory.openSession()){
            return sessionSave.createQuery("FROM ClaseDevolucion", ClaseDevolucion.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static boolean registrarDevolucion(ClaseDevolucion devolucion) {
        try (Session sessionSave = sessionFactory.openSession()) {
            sessionSave.beginTransaction();
            sessionSave.save(devolucion);
            sessionSave.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

}
