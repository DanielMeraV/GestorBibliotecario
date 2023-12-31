package logica;

import dao.EstudianteDAO;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;

@Entity
@Table(name = "prestamo", schema = "gestorbibliotecario")
public class ClasePrestamo {
    private static SessionFactory sessionFactory;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPrestamo", nullable = false)
    private int idPrestamo;
    @Basic
    @Column(name = "cedula", nullable = true, length = 255)
    private String cedula;
    @Basic
    @Column(name = "idLibro", nullable = true, length = 255)
    private String idLibro;
    @Basic
    @Column(name = "fechaPrestamo", nullable = true)
    private Date fechaPrestamo;
    @Basic
    @Column(name = "fechaDevolucion", nullable = true)
    private Date fechaDevolucion;
    @Basic
    @Column(name = "multa", nullable = true)
    private Boolean multa;

    public ClasePrestamo() {
    }

    public ClasePrestamo(String cedula, String idLibro, Date fechaPrestamo, Date fechaDevolucion, Boolean multa) {
        this.cedula = cedula;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.multa = multa;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Boolean getMulta() {
        return multa;
    }

    public void setMulta(Boolean multa) {
        this.multa = multa;
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public static int compararFechas(Date fecha1, Date fecha2){
        if(fecha1.equals(fecha2)){
            return 0;
        }else if(fecha1.after(fecha2)){
            return 1;
        }else if(fecha1.before(fecha2)){
            return -1;
=======
=======
>>>>>>> parent of 7070510 (feat: creación interfaz de renovar y asignación de  multas)
    public static boolean solicitarPrestamo(String cedula, String idlibro){
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Verificar si el estudiante existe
            ClaseEstudiante estudiante = ClaseEstudiante.getEstudiante(cedula, session);

            if (estudiante == null) {
                // El estudiante no existe
                return false;
            }

            // Verificar si el libro existe
            ClaseLibro libro = ClaseLibro.getLibro(idlibro, session);

            if (libro == null || libro.getDisponibilidad() == false) {
                // El libro no existe o no está disponible
                return false;
            }

            transaction.commit();

            // Ambos existen, el préstamo es válido
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
<<<<<<< HEAD
>>>>>>> parent of 7070510 (feat: creación interfaz de renovar y asignación de  multas)
=======
>>>>>>> parent of 7070510 (feat: creación interfaz de renovar y asignación de  multas)
        }
        return 0;
    }
}