package logica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Administrador {

    private String usuario;
    private String contraseña;

    public Administrador() {
        this.usuario = "Miguel";
        this.contraseña = "123";
    }

    public Administrador(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public static boolean validarDatosRegistro(ClaseEstudiante estudiante) {
        String cedula = estudiante.getCedula();
        String nombre = estudiante.getNombre();
        String telefono = estudiante.getTelefono();
        String codigo = estudiante.getCodigoUnico();
        String direccion = estudiante.getDireccion();
        String correo = estudiante.getCorreoElectronico();

        if (cedula == "" || nombre == "" || telefono == "" || codigo =="" || direccion == "" || correo == "")  {
            return false;
        }
        else if (cedula == null || nombre == null || telefono == null || codigo ==null || direccion == null || correo == null)  {
            return false;
        }
        else if (!nombre.equals("")) {
            String regex = "^[a-zA-Z]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(nombre);
            if (matcher.matches() == false)
                return false;
        }
        else if (cedula.length() != 10 || telefono.length() != 10 || codigo.length() != 9) {
            return false;
        }
        return true;
    }
}














