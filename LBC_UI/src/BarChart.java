
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;         //导入错了包，导成 了demon.jar.害我一直用不了里面的类
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

//说明，可以输入x,y确定横轴纵的内容，输入一个哈希表数据集，产生相应的直方图
public class BarChart{
	
	ChartPanel	chartPanel;
	private String xLabel;
	private String yLable;
	private Map data;
	
	public  BarChart(String xLabel,String yLabel,Map data){
		setBarChart(xLabel,yLabel,data);		
	}
	
	private void init(){
		//构造函数
		CategoryDataset dataset = getDataSet(xLabel,yLable,data);    //声明一个数据集
		JFreeChart chart = ChartFactory.createBarChart3D(
				"",  //title,
				xLabel,  //categoryAxisLabel,
				yLable,  //valueAxisLabel, 
				dataset,  // 画统计图需要数据集
				PlotOrientation.VERTICAL,//orientation,
				true,   //legend,干嘛用的 ,是否生成图例 
				false,  //tooltips,是否生成工具
				false);  //urls是否生成url
	
		//从这里开始
		CategoryPlot plot=chart.getCategoryPlot();  //获取图表区域对象  
		CategoryAxis domainAxis=plot.getDomainAxis();//水平底部列表  
		domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));  //水平底部标题  
		domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题  
		ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状  
		rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体  
		
		//到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题 
		chartPanel = new ChartPanel(chart,true);
	}
	
	 //getdataset，收集数据集，以作为转化的原材料
     private static CategoryDataset getDataSet(String x, String y,Map data){
    	 HashMap time = new HashMap();
    	  //根据键值排序
    	 
    	  Object[] key = data.keySet().toArray();
    	  Arrays.sort(key);
    	  // 传入的哈希键值为新闻年份
    
    	  DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	  //先把所有年份找出来，再重新按年份找。
    	  for(int i = 0;i<key.length;i++){
    		  Map atagdataset = new HashMap();
    		  atagdataset = (HashMap)data.get(key[i]);
    		  Object[] key2 = atagdataset.keySet().toArray();
    		  Arrays.sort(key2);
    	      for(int j = 0;j<key2.length;j++){    		     		 
    	//	  int defen = (int) atagdataset.get(key2[j]);
    		  String s =(String) key2[j];
    	//	  dataset.addValue(defen,(String)key[i],s);
    	    	  time.put(s,0);
    	      }	      
    	  }
    	 
    	   Object[] timekey = time.keySet().toArray();
    	   Arrays.sort(timekey);
    	   for(int m=0;m<timekey.length;m++){
    			  for(int i = 0;i<key.length;i++){
    	    		  Map atagdataset = new HashMap();
    	    		  atagdataset = (HashMap)data.get(key[i]);
    	    		 
    	    		  
    	    		  
    	    		  Object[] key2 = atagdataset.keySet().toArray();
    	    		  Arrays.sort(key2);
    	    	      for(int j = 0;j<key2.length;j++){
    	    	    
    	    		  int defen = (int) atagdataset.get(key2[j]);
    	    		  String s =(String) key2[j];
    	    		  if(s.equals((String)timekey[m])){
    	    		  dataset.addValue(defen,(String)key[i],s);
    	    		  continue;
    	    		  }
    	    	      }	      
    	    	  }
    	   }
    	  
		  return dataset; 
     }
     
     // 一个对象可以画多次
     public void setBarChart(String xLabel,String yLabel,Map data){
    	this.xLabel = xLabel;
 		this.yLable = yLabel;
 		this.data = data;	
 		init();
     }
          
     public ChartPanel getChartPanel(){
		return chartPanel;
     }   //返回通过构造函数得到的面板
   
     
     
}