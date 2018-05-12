/**
 * 
 */
/**
 * @author 帆
 *
 */
package sokoban.ui;					


/*1.对于障碍的碰撞检测使用二维数组的模型，人物的位置使用人物的坐标获取，检测人物将要移动方向的二维数组模型中的具体值，如果将要移动方向的二维数组元素值为1，则return。
 * 不采取移动的动作（这里应该对于人移动应该采取检测前方是否为1，因为当箱子与树木在一起时）
 *2.对于箱子的碰撞检测，同样的，在二维数组中使用数字4所在的二维数组的角标值来表示箱子的坐标，在人物移动时加入检测，如果将要前进的方向是数字4，则表示前方为箱子，
 *此时应该将箱子的二维数组模型的值做一个对应的改动，即人物方向加2的位置为4，人物方向加1的值改为0.且将箱子的模型向前移动一位，将人物的模型向前移动一位。
 *在这里将要检测如果箱子的移动方向是箱子4，或者是数字1，那就采取移动的动作。 
 * */
import java.awt.Frame;				//导入创建窗体的类
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;			//导入创建图片对象的接口
import javax.swing.ImageIcon;		//导入创建图片对象的类
import javax.swing.JLabel;			//导入创建背景的类

//用于主背景的类继承了背景实现的类，实现了键盘监听的接口
 public class MainFrame extends Frame implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lab_zm_wolf ;			//人物对象引用
	private Tree_Init ti = new Tree_Init();		//障碍实体对象
	private int[][] map ;			//障碍对象的方法，用于创建地图
	private int[][] nums = new int[12][16];				//用于记录目标位置的二维数组
	private int num = 0;						//用于记录进入目标的对象
	private int over = 3;
	private Icon ct = new ImageIcon("ct.png");
	private Icon nt = new ImageIcon("sheep-no.png");
	//private Icon nt = new ImageIcon("sheep-no.png");
	public MainFrame(int count){
		map = ti.getmap(count);	
		//制作目标的背景-----笼子的背景应该是在最上面的，因为要把它们关起来
		endInit();
		//制作灰太狼的背景，由于背景的覆盖问题，灰太狼需要放在大背景代码的上方
		wolfInit();
		//制作懒羊羊的背景，放在灰太狼的下面
		sheepInit();
		//制作障碍物树的背景，位置应该在主背景的上面，其他没关系
		treeInit();
		//制作一个背景，背景初始化
		backGroundInit();
		
		
		
		//用于设置窗口
		setMainFrameUi();
		//添加键盘的监听
		this.addKeyListener(this);
		//添加按钮监听
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				System.exit(0);
			}		
		});
	}
	//----------人物初始化------------
	private void wolfInit(){
		ti.wx = 4;
		ti.wy = 6;
		Icon i = new ImageIcon("wolf-zm.png");
		lab_zm_wolf = new JLabel(i);
		lab_zm_wolf.setBounds(ti.wx*50 + 12, ti.wy*50 + 38, 50, 50);
		this.add(lab_zm_wolf);
	}
	
	//------------初始化箱子-----------
	private void sheepInit() {
		int s_1x = 6, s_1y = 4;
		int s_2x = 6, s_2y = 6;
		int s_3x = 6, s_3y = 8;
		map[s_1y][s_1x] = 4;
		map[s_2y][s_2x] = 4;
		map[s_3y][s_3x] = 4;
		JLabel sheep_1 = new JLabel(nt);
		JLabel sheep_2 = new JLabel(nt);
		JLabel sheep_3 = new JLabel(nt);
		sheep[s_1y][s_1x] = sheep_1;
		sheep[s_2y][s_2x] = sheep_2;
		sheep[s_3y][s_3x] = sheep_3;
		sheep_1.setBounds(s_1x*50+12, s_1y*50+38, 50, 50);
		sheep_2.setBounds(s_2x*50+12, s_2y*50+38, 50, 50);
		sheep_3.setBounds(s_3x*50+12, s_3y*50+38, 50, 50);
		this.add(sheep_1);
		this.add(sheep_2);
		this.add(sheep_3);
	}
	//---------创建箱子的二维数组-------
	JLabel[][] sheep = new JLabel[12][16];
	
	//----------------目标的初始化---------
	private void endInit(){
		Icon i = new ImageIcon("end.png");
		JLabel end_1 = new JLabel(i);
		JLabel end_2 = new JLabel(i); 
		JLabel end_3 = new JLabel(i); 
		end_1.setBounds(14*50+12, 4*50+38, 50, 50);
		nums[4][14] = 1;
		end_2.setBounds(14*50+12, 5*50+38, 50, 50);
		nums[5][14] = 1;
		end_3.setBounds(14*50+12, 6*50+38, 50, 50);
		nums[6][14] = 1;
		this.add(end_1);
		this.add(end_2);
		this.add(end_3);
	}
	//-------------障碍物的初始化，使用遍历二维数组的方式来实现
	private void treeInit(){
		Icon ic = new ImageIcon("tree.png");
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				if(map[i][j] == 1){
					JLabel tree_lab = new JLabel(ic);
					tree_lab.setBounds(12+j*50, 38+i*50, 50, 50);
					this.add(tree_lab);
				}
			}
		}
		
	}
	//-------------设置窗体------------
	private void setMainFrameUi(){
		//设置窗体的布局模式为自由布局
		this.setLayout(null);
		//用于设置窗口的标题
		this.setTitle("推箱子 Version-1.0 /AFan 2017.12.30");
		//用于设置窗口的大小，传入两个参数，为窗口的长宽，使用像素
		this.setSize(821, 648);
		//用于设置窗口的位置，使用坐标来确定，同样的参数为像素
		this.setLocation(450, 150);
		//用于将窗口显示出来  参数为布尔类型，选择true。且这条语句写在其他语句的最后
		this.setVisible(true);
	}
	//------------背景初始化---------------
	private void backGroundInit(){
		//创建一个图片的对象，使用多态的特性用Icon来指向这个对象
		//Icon是一个接口用于指向一个图片的对象，这个图片的对象使用文件名来作为参数
		Icon ic = new ImageIcon("bg.png");		
		//Icon ic = new ImageIcon("yuyu.jpg");
		//使用可以创作背景的组件Jlabel，将图片对象添加进去
		JLabel lab_bg = new JLabel(ic);
		//设置背景组件的大小和位置
		lab_bg.setBounds(12, 32, 800, 610);
		//将背景组件添加到窗体中
		this.add(lab_bg);
	}
	
	//按下键盘监听 37左 38上。。。
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == 37 || key == 'A'){
			Icon ic = new ImageIcon("wolf-left.png");
			if(map[ti.wy][ti.wx-1] == 1){
				setWolf(0, ic);
				return;
			}
			//将要去的方向为箱子
			if(map[ti.wy][ti.wx-1] == 4){
				if(map[ti.wy][ti.wx-2] == 0){
					if(nums[ti.wy][ti.wx-1] - nums[ti.wy][ti.wx-2] == -1){
						num++;
						sheep[ti.wy][ti.wx-1].setIcon(ct);
					}
					if(nums[ti.wy][ti.wx-1] - nums[ti.wy][ti.wx-2] == 1){
						num--;
						sheep[ti.wy][ti.wx-1].setIcon(nt);
					}
					map[ti.wy][ti.wx-2] = 4;
					map[ti.wy][ti.wx-1] = 0;
					sheep[ti.wy][ti.wx-1].setLocation((ti.wx-2)*50+12, ti.wy*50+38);
					sheep[ti.wy][ti.wx-2] = sheep[ti.wy][ti.wx-1];
					sheep[ti.wy][ti.wx-1] = null;
					ti.wx--;
					setWolf(1, ic);
				}
			}
			if(map[ti.wy][ti.wx-1] == 0){
				ti.wx--;
				setWolf(1, ic);
			}
			setWolf(0, ic);
			v();
		}
		if(key == 38 || key == 'W'){
			Icon ic = new ImageIcon("wolf-up.png");
			if(map[ti.wy-1][ti.wx] == 1){
				setWolf(0, ic);
				return;
			}
			if(map[ti.wy-1][ti.wx] == 4){
				if(map[ti.wy-2][ti.wx] == 0){
					if(nums[ti.wy-1][ti.wx] - nums[ti.wy-2][ti.wx] == -1){
						num++;
						sheep[ti.wy-1][ti.wx].setIcon(ct);
					}
					if(nums[ti.wy-1][ti.wx] - nums[ti.wy-2][ti.wx] == 1){
						num--;
						sheep[ti.wy-1][ti.wx].setIcon(nt);
					}
					map[ti.wy-2][ti.wx] = 4;
					map[ti.wy-1][ti.wx] = 0;
					sheep[ti.wy-1][ti.wx].setLocation(ti.wx*50+12, (ti.wy-2)*50+38);
					sheep[ti.wy-2][ti.wx] = sheep[ti.wy-1][ti.wx];
					sheep[ti.wy-1][ti.wx] = null;
					ti.wy--;
					setWolf(2, ic);
				}
			}
			if(map[ti.wy-1][ti.wx] == 0){
				ti.wy--;
				setWolf(2, ic);
			}	
			setWolf(0, ic);
			v();
		}
		if(key == 39 || key == 'D'){
			Icon ic = new ImageIcon("wolf-right.png");		
			if(map[ti.wy][ti.wx+1] == 1){
				setWolf(0, ic);
				return;
			}
			if(map[ti.wy][ti.wx+1] == 4){
				if(map[ti.wy][ti.wx+2] == 0){
					if(nums[ti.wy][ti.wx+1] - nums[ti.wy][ti.wx+2] == -1){
						num++;
						sheep[ti.wy][ti.wx+1].setIcon(ct);
					}
					if(nums[ti.wy][ti.wx+1] - nums[ti.wy][ti.wx+2] == 1){
						num--;
						sheep[ti.wy][ti.wx+1].setIcon(nt);
					}
					map[ti.wy][ti.wx+2] = 4;
					map[ti.wy][ti.wx+1] = 0;
					sheep[ti.wy][ti.wx+1].setLocation((ti.wx+2)*50+12, ti.wy*50+38);
					sheep[ti.wy][ti.wx+2] = sheep[ti.wy][ti.wx+1];
					sheep[ti.wy][ti.wx+1] = null;
					ti.wx++;
					setWolf(3, ic);
				}
			}
			if(map[ti.wy][ti.wx+1] == 0){
				ti.wx++;
				setWolf(3, ic);
			}
			setWolf(0, ic);
			v();
		}
		if(key == 40 || key == 'S'){
			Icon ic = new ImageIcon("wolf-zm.png");	
			if(map[ti.wy+1][ti.wx] == 1){
				setWolf(0, ic);
				return;
			}
			if(map[ti.wy+1][ti.wx] == 4){
				if(map[ti.wy+2][ti.wx] == 0){
					if(nums[ti.wy+1][ti.wx] - nums[ti.wy+2][ti.wx] == -1){
						num++;
						sheep[ti.wy+1][ti.wx].setIcon(ct);
					}
					if(nums[ti.wy+1][ti.wx] - nums[ti.wy+2][ti.wx] == 1){
						num--;
						sheep[ti.wy+1][ti.wx].setIcon(nt);
					}
					map[ti.wy+2][ti.wx] = 4;
					map[ti.wy+1][ti.wx] = 0;
					sheep[ti.wy+1][ti.wx].setLocation(ti.wx*50+12, (ti.wy+2)*50+38);
					sheep[ti.wy+2][ti.wx] = sheep[ti.wy+1][ti.wx];
					sheep[ti.wy+1][ti.wx] = null;
					ti.wy++;
					setWolf(4, ic);
				}
			}
			if(map[ti.wy+1][ti.wx] == 0){
				ti.wy++;
				setWolf(4, ic);
			}	
			setWolf(0, ic);
			v();
		}
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	public void keyPressed(KeyEvent e) {
	}
	//抽取人物移动程序的共性内容，提高代码额复用性
	private void setWolf(int flag, Icon ic ){
		int x = (int)lab_zm_wolf.getLocation().getX();
		int y = (int)lab_zm_wolf.getLocation().getY();
		lab_zm_wolf.setIcon(ic);
		if(flag == 0)lab_zm_wolf.setLocation(x, y);
		if(flag == 1)lab_zm_wolf.setLocation(x-50, y);
		if(flag == 2)lab_zm_wolf.setLocation(x, y-50);	
		if(flag == 3)lab_zm_wolf.setLocation(x+50, y);
		if(flag == 4)lab_zm_wolf.setLocation(x, y+50);
	}
	public boolean v(){
		if(num == over){
			NextOrExit noe = new NextOrExit();
			this.setVisible(false);
			/*	noe.closeTheFrame();*/
			return true;
		}
		return false;
	}
 }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 