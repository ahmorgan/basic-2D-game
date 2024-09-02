import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

import javax.imageio.ImageIO;

public class Player extends MovableEntity {
    
    GamePanel gp;
    KeyHandler keyH;

    double gravitySpeed = 4.0;
    double gravityAcceleration = 0.1;
    double jumpSpeed = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getSprites();
    }

    public void setDefaultValues() {
        x = 600;
        y = 100;
        speed = 4;
        direction = "right";
    }

    public void getSprites() {
        try { //grab sprite png files and convert to bufferedimage
            File right1ImageFile = new File("right1.png");
            File right2ImageFile = new File("right2.png");
            File left1ImageFile = new File("left1.png");
            File left2ImageFile = new File("left2.png");
            File forward1ImageFile = new File("forward1.png");
            File forward2ImageFile = new File("forward2.png");
            File down1ImageFile = new File("down1.png");
            File down2ImageFile = new File("down2.png");
            right1 = ImageIO.read(right1ImageFile);
            right2 = ImageIO.read(right2ImageFile);
            left1 = ImageIO.read(left1ImageFile);
            left2 = ImageIO.read(left2ImageFile);
            forward1 = ImageIO.read(forward1ImageFile);
            forward2 = ImageIO.read(forward2ImageFile);
            down1 = ImageIO.read(down1ImageFile);
            down2 = ImageIO.read(down2ImageFile);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }



    public void update(Background background) {
        
        int updatedY = y;
        int updatedX = x;

       
        updatedY += gravitySpeed;
        for (Platform plat : background.getPlatforms()) {
            // axis aligned collision detection algorithm
            // https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection
            if (
                updatedX < plat.getCoordinate().getX() + 100 &&
                updatedX + gp.tileSize > plat.getCoordinate().getX() &&
                updatedY < plat.getCoordinate().getY() + 10 &&
                gp.tileSize + updatedY > plat.getCoordinate().getY()
              )  {
                    // prevents player from going into platform by setting player y to the platform y only on the first frame when the player collides with the platform
                    // distance is the number of pixels between how far you are inside the platform and the platform's y-value (the top of the platform)
                    int distance = updatedY - plat.getCoordinate().getY();
                    if (distance > 0) {
                        updatedY -= distance;
                    }
                    else {
                        updatedY -= gravitySpeed;
                    }
                    
                    if (keyH.upPressed == true && jumpSpeed <= 0) {
                        direction = "down";
                        jumpSpeed = 16;
                    }      
                }
            }
        updatedY -= jumpSpeed;
        if (jumpSpeed > 0) {
            jumpSpeed--;
        }

        
        if (keyH.downPressed == true) {
            direction = "forward";
        }
        else if (keyH.leftPressed == true) {
            direction = "left";
            updatedX -= speed;
        }
        else if (keyH.rightPressed == true) {
            direction = "right";
            updatedX += speed;
        }



/* 
        //handles platform physics, uses simply ingenious method of doing crazy stuff that I copy pasted from website --> https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection
        //ground int variable (NOT copy pasted, brainchild of Andrew Morgan) allows for on the fly changes to player's minimum gravity level (Ground level)
        if (
                updatedX < plat.getCoordinate().getX() + 100 &&
                updatedX + gp.tileSize > plat.getCoordinate().getX() &&
                updatedY < plat.getCoordinate().getY() + 10 &&
                gp.tileSize + updatedY > plat.getCoordinate().getY()
              )  {
                ground = 356; //platform y level is 360
            }
        else {
            ground = 400; //grass y level is 400
        }
            
        
        //jumpingGravityHandler goes from 30 -> 1. this if triggers when up key pressed and continues until...
        if (currentlyJumping == true && jumpingGravityHandler > 0) {
            updatedY -= Math.pow(1.05, jumpingGravityHandler);
            jumpingGravityHandler--;
            gravityHandler++;
        }


        //...jumpingGravityHandler equals 1. gravityHandler set to zero to re-activate jump (see upkey if statement)
        if (jumpingGravityHandler == 1) {
            currentlyJumping = false;
            jumpingGravityHandler = 30;
            gravityHandler = 1.0;
        }

        
        //logic for gravity (only triggers when not jumping and not on ground-y (y=400))
        
       if (updatedY < ground && currentlyJumping == false) { 
            if (updatedY + Math.pow(1.05, gravityHandler) < ground) {
                updatedY += Math.pow(1.05, gravityHandler);
            }
            else {
                updatedY += (ground - updatedY);
            }
            if (updatedY == ground) {
                gravityHandler = 0.0;
            }
            gravityHandler++;
        }
       /*  if (!currentlyJumping) {
        for (Coordinate coord : background.getTreeCoordinates()) {
            // axis aligned collision detection algorithm
            // https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection
            if (
                updatedX < coord.getX() + gp.tileSize*2-6 &&
                updatedX + gp.tileSize-6 > coord.getX() &&
                updatedY < coord.getY() + gp.tileSize*2-6 &&
                gp.tileSize-6 + updatedY > coord.getY()
              )  {
                return;
            }
        }


        boolean collidedWithPlatform = false;
        for (Platform plat : background.getPlatforms()) {
            // axis aligned collision detection algorithm
            // https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection
            if (
                updatedX < plat.getCoordinate().getX() + 100 &&
                updatedX + gp.tileSize-6 > plat.getCoordinate().getX() &&
                updatedY < plat.getCoordinate().getY() + 10 &&
                gp.tileSize-6 + updatedY > plat.getCoordinate().getY()
              )  {
                collidedWithPlatform = true;
                if (updatedY > plat.getCoordinate().getY() - 10) {
                    updatedY = y;
                }
            }
            
        }

    
        if (!collidedWithPlatform) {
            updatedY = applyGravity(updatedY);
        }
        
    }
*/

        x = updatedX;
        y = updatedY;
        

        //handles walking animation, sprite walking animation changes every 15 frames
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true) {
            spriteCounter++;
        }
        if(keyH.upPressed == false && keyH.downPressed == false && keyH.rightPressed == false && keyH.leftPressed == false) {
            spriteCounter = 0;
        }
        if (spriteCounter != 0 && spriteCounter % 15 == 0) {
            if(spriteNumber == 1) {
                spriteNumber = 2;
            }
            else {
                spriteNumber = 1;
            }
        }

        
    }

    

    public void draw(Graphics2D g2) {
        

        BufferedImage image = null;

        switch(direction) {
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                else {
                    image = right2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                image = left1;
                }
                else {
                image = left2;
                }
                break;
            case "forward":
                if (spriteNumber == 1) {
                image = forward1;
                }
                else {
                image = forward2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                image = down1;
                }
                else {
                image = down2;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
