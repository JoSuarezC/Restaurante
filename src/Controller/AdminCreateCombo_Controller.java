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
import javafx.util.StringConverter;

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
    @FXML private TableColumn<Product, String> tableColumn_Combo_Producto;
    @FXML private TableColumn<Product, String> tableColumn_Combo_Cantidad;
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

        //btn_crearCombo.setDisable(true);

        SpinnerValueFactory discountValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1);
        discountValueFactory.setConverter(new StringConverter<Integer>() {

            @Override
            public String toString(Integer value) {
                return value.toString()+" %";
            }

            @Override
            public Integer fromString(String string) {
                String valueWithoutUnits = string.replaceAll("%", "").trim();
                System.out.println(valueWithoutUnits);
                if (valueWithoutUnits.isEmpty()) {
                    return 0 ;
                } else {
                    return Integer.valueOf(valueWithoutUnits);
                }
            }

        });

        spinner_descuento.setValueFactory(discountValueFactory);
        spinner_descuento.setEditable(true);
        /*
        spinner_descuento.valueProperty().addListener((obs) -> {
            btn_crearCombo.setDisable(false);
        });*/
        //

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

        tableView_Producto.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableView_Producto.setItems(product_list);



        //Tabla Combo
        tableColumn_Combo_Producto.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        tableColumn_Combo_Cantidad.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

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
                                valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1, 1);
                                count = new Spinner<>(valueFactory);
                                valueChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                                    valueFactory.setValue(newValue.intValue());
                                };
                            }

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    valueFactory.setValue(1);
                                    count.valueProperty().addListener((obs, oldValue, newValue) -> {
                                        productQty.put(getTableView().getItems().get(getIndex()).getProductID(), newValue);
                                    });

                                    setGraphic(count);
                                }
                            }
                        };
                        return cell;
                    }
                };

        tableColumn_Combo_Cantidad.setCellFactory(cellFactorySpinner);

        tableView_Combo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //tableView_Combo.setItems(product_list);
    }

    @FXML
    void addToComboList(ActionEvent event){
        ObservableList productCombo = tableView_Producto.getSelectionModel().getSelectedItems();
        ObservableList<Product> newObsList = FXCollections.observableArrayList(productCombo);

        //HashMap
        productQty = new HashMap<>();
        newObsList.forEach((product) -> {
            productQty.put(product.getProductID(),1);
        });

        tableView_Combo.setItems(newObsList);
    }

    @FXML
    void removeFromComboList(ActionEvent event){
        ObservableList<Product> productCombRemove = tableView_Combo.getSelectionModel().getSelectedItems();
        productCombRemove.forEach((product -> {
            productQty.remove(product.getProductID());
        }));
        tableView_Combo.getItems().removeAll(productCombRemove);
    }


    @FXML
    void MakeCombo(ActionEvent event) {

        if(!tableView_Producto.getItems().isEmpty()){
            Date fecha = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd H:m");
            String comboID;
            Integer discountInteger = (Integer)spinner_descuento.getValue()/100;
            Double discountDouble = ((Integer) spinner_descuento.getValue()).doubleValue()/100;
            comboID = ConnectionDB.getInstance().makeCombo("Desc Test", dateFormat.format(fecha).toString(),  discountDouble.toString());

            System.out.println("Discount Double: " + discountDouble);


            ObservableList<Product> productList;
            productList = tableView_Combo.getItems();

            productList.forEach((product -> {
                ConnectionDB.getInstance().addProductCombo(comboID, product.getProductID(), productQty.get(product.getProductID()).toString());
                System.out.println(product.getProductName() + " - " + productQty.get(product.getProductID()).toString());
            }));

            tableView_Combo.getItems().clear();
            productQty.clear();
            spinner_descuento.getValueFactory().setValue(0);
            //btn_crearCombo.setDisable(true);
        }else{
            Main.MessageBox("No hay productos", "Seleccione los productos que desea añadir al combo.");
        }

    }

    @FXML
    public void GoBack(ActionEvent event){
        try {
            FXRouter.goTo("Adm_ManagePMenu");
        } catch (IOException e) {
            System.out.print(e);
        }
    }

}
