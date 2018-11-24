package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    @FXML
    protected void initialize(){
        //HBox for Charts
        hBox_Charts.setAlignment(Pos.CENTER);
        //Label Caption
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 24 sansserif");
        //Radio ButtonsanchorPaneChart
        toggleGroup = new ToggleGroup();
        rbtn_Sales.setToggleGroup(toggleGroup);
        rbtn_Sales.setSelected(true);
        rbtn_Sales.setUserData("ventas");
        rbtn_Earnings.setToggleGroup(toggleGroup);
        rbtn_Earnings.setUserData("ganancias");
        //Product List
        ObservableList<Product> product_list = FXCollections.observableArrayList();
        product_list.add(new Product("Todos"));
        product_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Comida"));
        product_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Bebida"));
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
        subsidiary_list.add(new Sucursal("Todas"));
        subsidiary_list.addAll(ConnectionDB.getInstance().selectSucursales());
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
        //Manager ComboBox

        //ToogleButtons
        tbtn_Producto.setOnAction(event -> {
            changeTbtnColor(tbtn_Producto);
        });

        tbtn_Date.setOnAction(event -> {
            changeTbtnColor(tbtn_Date);
        });

        tbtn_Subsidiary.setOnAction(event -> {
            changeTbtnColor(tbtn_Subsidiary);
        });

        tbtn_Manager.setOnAction(event -> {
            changeTbtnColor(tbtn_Manager);
        });
    }

    private void setComboBox(ComboBox comboBox, Callback cb, ObservableList obsList){
        comboBox.setButtonCell((ListCell) cb.call(null));
        comboBox.setCellFactory(cb);
        comboBox.setItems(obsList);
    }


    @SuppressWarnings("Duplicates")
    public void createChart(){
        if(!hBox_Charts.getChildren().isEmpty()){
            hBox_Charts.getChildren().clear();
            caption.setTranslateX(0);
            caption.setTranslateY(0);
            caption.setText("");
        }
        //Acá empieza lo chido
        if (tbtn_Date.isSelected()){
            //Es necesario hacer un LineChart
            if(tbtn_Producto.isSelected()){
                if (tbtn_Subsidiary.isSelected()){
                    //Se desea hacer por producto por sucursal por fecha [Varios Charts] [NO SE PUEDE TODOS PRODUCTOS]

                }else if(tbtn_Manager.isSelected()){
                    //Se desea hacer por producto por gerente por fecha [Varios Charts] [NO SE PUEDE TODOS PRODUCTOS]

                }else{
                    //Se desea hacer un chart por productos por fecha [NO SE PUEDE TODOS PRODUCTOS]

                }
            }else{
                if (tbtn_Subsidiary.isSelected()){
                    //Se desea hacer por sucursal por fecha [NO SE PUEDE TODAS SUCURSALES]

                }else if(tbtn_Manager.isSelected()){
                    //Se desea hacer por gerente por fecha [NO SE PUEDE TODOS GERENTES]

                }else{
                    //Se desea hacer un chart por fecha [TOTAL]

                }
            }
            final NumberAxis xAxis = new NumberAxis();
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Number of Month");
            //creating the chart
            final LineChart<Number,Number> lineChart =
                    new LineChart<Number,Number>(xAxis,yAxis);

            lineChart.setTitle("Reporte de " + toggleGroup.getSelectedToggle().getUserData());
            XYChart.Series series = new XYChart.Series();
            series.setName("My portfolio");
            //populating the series with data
            series.getData().add(new XYChart.Data(1, 23));
            series.getData().add(new XYChart.Data(2, 14));
            series.getData().add(new XYChart.Data(3, 15));
            series.getData().add(new XYChart.Data(4, 24));
            series.getData().add(new XYChart.Data(5, 34));
            series.getData().add(new XYChart.Data(6, 36));
            series.getData().add(new XYChart.Data(7, 22));
            series.getData().add(new XYChart.Data(8, 45));
            series.getData().add(new XYChart.Data(9, 43));
            series.getData().add(new XYChart.Data(10, 17));
            series.getData().add(new XYChart.Data(11, 29));
            series.getData().add(new XYChart.Data(12, 25));
            lineChart.getData().add(series);
            hBox_Charts.getChildren().add(lineChart);
        }else{
            //Es necesario hacer un PieChart sin usar fechas

            if (tbtn_Producto.isSelected()){
                if (tbtn_Subsidiary.isSelected()){
                    //Se desea hacer por producto por sucursal [Varios Charts]
                    ArrayList<Multiple_PieChart> multiplePieChart = ConnectionDB.getInstance().reporte_por_producto_por_sucursal(
                            comboBox_Producto.getItems().get(comboBox_Producto.getSelectionModel().getSelectedIndex()).getProductID(),
                            "","","",
                            comboBox_Subsidiary.getItems().get(comboBox_Subsidiary.getSelectionModel().getSelectedIndex()).getID());
                    multiplePieChart.forEach(multiple_pieChart -> {
                        //Genero un chart para cada sucursal
                        System.out.println(multiple_pieChart.getName());
                        PieChart chart = new PieChart(multiple_pieChart.getPieChartDataList());
                        chart.setTitle("Sucursal " + multiple_pieChart.getName());
                        chart.setLegendSide(Side.LEFT);

                        for (final PieChart.Data data : chart.getData()) {
                            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                                    e -> {
                                        caption.setTranslateX(e.getSceneX());
                                        caption.setTranslateY(e.getSceneY());
                                        caption.setText(String.valueOf(data.getPieValue()));
                                    });
                        }
                        hBox_Charts.getChildren().add(chart);
                    });


                }else if(tbtn_Manager.isSelected()){
                    //Se desea hacer por producto por gerente [Varios Charts]

                }else{
                    //Se desea hacer un chart por productos solamente
                    ObservableList<PieChart.Data> pieChartData;
                    pieChartData = ConnectionDB.getInstance().reporte_por_producto(
                            comboBox_Producto.getItems().get(comboBox_Producto.getSelectionModel().getSelectedIndex()).getProductID(),
                            "","","", "");
                    PieChart chart = new PieChart(pieChartData);
                    chart.setTitle("Reporte de " + toggleGroup.getSelectedToggle().getUserData());
                    chart.setLegendSide(Side.LEFT);

                    for (final PieChart.Data data : chart.getData()) {
                        data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                                e -> {
                                    caption.setTranslateX(e.getSceneX());
                                    caption.setTranslateY(e.getSceneY());
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
                            comboBox_Subsidiary.getItems().get(comboBox_Subsidiary.getSelectionModel().getSelectedIndex()).getID());
                    PieChart chart = new PieChart(pieChartData);
                    chart.setTitle("Reporte de " + toggleGroup.getSelectedToggle().getUserData());
                    chart.setLegendSide(Side.LEFT);

                    for (final PieChart.Data data : chart.getData()) {
                        data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                                e -> {
                                    caption.setTranslateX(e.getSceneX());
                                    caption.setTranslateY(e.getSceneY());
                                    caption.setText(String.valueOf(data.getPieValue()));
                                });
                    }
                    hBox_Charts.getChildren().add(chart);
                }else if(tbtn_Manager.isSelected()){
                    //Se desea hacer por gerente

                }else {
                    //Se desea hacer un chart por las ventas totales - Es un solo número

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
}
