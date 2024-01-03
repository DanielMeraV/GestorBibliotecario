package logica;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Objects;

@Entity
@Table(name = "libro", schema = "gestorbibliotecario")
public class ClaseLibro {
    private static SessionFactory sessionFactory;
    @Id
    @Column(name = "idLibro", nullable = false, length = 3)
    private String idLibro;
    @Basic
    @Column(name = "titulo", nullable = true, length = 255)
    private String titulo;
    @Basic
    @Column(name = "autor", nullable = true, length = 255)
    private String autor;
    @Basic
    @Column(name = "genero", nullable = true, length = 255)
    private String genero;
    @Basic
    @Column(name = "disponibilidad", nullable = true)
    private Boolean disponibilidad;

    public ClaseLibro() {
    }

    public ClaseLibro(String idLibro, String titulo, String autor, String genero, Boolean disponibilidad) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.disponibilidad = disponibilidad;
    }

    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLibro, titulo, autor, genero, disponibilidad);
    }

}