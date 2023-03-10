package tool;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*
 * 管理keyboard的事件
 */
public class KeyManager implements KeyListener{

	private boolean[] keys;
	public boolean up,down,left,right,r,esc,enter,W,A,S,D;
	public KeyManager() {
		// TODO Auto-generated constructor stub
		keys = new boolean[256];
		
	}
	//update,更新確認按鍵是否被按下
	public void tick() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		W =  keys[KeyEvent.VK_W];
		A =  keys[KeyEvent.VK_A];
		S =  keys[KeyEvent.VK_S];
		D =  keys[KeyEvent.VK_D];
		r = keys[KeyEvent.VK_R];
		esc = keys[KeyEvent.VK_ESCAPE]; 
		enter = keys[KeyEvent.VK_ENTER];
	}
	@Override
	//按下某按鍵 將keys[]對應到的數值設為true(被按下)
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()]=true;
	}
	
	//放開某按鍵 將keys[]對應到的數值設為false(未被按下)
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()]=false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

		
}
