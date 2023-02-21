package org.AccelerationAndSpeed;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class KeyPress implements KeyListener {
    public KeyPress() {
        addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        switch (keyChar) {
            case 'w':
                System.out.println("'w' key was pressed");
                break;
            case 'a':
                System.out.println("'a' key was pressed");
                break;
            case 's':
                System.out.println("'s' key was pressed");
                break;
            case 'd':
                System.out.println("'d' key was pressed");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // not implemented
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not implemented
    }
}
