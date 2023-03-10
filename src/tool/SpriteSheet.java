package tool;
import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sheet;
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
		
	}
	//�q�@�i�Ӥ��W���U�����Ӥ�
	public BufferedImage crop(int x,int y,int width,int height) {
		return sheet.getSubimage(x, y, width, height);
	}
}
