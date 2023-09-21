package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    boolean fTyped;
    boolean lTyped;


    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_F) {
            fTyped = true;
        }
        if (code == KeyEvent.VK_L) {
            lTyped = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
