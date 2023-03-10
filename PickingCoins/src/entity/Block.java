package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import org.w3c.dom.css.Rect;

import game.Game;
import tool.ObjectID;

public class Block extends Entity{
	
	public Block(Game game,float x, float y, ObjectID id) {
		super(game,x, y, id);
	}


	@Override	
	public void tick(ArrayList<Entity> entityList) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.drawRect((int)x,(int) y, 45, 45)	;
		
	}
	@Override
	public Rectangle getUpperBound() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y, 32, 32);
	}
}
