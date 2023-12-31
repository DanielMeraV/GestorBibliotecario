package logica;

import jakarta.persistence.*;
import org.hibernate.SessionFactory;

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

    public static int compararFechas(Date fecha1, Date fecha2){
        if(fecha1.equals(fecha2)){
            return 0;
        }else if(fecha1.after(fecha2)){
            return 1;
        }else if(fecha1.before(fecha2)){
            return -1;
        }
        return 0;
    }
}