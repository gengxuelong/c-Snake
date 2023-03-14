package game;

import javax.swing.*;
import java.awt.*;

public class FrameMain extends JFrame
{
    public static void main(String[] args) {
        FrameMain f=new FrameMain();
        f.setDefaultCloseOperation(3);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height=Toolkit.getDefaultToolkit().getScreenSize().height;
        f.setBounds((width-860)/2,(height-600)/2,860,600);
        f.setTitle("耿雪龙作");
        f.setResizable(false);
        GamePanel p=new GamePanel();
        f.add(p);
        f.setVisible(true);
    }
}
