import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Money extends MovableEntity {
    GamePanel gp;

    public Money(GamePanel gp) {
        this.gp = gp;

        getMoneySprite();
    }

    public void getMoneySprite() {
        try {
        File moneyFile = new File("money.png");
        money = ImageIO.read(moneyFile);
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        //g2.drawImage(money, 100, 390, 64, 32, null);
    }

    public void update(Background background) {

    }


}
