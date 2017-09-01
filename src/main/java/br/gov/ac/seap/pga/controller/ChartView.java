package br.gov.ac.seap.pga.controller;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.context.annotation.Scope;
 
@ManagedBean
@Scope("view")
public class ChartView implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    private CartesianChartModel combinedModel;
    private LineChartModel lineModel1;
    private LineChartModel lineModel2;
    private LineChartModel areaModel;
    
    @PostConstruct
    public void init() {
        createAnimatedModels();
        createCombinedModel();
        createLineModels();
        createAreaModel();
        
    }
 
    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }
 
    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }
    
    public CartesianChartModel getCombinedModel() {
        return combinedModel;
    }
    
    public LineChartModel getLineModel1() {
        return lineModel1;
    }
 
    public LineChartModel getLineModel2() {
        return lineModel2;
    }
    
    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Gráfico Linear");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
         
        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Gráfico em Barras");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
     
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Banana");
        boys.set("2015", 120);
        boys.set("2015", 100);
        boys.set("2016", 44);
        boys.set("2016", 150);
        boys.set("2017", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Café");
        girls.set("2015", 52);
        girls.set("2015", 60);
        girls.set("2016", 110);
        girls.set("2016", 135);
        girls.set("2017", 120);
        
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Banana");
 
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Melancia");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
 
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
    
    
    private void createCombinedModel() {
        combinedModel = new BarChartModel();
 
        BarChartSeries boys = new BarChartSeries();
        boys.setLabel("Mandioca");
 
        boys.set("2014", 120);
        boys.set("2015", 100);
        boys.set("2016", 44);
        boys.set("2017", 150);
        boys.set("2017", 25);
 
        LineChartSeries girls = new LineChartSeries();
        girls.setLabel("Café");
 
        girls.set("2015", 52);
        girls.set("2016", 60);
        girls.set("2016", 110);
        girls.set("2017", 135);
        girls.set("2017", 120);
 
        combinedModel.addSeries(boys);
        combinedModel.addSeries(girls);
         
        combinedModel.setTitle("Barra e Linha");
        combinedModel.setLegendPosition("ne");
        combinedModel.setMouseoverHighlight(false);
        combinedModel.setShowDatatip(false);
        combinedModel.setShowPointLabels(true);
        Axis yAxis = combinedModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
    
    
    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Gráfico Linear");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
         
        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Gráfico em Categoria");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Anos"));
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Café");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
     
    
     
    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Banana");
        boys.set("2015", 120);
        boys.set("2015", 100);
        boys.set("2016", 44);
        boys.set("2016", 150);
        boys.set("2017", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Café");
        girls.set("2015", 52);
        girls.set("2015", 60);
        girls.set("2016", 110);
        girls.set("2016", 135);
        girls.set("2017", 120);
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
    public LineChartModel getAreaModel() {
        return areaModel;
    }
     
    private void createAreaModel() {
        areaModel = new LineChartModel();
 
        LineChartSeries boys = new LineChartSeries();
        boys.setFill(true);
        boys.setLabel("Melancia");
        boys.set("2014", 120);
        boys.set("2015", 100);
        boys.set("2016", 44);
        boys.set("2017", 150);
        boys.set("2018", 205);
 
        LineChartSeries girls = new LineChartSeries();
        girls.setFill(true);
        girls.setLabel("Mandioca");
        girls.set("2014", 52);
        girls.set("2014", 60);
        girls.set("2015", 110);
        girls.set("2015", 90);
        girls.set("2016", 120);
 
        areaModel.addSeries(boys);
        areaModel.addSeries(girls);
         
        areaModel.setTitle("Gráfico de Área");
        areaModel.setLegendPosition("ne");
        areaModel.setStacked(true);
        areaModel.setShowPointLabels(true);
         
        Axis xAxis = new CategoryAxis("Anos");
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(300);
    }
}
