package Controller;

import Model.ConnectionDB;
import Model.Product;
import Model.Sucursal;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class AdminReportes_Controller {

    @FXML
    private RadioButton rbtn_Sales;
    @FXML
    private RadioButton rbtn_Earnings;
    @FXML
    private ComboBox<Product> comboBox_Producto;
    @FXML
    private DatePicker datePicker_FechaI;
    @FXML
    private DatePicker datePicker_FechaF;
    @FXML
    private ComboBox<Sucursal> comboBox_Subsidiary;
    @FXML
    private ComboBox<User> comboBox_Manager;
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private ToggleButton tbtn_Producto;
    @FXML
    private ToggleButton tbtn_Date;
    @FXML
    private ToggleButton tbtn_Subsidiary;
    @FXML
    private ToggleButton tbtn_Manager;

    @FXML
    protected void initialize(){
        toggleGroup = new ToggleGroup();
        rbtn_Sales.setToggleGroup(toggleGroup);
        rbtn_Sales.setSelected(true);
        rbtn_Earnings.setToggleGroup(toggleGroup);

        ObservableList<Product> product_list = FXCollections.observableArrayList();
        product_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Comida"));
        product_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Bebida"));

        Callback<ListView<Product>,ListCell<Product>> cellFactory = new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> p) {
                return new ListCell<Product>() {

                    @Override
                    protected void updateItem(Product item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getProductName());
                        }
                    }
                };
            }

        };

        comboBox_Producto.setButtonCell(cellFactory.call(null));
        comboBox_Producto.setCellFactory(cellFactory);



        comboBox_Producto.setItems(product_list);
        FXComboAutoComplete.autoCompleteComboBoxPlus(comboBox_Producto, (typedText, itemToCompare) -> itemToCompare.getProductName().toLowerCase().contains(typedText.toLowerCase()));

    }
}
