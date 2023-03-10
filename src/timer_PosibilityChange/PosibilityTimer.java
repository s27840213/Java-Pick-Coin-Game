package timer_PosibilityChange;

import java.util.Timer;

import entity.Player;
import playerState.Recover;
import tool.DropItemCreator;

public class PosibilityTimer {
	DropItemCreator creator;
	Timer chageTimer = new Timer();
	PosibilityChange change;
	private int delay = 200;
	public PosibilityTimer(DropItemCreator creator) {
		this.creator = creator;
		change = new PosibilityChange(creator);
	}
	public void run() {
		chageTimer.schedule(change,5000,5000);
	}
}
	