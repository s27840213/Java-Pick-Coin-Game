package game;

import java.awt.Graphics;
import java.util.Timer;

import tool.Assets;

/*
 * ±Ò°Ê¹CÀ¸
 */
public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game("AnniePickingCoin", 960, 540);
		game.start();
	}

//	public static void loading () {
//		Graphics g = null;
//		g.drawImage(Assets.loading,0,0,null	);
//	}
}
