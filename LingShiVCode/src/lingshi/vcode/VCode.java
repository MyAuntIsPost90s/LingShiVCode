package lingshi.vcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码
 * 
 * @author caich
 *
 */
public class VCode implements BaseVCode {

	private Random random = null;
	private BufferedImage image = null;

	public VCode() {
		random = new Random();
	}

	@Override
	public String getVerifyCode(int len) {
		int codesLen = Constant.VERIFY_CODES.length();
		Random random = new Random(System.currentTimeMillis());
		StringBuilder verifyCode = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			verifyCode.append(Constant.VERIFY_CODES.charAt(random.nextInt(codesLen - 1)));
		}
		return verifyCode.toString();
	}

	@Override
	public Graphics2D getGraphics(int width, int height) {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D codeGraphics = image.createGraphics();
		return codeGraphics;
	}

	@Override
	public void setBackground(Graphics2D graphics) {
		graphics.setColor(new Color(255, 255, 255));
		graphics.fillRect(0, 2, image.getWidth(), image.getHeight() - 4);
	}

	@Override
	public void setBorder(Graphics2D graphics) {
		graphics.setColor(Color.GRAY);
		graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
	}

	@Override
	public void setInterfere(Graphics2D graphics) {
		// 绘制干扰线
		graphics.setColor(getRandColor(160, 200));// 设置线条的颜色
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(image.getWidth() - 1);
			int y = random.nextInt(image.getHeight() - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			graphics.drawLine(x, y, x + xl + 40, y + yl + 20);
		}
	}

	@Override
	public void setNoise(Graphics2D graphics) {
		float yawpRate = 0.05f;// 噪声率
		int area = (int) (yawpRate * image.getWidth() * image.getHeight());
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(image.getWidth());
			int y = random.nextInt(image.getHeight());
			int rgb = getRandomIntColor();
			image.setRGB(x, y, rgb);
		}
	}

	@Override
	public void setShear(Graphics2D graphics) {
		Color color = getRandColor(200, 250);
		shearX(graphics, image.getWidth(), image.getHeight(), color);
		shearY(graphics, image.getWidth(), image.getHeight(), color);
	}

	private void shearX(Graphics graphics, int width, int height, Color color) {
		int period = random.nextInt(2);
		int frames = 1;
		int phase = random.nextInt(2);
		for (int i = 0; i < height; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			graphics.copyArea(0, i, width, 1, (int) d, 0);
			graphics.setColor(color);
			graphics.drawLine((int) d, i, 0, i);
			graphics.drawLine((int) d + width, i, width, i);
		}
	}

	private void shearY(Graphics graphics, int width, int height, Color color) {
		int period = random.nextInt(40) + 10; // 50;
		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < width; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			graphics.copyArea(i, 0, 1, height, 0, (int) d);
			if (borderGap) {
				graphics.setColor(color);
				graphics.drawLine(i, (int) d, i, 0);
				graphics.drawLine(i, (int) d + height, i, height);
			}
		}
	}

	@Override
	public void setCode(Graphics2D graphics, String code) {
		graphics.setColor(getRandColor(100, 160));
		int fontSize = image.getHeight() - 4;
		Font font = new Font("Algerian", Font.ITALIC, fontSize);
		graphics.setFont(font);
		char[] chars = code.toCharArray();
		for (int i = 0; i < code.length(); i++) {
			AffineTransform affine = new AffineTransform();
			affine.setToRotation(Math.PI / 4 * random.nextDouble() * (random.nextBoolean() ? 1 : -1),
					(image.getWidth() / code.length()) * i + fontSize / 2, image.getHeight() / 2);
			graphics.setTransform(affine);
			graphics.drawChars(chars, i, 1, ((image.getWidth() - 10) / code.length()) * i + 5,
					image.getHeight() / 2 + fontSize / 2 - 10);
		}

		graphics.dispose();
	}

	@Override
	public Image getImage() {
		return image;
	}

	private int getRandomIntColor() {
		int[] rgb = getRandomRgb();
		int color = 0;
		for (int c : rgb) {
			color = color << 8;
			color = color | c;
		}
		return color;
	}

	private int[] getRandomRgb() {
		int[] rgb = new int[3];
		for (int i = 0; i < 3; i++) {
			rgb[i] = random.nextInt(255);
		}
		return rgb;
	}

	private Color getRandColor(int rangeBeg, int rangeEnd) {
		if (rangeBeg > 255) {
			rangeBeg = 255;
		}
		if (rangeEnd > 255) {
			rangeEnd = 255;
		}
		int r = rangeBeg + random.nextInt(rangeEnd - rangeBeg);
		int g = rangeBeg + random.nextInt(rangeEnd - rangeBeg);
		int b = rangeBeg + random.nextInt(rangeEnd - rangeBeg);
		return new Color(r, g, b);
	}
}
