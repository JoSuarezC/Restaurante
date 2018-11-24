package Controller;

import Model.ConnectionDB;
import Model.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;

import java.io.IOException;

public class DetalleProducto_Controller {

    Product producto = null;

    @FXML
    private Button btnAtras;

    @FXML
    private TextField tField_Nombre;

    @FXML
    private TextArea tArea_Descripcion;

    @FXML
    private TextField tField_Precio;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnModficar;

    @FXML
    private Text txtNombre;

    @FXML
    private Text txtTipo;

    @FXML
    private Text txtDescripcion;

    @FXML
    private Text txtPrecio;

    @FXML
    private CheckBox chkBoxEstado;

    @FXML
    protected void initialize(){
        producto = ((Product) FXRouter.getData());
        clearFields();
        desactivarEspacios();
        showInfo(producto);
        showButtonsOptions();
    }

    protected void clearFields(){
        this.txtNombre.setText(new String(""));
        this.txtDescripcion.setText(new String(""));
        this.txtPrecio.setText(new String(""));
        this.txtTipo.setText(new String(""));
        this.chkBoxEstado.setSelected(false);

        this.tField_Nombre.setText(new String(""));
        this.tArea_Descripcion.setText(new String(""));
        this.tField_Precio.setText(new String(""));
    }

    protected  void showInfo(Product producto){
        this.txtNombre.setText(producto.getProductName());
        this.txtDescripcion.setText(producto.getProductDetail());
        this.txtPrecio.setText(String.format ("%d", producto.getProductPrize()));
        this.txtTipo.setText(producto.getProductType());
        if(producto.getProductState()==1){
            this.chkBoxEstado.setSelected(true);
        }
        else{
            this.chkBoxEstado.setSelected(false);
        }
        this.chkBoxEstado.setDisable(true);
    }

    protected void showButtonsOptions(){
        this.btnAtras.setVisible(true);
        this.btnModficar.setVisible(true);
        this.btnAtras.setDisable(false);
        this.btnModficar.setDisable(false);

        this.btnCancelar.setVisible(false);
        this.btnGuardar.setVisible(false);
        this.btnCancelar.setDisable(true);
        this.btnGuardar.setDisable(true);

        this.tField_Nombre.setVisible(false);
        this.tArea_Descripcion.setVisible(false);
        this.tField_Precio.setVisible(false);
    }

    private void showButtonsState(){
        this.btnCancelar.setVisible(true);
        this.btnGuardar.setVisible(true);
        this.btnCancelar.setDisable(false);
        this.btnGuardar.setDisable(false);

        this.btnAtras.setVisible(false);
        this.btnModficar.setVisible(false);
        this.btnAtras.setDisable(true);
        this.btnModficar.setDisable(true);
    }

    private void activarEspacios(){
        this.txtNombre.setVisible(false);
        this.txtDescripcion.setVisible(false);
        this.txtPrecio.setVisible(false);

        this.txtTipo.setVisible(true);

        this.tField_Nombre.setVisible(true);
        this.tArea_Descripcion.setVisible(true);
        this.tField_Precio.setVisible(true);

        this.tField_Nombre.setDisable(false);
        this.tArea_Descripcion.setDisable(false);
        this.tField_Precio.setDisable(false);

        this.chkBoxEstado.setDisable(false);
    }

    protected void desactivarEspacios(){
        this.txtNombre.setVisible(true);
        this.txtDescripcion.setVisible(true);
        this.txtPrecio.setVisible(true);

        this.txtTipo.setVisible(true);

        this.tField_Nombre.setDisable(true);
        this.tArea_Descripcion.setDisable(true);
        this.tField_Precio.setDisable(true);

        this.tField_Nombre.setVisible(false);
        this.tArea_Descripcion.setVisible(false);
        this.tField_Precio.setVisible(false);

        this.chkBoxEstado.setDisable(true);
    }

    protected void ponerValores(){
        this.tField_Nombre.setText(this.txtNombre.getText());
        this.tArea_Descripcion.setText(this.txtDescripcion.getText());
        this.tField_Precio.setText(this.txtPrecio.getText());
    }

    @FXML
    public void atras(ActionEvent event) {
        try {
            FXRouter.goTo("Products");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cancelar(ActionEvent event) {
        desactivarEspacios();
        showButtonsOptions();
    }

    @FXML
    public void guardar(ActionEvent event) {
        try{
            if (this.tField_Nombre.getText().isEmpty() ||
                    this.tArea_Descripcion.getText().isEmpty() ||
                    this.tField_Precio.getText().isEmpty()) {
                Main.MessageBox("Advertencia", "Debe de llenar todos los campos para poder guardar los cambios");
            }else{
                ConnectionDB.getInstance().updateProduct(this.tField_Nombre.getText(),
                        this.txtTipo.getText(),
                        this.tArea_Descripcion.getText(),
                        Integer.parseInt(this.tField_Precio.getText()), this.chkBoxEstado.isSelected() ? 1 : 0, Integer.parseInt(producto.getProductID()));
                Main.MessageBox("Informacion", "El producto ha sido actualizado correctamente");
                actualizarDatos();
                desactivarEspacios();
                showButtonsOptions();
                showInfo(this.producto);
            }
        }catch (Exception e){
            Main.MessageBox("Advertencia","Durante la operación se presento un eroror, intentelo de nuevo");
            System.out.println(e);
        }
    }

    public void actualizarDatos(){
        this.producto.setProductName(this.tField_Nombre.getText());
        this.producto.setProductDetail(this.tArea_Descripcion.getText());
        this.producto.setProductPrize(Integer.parseInt(tField_Precio.getText()));
        this.producto.setProductType(this.txtTipo.getText());
        this.producto.setProductState(this.chkBoxEstado.isSelected()?1:0);
    }

    @FXML
    public void modificar(ActionEvent event) {
        try{
            if (ConnectionDB.getInstance().is_assigned(Integer.parseInt(producto.getProductID()))!=0){
                Main.MessageBox("Advertencia","Este producto ya ha sido asiganado a un pedido, por lo tanto" +
                        " no es modificable");
            }
            else {
                ponerValores();
                activarEspacios();
                showButtonsState();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
