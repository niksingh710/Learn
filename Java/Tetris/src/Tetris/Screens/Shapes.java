package Tetris.Screens;

import java.awt.Color;
import java.awt.Graphics;
import Tetris.Tools.Variables;

public class Shapes {

    private static final int ROWS = Variables.rows, COLUMNS = Variables.columns, BOX_SIZE = Variables.boxSize;

    private int[][] cords;
    private Color color;
    private int startX = 4, startY = 0;
    private int x = startX, y = startY;
    private int deltaX = 0;
    private Board board;

    private static final int NORMAL = Variables.normal;
    private static final int FAST = Variables.normal / 3;
    private long beginTime = 0;
    private Boolean collision = false;

    private int delayToMove = NORMAL;

    Shapes(int[][] cords, Color color, Board board) {
        this.cords = cords;
        this.color = color;
        this.board = board;
    }

    public void drawShape(Graphics g) {
        g.setColor(color);
        for (int row = 0; row < cords.length; row++) {
            for (int col = 0; col < cords[0].length; col++) {
                if (cords[row][col] != 0) {
                    g.fillRect(x * BOX_SIZE + col * BOX_SIZE, y * BOX_SIZE + row * BOX_SIZE, BOX_SIZE, BOX_SIZE);
                }
            }
        }
    }

    public void rotateShape() {
        int[][] rotatedShape = transpose(cords);
        if ((x + rotatedShape[0].length > ROWS) || (y + rotatedShape.length > COLUMNS)) {
            return;
        }
        for (int row = 0; row < rotatedShape.length; row++) {
            for (int col = 0; col < rotatedShape[0].length; col++) {
                if (rotatedShape[row][col] != 0) {
                    if (board.getColorBoard()[y + 1 + row][x + col] != null) {
                        return;
                    }
                }
            }
        }

        cords = reverseRow(rotatedShape);
    }

    public int[][] transpose(int[][] matrix) {
        int[][] temp = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                temp[j][i] = matrix[i][j];
            }
        }
        return temp;
    }

    public int[][] reverseRow(int[][] matrix) {
        int mid = matrix.length / 2;
        for (int i = 0; i < mid; i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[matrix.length - 1 - i];
            matrix[matrix.length - 1 - i] = temp;
        }
        return matrix;
    }

    private void checkLine() {
        int bottomLine = board.getColorBoard().length - 1;
        for (int topLine = board.getColorBoard().length - 1; topLine > 0; topLine--) {
            int count = 0;
            for (int col = 0; col < board.getColorBoard()[0].length; col++) {
                if (board.getColorBoard()[topLine][col] != null) {
                    count++;
                }
                board.getColorBoard()[bottomLine][col] = board.getColorBoard()[topLine][col];
            }
            if (count < board.getColorBoard()[0].length) {
                bottomLine--;
            }

        }
    }

    void drawBoard(Graphics g) {
        for (int row = 0; row < board.getColorBoard().length; row++) {
            for (int col = 0; col < board.getColorBoard()[0].length; col++) {
                if (board.getColorBoard()[row][col] != null) {
                    g.setColor(board.getColorBoard()[row][col]);
                    g.fillRect(col * BOX_SIZE, row * BOX_SIZE, BOX_SIZE, BOX_SIZE);
                }
            }
        }
    }

    public void update() {
        checkLine();
        if (collision) {
            for (int row = 0; row < cords.length; row++) {
                for (int col = 0; col < cords[0].length; col++) {
                    if (cords[row][col] != 0) {
                        board.getColorBoard()[y + row][x + col] = color;
                    }
                }
            }
            board.setCurrentShape();
            return;
        }
        Boolean moveX = true;
        if (!(x + deltaX + cords[0].length > 10) && !(x + deltaX < 0)) {
            for (int row = 0; row < cords.length; row++) {
                for (int col = 0; col < cords[0].length; col++) {
                    if (cords[row][col] != 0) {
                        if (board.getColorBoard()[y + row][x + deltaX + col] != null) {
                            moveX = false;
                        }
                    }
                }
            }
            if (moveX) {
                x += deltaX;
            }
            deltaX = 0;
        }
        if (System.currentTimeMillis() - beginTime > delayToMove) {
            if (!(y + 1 + cords.length > COLUMNS)) {
                for (int row = 0; row < cords.length; row++) {
                    for (int col = 0; col < cords[0].length; col++) {
                        if (cords[row][col] != 0) {
                            if (board.getColorBoard()[y + 1 + row][x + col] != null) {
                                collision = true;
                            }
                        }
                    }
                }
                if (!collision) {
                    y++;
                }
            } else {
                collision = true;
            }
            beginTime = System.currentTimeMillis();
        }
    }

    public void speedUp() {
        delayToMove = FAST;
    }

    public void speedNormal() {
        delayToMove = NORMAL;
    }

    public void moveLeft() {
        deltaX = -1;
    }

    public void moveRight() {
        deltaX = 1;
    }

    public void reset() {
        this.x = startX;
        this.y = startY;
        this.delayToMove = NORMAL;
        this.collision = false;
    }
}