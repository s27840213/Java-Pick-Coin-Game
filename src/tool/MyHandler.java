package tool;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import entity.Block;
import entity.Coin;
import entity.Entity;
import entity.Point;
import game.Game;
/*
 * 用來加入物件、移除物件、計算物件
 */
public class MyHandler {
	public ArrayList<Entity> entityList = new ArrayList<Entity>();
	public ArrayList<Entity> coinList = new ArrayList<Entity>();
	private boolean firstCreate =true;
	private int i2=0;
	private Entity tempEntity;
	private Entity pointList;
	private Entity coin;
	public void tick() {
		
		for(int i=0;i<entityList.size();i++) {
			tempEntity = entityList.get(i);
			tempEntity.tick(entityList);
		}
		//##############change
//		update();
//		for(int i=0;i<entityList.size();i++) {
//			tempEntity = entityList.get(i);
//			tempEntity.tick(entityList);
//			if(tempEntity.getID()!=ObjectID.Coin) {
//			tempEntity.tick(entityList);
//			}
//			if(tempEntity.getID()==ObjectID.Coin) {
//				for(;i2<coinList.size();i2++) {
//					coin = coinList.get(i2);
//
//					System.out.println(i2);
//					coin.tick(coinList);
//				}
//
//			}
//		}
	}	
	public void render(Graphics g) {
		for(int i=0;i<entityList.size();i++) {
			tempEntity = entityList.get(i);
			tempEntity.render(g);
		}
		//############Change 
//		for(int i=0;i<entityList.size();i++) {
//			tempEntity = entityList.get(i);
//			if(tempEntity.getID()!=ObjectID.Coin) {
//			tempEntity.render(g);
//			}
//			if(tempEntity.getID()==ObjectID.Coin) {
////				addCoinList(tempEntity);
//				if(count-lastCount==1) {
//					System.out.println("hi");
//					for(i=0;i<coinList.size();) {
//						coin=coinList.get(i);
//						System.out.println("coin");
//						coin.created = true;
//						firstCreate = true;
//						i++;
//					}
//				lastCount = count;
//				}
//				
//			
//			}
//			coin.render(g);
//		}
		
	//	System.out.println(entityList.size());
	}
	public void add(Entity entity) {
		this.entityList.add(entity);
	}

	public  void remove(Entity entity) {
		this.entityList.remove(entity);
	}


	
}
