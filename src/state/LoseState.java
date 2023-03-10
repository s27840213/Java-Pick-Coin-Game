package state;

import java.awt.Graphics;

import audio.AudioPlayer;
import game.Game;
import tool.Assets;

public class LoseState extends State {
	private float x=(game.width/2)-(Assets.gameoverText.getWidth()/2), y=(game.height/2)-(Assets.gameoverText.getHeight()/2)-50;
	private float vely=2;
	private float a = 0.5f;
	// center = game.width/2 - textWidth/2
	public LoseState(Game game) {
		super(game);
		Assets.bgmLose.playOnce();
	}

	@Override
	public void tick() {
		//System.out.println(vely);
		if(vely<=-2.5) {
			a=0.5f;			
		}
		if(vely>=2.5) {
			a=-0.5f;
		}
		vely+=a;
		//System.out.println((game.height/2)-(Assets.gameoverText.getHeight()/2)-50);
		if (game.getKeyManager().r) {
			//game.init();
			game.gameState = new GameState(game);
			State.setState(game.gameState);
			Assets.bgmLose.stop();
			GameState.lose=false;
			GameState.starting =false;
		}
		y+=vely;
	}

	// µe¥X­I´º¡A
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.gameover, 0, 0, null);
		g.drawImage(Assets.gameoverText,(int)x,(int)y,null); 
				}

}
