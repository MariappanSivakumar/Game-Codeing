import java.awt.*;
import java.applet.*;
import java.awt.event.*;
/*<applet code="BallGame" width=500 height=50></applet>*/
public class BallGame extends Applet implements Runnable,KeyListener
{
String s="_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_";
Thread t=null;
String M=" ";
int S,In=0,i,t1=0,x=30,y=20,r1=30,r2=30,r11=400,r12=20,r111=250,r112=10,w=5,h=17,h1=27,h2=37;
boolean stopFlag;
public void init()
{
addKeyListener(this);
setBackground(Color.cyan);
requestFocus();
}
public void keyReleased(KeyEvent e)
{
x=30;
}
public void keyPressed(KeyEvent e)
{
int key=e.getKeyCode();
if(key==KeyEvent.VK_ENTER)
{
x=x-30;
}
if(key==KeyEvent.VK_SPACE)
{
x=x+30;
}
if(key==KeyEvent.VK_SHIFT)
{
In++;
t1=0;
i=0;S=0;r1=30;r2=30;r11=400;r12=20;r111=250;r112=10;w=5;h=17;h1=27;h2=37;
t=new Thread(this);
t.start();
}
}
public void keyTyped(KeyEvent e){}
public void start()
{
t=new Thread(this);
stopFlag=false;
t.start();
}
public void run()
{
char ch;
for(;;)
{
try
{
repaint();
Thread.sleep(50);
ch=s.charAt(0);
s=s.substring(1,s.length());
s+=ch;
i=i+1;
if(i==30)
{
t1=t1+1;
i=0;
}
if(x==30)
{
S=S+1;
}
else
{
S=S-1;
}
r111=r111-5;
if(r111==0)
r111=490;
r11=r11-2;
if(r11==0)
r11=490;
r1=r1-10;
if(r1==0)
r1=490;
if(x==r1||x==r11||x==r111)
{
repaint();
break;
}
if(stopFlag)
break;
}
catch(InterruptedException e){}
}
}
public void stop()
{
stopFlag=true;
}
public void paint(Graphics g)
{
g.setColor(Color.blue);
g.fillOval(y,x,13,13);
g.setColor(Color.red);
g.fillRect(r1,r2,w,h);
g.setColor(Color.green);
g.fillRect(r11,r12,w,h1);
g.setColor(Color.orange);
g.fillRect(r111,r112,w,h2);
g.setColor(Color.black);
if(x==r1||x==r11||x==r111)
g.drawString("GAME OVER"+M,200,25);
g.setColor(Color.black);
g.drawLine(0,47,500,47);
g.drawLine(0,48,500,48);
g.drawString(s,0,49);
g.drawString(s,0,50);
g.drawString("Scorce:",300,20);
g.drawString(""+S,345,20);
g.drawString("Second:",400,20);
g.drawString(""+t1,445,20);
g.drawString(":"+i,460,20);
}
}