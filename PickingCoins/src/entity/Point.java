package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import game.Game;
import timer_remove_ground_item.CoinTimer;
import tool.Assets;
import tool.MyHandler;
import tool.ObjectID;

public class Point extends Entity{
	private float x,y;
	private float vely=2;
	Game game;
	MyHandler handler;
	public Point(Game game,float x,float y,MyHandler handler,ObjectID id) {
		super(game, x, y, id);
		this.x=x;
		this.y=y;
		this.handler = handler;
	}
	public void tick(ArrayList<Entity> entityList) {
		y-=2;
		CoinTimer timer = new CoinTimer(handler, this);
		timer.setDelay(400);
		timer.run();
	}
	public void render(Graphics g) {
		g.drawImage(Assets.point,(int) x, (int) y, null);
	}

	public Rectangle getUpperBound() {
		// TODO Auto-generated method stub
		return null;
	}
}
