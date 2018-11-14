package Model;

public class User {
    private String IdUsuario;
    private String Contraseña;
    private String Correo;
    private String Cedula;
    private String Apellidos;
    private String Nombre;
    private static User currentUser;

    public User(String idUsuario, String contraseña, String correo, String cedula, String apellidos, String nombre) {
        IdUsuario = idUsuario;
        Contraseña = contraseña;
        Correo = correo;
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

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }
}
