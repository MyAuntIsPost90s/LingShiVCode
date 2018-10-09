package lingshi.vcode;

import java.awt.Graphics2D;
import java.awt.Image;

interface BaseVCode {

	/**
	 * 生成随机数
	 * 
	 * @param len
	 * @return
	 */
	String getVerifyCode(int len);

	/**
	 * 获取画布
	 * 
	 * @return
	 */
	Graphics2D getGraphics(int width, int height);

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
	 * 设置干扰
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
	 * 设置二维码
	 * 
	 * @param code
	 */
	void setCode(Graphics2D graphics, String code);

	/**
	 * 获取图片
	 * 
	 * @return
	 */
	Image getImage();
}
