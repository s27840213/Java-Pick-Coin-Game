package tool;

import java.awt.image.BufferedImage;

public class Animation {
	private int speed, index;
	private BufferedImage[] frame;
	private long timer, lastTime;

	public Animation(int speed, BufferedImage[] frame) {
		this.speed = speed;
		this.frame = frame;
		timer = 0;
		index = 0;
		lastTime = System.currentTimeMillis();

	}

	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
//		System.out.println(timer);
		lastTime = System.currentTimeMillis();
		if (timer > speed) {
			index++;
			timer = 0;
			if (index >= frame.length) {
				index = 0;	
				
			}
		}

	}

	public BufferedImage getCurrentFrame() {
		return frame[index];
	}

}
