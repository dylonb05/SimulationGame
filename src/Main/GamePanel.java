package Main;

import Tile.TileManager;
import Tile.TileManagerNew;
import Entity.Entity;
import Entity.Farmer;
import Entity.Seed;
import Entity.LumberJack;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {


    final int originalTileSize = 4; //4x4

    final int scale = 2; //8x8

    public int tileSize = originalTileSize * scale;

    public int maxScreenCol = 96;

    public int maxScreenRow = 72;

    public final int screenWidth = tileSize * maxScreenCol; //768

    public final int screenHeight = tileSize * maxScreenRow; //576

    public final int timeBetweenMovement = 1;
    public final int growthTimeMultiplier = 10;
    int FPS = 60;


    ImageLoader imageL = new ImageLoader();
    TileManagerNew tileM = new TileManagerNew(this, imageL);
    KeyHandler keyH = new KeyHandler();
    MouseHandler mouseH = new MouseHandler();
    Thread gameThread;

    ArrayList<Entity> entities;
    ArrayList<Entity> toAdd;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true);

        entities = new ArrayList<Entity>();
        toAdd = new ArrayList<Entity>();
    }



    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }


    public void update() {
        spawnSeed();
        spawnFarmer();
        spawnLumberJack();
        if (!entities.isEmpty()) {
            for (Entity e: entities) {
                e.update();
            }
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        tileM.draw(g2D);
        if (!entities.isEmpty()) {
            for (Entity e: entities) {
                e.draw(g2D);
            }
        }


        g2D.dispose();

    }

    public void spawnFarmer() {
        if (keyH.fTyped) {
            entities.add(new Farmer(this, keyH, mouseH, tileM, mouseH.x, mouseH.y));
            keyH.fTyped = false;
        }
    }
    public void spawnSeed() {
        if (!toAdd.isEmpty()) {
            entities.addAll(toAdd);
            toAdd.clear();
        }
    }
    public void spawnLumberJack() {
        if (keyH.lTyped) {
            entities.add(new LumberJack(this, tileM, mouseH.x, mouseH.y));
            keyH.lTyped = false;
        }
    }
    public void addSeed(Seed seed) {
        toAdd.add(seed);
    }


}
