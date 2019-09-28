package co.chen.plugin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Panel extends JPanel implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        String[] args = new String[]{"ping", "www.google.com"};
        try {
            Process p = Runtime.getRuntime().exec("ping www.google.com");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("keyPressed: " + e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String[] args = new String[]{"ping", "www.google.com"};
        try {
            Process p = Runtime.getRuntime().exec("ping www.google.com");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
     /*       Robot r = null;
            try {
                String[] args = new String[] {"ping", "www.google.com"};
                Process proc = new ProcessBuilder(args).start();*/
/*
                r = new Robot();
                int keyCode = KeyEvent.VK_ENTER; // the A key
                r.keyPress(keyCode);
                int keyCode2 = KeyEvent.VK_F12;
                r.keyPress(keyCode2);
                r.keyPress(keyCode);
                r.keyPress(keyCode2);
        System.out.println("keyPressed: " + e);
*/

            // add(new EditorTextField("dddddddddd"));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String[] args = new String[]{"ping", "www.google.com"};
        try {
            Process p = Runtime.getRuntime().exec("ping www.google.com");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Panel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public Panel(LayoutManager layout) {
        super(layout);
    }

    public Panel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public Panel() {
    }
}
