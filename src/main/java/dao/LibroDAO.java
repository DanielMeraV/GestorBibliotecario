package dao;

import logica.ClaseLibro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();;

    public static boolean registrarLibro(ClaseLibro libro) {
        // Guardar el estudiante utilizando Hibernate
        try (Session sessionSave = sessionFactory.openSession()) {
            sessionSave.beginTransaction();
            sessionSave.save(libro);
            sessionSave.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    public static boolean eliminarLibro(String idLibro){
        try (Session sessionSave = sessionFactory.openSession()) {

            ClaseLibro libro = consultarLibro(idLibro);

            sessionSave.beginTransaction();
            sessionSave.delete(libro);
            sessionSave.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    public static List<ClaseLibro> listarLibros() {
        try (Session sessionSave = sessionFactory.openSession()){
            return sessionSave.createQuery("FROM ClaseLibro ", ClaseLibro.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static List<ClaseLibro> listarLibrosDisponibles() {
        try (Session sessionSave = sessionFactory.openSession()){
            List<ClaseLibro> listaLibros = sessionSave.createQuery("FROM ClaseLibro ", ClaseLibro.class).getResultList();
            List<ClaseLibro> listaLibrosDisponibles = new ArrayList<>();

            for (ClaseLibro libro : listaLibros) {
                if (libro.getDisponibilidad()) {
                    listaLibrosDisponibles.add(libro);
                }
            }

            return listaLibrosDisponibles;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static ClaseLibro consultarLibro(String idlibro) {
        try (Session sessionSave = sessionFactory.openSession()) {
            Query<ClaseLibro> libroQuery = sessionSave.createQuery ("FROM ClaseLibro WHERE idLibro = :idLibro", ClaseLibro.class);
            libroQuery.setParameter("idLibro", idlibro);
            return libroQuery.uniqueResult();
        }catch (Exception e) {
            return null;
        }
    }

    public static boolean cambiarDisponibilidadLibro(String idlibro){
        try (Session sessionSave = sessionFactory.openSession()) {

            sessionSave.beginTransaction();

            ClaseLibro libro = consultarLibro(idlibro);

            int actualizacion = sessionSave.createQuery("UPDATE ClaseLibro SET disponibilidad = :nuevaDisponibilidad WHERE idLibro = :idLibro").setParameter("nuevaDisponibilidad", !libro.getDisponibilidad()).setParameter("idLibro", idlibro).executeUpdate();

            sessionSave.getTransaction().commit();

            // Verificar si la actualización fue exitosa
            if (actualizacion > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}