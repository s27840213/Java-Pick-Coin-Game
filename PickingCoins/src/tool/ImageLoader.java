package tool;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/*
 * ��BufferImage�N�Ϥ����J
 */
public class ImageLoader {
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
