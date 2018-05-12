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
		//����
		setMainFrameUi();
		//��Ӱ�ť
		addbutton();
		//����
		backGroundInit();
		//��Ӱ�ť����
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeTheFrame();
			}		
		});
	}
	
	//����ʹ�رյ���
	protected void closeTheFrame() {
		this.setVisible(false);
	}
	
	
	//����һ������
	private void setMainFrameUi(){
		//���ô���Ĳ���ģʽΪ���ɲ���
		this.setLayout(null);
		//�������ô��ڵı���
		this.setTitle("����");
		//�������ô��ڵĴ�С����������������Ϊ���ڵĳ���ʹ������
		this.setSize(330, 212);
		//�������ô��ڵ�λ�ã�ʹ��������ȷ����ͬ���Ĳ���Ϊ����
		this.setLocation(680, 345);
		//���ڽ�������ʾ����  ����Ϊ�������ͣ�ѡ��true�����������д�������������
		this.setVisible(true);
	}
	//������ʼ��
	private void backGroundInit(){
		//����һ��ͼƬ�Ķ���ʹ�ö�̬��������Icon��ָ���������
		//Icon��һ���ӿ�����ָ��һ��ͼƬ�Ķ������ͼƬ�Ķ���ʹ���ļ�������Ϊ����
		Icon ic = new ImageIcon("g.png");		
		//Icon ic = new ImageIcon("yuyu.jpg");
		//ʹ�ÿ��Դ������������Jlabel����ͼƬ������ӽ�ȥ
		JLabel lab_bg = new JLabel(ic);
		//���ñ�������Ĵ�С��λ��
		lab_bg.setBounds(12, 32, 313, 180);
		//�����������ӵ�������
		this.add(lab_bg);
	}
	
	//��Ӱ�ť
	private void addbutton(){
		Button but_1 = new Button("��һ�� ��");
		but_1.setBounds(240, 160, 75, 38);
		this.add(but_1);
		Button but_2 = new Button("�˳���Ϸ��");
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
