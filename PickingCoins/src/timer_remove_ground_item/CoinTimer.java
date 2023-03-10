package timer_remove_ground_item;

import java.util.Timer;
import java.util.logging.Handler;

import entity.Entity;
import tool.MyHandler;

public class CoinTimer {
	MyHandler handler;
	Entity entity;
	private int delay = 2000;
	Timer timer = new Timer();
	CoinTimerTask coinTimerTask;


	public CoinTimer(MyHandler handler,Entity entity) {
		// TODO Auto-generated constructor stub
		this.handler=handler;
		this.entity=entity;
		coinTimerTask = new CoinTimerTask(handler, entity);
	}


	public void run() {

		timer.schedule(coinTimerTask, delay);

	}

	public int getDelay() {
		return delay;
	}


	public void setDelay(int delay) {
		this.delay = delay;
	}

}
