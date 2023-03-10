package tool;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import audio.AudioPlayer;

/*
 
Put all assets such as image,music here.
	

*/
public class Assets {

	//start
	public static BufferedImage guide;
	public static BufferedImage goal;
	public static BufferedImage itmeGuide;
	public static BufferedImage oprationGuide;
	public static BufferedImage start;
	public static BufferedImage StartBg;
	public static BufferedImage loading;
	public static BufferedImage start1;
	public static BufferedImage start2;
	public static BufferedImage start3;
	public static BufferedImage go;
	//
	public static BufferedImage background;
	public static BufferedImage gameover;
	public static BufferedImage win;
	public static BufferedImage winIcon;
	public static BufferedImage test;
	public static BufferedImage point;
	public static BufferedImage gameoverText;
	//coin
	public static BufferedImage[] coin;
	//player
	public static BufferedImage playerRight,playerLeft,playerWin;
	public static BufferedImage[] run_left;
	public static BufferedImage[] run_Right;
	public static BufferedImage jump_right;
	public static BufferedImage fall_right;
	public static BufferedImage jump_left;
	public static BufferedImage fall_left;
	public static BufferedImage hurted;
	public static BufferedImage hurted_left;
	public static BufferedImage dieRight;
	public static BufferedImage dieLeft;
	public static BufferedImage[] hp;
	public static BufferedImage[] score;
	//bomb
	public static BufferedImage[] bomb;
	//explosion
	public static BufferedImage[] explsion;
	//winState
	public static BufferedImage[] winPlayer;
	//snowman
	public static BufferedImage[] snowman;
	/*
	 * ############音樂###################
	 */
	public static HashMap<String, AudioPlayer>sfx;
	public static AudioPlayer bgmGame;
	public static AudioPlayer bgmWin;
	public static AudioPlayer bgmLose;
	public static AudioPlayer bgmStart;
	

	public static void init() {
	//SpriteSheet
		SpriteSheet coinSheet = new SpriteSheet(ImageLoader.loadImage("/textures/coin.png"));
		SpriteSheet run_left_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/player/run_left.png"));
		SpriteSheet run_right_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/player/run_right.png"));
		//SpriteSheet hp_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/hp.png"));
		//start
		guide = ImageLoader.loadImage("/textures/guide/BasicGuide.png");
		goal = ImageLoader.loadImage("/textures/guide/Goal.png");
		itmeGuide = ImageLoader.loadImage("/textures/guide/ItemGuide.png");
		oprationGuide = ImageLoader.loadImage("/textures/guide/opGuide.png");
		StartBg =  ImageLoader.loadImage("/textures/guide/bg2.png");
		loading = ImageLoader.loadImage("/textures/guide/loading.png");
		start1 = ImageLoader.loadImage("/textures/guide/1.png");
		start2 = ImageLoader.loadImage("/textures/guide/2.png");
		start3 = ImageLoader.loadImage("/textures/guide/3.png");
		go = ImageLoader.loadImage("/textures/guide/go.png");
		
		//coin
		coin = new BufferedImage[6];
		for(int i=0;i<6;i++) {
			coin[i] = coinSheet.crop(0+46*i, 0, 46, 46);
		}
		point = ImageLoader.loadImage("/textures/point.png");
		//player
		dieLeft = ImageLoader.loadImage("/textures/player/dieLeft.png");
		dieRight = ImageLoader.loadImage("/textures/player/die.png");
		run_left = new BufferedImage[6];
		run_Right = new BufferedImage[6];
		for(int i=0;i<6;i++) {
			run_left[i] = run_left_sheet.crop(0+80*i, 0, 80, 105);
		}
		for(int i=0;i<6;i++) {
			run_Right[i] = run_right_sheet.crop(0+80*i, 0, 80, 105);
		}
		
		jump_right = run_right_sheet.crop(0, 105, 85, 115);
		fall_right =  run_right_sheet.crop(85, 105, 85, 115);
		fall_left =  run_left_sheet.crop(0, 105, 85, 115);
		jump_left = run_left_sheet.crop(85, 105, 85, 115);
		playerRight = ImageLoader.loadImage("/textures/player/playerRight.png");
		playerLeft =ImageLoader.loadImage("/textures/player/playerLeft.png");
		hurted = ImageLoader.loadImage("/textures/player/hurted.png");
		hurted_left = ImageLoader.loadImage("/textures/player/hurted_left.png");
		playerWin = ImageLoader.loadImage("/textures/player/playerWin.png");
		
		//bomb
		bomb = new BufferedImage[8];
		bomb[0] = ImageLoader.loadImage("/textures/bomb/bomb0.png");
		bomb[1] = ImageLoader.loadImage("/textures/bomb/bomb1.png");
		bomb[2] = ImageLoader.loadImage("/textures/bomb/bomb2.png");
		bomb[3] = ImageLoader.loadImage("/textures/bomb/bomb3.png");
		bomb[4] = ImageLoader.loadImage("/textures/bomb/bomb4.png");
		bomb[5] = ImageLoader.loadImage("/textures/bomb/bomb5.png");
		bomb[6] = ImageLoader.loadImage("/textures/bomb/bomb6.png");
		bomb[7] = ImageLoader.loadImage("/textures/bomb/bomb7.png");
		
		explsion = new BufferedImage[8];
		explsion[0] =ImageLoader.loadImage("/textures/explosion/explosion0.png");
		explsion[1] =ImageLoader.loadImage("/textures/explosion/explosion1.png");
		explsion[2] =ImageLoader.loadImage("/textures/explosion/explosion2.png");
		explsion[3] =ImageLoader.loadImage("/textures/explosion/explosion3.png");
		explsion[4] =ImageLoader.loadImage("/textures/explosion/explosion4.png");
		explsion[5] =ImageLoader.loadImage("/textures/explosion/explosion5.png");
		explsion[6] =ImageLoader.loadImage("/textures/explosion/explosion6.png");
		explsion[7] =ImageLoader.loadImage("/textures/explosion/explosion7.png");

		//hp
		hp = new BufferedImage[6];		
		hp[0] = ImageLoader.loadImage("/textures/hp/hp0.png");
		hp[1] = ImageLoader.loadImage("/textures/hp/hp1.png");
		hp[2] = ImageLoader.loadImage("/textures/hp/hp2.png");
		hp[3] = ImageLoader.loadImage("/textures/hp/hp3.png");
		hp[4] = ImageLoader.loadImage("/textures/hp/hp4.png");
		hp[5] = ImageLoader.loadImage("/textures/hp/hp5.png");

		//score
		score = new BufferedImage[10];
		score[0] = ImageLoader.loadImage("/textures/score/score0.png");
		score[1] = ImageLoader.loadImage("/textures/score/score1.png");
		score[2] = ImageLoader.loadImage("/textures/score/score2.png");
		score[3] = ImageLoader.loadImage("/textures/score/score3.png");
		score[4] = ImageLoader.loadImage("/textures/score/score4.png");
		score[5] = ImageLoader.loadImage("/textures/score/score5.png");
		score[6] = ImageLoader.loadImage("/textures/score/score6.png");
		score[7] = ImageLoader.loadImage("/textures/score/score7.png");
		score[8] = ImageLoader.loadImage("/textures/score/score8.png");
		score[9] = ImageLoader.loadImage("/textures/score/score9.png");
		
		//win
		winPlayer = new BufferedImage[3];
		winPlayer[0] = ImageLoader.loadImage("/textures/player/winPlayer0.png");
		winPlayer[1] = ImageLoader.loadImage("/textures/player/winPlayer1.png");
		winPlayer[2] = ImageLoader.loadImage("/textures/player/winPlayer2.png");
		winIcon = ImageLoader.loadImage("/textures/winicon.png");
		snowman = new BufferedImage[8];
		for(int i=0;i<8;i++) {
			String path = "/textures/snowman/snowman"+i+".png";
			snowman[i] = ImageLoader.loadImage(path);
		}
		
		background = ImageLoader.loadImage("/textures/background.png");
		gameover = ImageLoader.loadImage("/textures/gameOver2.png");
		gameoverText = ImageLoader.loadImage("/textures/gameOverText.png");
		win =  ImageLoader.loadImage("/textures/win.png");
		
		/*
		 * ############################音效###############################
		 */
		bgmGame = new AudioPlayer("/audio/bgmGame.mp3");
		bgmLose = new AudioPlayer("/audio/bgmLose.mp3");
		bgmWin = new AudioPlayer("/audio/bgmWin.mp3");
		sfx = new HashMap<String,AudioPlayer>();
		sfx.put("coin", new AudioPlayer("/audio/coinPick.mp3"));
		sfx.put("boom",new AudioPlayer("/audio/boom.wav"));
		sfx.put("jump",new AudioPlayer("/audio/jump.mp3"));
		sfx.put("hurt",new AudioPlayer("/audio/hurt.wav"));
		sfx.put("die", new AudioPlayer("/audio/die.wav"));
		sfx.put("win", new AudioPlayer("/audio/win.wav"));
		sfx.put("start", new AudioPlayer("/audio/start.wav"));
		sfx.put("beep1", new AudioPlayer("/audio/beep1.wav"));
		sfx.put("beep2", new AudioPlayer("/audio/beep2.wav"));
	}
}
