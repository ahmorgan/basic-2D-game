import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Background extends Entity {

    GamePanel gp;
    boolean backgroundDisplayed = false;
    ArrayList<Coordinate> treeCoords = new ArrayList<>();
    ArrayList<Platform> platforms = new ArrayList<>();
    public BufferedImage grass, tree, bg, topGrass;

    public Background(GamePanel gp) {
        this.gp = gp;  

        platforms.add(new Platform(gp, 580, 300));
        platforms.add(new Platform(gp, 450, 420));

        getGrassSprite();
    }

    public void getGrassSprite() {
        try {
        File grassImageFile = new File("grass.png");
        File treeImageFile = new File("tree.png");
        File bgFile = new File("goofy ahh background.png");
        File topGrassFile = new File("topgrass.png");
        tree = ImageIO.read(treeImageFile);
        grass = ImageIO.read(grassImageFile);
        bg = ImageIO.read(bgFile);
        topGrass = ImageIO.read(topGrassFile);
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }
    
    public ArrayList<Coordinate> getTreeCoordinates() {
        return treeCoords;
    }

    public void draw(Graphics2D g2) {
        //first frame
        /*
        if (backgroundDisplayed == false) {
            g2.drawImage(bg, x, y, 768,576, null);
            for (int x = 0; x <= 768; x += 48) {
                g2.drawImage(topGrass, x, 448, gp.tileSize, gp.tileSize, null);
                }
            for (int y = 491; y <= 576; y += 48) {
                for (int x = 0; x <= 768; x += 48) {
                g2.drawImage(grass, x, y, gp.tileSize, gp.tileSize, null);
                }
            }
            
            //set randomized coordinates for trees and put coords into arraylist (arraylist is of type Coordinate, helper class to store coordinates)
            for (int z = 0; z < 2; z++) {
                int x = (int)(Math.random()*468);
                int y = 357;
                g2.drawImage(tree, x, y, gp.tileSize*2, gp.tileSize*2, null);
                treeCoords.add(new Coordinate(x, y));
                }    
        
            backgroundDisplayed = true;
        } 
        //all other frames
        else {
            g2.drawImage(bg, x, y, 768, 576, null);
            for (int x = 0; x <= 768; x += 48) {
                g2.drawImage(topGrass, x, 448, gp.tileSize, gp.tileSize, null);
                }
            for (int y = 491; y <= 576; y += 48) {
                for (int x = 0; x <= 768; x += 48) {
                g2.drawImage(grass, x, y, gp.tileSize, gp.tileSize, null);
                }
            }
            //grab tree coords from helper class Coordinate and draw those bad boys
            for (Coordinate b : treeCoords) {
                g2.drawImage(tree, b.getX(), b.getY(), gp.tileSize*2, gp.tileSize*2, null);
            }
            
            

        }
        */
        for (Platform plats : platforms) {
                plats.draw(g2);
            }
    }


}
