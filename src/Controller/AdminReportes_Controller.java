package Controller;

import Model.ConnectionDB;
import Model.Product;
import Model.Sucursal;
import Model.User;
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
        comboBox_Producto.setButtonCell(cellFactoryProduct.call(null));
        comboBox_Producto.setCellFactory(cellFactoryProduct);
        comboBox_Producto.setItems(product_list);
        FXComboAutoComplete.autoCompleteComboBoxPlus(comboBox_Producto, (typedText, itemToCompare) -> itemToCompare.getProductName().toLowerCase().contains(typedText.toLowerCase()));
        //Subsidiary ComboBox

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

    private ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
                    new PieChart.Data("Grapefruit", 13),
                    new PieChart.Data("Oranges", 25),
                    new PieChart.Data("Plums", 10),
                    new PieChart.Data("Pears", 22),
                    new PieChart.Data("Apples", 30));



    public void createChart(){
        if(!hBox_Charts.getChildren().isEmpty()){
            hBox_Charts.getChildren().clear();
        }
        //Acá empieza lo chido
        if (tbtn_Date.isSelected()){
            //Es necesario hacer un LineChart
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
            //Es necesario hacer un PieChart
            final PieChart chart = new PieChart(pieChartData);
            chart.setTitle("Reporte de " + toggleGroup.getSelectedToggle().getUserData());
            chart.setLegendSide(Side.LEFT);

            for (final PieChart.Data data : chart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                        e -> {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue()));
                        });
                data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED,
                        e -> {
                            caption.setTranslateX(0);
                            caption.setTranslateY(0);
                            caption.setText("");
                        });
            }

            hBox_Charts.getChildren().add(chart);
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
