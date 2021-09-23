import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Main extends JPanel implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;
    static Main obj = new Main();

    JFrame jf = new JFrame();
    JButton jb00 = new JButton();
    JButton jb01 = new JButton();
    JButton jb02 = new JButton();
    JButton jb10 = new JButton();
    JButton jb11 = new JButton();
    JButton jb12 = new JButton();
    JButton jb20 = new JButton();
    JButton jb21 = new JButton();
    JButton jb22 = new JButton();
    JButton restart = new JButton();

    ImageIcon X = new ImageIcon("img/X.png");
    ImageIcon O = new ImageIcon("img/O.png");

    Toolkit tk = Toolkit.getDefaultToolkit();

    Image img = tk.getImage("img/restart.png");
    Image ttt = tk.getImage("img/tic-tac-toe.png");

    boolean o;
    boolean x;
    boolean press[][] = new boolean[3][3];

    Graphics g;

    Dimension sdm = Toolkit.getDefaultToolkit().getScreenSize();

    char wot = ' ';

    int titlebar = 79;
    int box = 50;
    int val[][] = new int[3][3];

    public static void main(String[] args) {
        obj.run();
    }

    void run() {
        o = true;
        x = false;
        wot = ' ';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                press[i][j] = false;
                val[i][j] = 0;
            }
        }
        obj.frameproperties();
        obj.buttonproperties();
        obj.add();
    }

    void frameproperties() {
        jf.setIconImage(ttt);
        jf.setTitle("Tic-Tac-Toe");
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(box * 9, box * 9 + titlebar);
        jf.setLocation((int) sdm.getWidth() / 2 - jf.getWidth() / 2, (int) sdm.getHeight() / 2 - jf.getHeight() / 2);
        jf.setResizable(false);
        jf.setBackground(Color.BLACK);
    }

    void buttonproperties() {
        
        jf.addKeyListener(this);
        this.setFocusable(true);
        
        jb00.setSize(box * 3, box * 3);
        jb00.setLocation(box * 0, box * 0);
        jb00.setContentAreaFilled(false);
        jb00.setBorderPainted(false);
        jb00.addActionListener(this);
        jb00.setFocusPainted(false);
        jb00.setFocusable(false);

        jb01.setSize(box * 3, box * 3);
        jb01.setLocation(box * 3, box * 0);
        jb01.setContentAreaFilled(false);
        jb01.setBorderPainted(false);
        jb01.setFocusPainted(false);
        jb01.addActionListener(this);
        jb01.setFocusable(false);

        jb02.setSize(box * 3, box * 3);
        jb02.setLocation(box * 6, box * 0);
        jb02.setContentAreaFilled(false);
        jb02.setBorderPainted(false);
        jb02.addActionListener(this);
        jb02.setFocusPainted(false);
        jb02.setFocusable(false);

        jb10.setSize(box * 3, box * 3);
        jb10.setLocation(box * 0, box * 3);
        jb10.setContentAreaFilled(false);
        jb10.setBorderPainted(false);
        jb10.addActionListener(this);
        jb10.setFocusPainted(false);
        jb10.setFocusable(false);

        jb11.setSize(box * 3, box * 3);
        jb11.setLocation(box * 3, box * 3);
        jb11.setContentAreaFilled(false);
        jb11.setBorderPainted(false);
        jb11.addActionListener(this);
        jb11.setFocusPainted(false);
        jb11.setFocusable(false);

        jb12.setSize(box * 3, box * 3);
        jb12.setLocation(box * 6, box * 3);
        jb12.setContentAreaFilled(false);
        jb12.setBorderPainted(false);
        jb12.addActionListener(this);
        jb12.setFocusPainted(false);
        jb12.setFocusable(false);

        jb20.setSize(box * 3, box * 3);
        jb20.setLocation(box * 0, box * 6);
        jb20.setContentAreaFilled(false);
        jb20.setBorderPainted(false);
        jb20.setFocusPainted(false);
        jb20.addActionListener(this);
        jb20.setFocusable(false);

        jb21.setSize(box * 3, box * 3);
        jb21.setLocation(box * 3, box * 6);
        jb21.setContentAreaFilled(false);
        jb21.setBorderPainted(false);
        jb21.setFocusPainted(false);
        jb21.addActionListener(this);
        jb21.setFocusable(false);

        jb22.setSize(box * 3, box * 3);
        jb22.setLocation(box * 6, box * 6);
        jb22.setContentAreaFilled(false);
        jb22.setBorderPainted(false);
        jb22.setFocusPainted(false);
        jb22.addActionListener(this);
        jb22.setFocusable(false);

        restart.setSize(box * 2, box * 2);
        restart.setLocation(box * 3 + box / 2, box * 4);
        restart.setContentAreaFilled(false);
        restart.setBorderPainted(false);
        restart.setFocusPainted(false);
        restart.addActionListener(this);
        restart.setFocusable(false);


    }

    void add() {
        jf.addKeyListener(this);
        jf.add(jb00);
        jf.add(jb01);
        jf.add(jb02);
        jf.add(jb10);
        jf.add(jb11);
        jf.add(jb12);
        jf.add(jb20);
        jf.add(jb21);
        jf.add(jb22);
        jf.add(obj);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, box * 9, box * 10);
        if (wot == ' ') {
            g.setColor(Color.RED);
            g.drawLine(box * 3, box * 0, box * 3, box * 9);
            g.drawLine(box * 6, box * 0, box * 6, box * 9);
            g.drawLine(box * 0, box * 3, box * 9, box * 3);
            g.drawLine(box * 0, box * 6, box * 9, box * 6);
            g.drawLine(box * 0, box * 9, box * 9, box * 9);
            g.setColor(Color.red);
            g.setFont(new Font("Verdana", Font.BOLD, 10));
            g.drawString("Press SPACEBAR to Restart", jf.getWidth() / 2 - 80, jf.getHeight() - 64);
        } else {
            String print = "";
            if (wot == 'X') {
                print = "Winner X";
            } else if (wot == 'O') {
                print = "Winner O";
            } else if (wot == 't') {
                print = "Tie Match";
            }
            g.setColor(Color.WHITE);
            g.setFont(new Font("Verdana", Font.BOLD, box));
            g.drawString(print, jf.getWidth() / 2 - 120, jf.getHeight() / 3);
            g.drawImage(img, box * 3 + box / 2, box * 4, this);
        }

        g.setColor(Color.red);
        g.setFont(new Font("Verdana", Font.BOLD, 10));
        g.drawString("Created By: Nikhil Singh", jf.getWidth() / 2 - 70, jf.getHeight() - 44);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb00) {
            if (!press[0][0]) {
                obj.icon(jb00, 0, 0);
                obj.winortie();
            }
        }

        if (e.getSource() == jb01) {
            if (!press[0][1]) {
                obj.icon(jb01, 0, 1);
                obj.winortie();
            }
        }
        if (e.getSource() == jb02) {
            if (!press[0][2]) {
                obj.icon(jb02, 0, 2);
                obj.winortie();
            }
        }
        if (e.getSource() == jb10) {
            if (!press[1][0]) {
                obj.icon(jb10, 1, 0);
                obj.winortie();
            }
        }
        if (e.getSource() == jb11) {
            if (!press[1][1]) {
                obj.icon(jb11, 1, 1);
                obj.winortie();
            }
        }
        if (e.getSource() == jb12) {
            if (!press[1][2]) {
                obj.icon(jb12, 1, 2);
                obj.winortie();
            }
        }
        if (e.getSource() == jb20) {
            if (!press[2][0]) {
                obj.icon(jb20, 2, 0);
                obj.winortie();
            }
        }
        if (e.getSource() == jb21) {
            if (!press[2][1]) {
                obj.icon(jb21, 2, 1);
                obj.winortie();
            }
        }
        if (e.getSource() == jb22) {
            if (!press[2][2]) {
                obj.icon(jb22, 2, 2);
                obj.winortie();
            }
        }
        if (e.getSource() == restart) {
            obj.replay();
        }
    }

    void icon(JButton jb, int i, int j) {
        if (o) {
            jb.setIcon(O);
            o = false;
            x = true;
            val[i][j] = 1;
        } else if (x) {
            jb.setIcon(X);
            x = false;
            o = true;
            val[i][j] = -1;
        }
        press[i][j] = true;
    }

    void winortie() {
        int c1sum = 0;
        int c2sum = 0;
        int c3sum = 0;
        int r1sum = 0;
        int r2sum = 0;
        int r3sum = 0;
        int drsum = 0;
        int dlsum = 0;
        boolean fill = false;
        boolean br = false;

        for (int i = 0; i < 3; i++) {
            drsum = drsum + val[i][i];
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i + j == 2) {
                    dlsum = dlsum + val[i][j];
                }
            }
        }
        for (int j = 0; j < 3; j++) {
            c1sum = c1sum + val[0][j];
        }
        for (int j = 0; j < 3; j++) {
            c2sum = c2sum + val[1][j];
        }
        for (int j = 0; j < 3; j++) {
            c3sum = c3sum + val[2][j];
        }
        for (int j = 0; j < 3; j++) {
            r1sum = r1sum + val[j][0];
        }
        for (int j = 0; j < 3; j++) {
            r2sum = r2sum + val[j][1];
        }
        for (int j = 0; j < 3; j++) {
            r3sum = r3sum + val[j][2];
        }

        if (c1sum == 3 || c2sum == 3 || c3sum == 3 || drsum == 3 || dlsum == 3 || r1sum == 3 || r2sum == 3
                || r3sum == 3) {
            wot = 'O';
            repaint();
            obj.remover();
        } else if (c1sum == -3 || c2sum == -3 || c3sum == -3 || drsum == -3 || dlsum == -3 || r1sum == -3 || r2sum == -3
                || r3sum == -3) {
            wot = 'X';
            repaint();
            obj.remover();
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (val[i][j] != 0) {
                        fill = true;
                    } else {
                        fill = false;
                        br = true;
                        break;
                    }
                }
                if (br) {
                    break;
                } else {
                    continue;
                }
            }
            if (fill) {
                wot = 't';
                repaint();
                obj.remover();
            } else {
                wot = ' ';
            }
        }
    }

    void remover() {
        jf.remove(jb00);
        jf.remove(jb01);
        jf.remove(jb02);
        jf.remove(jb10);
        jf.remove(jb11);
        jf.remove(jb12);
        jf.remove(jb20);
        jf.remove(jb21);
        jf.remove(jb22);
        jf.add(restart);
        jf.add(obj);
    }

    void replay() {
        jf.getContentPane().removeAll();
        o = true;
        x = false;
        wot = ' ';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                press[i][j] = false;
                val[i][j] = 0;
            }
        }
        restart.removeActionListener(this);
        jb00.setIcon(null);
        jb01.setIcon(null);
        jb02.setIcon(null);
        jb10.setIcon(null);
        jb11.setIcon(null);
        jb12.setIcon(null);
        jb20.setIcon(null);
        jb21.setIcon(null);
        jb22.setIcon(null);
        obj.run();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) { 
            obj.replay(); 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}