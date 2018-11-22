package Controller;

import Model.ConnectionDB;
import Model.Product;
import Model.ShoppingList_Product;
import Model.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AdminCreateCombo_Controller {

    @FXML private TableView<Product> tableView_Producto;
    @FXML private TableColumn<Product, String> tableColumn_Producto_Producto;
    @FXML private TableColumn<Product, String> tableColumn_Producto_Precio;
    @FXML private TableColumn<Product, String> tableColumn_Producto_Ver;
    @FXML private Button btn_agregarP;
    @FXML private Button btn_quitarP;
    @FXML private Button btn_crearCombo;
    @FXML private TableView<Product> tableView_Combo;
    @FXML private Spinner spinner_descuento;

    private Map<String,Integer> productQty;

    @FXML
    protected void initialize(){
        //ObservableList<String> brachOffice_list = FXCollections.observableArrayList();
        //brachOffice_list.addAll(ConnectionDB.getInstance().selectSucursales());
        //choiceBox_Sucursal.setItems(brachOffice_list);
        fillTable();
    }

    private void fillTable(){
        // Tabla de Productos
        tableColumn_Producto_Producto.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        tableColumn_Producto_Precio.setCellValueFactory(cellData -> cellData.getValue().productPrizeProperty().asString());
        tableColumn_Producto_Ver.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFactory
                =
                new Callback<TableColumn<Product, String>, TableCell<Product, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Product, String> param) {
                        final TableCell<Product, String> cell = new TableCell<Product, String>() {

                            final Button btn = new Button("Ver Producto");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Product myProduct = getTableView().getItems().get(getIndex());
                                        try {
                                            FXRouter.goTo("AddProduct", myProduct);
                                        } catch (IOException e) {
                                            System.out.print(e);
                                        }

                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };


        tableColumn_Producto_Ver.setCellFactory(cellFactory);

        ObservableList<Product> product_list = FXCollections.observableArrayList();
        product_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Comida"));
        product_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Bebida"));

        tableView_Producto.setItems(product_list);

        //HashMap
        productQty = new HashMap<>();
        product_list.forEach((product) -> {
            productQty.put(product.getProductID(),1);
        });

        //Tabla Combo

        Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFactorySpinner
                =
                new Callback<TableColumn<Product, String>, TableCell<Product, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Product, String> param) {
                        final TableCell<Product, String> cell = new TableCell<Product, String>() {

                            private final Spinner<Integer> count;

                            private final SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory;
                            private final ChangeListener<Number> valueChangeListener;

                            {
                                valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0);
                                count = new Spinner<>(valueFactory);
                                count.setVisible(false);
                                setGraphic(count);
                                valueChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                                    valueFactory.setValue(newValue.intValue());
                                };
                                count.valueProperty().addListener((obs, oldValue, newValue) -> {
                                    //map.put(key, map.get(key) + 1);
                                    productQty.put()
                                });
                            }

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Product myProduct = getTableView().getItems().get(getIndex());
                                        try {
                                            FXRouter.goTo("AddProduct", myProduct);
                                        } catch (IOException e) {
                                            System.out.print(e);
                                        }

                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
    }

    @FXML
    void addToComboList(ActionEvent event){
        tableView_Combo.setItems(tableView_Producto.getSelectionModel().getSelectedItems());
    }

    @FXML
    void removeFromComboList(ActionEvent event){
        tableView_Combo.setItems(tableView_Producto.getSelectionModel().getSelectedItems());
    }

    @FXML
    void MakeCombo(ActionEvent event) {
        /*
        if(!tableView_Producto.getItems().isEmpty()){
            Date fecha = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd H:m");
            String orderID;
            String totalAPagar = Label_TotalCost.getText();
            if (User.getCurrentUser().getUserType().equals("Empleado")){
                orderID = ConnectionDB.getInstance().makeOrder(null, dateFormat.format(fecha).toString(), "Local", totalAPagar);
            }else{
                orderID = ConnectionDB.getInstance().makeOrder(User.getCurrentUser().getUserID(), dateFormat.format(fecha).toString(), "Local", totalAPagar);
            }
            System.out.print(orderID);
            ObservableList<ShoppingList_Product> list;
            list = tablaView_Inventario.getItems();
            int prize = 0;
            for (ShoppingList_Product i : list) {
                prize = i.getProductPrize() * i.getProductQuantity();
                ConnectionDB.getInstance().buyProduct(i.getProductID(),String.valueOf(i.getProductQuantity()),orderID, String.valueOf(prize));
            }
            tablaView_Inventario.getItems().clear();
        }else{
            Main.MessageBox("Tabla de productos vacía", "Seleccione los productos que desea comprar.");
        }
        */
    }

    @FXML
    public void GoBack(ActionEvent event){
        try {
            FXRouter.loadPreviousRoute();
        } catch (IOException e) {
            System.out.print(e);
        }
    }

}
