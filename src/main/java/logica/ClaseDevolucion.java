package logica;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "devolucion", schema = "gestorbibliotecario")
public class ClaseDevolucion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idDevolucion", nullable = false)
    private int idDevolucion;
    @Basic
    @Column(name = "idPrestamo", nullable = false)
    private int idPrestamo;
    @Basic
    @Column(name = "cedulaEstudiante", nullable = true, length = 10)
    private String cedulaEstudiante;
    @Basic
    @Column(name = "fechaDevolucion", nullable = true)
    private Date fechaDevolucion;

    public ClaseDevolucion() {
    }

    public ClaseDevolucion(int idPrestamo, String cedulaEstudiante, Date fechaDevolucion) {
        this.idPrestamo = idPrestamo;
        this.cedulaEstudiante = cedulaEstudiante;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getCedulaEstudiante() {
        return cedulaEstudiante;
    }

    public void setCedulaEstudiante(String cedulaEstudiante) {
        this.cedulaEstudiante = cedulaEstudiante;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

}
