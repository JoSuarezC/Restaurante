package Model;

public class Client extends User {
    private String IdCliente;
    private String Telefono1;
    private String Telefono2;

    public Client(String idUsuario, String contraseña, String correo, String cedula, String apellidos, String nombre, String idCliente, String telefono1, String telefono2) {
        super(idUsuario, contraseña, correo, cedula, apellidos, nombre);
        IdCliente = idCliente;
        Telefono1 = telefono1;
        Telefono2 = telefono2;
    }

    public String getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(String idCliente) {
        IdCliente = idCliente;
    }

    public String getTelefono1() {
        return Telefono1;
    }

    public void setTelefono1(String telefono1) {
        Telefono1 = telefono1;
    }

    public String getTelefono2() {
        return Telefono2;
    }

    public void setTelefono2(String telefono2) {
        Telefono2 = telefono2;
    }
}
