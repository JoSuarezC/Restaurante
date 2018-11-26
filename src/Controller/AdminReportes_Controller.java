package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

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
    private HBox hBox_Charts;
    @FXML
    private Label caption;

    @SuppressWarnings("Duplicates")
    @FXML
    protected void initialize(){
        //HBox for Charts
        hBox_Charts.setAlignment(Pos.CENTER);
        //Label Caption
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 24 sansserif");
        //DatePicker
        datePicker_FechaI.setValue(LocalDate.now());
        datePicker_FechaF.setValue(LocalDate.now());
        datePicker_FechaI.setEditable(false);
        datePicker_FechaF.setEditable(false);
        //Radio ButtonsanchorPaneChart
        toggleGroup = new ToggleGroup();
        rbtn_Sales.setToggleGroup(toggleGroup);
        rbtn_Sales.setSelected(true);
        rbtn_Sales.setUserData("Ventas");
        rbtn_Earnings.setToggleGroup(toggleGroup);
        rbtn_Earnings.setUserData("Ganancias");
        //Product List
        ObservableList<Product> product_list = FXCollections.observableArrayList();
        product_list.add(new Product("Todos"));
        product_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Comida"));
        product_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Bebida"));
        product_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Postre"));
        //Product ComboBox
        Callback<ListView<Product>,ListCell<Product>> cellFactoryProduct = new Callback<ListView<Product>, ListCell<Product>>() {
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
        setComboBox(comboBox_Producto,cellFactoryProduct,product_list);
        FXComboAutoComplete.autoCompleteComboBoxPlus(comboBox_Producto, (typedText, itemToCompare) -> itemToCompare.getProductName().toLowerCase().contains(typedText.toLowerCase()));
        comboBox_Producto.getSelectionModel().selectFirst();
        //Subsidiary List
        ObservableList<Sucursal> subsidiary_list = FXCollections.observableArrayList();
        if(User.getCurrentUser().getSucursalID().equals("4")){
            //Es el gerente general
            subsidiary_list.add(new Sucursal("Todas"));
            subsidiary_list.addAll(ConnectionDB.getInstance().selectSucursales());
        }else{
            subsidiary_list.add(new Sucursal(User.getCurrentUser().getSucursalName(),User.getCurrentUser().getSucursalID()));
            tbtn_Subsidiary.setSelected(true);
            tbtn_Subsidiary.setDisable(true);
            tbtn_Manager.setDisable(true);
            changeTbtnColor(tbtn_Subsidiary);
        }

        //Subsidiary ComboBox
        Callback<ListView<Sucursal>,ListCell<Sucursal>> cellFactorySubsidiary = new Callback<ListView<Sucursal>, ListCell<Sucursal>>() {
            @Override
            public ListCell<Sucursal> call(ListView<Sucursal> p) {
                return new ListCell<Sucursal>() {

                    @Override
                    protected void updateItem(Sucursal item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.toString());
                        }
                    }
                };
            }

        };
        setComboBox(comboBox_Subsidiary,cellFactorySubsidiary,subsidiary_list);
        FXComboAutoComplete.autoCompleteComboBoxPlus(comboBox_Subsidiary, (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()));
        comboBox_Subsidiary.getSelectionModel().selectFirst();

        //Manager List
        ObservableList<User> manager_list = FXCollections.observableArrayList();
        manager_list.add(new User("Todos"));
        manager_list.addAll(ConnectionDB.getInstance().selectAll_Gerentes());

        //Manager ComboBox
        Callback<ListView<User>,ListCell<User>> cellFactoryManager = new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> p) {
                return new ListCell<User>() {

                    @Override
                    protected void updateItem(User item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getUsername());
                        }
                    }
                };
            }

        };
        setComboBox(comboBox_Manager,cellFactoryManager,manager_list);
        FXComboAutoComplete.autoCompleteComboBoxPlus(comboBox_Manager, (typedText, itemToCompare) -> itemToCompare.getUsername().toLowerCase().contains(typedText.toLowerCase()));
        comboBox_Manager.getSelectionModel().selectFirst();

        //ToogleButtons
        tbtn_Producto.setOnAction(event -> {
            changeTbtnColor(tbtn_Producto);
        });

        tbtn_Date.setOnAction(event -> {
            changeTbtnColor(tbtn_Date);
        });

        tbtn_Subsidiary.setOnAction(event -> {
            changeTbtnColor(tbtn_Subsidiary);
            tbtn_Manager.setDisable(tbtn_Subsidiary.isSelected());
        });

        tbtn_Manager.setOnAction(event -> {
            changeTbtnColor(tbtn_Manager);
            tbtn_Subsidiary.setDisable(tbtn_Manager.isSelected());

        });
    }

    private void setComboBox(ComboBox comboBox, Callback cb, ObservableList obsList){
        comboBox.setButtonCell((ListCell) cb.call(null));
        comboBox.setCellFactory(cb);
        comboBox.setItems(obsList);
    }

    public static String theMonth(int month){
        String[] monthNames = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return monthNames[month-1];
    }


    @SuppressWarnings("Duplicates")
    public void createChart(){
        String ventasGanancias = (String) toggleGroup.getSelectedToggle().getUserData();
        boolean isEarningsReport= rbtn_Earnings.isSelected();

        if(!hBox_Charts.getChildren().isEmpty()){
            hBox_Charts.getChildren().clear();
            caption.setTranslateX(0);
            caption.setTranslateY(0);
            caption.setText("");
        }

        //Acá empieza lo chido
        if (tbtn_Date.isSelected()){
            //Es necesario hacer un LineChart
            //datePicker_FechaI.getValue().toString() +" 00:00:00"
            //datePicker_FechaF.getValue().toString() +" 23:59:59"
            if(datePicker_FechaI.getValue() != null && datePicker_FechaF.getValue() != null && datePicker_FechaI.getValue().isAfter(datePicker_FechaF.getValue())){
                Main.MessageBox("Fechas Inválidas", "La fecha final debe ir después de la fecha inicial.");
            }else{
                if(tbtn_Producto.isSelected()){
                    if (tbtn_Subsidiary.isSelected()){
                        //Se desea hacer por producto por sucursal por fecha [Varios Charts] [NO SE PUEDE TODOS PRODUCTOS]
                        ArrayList<Multiple_XYChart> multipleXYChart = ConnectionDB.getInstance().reporte_por_fecha_por_producto_por_sucursal(
                                comboBox_Producto.getItems().get(comboBox_Producto.getSelectionModel().getSelectedIndex()).getProductID(),
                                datePicker_FechaI.getValue().toString() +" 00:00:00",
                                datePicker_FechaF.getValue().toString() +" 23:59:59", "",
                                comboBox_Subsidiary.getItems().get(comboBox_Subsidiary.getSelectionModel().getSelectedIndex()).getIdSucursal(), isEarningsReport);
                        multipleXYChart.forEach(multiple_xyChart -> {
                            //Genero un chart para cada sucursal
                            final NumberAxis xAxis = new NumberAxis();
                            final NumberAxis yAxis = new NumberAxis();
                            xAxis.setLabel("Día");

                            xAxis.setAutoRanging(false);
                            xAxis.setLowerBound(multiple_xyChart.getMinValue());
                            xAxis.setUpperBound(multiple_xyChart.getMaxValue());
                            xAxis.setTickUnit(1);
                            xAxis.setMinorTickVisible(false);

                            yAxis.setLabel((String)toggleGroup.getSelectedToggle().getUserData());
                            final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
                            lineChart.setMinWidth(800);
                            lineChart.setTitle("Reporte de " + toggleGroup.getSelectedToggle().getUserData() + ": " + theMonth(multiple_xyChart.getMonth())+"\n"+multiple_xyChart.getName());
                            //Por cada serie
                            multiple_xyChart.getSeriesList().forEach(series -> {
                                lineChart.getData().add(series);
                            });

                            hBox_Charts.getChildren().add(lineChart);

                        });

                    }else if(tbtn_Manager.isSelected()){
                        //Se desea hacer por producto por gerente por fecha [Varios Charts] [NO SE PUEDE TODOS PRODUCTOS]
                        ArrayList<Multiple_XYChart> multipleXYChart = ConnectionDB.getInstance().reporte_por_fecha_por_producto_por_gerente(
                                comboBox_Producto.getItems().get(comboBox_Producto.getSelectionModel().getSelectedIndex()).getProductID(),
                                datePicker_FechaI.getValue().toString() +" 00:00:00",
                                datePicker_FechaF.getValue().toString() +" 23:59:59",
                                comboBox_Manager.getItems().get(comboBox_Manager.getSelectionModel().getSelectedIndex()).getUserID(),
                                "", isEarningsReport);
                        multipleXYChart.forEach(multiple_xyChart -> {
                            //Genero un chart para cada sucursal
                            final NumberAxis xAxis = new NumberAxis();
                            final NumberAxis yAxis = new NumberAxis();
                            xAxis.setLabel("Día");

                            xAxis.setAutoRanging(false);
                            xAxis.setLowerBound(multiple_xyChart.getMinValue());
                            xAxis.setUpperBound(multiple_xyChart.getMaxValue());
                            xAxis.setTickUnit(1);
                            xAxis.setMinorTickVisible(false);

                            yAxis.setLabel((String)toggleGroup.getSelectedToggle().getUserData());
                            final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
                            lineChart.setMinWidth(800);
                            lineChart.setTitle("Reporte de " + toggleGroup.getSelectedToggle().getUserData() + ": " + theMonth(multiple_xyChart.getMonth())+"\n"+multiple_xyChart.getName());
                            //Por cada serie
                            multiple_xyChart.getSeriesList().forEach(series -> {
                                lineChart.getData().add(series);
                            });

                            hBox_Charts.getChildren().add(lineChart);

                        });
                    }else{
                        //Se desea hacer un chart por productos por fecha [Varios Charts]
                        ArrayList<Multiple_XYChart> multipleXYChart = ConnectionDB.getInstance().reporte_por_fecha_producto(
                                comboBox_Producto.getItems().get(comboBox_Producto.getSelectionModel().getSelectedIndex()).getProductID(),
                                datePicker_FechaI.getValue().toString() +" 00:00:00",
                                datePicker_FechaF.getValue().toString() +" 23:59:59",
                                "", "", isEarningsReport);
                        multipleXYChart.forEach(multiple_xyChart -> {
                            //Genero un chart para cada sucursal
                            final NumberAxis xAxis = new NumberAxis();
                            final NumberAxis yAxis = new NumberAxis();
                            xAxis.setLabel("Día");

                            xAxis.setAutoRanging(false);
                            xAxis.setLowerBound(multiple_xyChart.getMinValue());
                            xAxis.setUpperBound(multiple_xyChart.getMaxValue());
                            xAxis.setTickUnit(1);
                            xAxis.setMinorTickVisible(false);

                            yAxis.setLabel((String)toggleGroup.getSelectedToggle().getUserData());
                            final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
                            lineChart.setMinWidth(800);
                            lineChart.setTitle("Reporte de " + toggleGroup.getSelectedToggle().getUserData() + ": " + theMonth(multiple_xyChart.getMonth()));
                            //Por cada serie
                            multiple_xyChart.getSeriesList().forEach(series -> {
                                lineChart.getData().add(series);
                            });
                            hBox_Charts.getChildren().add(lineChart);

                        });
                    }
                }else{
                    if (tbtn_Subsidiary.isSelected()){
                        //Se desea hacer por sucursal por fecha
                        ArrayList<Multiple_XYChart> multipleXYChart = ConnectionDB.getInstance().reporte_por_fecha_por_sucursal("",
                                datePicker_FechaI.getValue().toString() +" 00:00:00",
                                datePicker_FechaF.getValue().toString() +" 23:59:59",
                                "",
                                comboBox_Subsidiary.getItems().get(comboBox_Subsidiary.getSelectionModel().getSelectedIndex()).getIdSucursal(), isEarningsReport);
                        multipleXYChart.forEach(multiple_xyChart -> {
                            //Genero un chart para cada sucursal
                            final NumberAxis xAxis = new NumberAxis();
                            final NumberAxis yAxis = new NumberAxis();
                            xAxis.setLabel("Día");

                            xAxis.setAutoRanging(false);
                            xAxis.setLowerBound(multiple_xyChart.getMinValue());
                            xAxis.setUpperBound(multiple_xyChart.getMaxValue());
                            xAxis.setTickUnit(1);
                            xAxis.setMinorTickVisible(false);

                            yAxis.setLabel("Ventas");
                            final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
                            lineChart.setMinWidth(800);
                            lineChart.setTitle("Reporte de "+toggleGroup.getSelectedToggle().getUserData()+": " + theMonth(multiple_xyChart.getMonth()));
                            //Por cada serie
                            multiple_xyChart.getSeriesList().forEach(series -> {
                                lineChart.getData().add(series);
                            });
                            hBox_Charts.getChildren().add(lineChart);

                        });
                    }else if(tbtn_Manager.isSelected()){
                        //Se desea hacer por gerente por fecha
                        ArrayList<Multiple_XYChart> multipleXYChart = ConnectionDB.getInstance().reporte_por_fecha_gerente("",
                                datePicker_FechaI.getValue().toString() +" 00:00:00",
                                datePicker_FechaF.getValue().toString() +" 23:59:59",
                                comboBox_Manager.getItems().get(comboBox_Manager.getSelectionModel().getSelectedIndex()).getUserID(),
                                "", isEarningsReport);
                        multipleXYChart.forEach(multiple_xyChart -> {
                            //Genero un chart para cada sucursal
                            final NumberAxis xAxis = new NumberAxis();
                            final NumberAxis yAxis = new NumberAxis();
                            xAxis.setLabel("Día");

                            xAxis.setAutoRanging(false);
                            xAxis.setLowerBound(multiple_xyChart.getMinValue());
                            xAxis.setUpperBound(multiple_xyChart.getMaxValue());
                            xAxis.setTickUnit(1);
                            xAxis.setMinorTickVisible(false);

                            yAxis.setLabel((String)toggleGroup.getSelectedToggle().getUserData());
                            final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
                            lineChart.setMinWidth(800);
                            lineChart.setTitle("Reporte de "+toggleGroup.getSelectedToggle().getUserData()+": " + theMonth(multiple_xyChart.getMonth()));
                            //Por cada serie
                            multiple_xyChart.getSeriesList().forEach(series -> {
                                lineChart.getData().add(series);
                            });
                            hBox_Charts.getChildren().add(lineChart);

                        });

                    }else{
                        //Se desea hacer un chart por fecha [TOTAL del mes]
                        ArrayList<Multiple_XYChart> multipleXYChart = ConnectionDB.getInstance().reporte_por_fecha("",
                                datePicker_FechaI.getValue().toString() +" 00:00:00",
                                datePicker_FechaF.getValue().toString() +" 23:59:59",
                                "",
                                "", isEarningsReport);
                        multipleXYChart.forEach(multiple_xyChart -> {
                            //Genero un chart para cada sucursal
                            final NumberAxis xAxis = new NumberAxis();
                            final NumberAxis yAxis = new NumberAxis();
                            xAxis.setLabel("Mes");

                            xAxis.setAutoRanging(false);
                            xAxis.setLowerBound(multiple_xyChart.getMinValue());
                            xAxis.setUpperBound(multiple_xyChart.getMaxValue());
                            xAxis.setTickUnit(1);
                            xAxis.setMinorTickVisible(false);

                            yAxis.setLabel((String)toggleGroup.getSelectedToggle().getUserData());
                            final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
                            lineChart.setMinWidth(800);
                            lineChart.setTitle("Reporte de "+toggleGroup.getSelectedToggle().getUserData()+": 2018");
                            //Por cada serie
                            multiple_xyChart.getSeriesList().forEach(series -> {
                                lineChart.getData().add(series);
                            });
                            hBox_Charts.getChildren().add(lineChart);

                        });
                    }
                }
            }
        }else{
            //Es necesario hacer un PieChart sin usar fechas

            if (tbtn_Producto.isSelected()){
                if (tbtn_Subsidiary.isSelected()){
                    //Se desea hacer por producto por sucursal [Varios Charts]
                    ArrayList<Multiple_PieChart> multiplePieChart = ConnectionDB.getInstance().reporte_por_producto_por_sucursal(
                            comboBox_Producto.getItems().get(comboBox_Producto.getSelectionModel().getSelectedIndex()).getProductID(),
                            "","","",
                            comboBox_Subsidiary.getItems().get(comboBox_Subsidiary.getSelectionModel().getSelectedIndex()).getID(),isEarningsReport);
                    multiplePieChart.forEach(multiple_pieChart -> {
                        //Genero un chart para cada sucursal
                        System.out.println(multiple_pieChart.getName());
                        PieChart chart = new PieChart(multiple_pieChart.getPieChartDataList());
                        chart.setMinWidth(800);
                        chart.setTitle("Sucursal " + multiple_pieChart.getName());
                        chart.setLegendSide(Side.LEFT);

                        for (final PieChart.Data data : chart.getData()) {
                            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                                    e -> {
                                        caption.setText(String.valueOf(data.getPieValue()));
                                    });
                        }
                        hBox_Charts.getChildren().add(chart);
                    });


                }else if(tbtn_Manager.isSelected()){
                    //Se desea hacer por producto por gerente [Varios Charts]
                    ArrayList<Multiple_PieChart> multiplePieChart = ConnectionDB.getInstance().reporte_por_producto_por_gerente(
                            comboBox_Producto.getItems().get(comboBox_Producto.getSelectionModel().getSelectedIndex()).getProductID(),
                            "","",
                            comboBox_Manager.getItems().get(comboBox_Manager.getSelectionModel().getSelectedIndex()).getUserID(),
                            "",isEarningsReport);
                    multiplePieChart.forEach(multiple_pieChart -> {
                        //Genero un chart para cada sucursal
                        System.out.println(multiple_pieChart.getName());
                        PieChart chart = new PieChart(multiple_pieChart.getPieChartDataList());
                        chart.setMinWidth(800);
                        chart.setTitle("Gerente: " + multiple_pieChart.getName());
                        chart.setLegendSide(Side.LEFT);

                        for (final PieChart.Data data : chart.getData()) {
                            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                                    e -> {
                                        caption.setText(String.valueOf(data.getPieValue()));
                                    });
                        }
                        hBox_Charts.getChildren().add(chart);
                    });
                }else{
                    //Se desea hacer un chart por productos solamente
                    ObservableList<PieChart.Data> pieChartData;
                    pieChartData = ConnectionDB.getInstance().reporte_por_producto(
                            comboBox_Producto.getItems().get(comboBox_Producto.getSelectionModel().getSelectedIndex()).getProductID(),
                            "","","", "",isEarningsReport);
                    PieChart chart = new PieChart(pieChartData);
                    chart.setMinWidth(800);
                    chart.setTitle("Reporte de " + toggleGroup.getSelectedToggle().getUserData());
                    chart.setLegendSide(Side.LEFT);

                    for (final PieChart.Data data : chart.getData()) {
                        data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                                e -> {
                                    caption.setText(String.valueOf(data.getPieValue()));
                                });
                    }
                    hBox_Charts.getChildren().add(chart);
                }
            }else{
                //No se desea usar productos para este reporte
                if (tbtn_Subsidiary.isSelected()){
                    //Se desea hacer por sucursal
                    ObservableList<PieChart.Data> pieChartData;
                    pieChartData = ConnectionDB.getInstance().reporte_por_sucursal("", "","","",
                            comboBox_Subsidiary.getItems().get(comboBox_Subsidiary.getSelectionModel().getSelectedIndex()).getID(),isEarningsReport);
                    PieChart chart = new PieChart(pieChartData);
                    chart.setMinWidth(800);
                    chart.setTitle("Reporte de " + toggleGroup.getSelectedToggle().getUserData());
                    chart.setLegendSide(Side.LEFT);

                    for (final PieChart.Data data : chart.getData()) {
                        data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                                e -> {
                                    caption.setText(String.valueOf(data.getPieValue()));
                                });
                    }
                    hBox_Charts.getChildren().add(chart);
                }else if(tbtn_Manager.isSelected()){
                    //Se desea hacer por gerente
                    ObservableList<PieChart.Data> pieChartData;
                    pieChartData = ConnectionDB.getInstance().reporte_por_gerente("", "","",
                            comboBox_Manager.getItems().get(comboBox_Manager.getSelectionModel().getSelectedIndex()).getUserID()
                            , "",isEarningsReport);
                    PieChart chart = new PieChart(pieChartData);
                    chart.setMinWidth(800);
                    chart.setTitle("Reporte de " + toggleGroup.getSelectedToggle().getUserData());
                    chart.setLegendSide(Side.LEFT);

                    for (final PieChart.Data data : chart.getData()) {
                        data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                                e -> {
                                    caption.setText(String.valueOf(data.getPieValue()));
                                });
                    }
                    hBox_Charts.getChildren().add(chart);
                }else {
                    //Se desea hacer un chart por las ventas totales - Es un solo número
                    ObservableList<PieChart.Data> pieChartData;
                    pieChartData = ConnectionDB.getInstance().reporte_total("", "","", "", "", isEarningsReport);
                    PieChart chart = new PieChart(pieChartData);
                    chart.setMinWidth(800);
                    chart.setTitle("Reporte de " + toggleGroup.getSelectedToggle().getUserData());
                    chart.setLegendSide(Side.LEFT);

                    for (final PieChart.Data data : chart.getData()) {
                        data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                                e -> {
                                    caption.setText(String.valueOf(data.getPieValue()));
                                });
                    }
                    hBox_Charts.getChildren().add(chart);
                }
            }

        }
    }


    private void changeTbtnColor (ToggleButton tbtn){
        if(tbtn.isSelected()){
            tbtn.setStyle("-fx-base: green");
        }else{
            tbtn.setStyle("-fx-base: red");
        }
    }

    @FXML
    void GoBack(ActionEvent event) {
        try{
            FXRouter.goTo("MenuAdm");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
