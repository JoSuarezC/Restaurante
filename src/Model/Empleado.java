package Model;

public class Empleado extends Usuario{
    private String IdEmpleado;
    private String Puesto;
    private String CuentaBancaria;

    public Empleado(String idUsuario, String contraseña, String correo, String estado, String cedula, String apellidos, String nombre, String idEmpleado, String puesto, String cuentaBancaria) {
        super(idUsuario, contraseña, correo, estado, cedula, apellidos, nombre);
        IdEmpleado = idEmpleado;
        Puesto = puesto;
        CuentaBancaria = cuentaBancaria;
    }

    public String getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        IdEmpleado = idEmpleado;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        Puesto = puesto;
    }

    public String getCuentaBancaria() {
        return CuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        CuentaBancaria = cuentaBancaria;
    }
}
