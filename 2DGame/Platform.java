import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;

import javax.imageio.ImageIO;

public class Platform extends Entity {
    GamePanel gp;
    BufferedImage platform;
    int x;
    int y;
    Coordinate platCoord;

    public Platform(GamePanel gp, int x, int y) {
        this.gp = gp;
        this.x = x;
        this.y = y;

        platCoord = new Coordinate(x, y);

        getPlatformSprite();
    }

    public Platform(GamePanel gp)  {
        this(gp, 550, 200);
    }

    public void getPlatformSprite() {
        try {
        File platformFile = new File("platform.png");
        platform = ImageIO.read(platformFile);
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Coordinate getCoordinate() {
        return platCoord;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(platform, x, y, 100, 10, null);
    }

    public void update(Graphics2D g2) {

    }
}
