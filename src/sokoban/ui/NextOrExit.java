package sokoban.ui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class NextOrExit extends Frame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 909394L;
	public NextOrExit(){
		//窗体
		setMainFrameUi();
		//添加按钮
		addbutton();
		//背景
		backGroundInit();
		//添加按钮监听
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeTheFrame();
			}		
		});
	}
	
	//用于使关闭弹窗
	protected void closeTheFrame() {
		this.setVisible(false);
	}
	
	
	//设置一个窗体
	private void setMainFrameUi(){
		//设置窗体的布局模式为自由布局
		this.setLayout(null);
		//用于设置窗口的标题
		this.setTitle("继续");
		//用于设置窗口的大小，传入两个参数，为窗口的长宽，使用像素
		this.setSize(330, 212);
		//用于设置窗口的位置，使用坐标来确定，同样的参数为像素
		this.setLocation(680, 345);
		//用于将窗口显示出来  参数为布尔类型，选择true。且这条语句写在其他语句的最后
		this.setVisible(true);
	}
	//背景初始化
	private void backGroundInit(){
		//创建一个图片的对象，使用多态的特性用Icon来指向这个对象
		//Icon是一个接口用于指向一个图片的对象，这个图片的对象使用文件名来作为参数
		Icon ic = new ImageIcon("g.png");		
		//Icon ic = new ImageIcon("yuyu.jpg");
		//使用可以创作背景的组件Jlabel，将图片对象添加进去
		JLabel lab_bg = new JLabel(ic);
		//设置背景组件的大小和位置
		lab_bg.setBounds(12, 32, 313, 180);
		//将背景组件添加到窗体中
		this.add(lab_bg);
	}
	
	//添加按钮
	private void addbutton(){
		Button but_1 = new Button("下一关 ！");
		but_1.setBounds(240, 160, 75, 38);
		this.add(but_1);
		Button but_2 = new Button("退出游戏！");
		but_2.setBounds(22, 160, 75, 38);
		this.add(but_2);
		but_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		but_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				 closeTheFrame();
			}
		});
	}
	
}
