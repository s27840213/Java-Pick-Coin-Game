package timer_remove_ground_item;

import java.util.TimerTask;
import java.util.logging.Handler;

import org.omg.CORBA.SystemException;

import entity.Entity;
import entity.Entity;
import entity.Entity;
import tool.MyHandler;

public class CoinTimerTask extends TimerTask{
	MyHandler handler;
	Entity entity;
	public CoinTimerTask(MyHandler handler,Entity entity) {
		// TODO Auto-generated constructor stub
		this.handler=handler;
		this.entity = entity;
//		if(coin==null) {
//			System.out.println(coin);
//		}
	}
	public void run() {		
		handler.remove(entity);
	}
}
