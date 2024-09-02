import java.awt.image.BufferedImage;

abstract public class MovableEntity extends Entity {
    public int speed;

    public BufferedImage right1, right2, left1, left2, forward1, forward2, down1, down2, money;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;

    abstract public void update(Background background);
}
