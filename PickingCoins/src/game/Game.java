package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.logging.Handler;

import audio.AudioPlayer;
import entity.Entity;
import entity.Player;
import state.GameState;
import state.LoseState;
import state.StartState;
import state.State;
import state.WinState;
import tool.Assets;
import tool.KeyManager;

public class Game implements Runnable {

	private Display display;
	private Thread thread;
	public int width, height;
	public String title;
	private boolean running = false;
	private boolean loadingDone = false;
	
	private BufferStrategy bs;
	private Graphics g;// 畫筆
	AudioPlayer bgmusic;

	// private SpriteSheet sheet;

	// State
	public State gameState;
	public State loseState;
	public State winState;
	public State startState;

	// Input
	public KeyManager keyManager;
	

	//Random num

	public Game(String title, int width, int height) {
		this.height = height;
		this.width = width;
		this.title = title;
		keyManager = new KeyManager();

	}
	public void init() {
		display = new Display(title, width, height);
		// 在Frame中加入KeyListener
		display.getFrame().addKeyListener(keyManager);
		// 將Assets中的物件載入
		Assets.init();
		
		// 狀態
		startState = new StartState(this);
		//gameState = new GameState(this);
//		loseState = new LoseState(this);
//		winState = new WinState(this);
		// 將當前狀態設置為GameState
		State.setState(startState);
		//


	}

	public void tick() { // update
		// 更新當下所按下(沒按下)的按鍵
		keyManager.tick();
		// 若當前狀態不為null,更新當下狀態
		if (State.getState() != null) {

			State.getState().tick();
			
		}
	}

	private void render() { // draw

		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, width, height);
		// Draw here

		if (State.getState() != null) {
			State.getState(	).render(g);
		}

		// End Drawing
		bs.show();
		g.dispose();
	}


	// if start,run the thread;
	public void run() {
		init();
		// Timer decalaration
		double fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (running) {

			// Timer operation;
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}

			if (timer >= 1000000000) {
				ticks = 0;
				timer = 0;
			}
		}
		stop();

	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();

	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
