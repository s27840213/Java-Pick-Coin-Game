package tool;

import java.util.LinkedList;
import java.util.Random;

import entity.Bomb;
import entity.Coin;
import entity.Entity;
import game.Game;
import state.GameState;

public class DropItemCreator {
	Game game;
	MyHandler handler;
	Coin coin;
	Random random = new Random();
	private long currentTime = 0;
	private long lastTime = 0;
	private int count = 0;
	private int CoinCount = 0;
	private int lastCount = 0;
	public int position = 0;
	private int CoinNum = 250;
	private int CoinNumPerTime = 2;
	private float PosibilityOfCoin = 0.8f;
	public boolean done = false;

	public LinkedList<Entity> coinList = new LinkedList<Entity>();



	public DropItemCreator(Game game, MyHandler handler) {
		this.game = game;
		this.handler = handler;

	}

	// private long currentTime =0;
	// private long lastTime =0;
	// private int count = 0;
	// public void update(Game game,MyHandler handler,ObjectID id) {
	// currentTime = System.currentTimeMillis();
	//
	// if((currentTime-lastTime)>1000) {
	// lastTime = currentTime;
	// count++;
	// add(game, handler, id);
	// add(game, handler, id);
	// }
	//
	// }
	public void add(Game game, MyHandler handler) {
		Random rand = new Random();
		if (rand.nextInt(1000) < 1000*PosibilityOfCoin) {
			addCoinList(new Coin(game, random.nextInt(960 - Assets.coin[0].getWidth()), 0, handler,ObjectID.Coin));
			// handler.add(new Coin(game, random.nextInt(960 - Assets.coin[0].getWidth()),
			// 0, handler, id));
		}
		else {
			addCoinList(new Bomb(game, random.nextInt(960 - Assets.coin[0].getWidth()), 0, handler,ObjectID.Bomb));
		}
	}

	public void createAllCoin(Game game, MyHandler handler) {
		for (int i = 0; i < CoinNum; i++) {

			add(game, handler);
		}
	}

	public void update() {
		currentTime = System.currentTimeMillis();
		if (GameState.starting) {
		if ((currentTime - lastTime) > 600) {
			if (CoinCount <= (CoinNum - 1)) {
				for (int i = 0; i < CoinNumPerTime; i++) {
					handler.add(coinList.get(position));
					position++;
					CoinCount++;
//					System.out.println(CoinCount);
//					System.out.println(CoinNum);
				}

			}
			if (CoinCount > (CoinNum - 1)) {
				done = true;
			}
			lastTime = currentTime;
			count++;
		}
		}

	}

	public void addCoinList(Entity entity) {
		this.coinList.add(entity);
	}
	public float getPosibilityOfCoin() {
		return PosibilityOfCoin;
	}

	public void setPosibilityOfCoin(float posibilityOfCoin) {
		PosibilityOfCoin = posibilityOfCoin;
	}
}
