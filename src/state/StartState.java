package state;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import game.Game;
import tool.Assets;
import tool.ImageLoader;
import tool.KeyManager;

public class StartState extends State{
	KeyManager keyManager;
	BufferedImage start ;
	public StartState(Game game) {
		super(game);
		
	}

	@Override
	public void tick() {
		if(game.getKeyManager().enter) {
			game.gameState = new GameState(game);
			State.setState(game.gameState);
			game.startState = null;
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.StartBg,0,0,null);

	}

}
