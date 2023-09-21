package Tile.MapTiles;

import Tile.Tile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class SoilTile extends Tile {
    int stage = 0;
    public SoilTile() {

    }

    @Override
    public int getType() {
        return 2;
    }



}
