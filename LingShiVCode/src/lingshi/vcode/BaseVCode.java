package lingshi.vcode;

import java.awt.Graphics2D;
import java.awt.Image;

interface BaseVCode {

	/**
	 * ���������
	 * 
	 * @param len
	 * @return
	 */
	String getVerifyCode(int len);

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	Graphics2D getGraphics(int width, int height);

	/**
	 * ���ñ���
	 * 
	 * @param graphics
	 * @param width
	 * @param height
	 */
	void setBackground(Graphics2D graphics);

	/**
	 * ���ñ߿�
	 */
	void setBorder(Graphics2D graphics);

	/**
	 * ���ø���
	 */
	void setInterfere(Graphics2D graphics);

	/**
	 * �������
	 * 
	 * @param graphics
	 */
	void setNoise(Graphics2D graphics);

	/**
	 * ����Ť��
	 * 
	 * @param graphics
	 * @param width
	 * @param height
	 */
	void setShear(Graphics2D graphics);

	/**
	 * ���ö�ά��
	 * 
	 * @param code
	 */
	void setCode(Graphics2D graphics, String code);

	/**
	 * ��ȡͼƬ
	 * 
	 * @return
	 */
	Image getImage();
}
