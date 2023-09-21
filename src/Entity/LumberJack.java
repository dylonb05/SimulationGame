package Entity;

import Main.GamePanel;
import Tile.TileManagerNew;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class LumberJack extends Entity {

    GamePanel gp;
    TileManagerNew tileM;

    int move;
    public LumberJack(GamePanel gp, TileManagerNew tileM, int x, int y) {
        this.gp = gp;
        this.tileM = tileM;
        move = 0;

        this.x = x;
        this.y = y;
        speed = 2;

        getLumberJackImage();
    }
    public int nearestTreeTile() {
        int col = x / 8;
        int row =  y / 8;
        if (row + 1 < gp.maxScreenRow && tileM.getTile(col, row + 1).getType() == 3) { //North
            return 2;
        }
        if (row - 1 >= 0 && tileM.getTile(col, row - 1).getType() == 3) { //South
            return 0;
        }
        if (col + 1 < gp.maxScreenCol && tileM.getTile(col + 1, row).getType() == 3) { //East
            return 1;
        }
        if (col - 1 >= 0 && tileM.getTile(col - 1, row).getType() == 3) {
            return 3;
        }
        return -1;
    }
    public void getLumberJackImage() {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entities/LumberJack.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update() {
        if (move == gp.timeBetweenMovement) {
            int direction = -1;
            int nt = nearestTreeTile();
            if (nt == -1) {
                direction = (int) (Math.random() * 4);
            } else {
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
        if (tileM.getTile(x / 8, y/ 8).getType() == 3) {
            int random = (int) (Math.random() * 2);
            if (random == 1) {
                tileM.setTile(x / 8, y / 8, 0);
            } else {
                tileM.setTile(x / 8, y / 8, 5);
            }


        }
    }
    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(image, x, y, gp.tileSize,gp.tileSize, null);

    }
}
