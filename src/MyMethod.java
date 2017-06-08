import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class MyMethod {
	
	
	SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");//设置日期格式
	private Connect con;
	private AppiumDriver driver;
	private int width,height;
	public MyMethod(Connect con){
		this.con = con;
		driver = con.getDriver();
		width = con.getWidth();
		height = con.getHeight();
	}
	//打印信息
	public void echo(String msg){

		System.out.println(df.format(new Date())+":"+msg);// new Date()为获取当前系统时间

	}
	//等待函数
	public void slp(int times){
		echo("wait for "+times+"s……");
		try {
			Thread.sleep(times*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//以中心点滑动
    public void my_swipe(int direction,double range){
    	if(range > 0.48) range = 0.48;
    	if(range < 0) range = 0;


    	switch(direction){
    	case 0:
    		echo("向上滑动了："+(int)(height*range)+"像素");
    		driver.swipe(width/2, height/2, width/2, (int)(height/2-height*range), 200);
    		break;
    	case 1:
    		echo("向右滑动了："+(int)(width*range)+"像素");
    		driver.swipe(width/2, height/2,(int)(width/2+width*range),height/2 , 200);
    		break;
    	case 2:
    		echo("向下滑动了："+(int)(height*range)+"像素");
    		driver.swipe(width/2, height/2,width/2,(int)(height/2+height*range), 200);
    		break;
    	case 3:
    		echo("向左滑动了："+(int)(width*range)+"像素");
    		driver.swipe(width/2,height/2,(int)(width/2-width*range),height/2, 200);
    		break;
    	}
    }
    //判断xpath元素是否存在
    public boolean is_exist(String xpath){
    	try{
    		driver.findElementByXPath(xpath);
    		return true;
    	}catch(Exception e){
    		return false;
    	}
    	
    }
    //获取元素属性
    public String get_el_attr(String xpath,String attr){
    	return driver.findElementByXPath(xpath).getAttribute(attr);
    }
    //封装执行cmd命令
    public void cmd(String command){
    	System.out.println(command);

		try {
			Runtime.getRuntime().exec(command).waitFor();
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    //封装点击
    public void my_click(String xpath){
    	driver.findElementByXPath(xpath).click();
    }
    

}
