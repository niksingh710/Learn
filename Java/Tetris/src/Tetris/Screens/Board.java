package Tetris.Screens;

import Tetris.Tools.Variables;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final int ROWS = Variables.rows, COLUMNS = Variables.columns, BOX_SIZE = Variables.boxSize;

    private Timer looper;
    private Random random;
    private int delay = Variables.delay;
    private Color[][] colorBoard;
    private Shapes[] shapes;
    private Shapes currentShape;

    Board() {
        looper = new Timer(delay, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                currentShape.update();
                repaint();
            }

        });
        looper.start();
        shapes = new Shapes[7];
        colorBoard = new Color[COLUMNS][ROWS];
        random = new Random();
        initShapes();
        currentShape = shapes[random.nextInt(shapes.length)];
    }

    private void initShapes() {
        shapes[0] = new Shapes(new int[][] { { 1, 1, 1 }, { 0, 1, 0 } }, Color.red, this);
        shapes[1] = new Shapes(new int[][] { { 1, 1, 1 }, { 0, 0, 1 } }, Color.green, this);
        shapes[2] = new Shapes(new int[][] { { 1, 1, 1 }, { 1, 0, 0 } }, Color.yellow, this);
        shapes[3] = new Shapes(new int[][] { { 1, 1, 1, 1 } }, Color.blue, this);
        shapes[4] = new Shapes(new int[][] { { 1, 1, 0 }, { 0, 1, 1 } }, Color.magenta, this);
        shapes[5] = new Shapes(new int[][] { { 0, 1, 1 }, { 1, 1, 0 } }, Color.gray, this);
        shapes[6] = new Shapes(new int[][] { { 1, 1 }, { 1, 1 } }, Color.pink, this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBackground(g);
        currentShape.drawShape(g);
        currentShape.drawBoard(g);
        drawGrid(g);
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawGrid(Graphics g) {
        for (int row = 0; row <= ROWS; row++) {
            g.setColor(Color.white);
            g.drawLine(row * BOX_SIZE, 0, row * BOX_SIZE, COLUMNS * BOX_SIZE);
        }

        for (int col = 0; col <= COLUMNS; col++) {
            g.setColor(Color.white);
            g.drawLine(0, col * BOX_SIZE, ROWS * BOX_SIZE, col * BOX_SIZE);
        }
    }

    public Color[][] getColorBoard() {
        return colorBoard;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            currentShape.speedUp();
        }
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            currentShape.moveRight();
        }
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            currentShape.moveLeft();
        }

        if (event.getKeyCode() == KeyEvent.VK_UP) {
            currentShape.rotateShape();
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            currentShape.speedNormal();
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {

    }

    public void setCurrentShape() {
        currentShape = shapes[random.nextInt(shapes.length)];
        currentShape.reset();
    }

}