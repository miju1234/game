package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class FallObject {
	static int count = 0;//*���� �ʵ� ����
	int SPEED ;
	int X;
	int Y;
	String[] images = new String[7];
	Image basicImage;
	int[] startPoints = {50,150,250,350,450,550,650};
	
	public FallObject()
	{
		
		images[0] = "image/youtube30px.png";
		images[1] = "image/instagram30px.png";
		images[2] = "image/netflix30px.png";
		images[3] = "image/webtoon30px.png";
		images[4] = "image/kakao30px.png";
		images[5] = "image/task3040px.png";
		images[6] = "image/book3038px.png";
		
		basicImage = new ImageIcon(images[count]).getImage();
		count++;
		reStart();
		
	}
	public void moveY(int height)
	{
		Y += SPEED;
		if(height < Y)//*��ü�� ��� �������� ����
		{   
			reStart();//*reStart ȣ��
		}
	}
	
	public void reStart() {
		int randomValue = (int) ( Math.random() * 7);//*���� �Լ��� �̿��Ͽ� ��ü�� �������� ����
		X = startPoints[randomValue];
		Y = -10;
	}
}
