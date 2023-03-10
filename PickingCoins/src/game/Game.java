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
	private Graphics g;// �e��
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
		// �bFrame���[�JKeyListener
		display.getFrame().addKeyListener(keyManager);
		// �NAssets����������J
		Assets.init();
		
		// ���A
		startState = new StartState(this);
		//gameState = new GameState(this);
//		loseState = new LoseState(this);
//		winState = new WinState(this);
		// �N��e���A�]�m��GameState
		State.setState(startState);
		//


	}

	public void tick() { // update
		// ��s��U�ҫ��U(�S���U)������
		keyManager.tick();
		// �Y��e���A����null,��s��U���A
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
