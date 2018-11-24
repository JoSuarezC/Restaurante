package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Puesto {
    private StringProperty IdPuesto;
    private StringProperty NombrePuesto;

    public Puesto(String idPuesto, String nombrePuesto) {
        IdPuesto = new SimpleStringProperty(idPuesto);
        NombrePuesto = new SimpleStringProperty(nombrePuesto);
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

    @Override
    public String toString() {
        return NombrePuesto.get();
    }
}
