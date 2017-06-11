import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

public class OperationClient extends JFrame{
	
	JPopupMenu pop;

	JMenuItem item1;

	JMenuItem item2;

	JPanel p;

	JToolBar toolbar;
	
	static final int WIDTH=600;

	static final int HEIGHT=400;
	
	private FileDialog openDia;
	
	
	
	public OperationClient(){
		init();
	}
	public void init(){
		 this.setTitle("GreenApple1.0");
//		 this.setSize(500,350);
//		 this.setLocation(200,200);
		 this.setResizable(false);
		 this.setLayout(null);
		 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 
		 JMenuBar menubar1 = new JMenuBar();

		 p = new JPanel();

		 this.setContentPane(p);

		 this.setJMenuBar(menubar1);

		 JMenu menu1=new JMenu("文件");

		 JMenu menu2=new JMenu("帮助");

		     JMenu menu3=new JMenu("菜单3");

		     JMenu menu4=new JMenu("菜单4");

		     JMenu menu5=new JMenu("菜单5");

		     menubar1.add(menu1);

		     menubar1.add(menu2);


		     item1=new JMenuItem("打开");
		     
		     item1.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
//					 JFileChooser jfc=new JFileChooser();  
//				        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
//				        jfc.showDialog(new JLabel(), "选择");  
//				        File file=jfc.getSelectedFile();  
//				        if(file.isDirectory()){  
//				            System.out.println("文件夹:"+file.getAbsolutePath());  
//				        }else if(file.isFile()){  
//				            System.out.println("文件:"+file.getAbsolutePath());  
//				        }  
//				        System.out.println(jfc.getSelectedFile().getName());  
					openDia = new FileDialog(OperationClient.this,"选择文件",FileDialog.LOAD);
					openDia.setVisible(true);
					String dirPath = openDia.getDirectory();
					String fileName = openDia.getFile();
					
					if(dirPath==null||fileName==null){
						return;
					}
					
					System.out.println(dirPath+"<<<"+fileName);
					 
				}
		    	 
		     });

		     item2=new JMenuItem("关闭");
		     item2.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.exit(0);
				}
		    	 
		     });

		     JMenuItem item4=new JMenuItem("关于");
		     




		     menu1.add(item1);

		     menu1.addSeparator();

		     menu1.add(item2);



		     menu2.add(item4);




		    

		     JButton button1 = new JButton("工具1");

		     JButton button2 = new JButton("工具2");

		     JButton button3 = new JButton("工具3");

		     toolbar = new JToolBar();

		     toolbar.add(button1);   

		     toolbar.add(button2);        

		     toolbar.add(button3);
		  
		     BorderLayout bord = new BorderLayout();

		     p.setLayout(bord);
		     toolbar.setFloatable(false);
		     p.add("North",toolbar);    

		     this.setVisible(true);

		     this.setSize(WIDTH,HEIGHT);

		     Toolkit kit=Toolkit.getDefaultToolkit();

		     Dimension screenSize=kit.getScreenSize();
		     
		     int width=screenSize.width;

		     int height=screenSize.height;

		     int x=(width-WIDTH)/2;

		     int y=(height-HEIGHT)/2;

		     this.setLocation(x,y); 
		     
		     
		     item4.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					String msg = "89786632@qq.com\n";
					msg = msg+"https://github.com/jianchenghanshe/GreenApple\n";
					JOptionPane.showMessageDialog(OperationClient.this, msg);

				}
		    	 
		     });
		 
		 this.setVisible(true);
	}
	
	public static void main(String[] args){
		new OperationClient();
	}
}
