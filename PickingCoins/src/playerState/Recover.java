package playerState;

import java.util.TimerTask;

import entity.Player;

public class Recover extends TimerTask{
	Player player;

	public Recover(Player player) {
		this.player=player;
	}
	
	
	public void run() {
		player.setHurted(false);
		player.setVelx(10);
	}

}
