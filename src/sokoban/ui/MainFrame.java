/**
 * 
 */
/**
 * @author ��
 *
 */
package sokoban.ui;					


/*1.�����ϰ�����ײ���ʹ�ö�ά�����ģ�ͣ������λ��ʹ������������ȡ��������ｫҪ�ƶ�����Ķ�ά����ģ���еľ���ֵ�������Ҫ�ƶ�����Ķ�ά����Ԫ��ֵΪ1����return��
 * ����ȡ�ƶ��Ķ���������Ӧ�ö������ƶ�Ӧ�ò�ȡ���ǰ���Ƿ�Ϊ1����Ϊ����������ľ��һ��ʱ��
 *2.�������ӵ���ײ��⣬ͬ���ģ��ڶ�ά������ʹ������4���ڵĶ�ά����ĽǱ�ֵ����ʾ���ӵ����꣬�������ƶ�ʱ�����⣬�����Ҫǰ���ķ���������4�����ʾǰ��Ϊ���ӣ�
 *��ʱӦ�ý����ӵĶ�ά����ģ�͵�ֵ��һ����Ӧ�ĸĶ��������﷽���2��λ��Ϊ4�����﷽���1��ֵ��Ϊ0.�ҽ����ӵ�ģ����ǰ�ƶ�һλ���������ģ����ǰ�ƶ�һλ��
 *�����ｫҪ���������ӵ��ƶ�����������4������������1���ǾͲ�ȡ�ƶ��Ķ����� 
 * */
import java.awt.Frame;				//���봴���������
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;			//���봴��ͼƬ����Ľӿ�
import javax.swing.ImageIcon;		//���봴��ͼƬ�������
import javax.swing.JLabel;			//���봴����������

//��������������̳��˱���ʵ�ֵ��࣬ʵ���˼��̼����Ľӿ�
 public class MainFrame extends Frame implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lab_zm_wolf ;			//�����������
	private Tree_Init ti = new Tree_Init();		//�ϰ�ʵ�����
	private int[][] map ;			//�ϰ�����ķ��������ڴ�����ͼ
	private int[][] nums = new int[12][16];				//���ڼ�¼Ŀ��λ�õĶ�ά����
	private int num = 0;						//���ڼ�¼����Ŀ��Ķ���
	private int over = 3;
	private Icon ct = new ImageIcon("ct.png");
	private Icon nt = new ImageIcon("sheep-no.png");
	//private Icon nt = new ImageIcon("sheep-no.png");
	public MainFrame(int count){
		map = ti.getmap(count);	
		//����Ŀ��ı���-----���ӵı���Ӧ������������ģ���ΪҪ�����ǹ�����
		endInit();
		//������̫�ǵı��������ڱ����ĸ������⣬��̫����Ҫ���ڴ󱳾�������Ϸ�
		wolfInit();
		//����������ı��������ڻ�̫�ǵ�����
		sheepInit();
		//�����ϰ������ı�����λ��Ӧ���������������棬����û��ϵ
		treeInit();
		//����һ��������������ʼ��
		backGroundInit();
		
		
		
		//�������ô���
		setMainFrameUi();
		//��Ӽ��̵ļ���
		this.addKeyListener(this);
		//��Ӱ�ť����
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				System.exit(0);
			}		
		});
	}
	//----------�����ʼ��------------
	private void wolfInit(){
		ti.wx = 4;
		ti.wy = 6;
		Icon i = new ImageIcon("wolf-zm.png");
		lab_zm_wolf = new JLabel(i);
		lab_zm_wolf.setBounds(ti.wx*50 + 12, ti.wy*50 + 38, 50, 50);
		this.add(lab_zm_wolf);
	}
	
	//------------��ʼ������-----------
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
	//---------�������ӵĶ�ά����-------
	JLabel[][] sheep = new JLabel[12][16];
	
	//----------------Ŀ��ĳ�ʼ��---------
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
	//-------------�ϰ���ĳ�ʼ����ʹ�ñ�����ά����ķ�ʽ��ʵ��
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
	//-------------���ô���------------
	private void setMainFrameUi(){
		//���ô���Ĳ���ģʽΪ���ɲ���
		this.setLayout(null);
		//�������ô��ڵı���
		this.setTitle("������ Version-1.0 /AFan 2017.12.30");
		//�������ô��ڵĴ�С����������������Ϊ���ڵĳ���ʹ������
		this.setSize(821, 648);
		//�������ô��ڵ�λ�ã�ʹ��������ȷ����ͬ���Ĳ���Ϊ����
		this.setLocation(450, 150);
		//���ڽ�������ʾ����  ����Ϊ�������ͣ�ѡ��true�����������д�������������
		this.setVisible(true);
	}
	//------------������ʼ��---------------
	private void backGroundInit(){
		//����һ��ͼƬ�Ķ���ʹ�ö�̬��������Icon��ָ���������
		//Icon��һ���ӿ�����ָ��һ��ͼƬ�Ķ������ͼƬ�Ķ���ʹ���ļ�������Ϊ����
		Icon ic = new ImageIcon("bg.png");		
		//Icon ic = new ImageIcon("yuyu.jpg");
		//ʹ�ÿ��Դ������������Jlabel����ͼƬ������ӽ�ȥ
		JLabel lab_bg = new JLabel(ic);
		//���ñ�������Ĵ�С��λ��
		lab_bg.setBounds(12, 32, 800, 610);
		//�����������ӵ�������
		this.add(lab_bg);
	}
	
	//���¼��̼��� 37�� 38�ϡ�����
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == 37 || key == 'A'){
			Icon ic = new ImageIcon("wolf-left.png");
			if(map[ti.wy][ti.wx-1] == 1){
				setWolf(0, ic);
				return;
			}
			//��Ҫȥ�ķ���Ϊ����
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
	//��ȡ�����ƶ�����Ĺ������ݣ���ߴ�������
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
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 