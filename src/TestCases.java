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
	//������������Ѷҳ��鿴��������Ƿ�һ��

	public void change_tab(){
		mm.echo("��������1���л���Ѷtab");
		
	}
	public void count_redbag(){
		mm.echo("��Ѷҳ��������Ƿ��뽱������һ��");
		String xpath = "//android.widget.TextView[@resource-id='com.coohua.xinwenzhuan:id/tab_rcmd_redbag_count']";
		int num = Integer.parseInt(mm.get_el_attr(xpath, "text"));   //��ǰ��������������������ж�
		if(mm.is_exist(xpath))
			mm.echo("��ǰ��������ǣ�"+ num);
		//�жϵ�Ǯҳ���Ƿ���ڽ�����ʶ
		String text = "";
		int count_num = 0;
		int count_swipe = 0;
		boolean pass = false;     //�����Ƿ�ͨ����Ĭ��Ϊͨ��
		for(int i=0;i<300;i++){   
			xpath = "//android.widget.TextView[@resource-id='com.coohua.xinwenzhuan:id/tab_feed_item_img_ad_credit']/preceding-sibling::*";
			if(mm.is_exist(xpath)){   //������н�����ʶ�ĸ�Ԫ�أ���ʵ�����������б�ʶ�ķ���
				if(!driver.findElementByXPath(xpath).getText().equals(text)){
					count_num++;
					text = driver.findElementByXPath(xpath).getText();
					mm.echo("�ҵ�1������,��ǰ�ҵ�"+count_num);
					if(count_num==num){
						mm.echo("��ǰ�����Ѿ�ȫ���ҵ�������");
						i = 285;
						pass = true;
						
					}
					if(count_num>num){
						mm.echo("���Ͷ�Ŷ���tab����ʾ�����󣬴��󣡣�������������");
						break;
					}
				}
		
			}
			//�����ҵ���񣬶����ϻ���0.2����
			
			mm.my_swipe(0, 0.2);
			count_swipe++;
			mm.echo("��ǰ���ҵ���"+count_num+"������;"+"�ƶ��ˣ�"+count_swipe+"��");
			
			
			if(count_swipe==300 && count_num<num){
				mm.echo("����300��δ��ȫ���������ʧ�ܣ������쳣");
				break;
			}
			mm.slp(3);
		}
		//�ж����passΪFALSE˵����;���������ִ�������δͨ��
		mm.echo(pass?"�������������ͨ�������쳣":"��������쳣������ʧ�ܣ��������־");
		mm.echo("��ת��Ѷҳ����");
		driver.findElementByXPath("//android.widget.TextView[@text='�Ƽ�']").click();
		
	}
	
	//�Ķ����͹����ȡ����
	public void get_read_praise(){
		String xpath = "//android.widget.TextView[@text='�Ķ���ȡ']/parent::*";
		if(mm.is_exist(xpath)){
			mm.echo("��������Ķ����������Ĺ��");
			driver.findElementByXPath(xpath).click();
			mm.slp(8);
			String cmd = "adb shell input keyevent 4";
			mm.cmd(cmd);
			mm.echo("����feed��ҳ��");
			mm.slp(2);
		}
		mm.echo("���ϻ���0.2��Ļ�߶�");
		
		mm.my_swipe(0, 0.2);
		mm.slp(2);
	}
	//�������潱����ȡ
	public void get_down_praise(){
		String xpath = "//android.widget.TextView[@text='����������ȡ']/parent::*";
		if(mm.is_exist(xpath)){
			mm.echo("����������ؽ��������Ĺ��");
			driver.findElementByXPath(xpath).click();
			mm.slp(3);
			xpath = "//android.widget.TextView[@text='��������']";
			if(mm.is_exist(xpath)){
				mm.echo("�ҵ����������ء���ť�������������");
				mm.my_click(xpath);
				mm.slp(5);
			}
			xpath = "//android.widget.TextView[@text='��װ']";
			if(mm.is_exist(xpath)){
				mm.echo("�ҵ�����װ����ť�������������");
				mm.my_click(xpath);
				mm.slp(5);
				//�ݴ�
				if(mm.is_exist("//android.widget.TextView[@text='ȥ����']")){
					mm.my_click("//android.widget.TextView[@text='ȥ����']");
					mm.slp(3);
					//�ҵ��Ữ���ű�ʶ���򿪿���
					mm.my_always_search("//android.widget.TextView[@text='�Ữ����׬']", 3);
					mm.my_click("//android.widget.TextView[@text='�Ữ����׬']");
					if(mm.is_exist("//android.widget.Button[@text='ȷ��']")){
						mm.my_click("//android.widget.Button[@text='ȷ��']");
						mm.cmd(Cmd.BACK);
						
					}else{
						mm.echo("�쳣��Ϊ�ҵ�ȷ����ť");
					}
					//�򿪺󣬼�������
					mm.my_click(xpath);
				}
				//�����װ��ť���������װ
				mm.my_click("//android.widget.Button[@resource-id='com.android.packageinstaller:id/ok_button']");
				mm.slp(55);
				
				

				
			}
			
			String cmd = "adb shell input keyevent 4";
			mm.cmd(cmd);
			mm.echo("����feed��ҳ��");
			mm.slp(2);
		}
		mm.echo("���ϻ���0.2��Ļ�߶�");
		
		mm.my_swipe(0, 0.2);
		mm.slp(2);
	}
	
	
	
}
