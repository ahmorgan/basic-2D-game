import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    //Screen Settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; 
    final int maxScreenCol = 16;
    final int maxScreenRow = 12; // 16*12 48x48 tiles fit on screen
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    final int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Background background = new Background(this);
   // Platform platform = new Platform(this);

    ArrayList<MovableEntity> movableEntityList = new ArrayList<>();

    int x = 100;
    int y = 100;
    int speed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
        movableEntityList.add(new Player(this, keyH));
        movableEntityList.add(new Money(this));
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; //how often in nanoseconds to draw screen
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            /*what this loop does: 
                1. update information on screen
                2. draw the new info on screen
            */
            update();
            repaint();

            double remainingTime = nextDrawTime - System.nanoTime();
            remainingTime = remainingTime/1000000;

            if (remainingTime < 0) {
                remainingTime = 0;
            }

            try {
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        for (MovableEntity movEnt : movableEntityList) {
            movEnt.update(background);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        background.draw(g2);
        //platform.draw(g2);

        

        for (MovableEntity ent : movableEntityList) {
            ent.draw(g2);
        }

        g2.dispose();
    }
}
