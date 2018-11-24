package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Puesto {
    private StringProperty IdPuesto;
    private StringProperty NombrePuesto;
    private StringProperty SalarioMinimo;
    private StringProperty SalarioMaximo;
    private StringProperty Detalle;
    private StringProperty Comision;

    public Puesto(String idPuesto, String nombrePuesto, String salarioMin, String salarioMax, String detalle, String comision) {
        IdPuesto = new SimpleStringProperty(idPuesto);
        NombrePuesto = new SimpleStringProperty(nombrePuesto);
        SalarioMinimo = new SimpleStringProperty(salarioMin);
        SalarioMaximo = new SimpleStringProperty(salarioMax);
        Detalle = new SimpleStringProperty(detalle);
        Comision = new SimpleStringProperty(comision);
    }

    public String getIdPuesto() {
        return IdPuesto.get();
    }

    public StringProperty idPuestoProperty() {
        return IdPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        this.IdPuesto.set(idPuesto);
    }

    public String getNombrePuesto() {
        return NombrePuesto.get();
    }

    public StringProperty nombrePuestoProperty() {
        return NombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.NombrePuesto.set(nombrePuesto);
    }

    public String getSalarioMinimo() {
        return SalarioMinimo.get();
    }

    public StringProperty salarioMinimoProperty() {
        return SalarioMinimo;
    }

    public void setSalarioMinimo(String salarioMinimo) {
        this.SalarioMinimo.set(salarioMinimo);
    }

    public String getSalarioMaximo() {
        return SalarioMaximo.get();
    }

    public StringProperty salarioMaximoProperty() {
        return SalarioMaximo;
    }

    public void setSalarioMaximo(String salarioMaximo) {
        this.SalarioMaximo.set(salarioMaximo);
    }


    public String getDetalle() {
        return Detalle.get();
    }

    public StringProperty detalleProperty() {
        return Detalle;
    }

    public void setDetalle(String detalle) {
        this.Detalle.set(detalle);
    }

    public String getComision() {
        return Comision.get();
    }

    public StringProperty comisionProperty() {
        return Comision;
    }

    public void setComision(String comision) {
        this.Comision.set(comision);
    }

    @Override
    public String toString() {
        return NombrePuesto.get();
    }
}
