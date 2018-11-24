package Model;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class Multiple_PieChart {
    private ObservableList<PieChart.Data> pieChartDataList;
    private String name;

    Multiple_PieChart(String name, ObservableList<PieChart.Data> pieChartDataList){
        this.name = name;
        this.pieChartDataList = pieChartDataList;
    }

    Multiple_PieChart(){
        this.name = null;
        this.pieChartDataList = null;
    }

    public ObservableList<PieChart.Data> getPieChartDataList(){
        return pieChartDataList;
    }

    public String getName(){
        return name;
    }

    public void setPieChartDataList(ObservableList<PieChart.Data> pieChartDataList){
        this.pieChartDataList = pieChartDataList;
    }

    public void setName(String name){
        this.name = name;
    }
}
