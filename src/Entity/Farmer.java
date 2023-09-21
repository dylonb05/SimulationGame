package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.MouseHandler;
import Tile.TileManager;
import Tile.TileManagerNew;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Farmer extends Entity{
    GamePanel gp;

    KeyHandler keyH;
    MouseHandler mouseH;
    TileManagerNew tileM;
    int move;
    int farmLand;
    int seeds;
    public Farmer(GamePanel gp, KeyHandler keyH, MouseHandler mouseH, TileManagerNew tileM, int x, int y) {
        this.gp = gp;
        this.keyH = keyH;
        this.mouseH = mouseH;
        this.tileM = tileM;
        move = 0;



        this.x = x;
        this.y = y;
        speed = 2; // 2
        farmLand = 5;
        seeds = 5;

        getFarmerImage();

    }
    public void getFarmerImage() {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entities/Farmer.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update() {
        if (move == gp.timeBetweenMovement) {
            int direction = -1;
            if (farmLand > 0) {
                int nt = nearestGrassTile();
                if (nt == -1) {
                    direction = (int) (Math.random() * 4);
                } else {
                    direction = nt;
                }
            }
            if (seeds > 0 && farmLand == 0) {
                int nt = nearestTile(1);
                if (nt == -1) {
                    direction = (int) (Math.random() * 4);
                } else {
                    direction = nt;
                }
            }
            if (seeds == 0 && farmLand == 0) {
                int nt = nearestTile(3);
                direction = nt;
            }
            switch(direction) {
                case 0:
                    if (y > 0) {
                        y -= speed; //North
                    }
                    break;
                case 1:
                    if (x < gp.screenWidth - 2) {
                        x += speed; //East
                    }
                    break;
                case 2:
                    if (y < gp.screenHeight - 2) {
                        y += speed; // South
                    }

                    break;
                case 3:
                    if (x > 0) {
                        x -= speed; //West
                    }
                    break;
            }
            move = 0;
        } else {
            move++;
        }
        if (farmLand > 0) {
            if (tileM.getTile(x /8, y/8).getType() == 0 || tileM.getTile(x /8, y/8).getType() == 5) {
                tileM.setTile(x / 8, y / 8, 1);
                farmLand--;
            }
        }
        if (seeds > 0 && farmLand == 0) {
            if (tileM.getTile(x / 8, y / 8).getType() == 1) {
                tileM.setTile(x / 8, y / 8, 2);
                gp.addSeed(new Seed((x / 8) * 8 + 2, (y / 8) * 8 + 2, tileM, gp));
                seeds--;
            }
        }
        if (seeds == 0 && farmLand == 0) {
            if (tileM.getTile(x / 8, y / 8).getType() == 3) {
                tileM.setTile(x/8, y/8, 4);

            }
        }






    }
    public int nearestGrassTile() {
        int col = x/8;
        int row = y/8;

        if (row + 1 < gp.maxScreenRow && tileM.getTile(col, row + 1).getType() == 0 || tileM.getTile(col, row + 1).getType() == 5) { //North
            return 2;
        }
        if (row - 1 >= 0 && tileM.getTile(col, row - 1).getType() == 0 || tileM.getTile(col, row + 1).getType() == 5) { //South
            return 0;
        }
        if (col + 1 < gp.maxScreenCol && tileM.getTile(col + 1, row).getType() == 0 || tileM.getTile(col, row + 1).getType() == 5) { //East
            return 1;
        }
        if (col - 1 >= 0 && tileM.getTile(col - 1, row).getType() == 0 || tileM.getTile(col, row + 1).getType() == 5) {
            return 3;
        }
        return -1;
    }
    public int nearestTile(int n) {
        int col = x/8;
        int row = y/8;

        if (row + 1 < gp.maxScreenRow && tileM.getTile(col, row + 1).getType() == n) { //North
            return 2;
        }
        if (row - 1 >= 0 && tileM.getTile(col, row - 1).getType() == n) { //South
            return 0;
        }
        if (col + 1 < gp.maxScreenCol && tileM.getTile(col + 1, row).getType() == n) { //East
            return 1;
        }
        if (col - 1 >= 0 && tileM.getTile(col - 1, row).getType() == n) {
            return 3;
        }
        return -1;
    }


    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }




}
