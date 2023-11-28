package logica;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Entity
@Table(name = "estudiante", schema = "gestorbibliotecario")
public class ClaseEstudiante {
    @Id
    @Column(name = "cedula", nullable = false, length = 10)
    private String cedula;
    @Basic
    @Column(name = "nombre", nullable = true, length = 255)
    private String nombre;
    @Basic
    @Column(name = "direccion", nullable = true, length = 255)
    private String direccion;
    @Basic
    @Column(name = "telefono", nullable = true, length = 10)
    private String telefono;
    @Basic
    @Column(name = "codigoUnico", nullable = true, length = 9)
    private String codigoUnico;
    @Basic
    @Column(name = "correoElectronico", nullable = true, length = 255)
    private String correoElectronico;

    public ClaseEstudiante() {
    }

    public ClaseEstudiante(String cedula, String nombre, String direccion, String telefono,
                           String codigoUnico, String correoElectronico) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.codigoUnico = codigoUnico;
        this.correoElectronico = correoElectronico;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }


    static ClaseEstudiante getEstudiante(String cedula, Session session) {
        Query<ClaseEstudiante> estudianteQuery = session.createQuery ("FROM ClaseEstudiante WHERE cedula = :cedula", ClaseEstudiante.class);
        estudianteQuery.setParameter("cedula", cedula);
        ClaseEstudiante estudiante = estudianteQuery.uniqueResult();
        return estudiante;
    }

}