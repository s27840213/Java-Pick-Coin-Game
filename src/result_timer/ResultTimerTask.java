package result_timer;

import java.util.TimerTask;

import game.Game;
import state.GameState;
import state.LoseState;
import state.State;
import state.WinState;
import tool.Assets;

public class ResultTimerTask extends TimerTask {
	Game game;

	public ResultTimerTask(Game game) {
		this.game = game;
	}

	@Override
	public void run() {
		if (GameState.lose) {
			game.loseState = new LoseState(game);
			State.setState(game.loseState);
			Assets.bgmGame.stop();
			game.gameState = null;
		}
		if (GameState.win) {
			game.winState = new WinState(game);
			State.setState(game.winState);
			Assets.bgmGame.stop();
			game.gameState = null;
		}
	}

}
