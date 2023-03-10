package count_down;

import java.util.TimerTask;

import game.Game;
import state.GameState;

public class CountDown extends TimerTask{
	Game game;
	public CountDown(Game game) {
		this.game = game;
	}
	@Override
	public void run() {
		//System.out.println(GameState.countdown);
		GameState.countdown--;
	}
	

}
