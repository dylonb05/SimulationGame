package Tile;

import Main.GamePanel;
import Main.ImageLoader;
import Tile.MapTiles.GrassTile;
import Tile.MapTiles.TreeTile;

import java.awt.*;

public class TileManagerNew {


    ImageLoader imageL;
    GamePanel gp;


    Tile[][] map;



    /*
    Implement chunk system when I go crazy


     */
    public TileManagerNew(GamePanel gp, ImageLoader imageL) {
        this.gp = gp;
        this.imageL = imageL;


        map = new Tile[gp.maxScreenCol][gp.maxScreenRow];

        createWorld();
    }
    public Tile getTile(int col, int row) {
        return map[col][row];
    }
    public void setTile(int col, int row, int type) {
        switch (type) {
            case 0:
                map[col][row] = new GrassTile();
                break;

        }
    };
    public void createWorld() {
        for (int col = 0; col < gp.maxScreenCol; col++) {
            for (int row = 0; row < gp.maxScreenRow; row++) {
                if ((int) (Math.random() * 2) == 0) {
                    map[col][row] = new GrassTile();
                } else {
                    map[col][row] = new TreeTile();
                }

            }
        }

    }
    public void draw(Graphics2D g2D) {
        for (int col = 0; col < gp.maxScreenCol; col++) {
            for (int row = 0; row < gp.maxScreenRow; row++) {
                int imageType = map[col][row].getType();
                g2D.drawImage(imageL.getImage(imageType), col * 8, row * 8, gp.tileSize, gp.tileSize, null);
            }
        }
    }





}
