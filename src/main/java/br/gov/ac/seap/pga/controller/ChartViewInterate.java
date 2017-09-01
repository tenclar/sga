package br.gov.ac.seap.pga.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.annotation.Scope;

@ManagedBean
@Scope("view")
public class ChartViewInterate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private BarChartModel barModel;
    private PieChartModel pieModel1;
 
    @PostConstruct
    public void init() {
        createBarModels();
        createPieModels();
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
 
    private void createBarModels() {
        createBarModel();
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Gráfico em Barras");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Melancia");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Mandioca");
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
 
    private void createPieModels() {
        createPieModel1();
    }
     
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
         
        pieModel1.set("Café 1", 540);
        pieModel1.set("Melancia 2", 325);
        pieModel1.set("Mandioca 3", 702);
        pieModel1.set("Milho", 421);
         
        pieModel1.setTitle("Gráfico em Pizza");
        pieModel1.setLegendPosition("w");
    }
 
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
}
