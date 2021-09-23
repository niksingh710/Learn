package app.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * Work
 */
public class Work extends Panel implements Runnable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int size;
    private Thread thread;
    private ArrayList<Integer> heights;
    private Random r;
    private int changeline_index = 0;
    private long speed;
    private String algo;
    private char todo;
    private double start_time;
    private int div;
    private String ext;

    Work(int w, int h, int x, int y, Color color, String algo, char todo, double start_time, char t, int speed) {
        super(w, h + 20, x, y, color);
        this.size = (w + h) / 2;
        this.algo = algo;
        this.speed = 10 - speed;
        r = new Random();
        this.start_time = start_time;
        if (t == 'm') {
            this.div = 1;
            this.ext = "Milli";
        } else if (t == 's') {
            this.div = 1000;
            this.ext = "";
        }
        this.todo = todo;
        this.heights = new ArrayList<Integer>();
        this.thread = new Thread(this);
        Start();
    }

    void Start() {
        fillHeigths();
        this.thread.start();
    }

    private void fillHeigths() {
        for (int i = 0; i < size;) {
            int val = r.nextInt(size);
            if (this.heights.contains(val)) {
                val = r.nextInt(size);
                continue;
            } else {
                this.heights.add(val);
                i++;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < heights.size(); i++) {
            if (i == getChangeLineIndex()) {
                g.setColor(new Color(255, 0, 0));
            } else {
                g.setColor(new Color(255, 255, 255));
            }
            g.drawLine(i, size, i, size - heights.get(i));
        }
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf((int) ((System.currentTimeMillis() - start_time) / div)) + " " + ext + "Seconds",
                size / 2 - 40, size + 15);
    }

    @Override
    public void run() {
        switch (algo) {
            case "Bubble Sort":
                bubble_sort();
                break;
            case "Selection Sort":
                selection_sort();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Coming Soon.", "Dialog", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
        setChangeLineIndex(0);
    }

    void setChangeLineIndex(int x) {
        this.changeline_index = x;
    }

    int getChangeLineIndex() {
        return this.changeline_index;
    }

    private ArrayList<Integer> swap(ArrayList<Integer> arr, int i, int j) {
        int t = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, t);
        return arr;
    }

    private void bubble_sort() {
        for (int i = 0; i < heights.size(); i++) {
            try {
                for (int j = 0; j < heights.size() - 1; j++) {
                    Thread.sleep(speed);
                    setChangeLineIndex(j + 1);
                    if (todo == 'a') {
                        if (heights.get(j) < heights.get(j + 1)) {
                            heights = swap(heights, j, j + 1);
                        }
                    } else if (todo == 'd') {
                        if (heights.get(j) > heights.get(j + 1)) {
                            heights = swap(heights, j, j + 1);
                        }
                    }
                    repaint();
                    Toolkit.getDefaultToolkit().sync();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void selection_sort() {
        for (int i = 0; i < heights.size() - 1; i++) {
            int m = i;
            try {
                for (int j = i + 1; j < heights.size(); j++) {
                    Thread.sleep(speed);
                    setChangeLineIndex(j + 1);
                    if (todo == 'a') {
                        if (heights.get(j) < heights.get(m)) {
                            m = j;
                        }
                    } else if (todo == 'd') {
                        if (heights.get(j) > heights.get(m)) {
                            m = j;
                        }
                    }
                    repaint();
                    Toolkit.getDefaultToolkit().sync();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            heights = swap(heights, i, m);
        }
    }

}