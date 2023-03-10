package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import game.Game;
import timer_remove_ground_item.CoinTimer;
import tool.Animation;
import tool.Assets;
import tool.MyHandler;
import tool.ObjectID;

public class Explosion extends Entity{
	private float x,y;
	private float vely=2;
	Game game;
	MyHandler handler;
	Animation animation;
	public Explosion(Game game,float x,float y,MyHandler handler,ObjectID id) {
		super(game, x, y, id);
		this.x=x;
		this.y=y;
		this.handler = handler;
		animation = new Animation(60, Assets.explsion);
	}
	public void tick(ArrayList<Entity> entityList) {
		CoinTimer timer = new CoinTimer(handler, this);
		animation.tick();
		timer.setDelay(480);
		timer.run();
	}
	public void render(Graphics g) {
		g.drawImage(animation.getCurrentFrame(),(int) x, (int) y, null);
	}

	public Rectangle getUpperBound() {
		// TODO Auto-generated method stub
		return null;
	}
}
