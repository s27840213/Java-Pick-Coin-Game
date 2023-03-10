package state;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import audio.AudioPlayer;
import game.Game;
import tool.Animation;
import tool.Assets;

public class WinState extends State {
	Animation snowman;
	private float x = (game.width - Assets.winPlayer[0].getWidth()) / 2;
	private float y = 300,y_icon =0;;
	private float vely = -25,vely_icon=0;
	
	private float gravity = 2,a_icon =0.1f;;
	private boolean ground = false;
	public WinState(Game game) {
		super(game);
		snowman = new Animation(80, Assets.snowman);
		Assets.bgmWin.playLoop();
	}

	@Override
	public void tick() {
		if(vely_icon<=-2.5) {
			a_icon=0.1f;			
		}
		if(vely_icon>=2.5) {
			a_icon=-0.1f;
		}
		vely_icon+=a_icon;
		if (game.getKeyManager().r) {
			//game.init();
			game.gameState = new GameState(game);
			State.setState(game.gameState);
			Assets.bgmWin.stop();
			GameState.win=false;
			game.winState=null;
			GameState.starting = false;
		}
		snowman.tick();
		if(!ground)
			vely += 1;
		y += vely;
		y_icon+=vely_icon;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.win, 0, 0, null);
		g.drawImage(Assets.winIcon, (game.width-Assets.winIcon.getWidth())/2, (int)y_icon,null);
		g.drawImage(getCurrentFrame(), (int) x, (int) y, null);
		g.drawImage(snowman.getCurrentFrame(), 0, 330, null);
		g.drawImage(snowman.getCurrentFrame(), 100, 330, null);
		g.drawImage(snowman.getCurrentFrame(),200, 330, null);
		
		g.drawImage(snowman.getCurrentFrame(), game.width-Assets.snowman[0].getWidth(), 330, null);
		g.drawImage(snowman.getCurrentFrame(), game.width-Assets.snowman[0].getWidth()-100, 330, null);
		g.drawImage(snowman.getCurrentFrame(), game.width-Assets.snowman[0].getWidth()-200, 330, null);
	}

	public BufferedImage getCurrentFrame() {
		if (y >= 300) {
			ground = true;
			vely = -23;
			return Assets.winPlayer[1];
		}
		if (vely >= 0) {
			ground=false;
			return Assets.winPlayer[1];
		}
		if (vely < 0) {
			ground=false;
			return Assets.winPlayer[0];
		} else
			return Assets.winPlayer[2];
	}

}
