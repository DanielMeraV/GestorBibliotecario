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

    public static ClaseLibro getLibro(String idlibro, Session session) {
        try {
            Query<ClaseLibro> libroQuery = session.createQuery ("FROM ClaseLibro WHERE idLibro = :idLibro", ClaseLibro.class);
            libroQuery.setParameter("idLibro", idlibro);
            ClaseLibro libro = libroQuery.uniqueResult();
            return libro;
        }catch (Exception e) {
            return null;
        }
    }

    public static boolean validarDatosLibro(ClaseLibro libro){
        boolean valido = true;
        if (libro.getIdLibro() == null || libro.getTitulo() == null || libro.getAutor() == null || libro.getGenero() == null) {
            valido = false;
        } else if(libro.getIdLibro().length() != 3){
            valido = false;
        } else if (!(libro.getDisponibilidad() == true || libro.getDisponibilidad() == false)) {
            valido = false;
        } else if (!libro.getIdLibro().matches("\\d+")) {
            valido = false;
        }
        return valido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLibro, titulo, autor, genero, disponibilidad);
    }

}