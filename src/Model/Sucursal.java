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

    @Override
    public String toString() {
        return nombreSucursal;
    }

    public String getID(){
        return idSucursal;
    }
}
