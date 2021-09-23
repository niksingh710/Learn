package Tetris.Screens;

import Tetris.Tools.Variables;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final int WIDTH = Variables.width;
    private static final int HEIGHT = Variables.height;

    private Board board;

    GameWindow() {
        super("Tetris");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        board = new Board();

        add();
    }

    private void add() {
        addKeyListener(board);
        add(board);
    }

    public static void main(String[] args) {
        new GameWindow();
    }
}