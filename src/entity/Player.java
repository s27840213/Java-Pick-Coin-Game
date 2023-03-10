package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import audio.AudioPlayer;
import game.Game;
import playerState.RecoverTimer;
import state.GameState;
import tool.Animation;
import tool.Assets;
import tool.MyHandler;
import tool.ObjectID;

public class Player extends Entity {
	// sound effect
	// private HashMap<String, AudioPlayer>sfx;
	MyHandler handler;
	private float max_vel = -10;
	private boolean handling = true;
	private boolean facingRight = true;
	private boolean ground = false;
	private boolean hurted = false;

	private int hp = 5;
	private int score0 = 0;
	private int score1 = 0;

	Point point;
	Explosion explosion;
	Animation runLeft;
	Animation runRight;
	Animation runUp;
	Animation runDown;
	Animation standStil;

	// constructor -> set initial coordinate,and the which game it should be
	// contained
	public Player(Game game, float x, float y, MyHandler handler, ObjectID id) {
		super(game, x, y, id);
		this.handler = handler;
		setVelx(10);
		setVely(10);
		gravity = -2;
		// sound effect
		// sfx = new HashMap<String,AudioPlayer>();
		// sfx.put("coin", new AudioPlayer("/audio/coinPick.mp3"));
		// sfx.put("boom",new AudioPlayer("/audio/boom.wav"));
		// sfx.put("jump",new AudioPlayer("/audio/jump.mp3"));
		// sfx.put("hurt",new AudioPlayer("/audio/hurt.wav"));

		runLeft = new Animation(100, Assets.run_left);
		runRight = new Animation(100, Assets.run_Right);
		// runLeft = new Animation(100, Assets.run_left);
		// runLeft = new Animation(100, Assets.run_left);
	}

	// update,when press up,left,right,down, change the cordinate
	public void tick(ArrayList<Entity> entityList) {

		// System.out.println(entityList);
		// Animation
		if (GameState.starting) {
		runLeft.tick();
		runRight.tick();
		y -= (vely);
		// System.out.println(this.id);

			if ((game.getKeyManager().up||game.getKeyManager().W) && (!jumping && !falling) && (!GameState.lose) && !GameState.win) {
				setVely(30);
				setJumping(true);
				setHandling(false);
				Assets.sfx.get("jump").playOnce();

			}
			if ((game.getKeyManager().left||game.getKeyManager().A) && (!GameState.lose) && !GameState.win) {
				x -= velx;
				setHandling(false);
				setFacingRight(false);
			}
			if ((game.getKeyManager().right||game.getKeyManager().D) && (!GameState.lose) && !GameState.win) {
				x += velx;
				setHandling(false);
				setFacingRight(true);

			}
		}
		// if (game.getKeyManager().down) {
		// y += velx;
		// setHandling(false);
		//
		// }
		if (vely < 0) {
			jumping = false;
			falling = true;
		}
		/*
		 * 不讓Player跑出遊戲畫面
		 */
		if (x < 1)
			x = 1;
		if (x > game.width - Assets.run_left[0].getWidth())
			x = game.width - Assets.run_left[0].getWidth();
		if (y < 1)
			y = 1;
		if (y > game.height - Assets.run_left[0].getHeight()) {
			y = game.height - Assets.run_left[0].getHeight();

		}
		if (jumping || falling) {
			vely += gravity;
			if (vely < max_vel) {
				vely = max_vel;
			}
		}

		collision(entityList);

	}

	// draw the player(image loaded in Assets class)
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(getCurrentPlayerAnimationFrame(), (int) x, (int) y, null);
		g.drawImage(getCurrentHpFrame(), 0, 0, Assets.hp[0].getWidth(), Assets.hp[0].getHeight(), null);
		g.drawImage(getCurrentScore0Frame(), 850, 0, Assets.score[0].getWidth(), Assets.score[0].getHeight(), null);
		g.drawImage(getCurrentScore1Frame(), 780, 0, Assets.score[0].getWidth(), Assets.score[0].getHeight(), null);
		Graphics2D g2d = (Graphics2D) g;
		// draw Bound
		// g2d.setColor(Color.red);
		// g2d.draw(getUpperBound());
		// g2d.draw(getLowerBound());
	}

	private void collision(ArrayList<Entity> entityList) {
		for (int i = 0; i < handler.entityList.size(); i++) {
			// if(handler.entityList.get(i)=null)
			// System.out.println("null");
			Entity tempEntity = entityList.get(i);

			/*
			 * ############################## 碰撞判定:Player v.s Block
			 */
			if (tempEntity.getID() == ObjectID.Block) {
				if (getUpperBound().intersects(tempEntity.getUpperBound())
						|| getLowerBound().intersects(tempEntity.getUpperBound())) {
					y = tempEntity.getY() - (getCurrentPlayerAnimationFrame().getHeight()) + 5;
					setVely(0);
					falling = false;
					jumping = false;
					ground = true;
				}
				// else{
				// falling=true;
				// }
			}
			/*
			 * #############################
			 */

			if (!GameState.win && !GameState.lose) {
				/*
				 * ############################## 碰撞判定:Player v.s Coin
				 */
				if (tempEntity.getID() == ObjectID.Coin) {
					if ((getUpperBound().intersects(tempEntity.getUpperBound())
							|| getLowerBound().intersects(tempEntity.getUpperBound()))) {
						// System.out.println("You earn a point!");
						tempEntity.y = y - Assets.coin[1].getHeight();
						tempEntity.setVely(0);
						Assets.sfx.get("coin").playOnce();
						point = new Point(game, tempEntity.x, (tempEntity.y + 50), handler, ObjectID.Point);
						handler.remove(tempEntity);
						handler.add(point);
						if (score0 < 10) {
							score0++;
							if (score0 == 10) {
								score0 = 0;
								score1++;
							}
						}
					}

				}
				/*
				 * #############################
				 */

				/*
				 * ############################## 碰撞判定:Player v.s Bomb
				 */
				if (tempEntity.getID() == ObjectID.Bomb) {
					if ((getUpperBound().intersects(tempEntity.getUpperBound())
							|| getLowerBound().intersects(tempEntity.getUpperBound()))) {
						// System.out.println("You lose HP!");
						// tempEntity.y = y - Assets.bomb[1].getHeight();
						tempEntity.setVely(0);
						// explosion = new Explosion(game, tempEntity.x, tempEntity.y,handler,
						// ObjectID.Explosion);
						explosion = new Explosion(game,
								tempEntity.x - ((Assets.explsion[2].getWidth() - Assets.bomb[0].getWidth()) / 2),
								tempEntity.y - ((Assets.explsion[2].getHeight() - Assets.bomb[0].getHeight()) / 2),
								handler, ObjectID.Explosion);
						handler.add(explosion);
						handler.remove(tempEntity);
						if (hp > 1)
							Assets.sfx.get("hurt").playOnce();
						hp--;
						hurted = true;
						setVelx(0);
						RecoverTimer recoverTimer = new RecoverTimer(this);
						recoverTimer.run();
						Assets.sfx.get("boom").playOnce();

					}

				}
				/*
				 * #############################
				 */
			}
		}
	}

	@Override
	public Rectangle getUpperBound() {
		return new Rectangle((int) x, (int) y, Assets.run_left[0].getWidth(), Assets.run_left[0].getHeight() / 2);
	}

	public Rectangle getLowerBound() {
		return new Rectangle((int) x + (Assets.run_left[0].getWidth()) / 5,
				(int) y + Assets.run_left[0].getHeight() / 2, Assets.run_left[0].getWidth() / 2,
				(Assets.run_left[0].getHeight() / 2));
	}

	public boolean isHandling() {
		return handling;
	}

	public void setHandling(boolean handling) {
		this.handling = handling;
	}

	public BufferedImage getCurrentPlayerAnimationFrame() {
		// if (game.getKeyManager().up) {
		//
		//
		// }
		if(!GameState.starting) {
			return Assets.playerRight;
		}
		if (GameState.lose && facingRight) {
			return Assets.dieRight;
		}
		if (GameState.lose && !facingRight) {
			return Assets.dieLeft;
		}
		if (GameState.win) {
			return Assets.playerWin;
		}

		if (hurted && facingRight) {
			return Assets.hurted;
		}
		if (hurted && !facingRight) {
			return Assets.hurted_left;
		}
		if (jumping && facingRight) {
			return Assets.jump_right;
		}
		if (jumping && !facingRight) {
			return Assets.jump_left;
		}
		if (falling && facingRight) {
			return Assets.fall_right;
		}
		if (falling && !facingRight) {
			return Assets.fall_left;
		}
		if (game.getKeyManager().left||game.getKeyManager().A) {
			return runLeft.getCurrentFrame();
		}
		if (game.getKeyManager().right||game.getKeyManager().D) {
			return runRight.getCurrentFrame();
		}
		// if (game.getKeyManager().down) {
		// y += velx;
		// setHandling(false);
		// }
		else if (facingRight)
			return Assets.playerRight;
		else
			return Assets.playerLeft;
	}

	public BufferedImage getCurrentHpFrame() {
		switch (hp) {
		case 5:
			return Assets.hp[5];
		case 4:
			return Assets.hp[4];

		case 3:
			return Assets.hp[3];

		case 2:
			return Assets.hp[2];

		case 1:
			return Assets.hp[1];

		default:
			return Assets.hp[0];
		}
	}

	public BufferedImage getCurrentScore0Frame() {
		switch (score0) {
		case 0:
			return Assets.score[0];
		case 1:
			return Assets.score[1];
		case 2:
			return Assets.score[2];
		case 3:
			return Assets.score[3];
		case 4:
			return Assets.score[4];
		case 5:
			return Assets.score[5];
		case 6:
			return Assets.score[6];
		case 7:
			return Assets.score[7];
		case 8:
			return Assets.score[8];
		case 9:
			return Assets.score[9];
		default:
			return Assets.score[0];
		}
	}

	public BufferedImage getCurrentScore1Frame() {
		switch (score1) {
		case 0:
			return Assets.score[0];
		case 1:
			return Assets.score[1];
		case 2:
			return Assets.score[2];
		case 3:
			return Assets.score[3];
		case 4:
			return Assets.score[4];
		case 5:
			return Assets.score[5];
		case 6:
			return Assets.score[6];
		case 7:
			return Assets.score[7];
		case 8:
			return Assets.score[8];
		case 9:
			return Assets.score[9];
		default:
			return Assets.score[0];
		}
	}

	public boolean isFacingRight() {
		return facingRight;
	}

	public void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public boolean isHurted() {
		return hurted;
	}

	public void setHurted(boolean hurted) {
		this.hurted = hurted;
	}

}
