package lingshi.vcode;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 验证码构造
 * 
 * @author caich
 *
 */
public class VCodeBuilder {

	public static void main(String[] args) throws IOException {
		File dir = new File("C:/log");
		int w = 200, h = 80;
		for (int i = 0; i < 1; i++) {
			String verifyCode = getVCode(4);
			Image image = getVCodeImage(w, h, verifyCode);
			File file = new File(dir, verifyCode + ".png");
			ImageIO.write((RenderedImage)image, "png", file);
		}
	}
	
	public static Image getVCodeImage(int width, int height, String code) {
		BaseVCode vCode = new VCode();
		Graphics2D graphics = null;
		try {
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			graphics = vCode.getGraphics(image);
			vCode.setBorder(graphics);
			vCode.setBackground(graphics);
			vCode.setInterfere(graphics);
			vCode.setNoise(graphics);
			vCode.setShear(graphics);
			vCode.setCode(graphics, code);
			return image;
		} finally {
			if (graphics != null) {
				graphics.dispose();
			}
		}
	}

	public static String getVCode(int size) {
		return new VCode().getVerifyCode(size);
	}

}
