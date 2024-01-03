package dao;

import logica.ClasePrestamo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class PrestamoDAO {

    private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();;

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

            if(verificarExistenciaPrestamo(idPrestamo)){
                ClasePrestamo prestamo = consultarPrestamo(idPrestamo);

                sessionSave.beginTransaction();
                sessionSave.delete(prestamo);
                sessionSave.getTransaction().commit();

                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean renovarPrestamo(int idPrestamo, Date nuevaFechaDevolucion) {
        try (Session sessionSave = sessionFactory.openSession()) {
            if (verificarExistenciaPrestamo(idPrestamo)) {
                if(ClasePrestamo.compararFechas(nuevaFechaDevolucion, consultarPrestamo(idPrestamo).getFechaDevolucion()) == 1){
                    ClasePrestamo prestamo = consultarPrestamo(idPrestamo);

                    sessionSave.beginTransaction();
                    prestamo.setFechaDevolucion(nuevaFechaDevolucion);
                    sessionSave.update(prestamo);
                    sessionSave.getTransaction().commit();
                    return true;
                }else{
                    return false;
                }
            }else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean verificarExistenciaPrestamo(int idPrestamo) {
        try (Session sessionSave = sessionFactory.openSession()) {
            Query<ClasePrestamo> prestamoQuery = sessionSave.createQuery ("FROM ClasePrestamo WHERE idPrestamo = :idPrestamo", ClasePrestamo.class);
            prestamoQuery.setParameter("idPrestamo", idPrestamo);

            return prestamoQuery.uniqueResult() != null;
        }catch (Exception e) {
            return false;
        }
    }

    public static boolean verificarExistenciaEstudianteYLibro(String cedula, String idlibro){
        try (Session sessionSave = sessionFactory.openSession()) {
            sessionSave.beginTransaction();

            // Verificar si el estudiante existe
            if (!EstudianteDAO.verificarExistenciaEstudiante(cedula)) {
                // El estudiante no existe
                return false;
            }

            // Verificar si el libro existe y si está o no está disponible
            if (!LibroDAO.verificarExistenciaLibro(idlibro) || !LibroDAO.consultarLibro(idlibro).getDisponibilidad()) {
                return false;
            }

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

    public static ClasePrestamo consultarPrestamo(int idPrestamo) {
        try (Session sessionSave = sessionFactory.openSession()) {
            Query<ClasePrestamo> prestamoQuery = sessionSave.createQuery ("FROM ClasePrestamo WHERE idPrestamo = :idPrestamo", ClasePrestamo.class);
            prestamoQuery.setParameter("idPrestamo", idPrestamo);
            return prestamoQuery.uniqueResult();
        }catch (Exception e) {
            return null;
        }
    }

    public static void actualizarMultas() {
        try (Session sessionSave = sessionFactory.openSession()) {
            sessionSave.beginTransaction();

            List<ClasePrestamo> listaPrestamos = sessionSave.createQuery("FROM ClasePrestamo", ClasePrestamo.class).getResultList();

            LocalDate fechaActual = LocalDate.now();  // Obtener la fecha actual

            for (ClasePrestamo prestamo : listaPrestamos) {
                LocalDate fechaDevolucion = prestamo.getFechaDevolucion().toLocalDate();

                // Comparar las fechas y asegurarse de que la multa no se establezca si son iguales
                if (compararFechas(fechaDevolucion, fechaActual) < 0 && !prestamo.getMulta()) {
                    prestamo.setMulta(true);
                    sessionSave.update(prestamo);
                } else if (compararFechas(fechaDevolucion, fechaActual) <= 0 && prestamo.getMulta()) {
                    prestamo.setMulta(false);  // Establecer la multa en false si son iguales
                    sessionSave.update(prestamo);
                }
            }
            sessionSave.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int compararFechas(LocalDate fecha1, LocalDate fecha2){

        return fecha1.compareTo(fecha2);
    }
}