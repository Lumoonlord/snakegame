package lu.moonlord.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MPanel extends JPanel implements KeyListener, ActionListener {
    ImageIcon title = new ImageIcon("./lib/title.jpg");
    ImageIcon body = new ImageIcon("./lib/body.png");
    ImageIcon up = new ImageIcon("./lib/up.png");
    ImageIcon down = new ImageIcon("./lib/down.png");
    ImageIcon left = new ImageIcon("./lib/left.png");
    ImageIcon right = new ImageIcon("./lib/right.png");
    ImageIcon food = new ImageIcon("./lib/food.png");

    int len = 3;
    int score = 0;
    int[] snakex = new int[750];
    int[] snakey = new int[750];
    String fx = "R"; //方向：R L U D
    boolean isStarted = false;
    boolean isFailed = false;
    Timer timer = new Timer(300,this);
    int foodx;
    int foody;
    Random ran = new Random();

    public MPanel(){
        initSnake();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    public void initSnake() {
        len = 3;
        snakex[0] = 100;
        snakey[0] = 100;
        snakex[1] = 75;
        snakey[1] = 100;
        snakex[2] = 50;
        snakey[2] = 100;
        foodx = 25 + 25 * ran.nextInt(30);
        foody = 75 + 25 * ran.nextInt(20);
        fx = "R";
        score = 0;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);

        //添加标题
        title.paintIcon(this,g,25,11);
        //添加游戏区
        g.fillRect(25,75,850,600);
        g.setColor(Color.WHITE);
        g.setFont(new Font("arail", Font.BOLD,16));
        g.drawString("Len : " + len,750,30);
        g.drawString("Score : " + score,750,55);
        //画蛇
        if (fx == "R") {
            right.paintIcon(this,g,snakex[0],snakey[0]);
        }else if (fx == "L") {
            left.paintIcon(this,g,snakex[0],snakey[0]);
        }else if (fx == "U") {
            up.paintIcon(this,g,snakex[0],snakey[0]);
        }else if (fx == "D") {
            down.paintIcon(this,g,snakex[0],snakey[0]);
        }

        for (int i = 1; i < len; i++) {
            body.paintIcon(this,g,snakex[i],snakey[i]);
        }

        //画食物
        food.paintIcon(this,g,foodx,foody);

        if (isStarted == false) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial",Font.BOLD,40));
            g.drawString("Please Space To Start",250,350);
        }

        if (isFailed) {
            g.setColor(Color.RED);
            g.setFont(new Font("arial",Font.BOLD,40));
            g.drawString("Please Space To Restart",250,350);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println(keyCode);
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFailed) {
                isFailed = false;
                initSnake();
            }else {
                isStarted = !isStarted;
            }
            repaint();
        }else if (keyCode == KeyEvent.VK_LEFT) {
            fx = "L";
        }else if (keyCode == KeyEvent.VK_RIGHT) {
            fx = "R";
        }else if (keyCode == KeyEvent.VK_UP) {
            fx = "U";
        }else if (keyCode == KeyEvent.VK_DOWN) {
            fx = "D";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStarted && !isFailed) {
            for (int i = len -1; i > 0; i--) {
                snakex[i] = snakex[i-1];
                snakey[i] = snakey[i-1];
            }

            if (fx == "R") {
                snakex[0] = snakex[0] + 25;
                if (snakex[0] > 850) {
                    snakex[0] = 25;
                }
            }else if (fx == "L") {
                snakex[0] = snakex[0] - 25;
                if (snakex[0] < 25) {
                    snakex[0] = 850;
                }
            }else if (fx == "U") {
                snakey[0] = snakey[0] - 25;
                if (snakey[0] < 75) {
                    snakey[0] = 650;
                }
            }else if (fx == "D") {
                snakey[0] = snakey[0] + 25;
                if (snakey[0] > 670) {
                    snakey[0] = 75;
                }
            }

            if (snakex[0] == foodx && snakey[0] == foody) {
                len++;
                score = score + 10;
                foodx = 25 + 25 * ran.nextInt(30);
                foody = 75 + 25 * ran.nextInt(20);
            }

            for (int i = 1; i < len; i++) {
                if (snakex[i] == snakex[0] && snakey[i] == snakey[0]) {
                    isFailed = true;
                }
            }
            repaint();
        }
        timer.start();

    }
}
