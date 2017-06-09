import javax.swing.JFrame;

public class OperationClient extends JFrame{
	
	public OperationClient(){
		init();
	}
	public void init(){
		 this.setTitle("GreenApple1.0");
		 this.setSize(500,350);
		 this.setLocation(200,200);
		 this.setResizable(false);
		 this.setLayout(null);
		 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 this.setVisible(true);
	}
	
	public static void main(String[] args){
		new OperationClient();
	}
}
