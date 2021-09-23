package app.screen;

import javax.swing.JPanel;

import java.awt.Color;

public class Panel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    Panel(int w, int h, int x, int y, Color color) {
        setSize(w, h);
        setBackground(color);
        setLayout(null);
        setLocation(x, y);
    }

    void add(Object obj) {
        add(obj);
    }
}