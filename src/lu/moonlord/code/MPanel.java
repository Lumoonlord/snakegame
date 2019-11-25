package lu.moonlord.code;

import javax.swing.*;
import java.awt.*;

public class MPanel extends JPanel {
    ImageIcon title = new ImageIcon("./lib/title.jpg");
    ImageIcon body = new ImageIcon("./lib/body.png");
    ImageIcon up = new ImageIcon("./lib/up.png");
    ImageIcon down = new ImageIcon("./lib/down.png");
    ImageIcon left = new ImageIcon("./lib/left.png");
    ImageIcon right = new ImageIcon("./lib/right.png");
    ImageIcon food = new ImageIcon("./lib/food.png");

    int len = 3;
    int[] snakex = new int[750];
    int[] snakey = new int[750];
    String fx = "R"; //方向：R L U D

    public MPanel(){
        initSnake();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);

        //添加标题
        title.paintIcon(this,g,16,11);

        //添加游戏区
        g.fillRect(16,68,850,600);

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

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.BOLD,40));
        g.drawString("Please Space To Start",250,350);
    }

    public void initSnake() {
        len = 3;
        snakex[0] = 100;
        snakey[0] = 100;
        snakex[1] = 75;
        snakey[1] = 100;
        snakex[2] = 50;
        snakey[2] = 100;
    }
}
