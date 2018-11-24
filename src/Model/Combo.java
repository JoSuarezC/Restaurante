package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Combo {
    private StringProperty idCombo;
    private StringProperty descripcion;
    private StringProperty fecha;
    private StringProperty descuento;
    ArrayList<ShoppingList_Product> listaProductos;
    private double precioTotal;

    public Combo(String idCombo, String descripcion, String fecha, String descuento, ArrayList<ShoppingList_Product> listaProductos) {
        this.idCombo= new SimpleStringProperty(idCombo);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.fecha = new SimpleStringProperty(fecha);
        this.descuento = new SimpleStringProperty(descuento);
        this.listaProductos = listaProductos;
        this.precioTotal = calcularPrecio();
    }

    public String getIdCombo() {
        return idCombo.get();
    }

    public StringProperty idComboProperty() {
        return idCombo;
    }

    public void setIdCombo(String idCombo) {
        this.idCombo.set(idCombo);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public String getFecha() {
        return fecha.get();
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public String getDescuento() {
        return descuento.get();
    }

    public StringProperty descuentoProperty() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento.set(descuento);
    }

    private double calcularPrecio(){
        double retorno=0;
        for(ShoppingList_Product producto : getListaProductos()){
            retorno+=producto.getProductPrize()*producto.getProductQuantity();
        }
        retorno = retorno - (retorno * Double.parseDouble(this.getDescuento()));
        return retorno;
    }

    public ArrayList<ShoppingList_Product> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<ShoppingList_Product> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }
}
