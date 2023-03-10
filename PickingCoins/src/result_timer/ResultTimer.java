package result_timer;

import java.util.Timer;

import game.Game;

public class ResultTimer {
	Game game;
	ResultTimerTask task;
	Timer resultTimer;

	public ResultTimer(Game game) {
		this.game = game;
		resultTimer = new Timer();
		task = new ResultTimerTask(game);
	}
	public void run() {
		resultTimer.schedule(task,4000);
	}

}
