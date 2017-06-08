import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class MyMethod {
	
	
	SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	private Connect con;
	private AppiumDriver driver;
	private int width,height;
	public MyMethod(Connect con){
		this.con = con;
		driver = con.getDriver();
		width = con.getWidth();
		height = con.getHeight();
	}
	//��ӡ��Ϣ
	public void echo(String msg){

		System.out.println(df.format(new Date())+":"+msg);// new Date()Ϊ��ȡ��ǰϵͳʱ��

	}
	//�ȴ�����
	public void slp(int times){
		echo("wait for "+times+"s����");
		try {
			Thread.sleep(times*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�����ĵ㻬��
    public void my_swipe(int direction,double range){
    	if(range > 0.48) range = 0.48;
    	if(range < 0) range = 0;


    	switch(direction){
    	case 0:
    		echo("���ϻ����ˣ�"+(int)(height*range)+"����");
    		driver.swipe(width/2, height/2, width/2, (int)(height/2-height*range), 200);
    		break;
    	case 1:
    		echo("���һ����ˣ�"+(int)(width*range)+"����");
    		driver.swipe(width/2, height/2,(int)(width/2+width*range),height/2 , 200);
    		break;
    	case 2:
    		echo("���»����ˣ�"+(int)(height*range)+"����");
    		driver.swipe(width/2, height/2,width/2,(int)(height/2+height*range), 200);
    		break;
    	case 3:
    		echo("���󻬶��ˣ�"+(int)(width*range)+"����");
    		driver.swipe(width/2,height/2,(int)(width/2-width*range),height/2, 200);
    		break;
    	}
    }
    //�ж�xpathԪ���Ƿ����
    public boolean is_exist(String xpath){
    	try{
    		driver.findElementByXPath(xpath);
    		return true;
    	}catch(Exception e){
    		return false;
    	}
    	
    }
    //��ȡԪ������
    public String get_el_attr(String xpath,String attr){
    	return driver.findElementByXPath(xpath).getAttribute(attr);
    }
    //��װִ��cmd����
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
    //��װ���
    public void my_click(String xpath){
    	driver.findElementByXPath(xpath).click();
    }
    

}
