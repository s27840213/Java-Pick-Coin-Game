package entity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Handler;

import game.Game;
import timer_remove_ground_item.CoinTimer;
import tool.Animation;
import tool.Assets;
import tool.MyHandler;
import tool.ObjectID;

public class Bomb extends Entity{
	
	private static final float MAX_SPEED = 15;
	private boolean ground = false;
	Animation animation;
	MyHandler handler;
	Random random = new Random();
	
	public Bomb(Game game,float x, float y,MyHandler handler,ObjectID id) {
		super(game,x, y,id);
		this.game=game;
		this.handler=handler;
		setVelx(random.nextInt(15)-5);
		setVely(random.nextInt(3));
		gravity = (float) 0.3;
		animation = new Animation(80, Assets.bomb);
//		createCoin(handler, this);
	}

	public void tick(ArrayList<Entity> entityList2) {
		vely+=gravity;
		if(vely>MAX_SPEED)
			vely=MAX_SPEED;
		if(velx>MAX_SPEED)
			velx=MAX_SPEED;
		y+=vely;
		x+=velx;
		if (x < 1)
			//x = 1;
			velx=-velx;
		if (x > game.width - Assets.bomb[0].getWidth())
			//x = game.width - Assets.bomb[0].getWidth();
			velx = -velx;

		collision(entityList2);
		animation.tick();
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(animation.getCurrentFrame(),(int)x,(int)y,null);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.red);
		//g2d.draw(getBound());
	}
	
	private void collision(ArrayList<Entity> entityList2) {
		for (int i = 0; i < handler.entityList.size(); i++) {
			Entity tempEntity = handler.entityList.get(i);
			
			/*##############################
			 * 	¸I¼²§P©w:bomb v.s Block
			 */
			if(tempEntity.getID() == ObjectID.Block) {
				if(getUpperBound().intersects(tempEntity.getUpperBound())) {
					y=tempEntity.getY()-(Assets.bomb[0].getHeight());
					setVely(0);
					if(getVelx()>0) {
					setVelx(11);
					}
					if(getVelx()<0) {
						setVelx(-11);
					}
					ground = true;
//					falling=false;
//					jumping=false;
				}
				else{
					//falling=true;
					ground = false;
				}
			}
			if(ground==true) {
				CoinTimer timer = new CoinTimer(handler, this);
				timer.setDelay(1500);
				//System.out.println(timer.getDelay());
				timer.run();
			}
			/*
			 * #############################
			 */
		}
	}

//	public void createCoin(MyHandler handler,Coin coin) {
//	timer = new GeneratorTimer(game,handler,coin);
//	timer.run();
//}
	
	
	@Override
	public Rectangle getUpperBound() {
		// TODO Auto-generated method stub
		return new Rectangle((int) x, (int) y, Assets.bomb[0].getWidth(), Assets.bomb[0].getHeight());
	}





}
