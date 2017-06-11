import org.openqa.selenium.Dimension;

import io.appium.java_client.AppiumDriver;

public class TestCases {

	private Connect con;
	private AppiumDriver driver;
	private MyMethod mm;

	public TestCases(Connect con){
		this.con = con;
		driver = con.getDriver();
		mm = new MyMethod(con);
	}
	//测试用例：资讯页面查看红包数量是否一致

	public void change_tab(){
		mm.echo("测试用例1：切换资讯tab");
		
	}
	public void count_redbag(){
		//确定滑动位置
        Dimension d1 = driver.findElementByXPath("//android.widget.RelativeLayout[@resource-id='com.coohua.xinwenzhuan:id/xlxl_actionbar']").getSize();
        
		int startY =  con.getHeight() - d1.height - 1;
		int startX = 20;
		Dimension d2 = driver.findElementByXPath("//android.widget.HorizontalScrollView[@resource-id='com.coohua.xinwenzhuan:id/home_tab']").getSize();
		int endX = 20;
		int endY = d2.height;
		Dimension d3 = driver.findElementByXPath("//android.widget.LinearLayout[@resource-id='com.coohua.xinwenzhuan:id/home_feed_tab_parent']").getSize();
		endY = endY + d3.height + 10;

		mm.echo("资讯页红包数量是否与奖励数量一致");
		String xpath = "//android.widget.TextView[@resource-id='com.coohua.xinwenzhuan:id/tab_rcmd_redbag_count']";
		int num = Integer.parseInt(mm.get_el_attr(xpath, "text"));   //当前红包数量，后面用来做判断
		if(mm.is_exist(xpath))
			mm.echo("当前红包数量是："+ num);
		//判断单钱页面是否存在奖励标识
		String text = "";
		int count_num = 0;
		int count_swipe = 0;
		boolean pass = false;     //用例是否通过，默认为通过
		for(int i=0;i<300;i++){   
			xpath = "//android.widget.TextView[@resource-id='com.coohua.xinwenzhuan:id/tab_feed_item_img_ad_credit']/preceding-sibling::*";
			if(mm.is_exist(xpath)){   //如果含有奖励标识的父元素（其实就是整个含有标识的方框）
				if(!driver.findElementByXPath(xpath).getText().equals(text)){
					count_num++;
					text = driver.findElementByXPath(xpath).getText();
					mm.echo("找到1个奖励,当前找到"+count_num);
					if(count_num==num){
						mm.echo("当前奖励已经全部找到！！！");
						i = 285;
						pass = true;
						
					}
					if(count_num>num){
						mm.echo("红包投放多余tab处显示，错误，错误！！！！！！！！");
						break;
					}
				}
		
			}
			//无论找到与否，都向上滑动0.2距离
			
			mm.my_swipe(startX,startY,endX,endY,500);
			count_swipe++;
			mm.echo("当前共找到："+count_num+"个奖励;"+"移动了："+count_swipe+"次");
			
			
			if(count_swipe==300 && count_num<num){
				mm.echo("搜索300次未找全红包，用例失败，出现异常");
				break;
			}
			mm.slp(3);
		}
		//判断如果pass为FALSE说明中途跳出，出现错误，用例未通过
		mm.echo(pass?"数红包数量用例通过，无异常":"红包数量异常，用例失败，详情见日志");
		mm.echo("跳转资讯页顶部");
		driver.findElementByXPath("//android.widget.TextView[@text='推荐']").click();
		
	}
	
	//阅读类型广告领取奖励
	public void get_read_praise(){
		
		//确定滑动位置
        Dimension d = driver.findElementByXPath("//android.widget.RelativeLayout[@resource-id='com.coohua.xinwenzhuan:id/xlxl_actionbar']").getSize();
        
		int startY =  con.getHeight() - d.height - 1;
		int startX = 20;
		d = driver.findElementByXPath("//android.widget.HorizontalScrollView[@resource-id='com.coohua.xinwenzhuan:id/home_tab']").getSize();
		int endX = 20;
		int endY = d.height;
		d = driver.findElementByXPath("//com.coohua.xinwenzhuan[@resource-id='android.support.v7.app.ActionBar$Tab']").getSize();
		endY = endY + d.height + 10;
		
		String xpath = "//android.widget.TextView[@text='阅读领取']/parent::*";
		if(mm.is_exist(xpath)){
			mm.echo("点击含有阅读奖励字样的广告");
			driver.findElementByXPath(xpath).click();
			mm.slp(8);
			String cmd = "adb shell input keyevent 4";
			mm.cmd(cmd);
			mm.echo("返回feed流页面");
			mm.slp(2);
		}
		mm.echo("向上滑动0.2屏幕高度");
		
		mm.my_swipe(startX,startY,endX,endY,500);
		mm.slp(2);
	}
	//下载类广告奖励领取
	public void get_down_praise(){
		String xpath = "//android.widget.TextView[@text='下载试玩领取']/parent::*";
		if(mm.is_exist(xpath)){
			mm.echo("点击含有下载奖励字样的广告");
			driver.findElementByXPath(xpath).click();
			mm.slp(3);
			xpath = "//android.widget.TextView[@text='点我下载']";
			if(mm.is_exist(xpath)){
				mm.echo("找到‘点我下载’按钮，点击进行下载");
				mm.my_click(xpath);
				mm.slp(5);
				deal_err(xpath);
				//点击安装按钮进行软件安装
				mm.my_click("//android.widget.Button[@resource-id='com.android.packageinstaller:id/ok_button']");
				mm.slp(55);
				
			}
			xpath = "//android.widget.TextView[@text='安装']";
			if(mm.is_exist(xpath)){
				mm.echo("找到‘安装’按钮，点击进行下载");
				mm.my_click(xpath);
				mm.slp(5);
				//容错
//				if(mm.is_exist("//android.widget.TextView[@text='去设置']")){
//					mm.my_click("//android.widget.TextView[@text='去设置']");
//					mm.slp(3);
//					//找到酷划新闻标识，打开开关
//					mm.my_always_search("//android.widget.TextView[@text='酷划新闻赚']", 3);
//					mm.my_click("//android.widget.TextView[@text='酷划新闻赚']");
//					if(mm.is_exist("//android.widget.Button[@text='确定']")){
//						mm.my_click("//android.widget.Button[@text='确定']");
//						mm.cmd(Cmd.BACK);
//						
//					}else{
//						mm.echo("异常，为找到确定按钮");
//					}
//					//打开后，继续操作
//					mm.my_click(xpath);
//					
//				}
				deal_err(xpath);
				//点击安装按钮进行软件安装
				mm.my_click("//android.widget.Button[@resource-id='com.android.packageinstaller:id/ok_button']");
				mm.slp(55);
				//检测是否安装成功
				

			}
			
			String cmd = "adb shell input keyevent 4";
			mm.cmd(cmd);
			mm.echo("返回feed流页面");
			mm.slp(2);
		}
		mm.echo("向上滑动0.2屏幕高度");
		
		mm.my_swipe(0, 0.15);
		mm.slp(2);
	}
	
	//下载类游戏，容错封装
	private void deal_err(String xpath){
		if(mm.is_exist("//android.widget.TextView[@text='去设置']")){
			mm.my_click("//android.widget.TextView[@text='去设置']");
			mm.echo("点击去设置按钮，等待3s");
			mm.slp(3);
			//找到酷划新闻标识，打开开关
			mm.my_always_search("//android.widget.TextView[@text='酷划新闻赚']", 5);
//			mm.my_click("//android.widget.TextView[@text='酷划新闻赚']");
			if(mm.is_exist("//android.widget.Button[@resource-id='android:id/button1']")){
				mm.my_click("//android.widget.Button[@resource-id='android:id/button1']");
				mm.echo("确定打开开关，等待3s");
				mm.slp(3);
				mm.cmd(Cmd.BACK);
				mm.echo("等待3s后返回安装界面");
				
			}else{
				mm.echo("异常，未找到确定按钮");
			}
			//打开后，继续操作
			mm.my_click(xpath);
		
		}
	}
}
