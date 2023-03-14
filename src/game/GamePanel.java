package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel
{
    int score;
    Random r=new Random();
    int[] snakeX=new int[200];
    int [] snakeY=new int[200];
    int foodX;
    int foodY;
    int length;
    boolean isStart;
    boolean isGameOver;
    Timer timer;
    String direction;
    int speed;
    public void init()
    {
        speed=200;
        score=0;
        isGameOver=false;
        isStart=false;
        direction="r";
        length=4;
        int a=125;
        snakeX[0]=a;
        snakeY[0]=110;
        snakeX[1]=a-25;
        snakeY[1]=110;
        snakeX[2]=a-50;
        snakeY[2]=110;
        snakeX[3]=a-75;
        snakeY[3]=110;
        foodX=50+25*r.nextInt(30);
        foodY=60+25*r.nextInt(20);

    }
    public GamePanel()
    {
        init();
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode=e.getKeyCode();
                System.out.println(keyCode);
                if(keyCode==32) {
                    System.out.println("空格键被按下");
                    isStart = !isStart;
                    repaint();
                }
                if(keyCode==37)
                {
                    direction="l";
                }
                if(keyCode==38)
                {
                    direction="u";
                }
                if(keyCode==39)
                {
                    direction="r";
                }
                if(keyCode==40)
                {
                    direction="d";

                }
            }
        });
        timer=new Timer(speed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(snakeX[0]==foodX&&snakeY[0]==foodY)
                {
                    foodX=50+25*r.nextInt(30);
                    foodY=60+25*r.nextInt(20);
                    length++;
                    score+=10;

                }
                for(int i=length-1;i>0;i--)
                {
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                if("r".equals(direction))
                {
                    snakeX[0]+=25;
                }
                if("l".equals(direction))
                {
                    snakeX[0]-=25;
                }
                if("u".equals(direction))
                {
                    snakeY[0]-=25;
                }
                if("d".equals(direction))
                {
                    snakeY[0]+=25;
                }



                if(snakeX[0]>775)
                {
                    snakeX[0]=50;
                }
                if(snakeX[0]<50)
                {
                    snakeX[0]=775;
                }
                if(snakeY[0]<60)
                {
                    snakeY[0]=535;
                }
                if(snakeY[0]>535)
                {
                    snakeY[0]=60;
                }

                for(int i=1;i<length;i++)
                {
                    if(snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i])
                    {
                        isGameOver=true;
                    }
                }
                repaint();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(255, 236, 191));
        image.title.paintIcon(this,g,0,0);
        g.setColor(Color.WHITE);
        g.setFont(new Font("",4,30));
        g.drawString("积分"+score,600,25);
        g.setColor(new Color(143, 124, 181));
        g.fillRect(50,60,750,500);
        //画蛇头
        if("r".equals(direction))
        image.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        if("l".equals(direction))
            image.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        if("u".equals(direction))
            image.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        if("d".equals(direction))
            image.down.paintIcon(this,g,snakeX[0],snakeY[0]);

        //画蛇身
        for(int i=1;i<length;i++)
        {
            image.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
        //画食物
        image.food.paintIcon(this,g,foodX,foodY);


        if(!isStart&&isGameOver==false)
        {
            g.setColor(new Color(1,1,1));
            g.setFont(new Font("微软雅黑",3,30));
            g.drawString("按下空格键开始",300,300);
            timer.stop();
        }
        if(isStart&&isGameOver==false)
        {
            timer.start();
        }
        if(isGameOver==true)
        {
            g.setColor(new Color(181, 19, 16));
            g.setFont(new Font("微软雅黑",3,30));
            g.drawString("游戏结束，按下空格键重新开始",200,300);
            init();
            timer.stop();
            //isStart=!isStart;

            // 二次推进=true;
        }




    }
}
