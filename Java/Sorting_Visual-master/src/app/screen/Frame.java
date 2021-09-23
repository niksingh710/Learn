package app.screen;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * Frame
 */
public class Frame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Work work;
    private String info;
    private double start_time;

    Frame(int size, String algo, char todo, String text, char t, int speed) {
        this.start_time = System.currentTimeMillis();
        if (todo == 'a') {
            this.info = "Ascending";
        } else if (todo == 'd') {
            this.info = "Descending";
        }
        text = text + " " + info + " (" + String.valueOf(size) + "X" + String.valueOf(size) + ")";
        setTitle(text);
        setBackground(Color.blue);
        setSize(size + 20, size + 70);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        setResizable(false);
        setVisible(true);

        work = new Work(size, size, ((this.getWidth() - size) / 2) - 4, 10, Color.black, algo, todo, start_time, t,
                speed);
        add();
    }

    private void add() {
        add(work);
        revalidate();
    }
}