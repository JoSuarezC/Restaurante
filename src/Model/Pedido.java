package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Pedido {

    private final IntegerProperty idPedido;
    private final StringProperty fechaPedido;
    private final StringProperty totalAPagar;
    private final StringProperty estadoPedido;
    private final ArrayList<ShoppingList_Product> listaProductos;

    public Pedido(int idPedido, String fechaPedido, String totalAPagar, String estadoPedido, ArrayList<ShoppingList_Product> listaProductos) {
        this.idPedido = new SimpleIntegerProperty(idPedido);
        this.fechaPedido = new SimpleStringProperty(fechaPedido);
        this.totalAPagar = new SimpleStringProperty(totalAPagar);
        this.estadoPedido = new SimpleStringProperty(estadoPedido);
        this.listaProductos = listaProductos;
    }

    public String getFechaPedido() {
        return fechaPedido.get();
    }

    public StringProperty fechaPedidoProperty() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido.set(fechaPedido);
    }

    public String getTotalAPagar() {
        return totalAPagar.get();
    }

    public StringProperty totalAPagarProperty() {
        return totalAPagar;
    }

    public void setTotalAPagar(String totalAPagar) {
        this.totalAPagar.set(totalAPagar);
    }

    public String getEstadoPedido() {
        return estadoPedido.get();
    }

    public StringProperty estadoPedidoProperty() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido.set(estadoPedido);
    }

    public ArrayList<ShoppingList_Product> getListaProductos() {
        return listaProductos;
    }

    public int getIdPedido() {
        return idPedido.get();
    }

    public IntegerProperty idPedidoProperty() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido.set(idPedido);
    }
}
