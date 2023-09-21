package Tile.MapTiles;

import Tile.Tile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class GrassTile extends Tile {
    int type;
    public GrassTile() {
        type = (int) (Math.random() * 2);

    }


    @Override
    public int getType() {
        return type;
    }



}

