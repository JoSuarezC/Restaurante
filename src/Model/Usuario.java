package Model;

public class Usuario {
    private String IdUsuario;
    private String Contraseña;
    private String Correo;
    private String Estado;
    private String Cedula;
    private String Apellidos;
    private String Nombre;

    public Usuario(String idUsuario, String contraseña, String correo, String estado, String cedula, String apellidos, String nombre) {
        IdUsuario = idUsuario;
        Contraseña = contraseña;
        Correo = correo;
        Estado = estado;
        Cedula = cedula;
        Apellidos = apellidos;
        Nombre = nombre;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
