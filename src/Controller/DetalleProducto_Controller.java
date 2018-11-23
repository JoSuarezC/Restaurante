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

    ObservableList<String> options =
            FXCollections.observableArrayList(
                    "Bebida",
                    "Comida",
                    "Postre"
            );

    @FXML
    private Text txt_NombreProducto;

    @FXML
    private Text txt_DescripcionProducto;

    @FXML
    private Text txt_PrecioProducto;

    @FXML
    private Text txt_TipoProducto;

    @FXML
    private CheckBox chkBox_Disponible;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnModficar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField tFileld_Nombre;

    @FXML
    private TextField tFileld_Descripcion;

    @FXML
    private TextField tFileld_Precio;

    @FXML
    private ComboBox<String> cmbTipo_Producto;

    @FXML
    protected void initialize(){
        producto = ((Product) FXRouter.getData());
        clearFields();
        desactivarEspacios();
        showInfo(producto);
        showButtonsOptions();
    }

    protected void clearFields(){
        this.txt_NombreProducto.setText(new String(""));
        this.txt_DescripcionProducto.setText(new String(""));
        this.txt_PrecioProducto.setText(new String(""));
        this.chkBox_Disponible.setSelected(false);

        this.tFileld_Nombre.setText(new String(""));
        this.tFileld_Descripcion.setText(new String(""));
        this.tFileld_Precio.setText(new String(""));
        this.cmbTipo_Producto.setItems(this.options);
    }

    protected  void showInfo(Product producto){
        this.txt_NombreProducto.setText(producto.getProductName());
        this.txt_DescripcionProducto.setText(producto.getProductDetail());
        this.txt_PrecioProducto.setText(String.format ("%d", producto.getProductPrize()));
        this.txt_TipoProducto.setText(producto.getProductType());
        if(producto.getProductState()==1){
            this.chkBox_Disponible.setSelected(true);
        }
        else{
            this.chkBox_Disponible.setSelected(false);
        }
        this.chkBox_Disponible.setDisable(true);
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

        this.tFileld_Nombre.setVisible(false);
        this.tFileld_Nombre.setVisible(false);
        this.tFileld_Nombre.setVisible(false);
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
        this.txt_NombreProducto.setVisible(false);
        this.txt_DescripcionProducto.setVisible(false);
        this.txt_PrecioProducto.setVisible(false);
        this.txt_TipoProducto.setVisible(false);

        this.tFileld_Nombre.setVisible(true);
        this.tFileld_Descripcion.setVisible(true);
        this.tFileld_Precio.setVisible(true);
        this.cmbTipo_Producto.setVisible(true);

        this.tFileld_Nombre.setDisable(false);
        this.tFileld_Descripcion.setDisable(false);
        this.tFileld_Precio.setDisable(false);
        this.cmbTipo_Producto.setDisable(false);

        this.chkBox_Disponible.setDisable(false);
    }

    protected void desactivarEspacios(){
        this.txt_NombreProducto.setVisible(true);
        this.txt_DescripcionProducto.setVisible(true);
        this.txt_PrecioProducto.setVisible(true);
        this.txt_TipoProducto.setVisible(true);

        this.tFileld_Nombre.setDisable(true);
        this.tFileld_Descripcion.setDisable(true);
        this.tFileld_Precio.setDisable(true);
        this.cmbTipo_Producto.setDisable(true);

        this.tFileld_Nombre.setVisible(false);
        this.tFileld_Descripcion.setVisible(false);
        this.tFileld_Precio.setVisible(false);
        this.cmbTipo_Producto.setVisible(false);

        this.chkBox_Disponible.setDisable(true);
    }

    protected void ponerValores(){
        this.tFileld_Nombre.setText(this.txt_NombreProducto.getText());
        this.tFileld_Descripcion.setText(this.txt_DescripcionProducto.getText());
        this.tFileld_Precio.setText(this.txt_PrecioProducto.getText());
        this.cmbTipo_Producto.setValue(this.txt_TipoProducto.getText());
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
            if (this.tFileld_Nombre.getText().isEmpty() &&
                    this.tFileld_Descripcion.getText().isEmpty() &&
                    this.tFileld_Precio.getText().isEmpty() &&
                    this.cmbTipo_Producto.getValue().isEmpty()) {
                Main.MessageBox("Advertencia", "Debe de llenar todos los campos para poder guardar los cambios");
            }
            if (this.tFileld_Nombre.getText() == this.producto.getProductName() &&
                    this.tFileld_Descripcion.getText() == this.producto.getProductDetail() &&
                    this.tFileld_Precio.getText() == String.valueOf(this.producto.getProductPrize()) &&
                    this.cmbTipo_Producto.getValue() == this.producto.getProductType()) {
                Main.MessageBox("Advertencia", "Para poder guardar es necesario cambiar alguno de los datos del producto");
            }
            else{
                ConnectionDB.getInstance().updateProduct(this.tFileld_Nombre.getText(),
                        this.cmbTipo_Producto.getSelectionModel().getSelectedItem(),
                        this.tFileld_Descripcion.getText(),
                        tFileld_Precio.getText());
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
        this.producto.setProductName(this.tFileld_Nombre.getText());
        this.producto.setProductDetail(this.tFileld_Descripcion.getText());
        this.producto.setProductPrize(Integer.parseInt(tFileld_Precio.getText()));
        this.producto.setProductType(this.cmbTipo_Producto.getValue());
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
