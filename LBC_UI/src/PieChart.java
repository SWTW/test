import java.awt.Font;
import java.awt.Frame;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart {
    ChartPanel frame1;
    public HashMap ThemeDataset= null;
    public PieChart(HashMap dataset){
    	setPieChart(dataset);
    }
    
    public DefaultPieDataset getDataSet(HashMap data){
    	DefaultPieDataset dataset = new DefaultPieDataset();
    	Object[] key = data.keySet().toArray();
    	 for(int i = 0;i<key.length;i++){    		     		 
   		  int num = (int) data.get(key[i]);
   		  String s =(String) key[i];
   		  dataset.setValue(s,num);
   	  }
    	return dataset;
    }
    
    public void init()
    {	DefaultPieDataset data = getDataSet(ThemeDataset);
    	JFreeChart chart = ChartFactory.createPieChart3D("标签比例",data,true,false,false);
    	//shezhebaifenbi
    	PiePlot pieplot =(PiePlot)chart.getPlot();
    	DecimalFormat df = new DecimalFormat("0.00%");
    	NumberFormat nf = NumberFormat.getNumberInstance();
    	StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0} {2}",nf,df);
    	pieplot.setLabelGenerator(sp1);
    	
    	pieplot.setNoDataMessage("NOThingtoShow");
    	pieplot.setCircular(false);
    	pieplot.setLabelGap(0.02D);
    	
    	pieplot.setIgnoreNullValues(true);
    	pieplot.setIgnoreZeroValues(true);
    	frame1= new ChartPanel(chart,true);
    	
    	//jiejueluanma
    	chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));
    	PiePlot piePlot=(PiePlot)chart.getPlot();
    	piePlot.setLabelFont(new Font("宋体",Font.BOLD,10));
    	chart.getLegend().setItemFont(new Font("宋体",Font.BOLD,10));
    }
    public ChartPanel getChartPanel(){
    	return frame1;
    }
    public void setPieChart(HashMap dataset){
    	this.ThemeDataset = dataset;
    	init();
    }
    public HashMap getThemeDataset(){
    	return this.ThemeDataset;
    }
    public static void main(String args[]){
    	HashMap dataset = new HashMap();
    	dataset.put("df", 1);
    	dataset.put("dfd", 2);
    	dataset.put("dfdff", 2);
    	PieChart instance = new PieChart(dataset);
    	ChartPanel pan = instance.getChartPanel();
    	Frame one = new Frame();
    	one.add(pan);
    	one.setSize(400, 400);
    	one.setVisible(true);
    }
}