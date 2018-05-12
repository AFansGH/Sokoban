package sokoban.ui;	
//用于程序的开始控制----第一个图形化程序 推箱子

public class AppStart {
	public static void main(String[] args) {
	/*	boolean b = true;
		MainFrame mf = null;
		int count = 1;
		while(true){
			if(b){
				b = false;
				mf = new MainFrame(count);
				System.out.println("当前关卡为:"+count);
				count++;
			}
			b = mf.v();
		}*/
		new MainFrame(1);
	}
}
