import java.awt.*; 
import java.applet.*;
import java.awt.event.*;
/*<applet code="BallClash" width=1000 height=600></applet>*/
public class BallClash extends Applet implements Runnable,KeyListener{
	Thread t=null;	
	boolean stopFlag;int codination=0;
	int score=0;
	int boardcodinationx=400,boardbase=600,ovalcodinationx=470,ovalcodinationy=500,zip=0,rectanglex=500,rectangley=0;
	public void init(){
		addKeyListener(this);
		setBackground(Color.black);
		requestFocus();
	}
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e)
	{
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_ENTER&&boardcodinationx>0){
				if(zip==0){
					t=new Thread(this);
					stopFlag=true;
					t.start();
					zip=1;
				}
		}
		if(key==KeyEvent.VK_RIGHT)if(boardcodinationx<800&&zip==1){
		 	boardcodinationx=boardcodinationx+30;
		 	boardbase+=30;
		}
		if(key==KeyEvent.VK_LEFT)if(boardcodinationx>0&&zip==1){
			boardcodinationx=boardcodinationx-30;
			boardbase-=30;
		}
		if(key==KeyEvent.VK_SPACE){
				t=new Thread(this);
				stopFlag=true;
				boardbase=600;
				ovalcodinationx=470;
				boardcodinationx=400;
				ovalcodinationy=500;
				zip=0;
				codination=0;
				t.start();
		}
		repaint();
	}
	public void keyTyped(KeyEvent e){}
	public void start(){}
	public void run(){
		for(;stopFlag&&zip==1;){
			try{
					repaint();
					Thread.sleep(50);
					rectangley+=10;
					//    >>> LEFT UP SIDE MOVE   <<<<
					if(boardcodinationx+100<ovalcodinationx&&boardbase>ovalcodinationx&&ovalcodinationy>500)codination=1;
					if(boardcodinationx<ovalcodinationx&&boardbase-100>ovalcodinationx&&ovalcodinationy>500)codination=0;
					if(boardcodinationx<rectanglex&&rectanglex>ovalcodinationx&&rectangley>550&&rectangley<600)score+=10;
					if(ovalcodinationy>600){
						zip=10;
						repaint();
						stopFlag=false;
						break;
					}
					if(codination==0){
						ovalcodinationx-=10;
						ovalcodinationy-=5;
						if(ovalcodinationx<0)codination=1;
						if(ovalcodinationy<0)
						{
								codination=3;
								rectangley=ovalcodinationy;
								rectanglex=ovalcodinationx;
						}
					}
					//    >>>> LEFT UP MOVE  <<<<
					if(codination==1){
						ovalcodinationx+=10;
						ovalcodinationy-=10;
						if(ovalcodinationy<0)
						{
							codination=2;
							rectangley=ovalcodinationy;
							rectanglex=ovalcodinationx;
						}
						if(ovalcodinationx>950)codination=0;
					}
					//   >>> RIGHT DOWN MOVE  <<<<
					if(codination==2){
						ovalcodinationx+=8;
						ovalcodinationy+=4;
						if(ovalcodinationx>950)codination=3;
					}
					//  >>> RIGHT DOWN MOVE   <<<<
					if(codination==3){
						ovalcodinationx-=8;
						ovalcodinationy+=4;
						if(ovalcodinationx<0)codination=2;
					}
					if(stopFlag==false)break;
				}catch(InterruptedException e){}
			}
	}
	public void stop(){}
	public void paint(Graphics g){
		if(zip==0){
			g.setColor(Color.white);
			g.drawString("(  ENTER TO START  )",450,300);
		}
		if(zip==10){
			g.setColor(Color.white);
			g.drawString("(  END THE GAME  )",450,300);
		}
		g.setColor(Color.white);
		g.drawString("SCORE:"+score,800,50);
		//===========================>>  Rect angle codination   <<<<<==================
		g.setColor(Color.green);
		g.fillRoundRect(rectanglex,rectangley,50,50,10,10);
		//==========================>>   Hit Board   <<<======================
		g.setColor(Color.blue);
		g.fillRoundRect(boardcodinationx,550,200,10,10,10);
		//=========================>> Ball Codination  <<================
		g.setColor(Color.pink);
		g.fillOval(ovalcodinationx,ovalcodinationy,50,50);
		g.setColor(Color.white);
		g.fillRect(0,560,1000,50);
	}
}
