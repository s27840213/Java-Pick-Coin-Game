package count_down;

import java.util.Timer;

import game.Game;
public class CountDownTimer {
	Game game;
	CountDown countDown;
	Timer timer ;
	
	public CountDownTimer(Game game) {
		this.game =game;
		countDown = new CountDown(game);
		timer = new Timer();
	}
	
	public void run() {
		timer.schedule(countDown, 1000,1000);
	}
	public void stop() {
		timer.cancel();
	}
}
