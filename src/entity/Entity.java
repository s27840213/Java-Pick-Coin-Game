package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import org.omg.PortableInterceptor.ObjectIdHelper;

import game.Game;
import tool.ObjectID;

/*
 *	Entity(such as player,Item.....) 
 */

public abstract class Entity {
	protected Game game;
	protected float x, y; // position
	protected float velx,vely;
	protected float gravity; 
	protected boolean jumping = true;
	protected boolean falling = true;
	public boolean created = false;
	protected ObjectID id;
	protected boolean deleted=false;

	//constructor -> set cordinate
	public Entity(Game game,float x, float y,ObjectID id) {
		this.game=game;
		this.x = x;
		this.y = y;
		this.id = id;
	}
	//abstract method
	public abstract void tick(ArrayList<Entity> entityList);
	public abstract void render(Graphics g);
	//collision detect
	public abstract Rectangle getUpperBound() ;
	//getter and setter
	public float getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	public void setX(float x) {
		// TODO Auto-generated method stub
		this.x = x;
	}

	public float getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

	public void setY(float y) {
		// TODO Auto-generated method stub
		this.y=y;
	}

	public float getVelx() {
		// TODO Auto-generated method stub
		return this.velx;
	}

	public void setVelx(float velx) {
		// TODO Auto-generated method stub
		this.velx=velx;
		
	}

	public float getVely() {
		// TODO Auto-generated method stub
		return this.vely;
	}

	public void setVely(float vely) {
		// TODO Auto-generated method stub
		this.vely=vely;
	}

	public boolean isJumping() {
		// TODO Auto-generated method stub
		return this.jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping=jumping;
		
	}

	public boolean isFailing() {
		// TODO Auto-generated method stub
		return this.falling;
	}

	public void setFailing(boolean failing) {
		this.falling = failing;
		
	}

	public void setGravity(float gravity) {
		// TODO Auto-generated method stub
		this.gravity=gravity;
	}

	public float getGravity() {
		// TODO Auto-generated method stub
		return this.gravity;
	}

	public ObjectID getID() {
		// TODO Auto-generated method stub
		return id;
	}
}
