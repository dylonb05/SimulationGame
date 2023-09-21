package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    BufferedImage[] images = new BufferedImage[10];
    public ImageLoader() {

        loadImages();
    }
    public BufferedImage getImage(int type) {
        return images[type];
    }
    public void loadImages() {
        try {
            images[0] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Grass.png"));
            images[1] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Grass2.png"));
            images[2] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Soil.png"));
            images[3] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Tree.png"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


}
