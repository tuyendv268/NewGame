
package Game.Setting;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyAction implements KeyListener {
	public boolean key[];
	public boolean up,down,right,left,attack;
	public KeyAction() {
		key= new boolean[100];
	}

	public void update() {
		up=key[KeyEvent.VK_UP];
		down=key[KeyEvent.VK_DOWN];
		right=key[KeyEvent.VK_RIGHT];
		left=key[KeyEvent.VK_LEFT];
		attack=key[KeyEvent.VK_A];
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		key[e.getKeyCode()]=true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		key[e.getKeyCode()]=false;
		
	}

}
