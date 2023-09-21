package Entity;

import Main.GamePanel;
import Tile.TileManagerNew;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;


public class Seed extends Entity{
    int stage;
    int growTime = 0; //1200
    TileManagerNew tileM;
    GamePanel gp;
    public Seed(int x, int y, TileManagerNew tileM, GamePanel gp) {
        this.x = x;
        this.y = y;
        this.tileM = tileM;
        this.gp = gp;

        stage = 0;

        getSeedImage();
    }

    public void getSeedImage() {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entities/Seed0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void grow() {
        if (stage != 5) {
            stage++;
            try {
                switch (stage) {
                    case 1:
                        image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entities/Seed1.png"));
                        break;
                    case 2:
                        image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entities/Seed2.png"));
                        break;
                    case 3:
                        image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entities/Seed3.png"));
                        break;
                    case 4:
                        image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entities/Seed4.png"));
                        break;
                    case 5:
                        image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entities/Seed5.png"));
                        tileM.setTile(x / 8, y / 8, 3);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void update() {
        if (stage == 5) {
            if (tileM.getTile(x /8, y/8).getType() == 4) {
                stage = 0;
                try {
                    image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entities/Seed0.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tileM.setTile(x/8, y/8, 2);
            }
        } else {
            if (growTime == 1200 / gp.growthTimeMultiplier) {//1200
                grow();
                growTime = 0;
            }
            growTime++;
        }

    }
    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(image, x, y , null);

    }
}
