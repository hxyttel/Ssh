package com.ssh.action;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
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
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Teacher;
import com.ssh.service.StudentService;
import com.ssh.service.TeacherService;
import com.ssh.util.FileUtil;

@SuppressWarnings("serial")
@ParentPackage("jfreechart-default")
@Namespace("/")
public class JFreeTeaStuAction extends ActionSupport{
	private JFreeChart chart;
	private StudentService studentService;
	private TeacherService teacherService;
	
// 必须提供 getChart() 方法，且由该方法返回 JFreeChart 对象
	public JFreeChart getChart() throws Exception {
			
		//JFreeChart类是一个制图对象类，先用它来创建一个制图对象chart
		//ChartFactory类是制图工厂类，用它来为制图对象chart完成实例化
		//createBarChart3D()是制图工厂的一个方法，用来创建一个3D的柱状图对象
		chart = ChartFactory.createBarChart3D(
	    		  
	    		"老师工作状况统计图",                 //图表标题
	            "用户",                        //X轴标题     
	            "学生人数(人)",                        //Y轴标题
	            createDataset(),              //数据集
	            PlotOrientation.VERTICAL,     //绘制方向
	      		true,                         //是否显示图例
	      		false,                        //是否采用标准生成器
	      		false                         //是否支持超链接
	      		);
	      
	    //通过JFreeChart对象的 setTitle方法，修改统计图表的标题部分（包括修改图表标题内容、字体大小等）
	      chart.setTitle(new TextTitle("老师工作状况", new Font("黑体", Font.ITALIC , 22))); 
	      //调用 JFreeChart对象的 getLegend(int index)方法，取得该图表的指定索引的图例对象，通过 LegendTitle对象来修改统计图表的图例  
	      LegendTitle legend = chart.getLegend(0); 
	      //设置图例的字体和字体大小，即位于下方的字的字体和大小
	      legend.setItemFont(new Font("宋体", Font.BOLD, 14));
	      // 设置画布背景色
	      chart.setBackgroundPaint(new Color(204, 204, 204)); 
	      //取得折线图的绘图(plot)对象
	      CategoryPlot plot = chart.getCategoryPlot();
	      //设置数据区的背景透明度，范围在0.0～1.0间
	      plot.setBackgroundAlpha(0.5f);
	      // 设置数据区的前景透明度，范围在0.0～1.0间     
	      plot.setForegroundAlpha(0.7f);     
	      // 设置横轴字体
	      plot.getDomainAxis().setLabelFont(new Font("黑体", Font.BOLD, 14));
	      // 设置坐标轴标尺值字体
		  plot.getDomainAxis().setTickLabelFont(new Font("宋体", Font.BOLD, 12));
		  // 设置纵轴字体
		  plot.getRangeAxis().setLabelFont(new Font("黑体", Font.BOLD, 14));
		  // 设置绘图区背景色
		  plot.setBackgroundPaint(Color.WHITE);
		  // 设置水平方向背景线颜色
		  plot.setRangeGridlinePaint(Color.BLACK);
		  // 设置是否显示水平方向背景线,默认值为true
		  plot.setRangeGridlinesVisible(true);
		  // 设置垂直方向背景线颜色
		  plot.setDomainGridlinePaint(Color.BLACK);
		  // 设置是否显示垂直方向背景线,默认值为false
		  plot.setDomainGridlinesVisible(false);
		  // 横轴上的label斜显示
		  plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45); 
		  // 没有数据时显示的消息
	      plot.setNoDataMessage("没有相关统计数据");
	      plot.setNoDataMessageFont(new Font("黑体", Font.CENTER_BASELINE, 16));
	      plot.setNoDataMessagePaint(Color.RED);
		
	            //显示每个柱的数值，并修改该数值的字体属性
	    		BarRenderer3D renderer = new BarRenderer3D();
	    		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	    		renderer.setBaseItemLabelsVisible(true);
	    		//设置外廓线不可见
	    		renderer.setDrawBarOutline(false);
	    		//设置每个系列直方图中所包含的平行柱的之间距离
	    		//renderer.setItemMargin(0.2);
	    		//去掉柱子的倒影
	    		renderer.setShadowVisible(false);
	    		//默认的数字显示在柱子中，通过如下两句可调整数字的显示
	    		//注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题
	    		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
	    		renderer.setItemLabelAnchorOffset(10D);
	    		//设置每个柱子上的字体
	    		//12号黑体加粗,字体为黑色
	    		renderer.setItemLabelFont(new Font("黑体",Font.BOLD,10)); 
	    		renderer.setItemLabelPaint(Color.black); 
	    		
	    		//改变图表中每个系列直方图的填充颜色
	    		renderer.setSeriesPaint(0, new Color(153, 0, 102));   
	    		renderer.setSeriesPaint(1, Color.orange);
	    		renderer.setSeriesPaint(2, Color.RED);
	    		renderer.setSeriesPaint(3, new Color(0, 0, 204));
	    		
	    		//提交设计的效果
	    		plot.setRenderer(renderer);
			
			//设置生成的图表的文件名
	        String fileName = "TeaStu.jpg";
	        //设置图像输出的指定路径
	        String filePath = FileUtil.getWebRootPath()+"backstage/image/"+fileName;
	        //输出图表到文件
	        saveAsFile(filePath, chart, 800, 300);
	        //取得session对象
	        HttpServletRequest request=ServletActionContext.getRequest();
	       
			//把生成的图表文件的路径filePath放进request对象中
	        request.getSession().setAttribute("filePath", fileName);
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
     * @return CategoryDataset
     */
	public  CategoryDataset createDataset() {
		
		// 类别
		String[] category = { "2017年"};
		
		// 图例名称
		List<Teacher> list = teacherService.getTeacherList();
		
		// 实例化DefaultCategoryDataset对象
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		// 使用循环向数据集合中添加数据
		for(int i=0;i<list.size();i++){
			int id= list.get(i).getTid();
			int count = studentService.getCountTeacher(id);
			String name  = list.get(i).getTname();
			dataset.addValue(count,name,
					"2017年");
		}
		return dataset;
	}
	@Action(value="JFreeTeaStu",results={@Result(name="success",type="chart",location="/backstage/JFreeStuTea.jsp",params={"width","800","height","380"})})
	public String jfreeteastu() {
		return "success";
	}
	
	public StudentService getStudentService() {
		return studentService;
	}
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	public TeacherService getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	

}
