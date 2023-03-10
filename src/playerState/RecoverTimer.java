package playerState;

import java.sql.Time;
import java.util.Timer;

import entity.Player;

public class RecoverTimer {
	Player player;
	Timer recoverTimer = new Timer();
	Recover recover;
	private int delay = 200;
	public RecoverTimer(Player player) {
		this.player = player;
		recover = new Recover(player);
	}
	public void run() {
		recoverTimer.schedule(recover, delay);
		
	}
}
