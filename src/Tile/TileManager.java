package Tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class TileManager {

    GamePanel gp;

    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];


        getTileImage();
        randomGrassTiles();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Grass.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Soil.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Soil.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Soil.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Soil.png"));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Grass2.png"));
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Tree.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
    public void setMapTileNum(int col, int row, int type) {
        if (mapTileNum[col][row] != type) {
            mapTileNum[col][row] = type;
        }
    }
    public int getMapTileNumType(int col, int row) {
        return mapTileNum[col][row];
    }

    public void randomGrassTiles() {
        int col = 0;
        int row = 0;
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            while(col < gp.maxScreenCol) {
                int num = (int) (Math.random() * 3);
                switch (num) {
                    case 0:
                        mapTileNum[col][row] = 0;
                        break;
                    case 1:
                        mapTileNum[col][row] = 5;
                        break;
                    case 2:
                        mapTileNum[col][row] = 6;
                        break;
                }
                col++;
            }
            if (col == gp.maxScreenCol) {
                col = 0;
                row++;
            }
        }
    }
    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = br.readLine();

                while(col < gp.maxScreenCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }

    }


    public void draw(Graphics2D g2D) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2D.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);

            col++;
            x += gp.tileSize;
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }

}

