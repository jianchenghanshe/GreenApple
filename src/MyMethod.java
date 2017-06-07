import io.appium.java_client.AppiumDriver;

public class MyMethod {
	
	private Connect con;
	public MyMethod(Connect con){
		this.con = con;
	}
	//��ӡ��Ϣ
	public void echo(String msg){
		System.out.println(msg);
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
    	int width = con.getWidth();
    	int height = con.getHeight();
    	AppiumDriver driver = con.getDriver();
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
    

}
