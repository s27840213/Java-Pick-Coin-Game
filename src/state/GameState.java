package state;

import java.awt.Graphics;
import java.awt.dnd.DragGestureEvent;
import java.util.Random;

import audio.AudioPlayer;
import count_down.CountDownTimer;
import entity.Block;
import entity.Bomb;
import entity.Coin;
import entity.Player;
import game.Game;
import result_timer.ResultTimer;
import result_timer.ResultTimerTask;
import timer_PosibilityChange.PosibilityTimer;
import tool.Assets;
import tool.DropItemCreator;
import tool.MyHandler;
import tool.ObjectID;

public class GameState extends State {

	// private Player player;
	// private Block level;
	Coin coin;
	MyHandler handler;
	Random random = new Random();
	DropItemCreator dropItemCreator;
	PosibilityTimer timer;
	CountDownTimer countDownTimer;
	ResultTimer resultTimer;
	private boolean beep1,beep2,beep3,beep4;
	Player player;
	// AudioPlayer bgmusic;
	public static boolean win = false;
	public static boolean lose = false;
	public static int countdown = 3;
	private boolean played =false;
	public static boolean starting = false;

	public GameState(Game game) {
		// TODO Auto-generated constructor stub
		super(game);
		// player = new Player(game, 100, 100);
		handler = new MyHandler();
		countdown = 3;
		beep1=beep2=beep3=beep4=false;
		// handler.add(new Coin(game, random.nextInt(960 - Assets.coin.getWidth()), 0,
		// handler, ObjectID.Coin));
		player = new Player(game, (game.width - Assets.playerRight.getWidth()) / 2,
				(game.width - Assets.playerRight.getHeight()) / 2, handler, ObjectID.Player);
		handler.add(player);
		createLevel();
		dropItemCreator = new DropItemCreator(game, handler);
		timer = new PosibilityTimer(dropItemCreator);
		resultTimer = new ResultTimer(game);
		timer.run();
		countDownTimer = new CountDownTimer(game);
		countDownTimer.run();
		dropItemCreator.createAllCoin(game, handler);
		Assets.bgmGame.playLoop();
		Assets.sfx.get("start").playOnce();
	}

	@Override
	public void tick() {
		if(countdown==-1)
		{
			countDownTimer.stop();
			starting =true;
			countdown--;
		}
		// player.tick();
		handler.tick();
		if (!win &&!lose) {
			dropItemCreator.update();
			// System.out.println(time);
			// updateLastTime();
			// System.out.println(handler.getSize());
		}
		if (player.getHp() <= 0 && !lose) {
			Assets.sfx.get("die").playOnce();
			Assets.bgmGame.stop();
			lose = true;
			// game.loseState = new LoseState(game);
			// State.setState(game.loseState);
			// Assets.bgmGame.stop();
			// game.gameState=null;
			resultTimer.run();;
		}
		if (player.getScore1() == 5 && !win) {
			Assets.sfx.get("win").playOnce();
			win = true;
			resultTimer.run();
		}


	}

	// µe¥X­I´º¡A
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.background, 0, 0, null);
		if(GameState.countdown==3) {
			g.drawImage(Assets.start3, (game.width-Assets.start3.getWidth())/2,(game.height-Assets.start3.getHeight())/2,null);
		}
		if(GameState.countdown==2) {
			g.drawImage(Assets.start2, (game.width-Assets.start2.getWidth())/2,(game.height-Assets.start2.getHeight())/2,null);
		}
		if(GameState.countdown==1) {
			g.drawImage(Assets.start1, (game.width-Assets.start1.getWidth())/2,(game.height-Assets.start1.getHeight())/2,null);
		}
		if(GameState.countdown==0) {
			g.drawImage(Assets.go, (game.width-Assets.go.getWidth())/2,(game.height-Assets.go.getHeight())/2,null);
		}
//		if(GameState.countdown==3&&!beep1) {
//			Assets.sfx.get("beep1").playOnce();
//			beep1=true;
//		}
//		if(GameState.countdown==2&&!beep2) {
//			Assets.sfx.get("beep1").playOnce();
//			beep2=true;
//		}
//		if(GameState.countdown==1&&!beep3) {
//			Assets.sfx.get("beep1").playOnce();
//			beep3=true;
//		}
//		if(GameState.countdown==0&&!beep4) {
//			Assets.sfx.get("beep2").playOnce();
//			beep4=true;
//		}
		// g.drawImage(Assets, arg1, arg2, arg3, arg4, arg5)
		// player.render(g);

		handler.render(g);
	}

	public void createLevel() {

		for (int i = 0; i < game.width; i += 45) {
			handler.add(new Block(game, i, game.height - 45, ObjectID.Block));
		}

	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public boolean isLose() {
		return lose;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}



}
