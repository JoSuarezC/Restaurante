package Controller;

import Model.ShoppingList_Product;
import javafx.collections.ObservableList;
import java.lang.String;
public class ClientOrder_Memento {

    private ObservableList<ShoppingList_Product> listaProductosMemento;
    private String precioTotal;
    private int precio;
    private static ClientOrder_Memento MementoInstance;

    public ClientOrder_Memento(ObservableList<ShoppingList_Product> listaProductosMemento, String precioTotal) {
        this.listaProductosMemento = listaProductosMemento;
        this.precioTotal = precioTotal;
    }

    public ObservableList<ShoppingList_Product> getListaProductosMemento() {
        return listaProductosMemento;
    }

    public void setListaProductosMemento(ObservableList<ShoppingList_Product> listaProductosMemento) {
        this.listaProductosMemento = listaProductosMemento;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }

    public static ClientOrder_Memento getMementoInstance() {
        if(MementoInstance==null){
            MementoInstance = new ClientOrder_Memento(null,"");
        }
        return MementoInstance;
    }

    public void setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

}
