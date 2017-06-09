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
			
			mm.my_swipe(0, 0.2);
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
		
		mm.my_swipe(0, 0.2);
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
			}
			xpath = "//android.widget.TextView[@text='安装']";
			if(mm.is_exist(xpath)){
				mm.echo("找到‘安装’按钮，点击进行下载");
				mm.my_click(xpath);
				mm.slp(5);
				//容错
				if(mm.is_exist("//android.widget.TextView[@text='去设置']")){
					mm.my_click("//android.widget.TextView[@text='去设置']");
					mm.slp(3);
					//找到酷划新闻标识，打开开关
					mm.my_always_search("//android.widget.TextView[@text='酷划新闻赚']", 3);
					mm.my_click("//android.widget.TextView[@text='酷划新闻赚']");
					if(mm.is_exist("//android.widget.Button[@text='确定']")){
						mm.my_click("//android.widget.Button[@text='确定']");
						mm.cmd(Cmd.BACK);
						
					}else{
						mm.echo("异常，为找到确定按钮");
					}
					//打开后，继续操作
					mm.my_click(xpath);
				}
				//点击安装按钮进行软件安装
				mm.my_click("//android.widget.Button[@resource-id='com.android.packageinstaller:id/ok_button']");
				mm.slp(55);
				
				

				
			}
			
			String cmd = "adb shell input keyevent 4";
			mm.cmd(cmd);
			mm.echo("返回feed流页面");
			mm.slp(2);
		}
		mm.echo("向上滑动0.2屏幕高度");
		
		mm.my_swipe(0, 0.2);
		mm.slp(2);
	}
	
	
	
}
