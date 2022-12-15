package game;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FallGame 
{
	FallObject[] insta = new FallObject[7];

	JFrame frame;
	int WIDTH = 700;
	int HEIGHT = 800;
	int ch_w = 100; //*캐릭터 폭
	int ch_h = 140; //* " 높이
	int ch_x = 300;//*캐릭터 좌표 0 ~ 583
	int ch_y = 800 - ch_h;    
	int xStep = 10;//*캐릭터가 이동할 방향,거리
	int yStep = 0;
	int score = 0;
	int heart=3;
	
	Image background = new ImageIcon("image/KSU.png").getImage();
	ImageIcon basicImage = new ImageIcon("image/basicman100px.png");
	ImageIcon sadImage = new ImageIcon("image/sadman100px.png");
	ImageIcon smaileImage = new ImageIcon("image/smaileman100px.png");
	Image emptyHeart = new ImageIcon("image/emptyheart40px.png").getImage();
	Image fullHeart = new ImageIcon("image/fullheart40px.png").getImage();	
	Image gameOver = new ImageIcon("image/gameover.png").getImage();	
	Image player = basicImage.getImage();
	Image score_A = new ImageIcon("image/A.png").getImage();
	Image score_C = new ImageIcon("image/C.png").getImage();
	Image score_F = new ImageIcon("image/F.png").getImage();
	
	JLabel label;
	
	
	public FallGame() {
		
		frame = new JFrame("FallGameDriver");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH,HEIGHT);
		
		for(int i=0; i<insta.length; i++)
		{
			insta[i] = new FallObject();
			int randomValue = (int) ( Math.random()*5)+3;
			insta[i].SPEED = randomValue;
		}
		
		frame.getContentPane().add(new GamePanel());
		frame.setVisible(true);
		frame.addKeyListener(new GameListener());
	}
	
	public void go()
	{
		int imagechange = 0;
		while(true)
		{
			for(int i=0; i<insta.length; i++) 
			{
				insta[i].moveY(800);
				
				if((ch_x-30 <= insta[i].X && insta[i].X <= ch_x+60)&& (ch_y+30>= insta[i].Y && insta[i].Y >= ch_y-30)) 
				{
					if(i < 5) 
					{
						player = sadImage.getImage();
						score -= 1;
					}
					else
					{
						player = smaileImage.getImage();
						if(i == 6) score += 5;//*전공책
						else score += 10;//*과제 하면 받는 점수
					}
					
					imagechange = 30;//*캐릭터의 표정이 바뀌는 시간을 조정
					label.setText("Score : " + score);
					insta[i].reStart();
				}
				
				
			}
			
			if(insta[5].Y>=750) 
			{
				heart -=1;
				insta[5].reStart();
			}
			
			if (imagechange > 0)
			{
				imagechange--;
				if(imagechange == 1)
					player = basicImage.getImage();
			}
			
			if(heart==0) 
			{
				break;
			}
			
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			frame.repaint();
		}
		
	}
	
	class GamePanel extends JPanel 
	{
		public GamePanel()
		{
			label = new JLabel();
			label.setText("Score : " + score);
			setLayout(null);
			add(label);
			label.setBounds(550,10, 150, 50);
			label.setFont(new Font("Serif",Font.BOLD,30));
		}
		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
			g.drawImage(background,WIDTH,HEIGHT, this);
			for(int i=0; i<insta.length; i++) {
				g.drawImage(insta[i].basicImage, insta[i].X, insta[i].Y, this);	
			g.drawImage(player,ch_x, ch_y,this);
			
			}
			
			for(int i=1; i<=heart; i++) 
			{	
				g.drawImage(fullHeart,30*i,30,this);
			}
			
			if(heart==0) {//*게임오버 창
				g.drawImage(gameOver,90,50,this);
				if(score<0) {
					g.drawImage(score_F,230,350,this);
				}
				if(score>0 && score<=30) {
					g.drawImage(score_C,230,350,this);
				}
				if(score>30) {
					g.drawImage(score_A,100,60,this);
				}
			}
			
		
		}
	}	
	class GameListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(ch_x > 0) {
					ch_x -= xStep ;
				}
				else
				{
					ch_x = 0;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(ch_x < 583) {
					ch_x += xStep;
				}
				break;
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
