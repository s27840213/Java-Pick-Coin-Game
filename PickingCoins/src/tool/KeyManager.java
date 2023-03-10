package tool;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*
 * �޲zkeyboard���ƥ�
 */
public class KeyManager implements KeyListener{

	private boolean[] keys;
	public boolean up,down,left,right,r,esc,enter,W,A,S,D;
	public KeyManager() {
		// TODO Auto-generated constructor stub
		keys = new boolean[256];
		
	}
	//update,��s�T�{����O�_�Q���U
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
	//���U�Y���� �Nkeys[]�����쪺�ƭȳ]��true(�Q���U)
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()]=true;
	}
	
	//��}�Y���� �Nkeys[]�����쪺�ƭȳ]��false(���Q���U)
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()]=false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

		
}
