package Model;

public class Sucursal {
    private String idSucursal;
    private String nombreSucursal;
    private String direccion;
    private String telfSucursal;
    private String telfPedidos;

    public Sucursal(String idSucursal, String nombreSucursal, String direccion, String telfSucursal, String telfPedidos) {
        this.idSucursal = idSucursal;
        this.nombreSucursal = nombreSucursal;
        this.direccion = direccion;
        this.telfSucursal = telfSucursal;
        this.telfPedidos = telfPedidos;
    }

    public Sucursal(String nombreSucursal) {
        this.idSucursal = "";
        this.nombreSucursal = nombreSucursal;
        this.direccion = "";
        this.telfSucursal = "";
        this.telfPedidos = "";
    }

    public Sucursal(String nombreSucursal, String idSucursal) {
        this.idSucursal = idSucursal;
        this.nombreSucursal = nombreSucursal;
        this.direccion = "";
        this.telfSucursal = "";
        this.telfPedidos = "";
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelfSucursal() {
        return telfSucursal;
    }

    public void setTelfSucursal(String telfSucursal) {
        this.telfSucursal = telfSucursal;
    }

    public String getTelfPedidos() {
        return telfPedidos;
    }

    public void setTelfPedidos(String telfPedidos) {
        this.telfPedidos = telfPedidos;
    }

    @Override
    public String toString() {
        return nombreSucursal;
    }

    public String getID(){
        return idSucursal;
    }
}
