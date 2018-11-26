package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class Multiple_XYChart {
    private ObservableList<XYChart.Series> seriesList;
    private XYChart.Series series;
    private String name;
    private int month;
    private int minValue;
    private int maxValue;


    Multiple_XYChart(){
        this.name = null;
        this.month = 0;
        this.series = new XYChart.Series();
        this.seriesList = FXCollections.observableArrayList();
        this.minValue = 1;
        this.maxValue = 31;
    }

    public XYChart.Series getXYChartSeries(){
        return series;
    }
    public ObservableList<XYChart.Series> getSeriesList(){return seriesList;}
    public String getName(){
        return name;
    }
    public int getMonth(){return month;}
    public int getMinValue(){return minValue;}
    public int getMaxValue(){return maxValue;}


    public void addXYChartData(XYChart.Data xyChartData){
        series.getData().add(xyChartData);
    }

    public void createNewSeries(){
        series = new XYChart.Series();
    }

    public void addSeries(){
        seriesList.add(series);
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSeriesName(String name){
        this.series.setName(name);
    }

    public void setMonth(int month){
        this.month = month;
    }



}
