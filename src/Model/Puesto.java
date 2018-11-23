package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Puesto {
    private final StringProperty idPuesto;
    private final StringProperty nombrePuesto;

    public Puesto(String idPuesto, String nombrePuesto) {
        this.idPuesto = new SimpleStringProperty(idPuesto);
        this.nombrePuesto = new SimpleStringProperty(nombrePuesto);
    }

    public String getIdPuesto() {
        return idPuesto.get();
    }

    public StringProperty idPuestoProperty() {
        return idPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        this.idPuesto.set(idPuesto);
    }

    public String getNombrePuesto() {
        return nombrePuesto.get();
    }

    public StringProperty nombrePuestoProperty() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto.set(nombrePuesto);
    }
}
