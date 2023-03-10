package timer_PosibilityChange;

import java.util.TimerTask;

import tool.DropItemCreator;

public class PosibilityChange extends TimerTask{
	DropItemCreator creator;
	private float posibility;
	public PosibilityChange(DropItemCreator creator) {
		this.creator = creator;		
	}
	@Override
	public void run() {
		posibility = creator.getPosibilityOfCoin()-0.1f;
		if(posibility<0.5) {
			posibility = 0.5f;
		}

		creator.setPosibilityOfCoin(posibility);
		
	}
	
}
