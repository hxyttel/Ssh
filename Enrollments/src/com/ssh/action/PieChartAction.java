package com.ssh.action;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import com.ssh.pojo.Student;
import com.ssh.service.AcademySerice;
import com.ssh.service.StudentService;
import com.ssh.util.FileUtil;

@ParentPackage("jfreechart-default")
@Namespace("/")
public class PieChartAction {
	private JFreeChart chart;
	private StudentService studentService;
	private AcademySerice academyService;
	

	// 必须提供 getChart() 方法，且由该方法返回 JFreeChart 对象
	public JFreeChart getChart() throws Exception {
		//JFreeChart类是一个制图对象类，先用它来创建一个制图对象chart
		//ChartFactory类是制图工厂类，用它来为制图对象chart完成实例化
		//createPieChart()是制图工厂的一个方法，用来创建一个常规的饼形图对象
        chart = ChartFactory.createPieChart(
        		"学生报名类型统计图",            //图表标题
        		createDataset(),         //数据集
        		true,                    //是否显示图例
        		false,                   //是否采用标准生成器
        		false                    //是否支持超链接
        		);
        
        //通过JFreeChart对象的 setTitle方法，修改统计图表的标题部分（包括修改图表标题内容、字体大小等）
        chart.setTitle(new TextTitle("各所高校人数统计图", new Font("黑体", Font.ITALIC , 22))); 
        //调用 JFreeChart对象的 getLegend(int index)方法，取得该图表的指定索引的图例对象，通过 LegendTitle对象来修改统计图表的图例  
        LegendTitle legend = chart.getLegend(0); 
        //设置图例的字体和字体大小，即位于下方的字的字体和大小
        legend.setItemFont(new Font("宋体", Font.BOLD, 14));
        // 设置画布背景色
        //chart.setBackgroundPaint(new Color(192, 228, 106));
        
        //取得饼图的绘图(plot)对象
        PiePlot plot = (PiePlot)chart.getPlot();
        
        //设置Pie图的分类标签的字体，即位于Pie图的中间部分的每个扇形旁边对应的字的字体
        plot.setLabelFont(new Font("隶书", Font.BOLD, 16));
        //设置数据区的背景透明度，范围在0.0～1.0间
        plot.setBackgroundAlpha(0.9f);
        //设置PieChart是否显示为圆形    
        plot.setCircular(true);
        //完全关闭片区外廓，若将值设为 true 即可让外廓显示出来
        plot.setSectionOutlinesVisible(false);
        //设置忽略零值，不设值时默认会将一个标签放置在饼图片区显示的位置，并且在图表的图例中添加一个分类。
        //plot.setIgnoreZeroValues(true);
        //设置忽略null值，不设值时默认情况跟零值一样
        //plot.setIgnoreNullValues(true);
        
        //设置旋转角度    
        plot.setStartAngle(90.0);    
        //设置旋转方向，Rotation.CLOCKWISE)为顺时针。    
        plot.setDirection(Rotation.CLOCKWISE); 
        
        //设置将某一片区取出，即偏离图表中心，以起到强调作用
        plot.setExplodePercent("北京大学", 0.1);
        
        //设置每个数据区的填充颜色，不设置即默认，默认时是自动分配的
        //plot.setSectionPaint("文学类", new Color(200, 255, 255));        
        
        // 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(),new DecimalFormat("0.00%")));
        // 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例
        //plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));
        
        //设置生成的图表的文件名
        String fileName = "PieChartBook.jpg";
        //设置图表输出的指定路径
        String filePath = FileUtil.getWebRootPath()+"backstage/image/"+fileName;
        //输出图表到文件
        saveAsFile(filePath, chart, 500, 300);
		
        //取得session对象
        HttpServletRequest request=ServletActionContext.getRequest();
       
		//把生成的图表文件的路径filePath放进request对象中
        request.getSession().setAttribute("PiePath", fileName);
        
      /*//取得request对象
        Map request = (Map)ActionContext.getContext().get("request");
		//把生成的图表文件的路径filePath放进request对象中
        request.put("filePath", filePath);*/
        
		return chart;
	}
	
	/**
	 * 保存图表为文件 
	 */
    public static void saveAsFile(String filePath, JFreeChart jfreeChart,      
            int weight, int height) throws IOException { 
        
    	//输出图表到文件，saveCharAsJPEG()方法的参数(File file,JFreeChart chart,int width,int height)
        ChartUtilities.saveChartAsJPEG(new File(filePath), jfreeChart, weight, height);
    }

	/**
     * 创建一个dataset，该dataset包含图表要显示的数据
     * @return DefaultPieDataset
     */
private  DefaultPieDataset createDataset() {
		
		//DefaultPieDataset是默认的饼形图数据集合对象类，可用于创建饼形图数据集合对象
		DefaultPieDataset dataset = new DefaultPieDataset(); 
		//为dataset对象设值
		List<Student> list =studentService.getStudentList();
		for(int i=0;i<list.size();i++){
			String sname = list.get(i).getStype();
			int count = studentService.getCountStype(sname);
			dataset.setValue(sname,count);
		}
		//返回数据集合对象
		return dataset; 
	}
	
	
	//在struts.xml中的对应<action>里，应该写的是  method="pieChart" 和  <result type="chart">
	@Action(value="pieChart",results={@Result(name="success",type="chart",params={"width","800","height","500"},location="/backstage/PieChart.jsp")})
	public String pieChart() {
		return "success";
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public AcademySerice getAcademyService() {
		return academyService;
	}

	public void setAcademyService(AcademySerice academyService) {
		this.academyService = academyService;
	}

	
	
	
	
	
	
}