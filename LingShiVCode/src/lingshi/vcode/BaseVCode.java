package lingshi.vcode;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

interface BaseVCode {

	/**
	 * 获取验证码
	 * 
	 * @param len
	 * @return
	 */
	String getVerifyCode(int len);

	/**
	 * 获取画笔
	 * 
	 * @return
	 */
	Graphics2D getGraphics(BufferedImage image);

	/**
	 * 设置背景
	 * 
	 * @param graphics
	 * @param width
	 * @param height
	 */
	void setBackground(Graphics2D graphics);

	/**
	 * 设置边框
	 */
	void setBorder(Graphics2D graphics);

	/**
	 * 设置干扰线
	 */
	void setInterfere(Graphics2D graphics);

	/**
	 * 设置噪点
	 * 
	 * @param graphics
	 */
	void setNoise(Graphics2D graphics);

	/**
	 * 设置扭曲
	 * 
	 * @param graphics
	 * @param width
	 * @param height
	 */
	void setShear(Graphics2D graphics);

	/**
	 * 设置验证码
	 * 
	 * @param code
	 */
	void setCode(Graphics2D graphics, String code);
}
