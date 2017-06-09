import java.io.File;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Connect {
	//屏幕的宽高
	private int width;
	private int height;
    private AppiumDriver driver;
   


    public Connect()throws Exception {  
    	

        File classpathRoot = new File(System.getProperty("user.dir"));  
        File appDir = new File(classpathRoot, "apps");  
        System.out.println(appDir);
        File app = new File(appDir, "xinwenzhuan-production-1.1.3.2.apk");  
        
        DesiredCapabilities capabilities = new DesiredCapabilities();  
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");  

        capabilities.setCapability("platformName", "Android");  
        capabilities.setCapability("deviceName","621QECPR2LBAD");  

        capabilities.setCapability("platformVersion", "4.4");  
        capabilities.setCapability("app", app.getAbsolutePath());  
        capabilities.setCapability("newCommandTimeout", "300");  //设置收到下一条命令的超时时间,超时appium会自动关闭session ,默认60秒
        capabilities.setCapability("noReset", true); //不需要再次安装
        capabilities.setCapability("appPackage", "com.coohua.xinwenzhuan"); 

        capabilities.setCapability("appActivity", ".controller.MainActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);  
        
       
        
        

        //获得屏幕的宽高
        height = driver.manage().window().getSize().height;
        width = driver.manage().window().getSize().width;
        System.out.println("启动程序，等待12s");
        Thread.sleep(12000);

    }
    
    //提供driver返回函数
    public AppiumDriver getDriver(){
    	return driver;
    }
    //提供屏幕的宽和高
    public int getWidth(){
    	return width;
    }
    public int getHeight(){
    	return height;
    }
    
    public static void main(String[] args){
    	try {
			Connect con = new Connect();
			TestCases tc = new TestCases(con);
			
			tc.count_redbag();
			while(true){
//				tc.get_read_praise();
//				tc.get_down_praise();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}

















