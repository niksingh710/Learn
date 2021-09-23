
// ======== importing necessary awt package 
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// ======== importing necessary swing package

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

// ======== Class Declaration

class Main extends JPanel implements ActionListener, KeyListener {
    // serial ID
    private static final long serialVersionUID = 1L;

    // ======== Declaring all objects and variables required

    static Main obj = new Main();

    Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();

    Color backgroundcolor = Color.DARK_GRAY;
    Color foregroundcolor = Color.black;

    ImageIcon colorimg = new ImageIcon("img/color.png");
    Image snakeicon = Toolkit.getDefaultToolkit().getImage("img/snake.png");
    Image lefticon = Toolkit.getDefaultToolkit().getImage("img/left.png");
    Image righticon = Toolkit.getDefaultToolkit().getImage("img/right.png");
    Image downicon = Toolkit.getDefaultToolkit().getImage("img/down.png");
    Image upicon = Toolkit.getDefaultToolkit().getImage("img/up.png");
    Image abouticon = Toolkit.getDefaultToolkit().getImage("img/about.png");
    Image infoicon = Toolkit.getDefaultToolkit().getImage("img/info.png");
    ImageIcon restartimg = new ImageIcon("img/restart.png");
    ImageIcon homeimg = new ImageIcon("img/home.png");
    ImageIcon resetimg = new ImageIcon("img/reset.png");
    Image lreseticon = Toolkit.getDefaultToolkit().getImage("img/lreset.png");
    Image lhomeicon = Toolkit.getDefaultToolkit().getImage("img/lhome.png");
    Image lrestarticon = Toolkit.getDefaultToolkit().getImage("img/lrestart.png");

    Color headcolor = Color.black;
    Color bodycolor = Color.white;
    Graphics g;

    boolean reoccur = true;
    boolean playing = false;

    boolean left = false;
    boolean right = false;
    boolean up = false;
    boolean down = false;
    boolean moves = true;
    boolean space = false;

    int score = 0;
    int foodx = 125;
    int foody = 125;
    int boxlength = 4;
    int hscore = 0;
    int[] boxx = new int[(int) dm.getWidth()];
    int[] boxy = new int[(int) dm.getHeight()];

    Timer timer;

    JFrame jf1 = new JFrame();
    JFrame jfh = new JFrame("Help");
    JFrame jfd = new JFrame("About Dev!");

    JPanel jp1 = new JPanel();
    JPanel jp2 = new JPanel();
    JPanel jp3 = new JPanel();
    JPanel jph = new JPanel();
    JPanel jpd = new JPanel();
    JPanel jpp = new JPanel();
    JPanel jppb = new JPanel();
    JPanel jpbp = new JPanel();
    JPanel theme = new JPanel();
    JPanel temp = new JPanel();
    JPanel jphs = new JPanel();
    JPanel jpb = new JPanel();

    JButton jbh = new JButton("OK");
    JButton jbp = new JButton("Play");
    JButton color = new JButton();
    JButton restart = new JButton();
    JButton home = new JButton();
    JButton replay = new JButton();
    JButton help = new JButton("HELP");
    JButton aboutdev = new JButton("About Dev!");
    JButton jbd = new JButton("OK");

    JLabel jlh1 = new JLabel();
    JLabel jlh2 = new JLabel();
    JLabel jld1 = new JLabel();
    JLabel jld2 = new JLabel();
    JLabel jld3 = new JLabel();
    JLabel lreset = new JLabel();
    JLabel reseticon = new JLabel(new ImageIcon(lreseticon));
    JLabel lhome = new JLabel();
    JLabel homeicon = new JLabel(new ImageIcon(lhomeicon));
    JLabel lrestart = new JLabel();
    JLabel restarticon = new JLabel(new ImageIcon(lrestarticon));
    JLabel scoreprint = new JLabel();
    JLabel hscoreprint = new JLabel();
    JLabel lspace = new JLabel();

    JRadioButton jc1 = new JRadioButton(" BABY");
    JRadioButton jc2 = new JRadioButton(" EASY");
    JRadioButton jc3 = new JRadioButton(" NORMAL");
    JRadioButton jc4 = new JRadioButton(" HARD");
    JRadioButton jc5 = new JRadioButton(" HARDEST");
    JRadioButton jc6 = new JRadioButton(" u can't :P");
    JRadioButton body = new JRadioButton(" Body");
    JRadioButton head = new JRadioButton(" Head");
    JRadioButton dark = new JRadioButton(" Dark");
    JRadioButton black = new JRadioButton(" Black");
    JRadioButton white = new JRadioButton(" White");
    JRadioButton day = new JRadioButton(" Day");

    int box = 25;
    int row = 20;
    int column = 15;
    int speedconstant = 100;
    int speed = speedconstant;
    boolean h = true;
    boolean b = false;
    // ======== Main method

    public static void main(String[] args) {
        obj.runner();
    }

    // ============Main Manager
    void runner() {
        try {
            obj.hset();
        } catch (Exception e) {
        }
        obj.tset();
        obj.run();
    }

    // =============HighScore initializer
    void hset() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("High_Score/HighScore.dat"));
        String hs = br.readLine();
        if (hs == null) {
            hscore = 0;
        } else {
            hscore = Integer.parseInt(hs);
        }
        br.close();
    }

    // ========= for theme in start
    void tset() {
        dark.setSelected(true);
    }

    // ======== run Controlling all methods
    void run() {
        obj.add();
        if (playing) {
            jf1.setResizable(false);
            obj.playingproperties();
            obj.foodlocationUpdater();
            replay.addActionListener(this);
            replay.setFocusable(false);
            home.addActionListener(this);
            home.setFocusable(false);
            restart.addActionListener(this);
            restart.setFocusable(false);
            jf1.addKeyListener(this);
            jf1.requestFocusInWindow();
            jf1.setFocusable(true);
            timer = new Timer(speed, this);
            timer.start();
        } else {
            obj.properties();

            jc1.addActionListener(this);
            jc2.addActionListener(this);
            jc3.addActionListener(this);
            jc4.addActionListener(this);
            jc5.addActionListener(this);
            jc6.addActionListener(this);
            body.addActionListener(this);
            head.addActionListener(this);
            color.addActionListener(this);
            help.addActionListener(this);
            aboutdev.addActionListener(this);
            day.addActionListener(this);
            black.addActionListener(this);
            dark.addActionListener(this);
            white.addActionListener(this);
            jbp.addActionListener(this);
        }

    }

    // ======== While playing adding all properties
    void playingproperties() {
        obj.playingpanelproperties();
        obj.playingbuttonproperties();
        obj.playinglableproperties();

    }

    // ============ Info on Playing screen
    void playinglableproperties() {
        String text1 = "<html> <p> ' &emsp ' Reset High Score.</p> </html>";
        lreset.setForeground(foregroundcolor);
        lreset.setText(text1);
        Font font0 = new Font("SansSerif", Font.BOLD, 12);
        lreset.setFont(font0);
        lreset.setText(text1);
        lreset.setSize(200, 14);
        lreset.setLocation(box * 7, 2);

        String text2 = "<html> <p>' &emsp ' Go Back To Main Menu. </p> </html>";
        lhome.setForeground(foregroundcolor);
        lhome.setText(text2);
        lhome.setFont(font0);
        lhome.setText(text2);
        lhome.setSize(200, 14);
        lhome.setLocation(box * 7, 17);

        String text3 = "<html> <p>' &emsp ' Replay the Game. </p> </html>";
        lrestart.setForeground(foregroundcolor);
        lrestart.setText(text3);
        lrestart.setFont(font0);
        lrestart.setSize(200, 14);
        lrestart.setLocation(box * 7, 32);

        String text4 = "<html> <p> Press 'SPACE' to Pause. </p> </html>";
        lspace.setForeground(foregroundcolor);
        lspace.setText(text4);
        lspace.setFont(font0);
        lspace.setSize(200, 20);
        lspace.setLocation(0, 30);

        reseticon.setSize(200, 14);
        reseticon.setLocation(box * 4 - 13, 2);

        homeicon.setSize(200, 14);
        homeicon.setLocation(box * 4 - 13, 18);

        restarticon.setSize(200, 14);
        restarticon.setLocation(box * 4 - 13, 34);
    }

    // ======== While playing Panel
    void playingpanelproperties() {
        jppb.setBackground(backgroundcolor);
        jppb.setLocation(0, 0);
        jppb.setSize(jf1.getWidth(), box * 2);
        jppb.setLayout(null);

        jpp.setBackground(backgroundcolor);
        jpp.setLocation(0, 0);
        jpp.setSize(box * 3, box * 2);
        TitledBorder sborder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(foregroundcolor),
                "SCORE");
        sborder.setTitleJustification(TitledBorder.CENTER);
        sborder.setTitleFont(new Font("Courier", Font.BOLD, 12));
        sborder.setTitleColor(foregroundcolor);
        jpp.setBorder(sborder);
        jpp.setLayout(null);

        jpb.setBackground(backgroundcolor);
        jpb.setLocation(jf1.getWidth() - box * 6 - 15, 0);
        jpb.setSize(box * 6, box * 2);
        jpb.setLayout(null);

        jphs.setBackground(backgroundcolor);
        jphs.setLocation(box * 3, 0);
        jphs.setSize(box * 3, box * 2);
        TitledBorder hborder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(foregroundcolor),
                "HIGHEST");
        hborder.setTitleJustification(TitledBorder.CENTER);
        hborder.setTitleFont(new Font("Courier", Font.BOLD, 12));
        hborder.setTitleColor(foregroundcolor);
        jphs.setBorder(hborder);
        jphs.setLayout(null);

    }

    // ======== While playing button
    void playingbuttonproperties() {

        replay.setIcon(resetimg);
        replay.setSize(box * 2, 30);
        replay.setBorder(null);
        replay.setBackground(backgroundcolor);
        replay.setLocation(0, 0);

        home.setIcon(homeimg);
        home.setSize(box * 2, 30);
        home.setBorder(null);
        home.setBackground(backgroundcolor);
        home.setLocation(box * 2, 0);

        restart.setIcon(restartimg);
        restart.setSize(box * 2, 30);
        restart.setBorder(null);
        restart.setBackground(backgroundcolor);
        restart.setLocation(box * 4, 0);
    }

    // ======== JFrame Properties
    void FrameProperties() {

        jf1.setIconImage(snakeicon);
        jf1.setTitle("Snake-Game-Java 2.O");
        jf1.setLocation((int) dm.getWidth() / 2 - (row * box) / 2, (int) dm.getHeight() / 2 - (column * box) / 2);
        jf1.setSize((int) (row * box), (int) (column * box));
        jf1.setResizable(false);
        jf1.setBackground(backgroundcolor);
        jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                try {
                    obj.exitwork();
                } catch (Exception e) {
                }
            }
        });
        jf1.setVisible(true);
    }

    // ======== Buttons Properties
    void ButtonProperties() {
        color.setIcon(colorimg);
        color.setSize(70, 70);
        color.setBackground(backgroundcolor);
        color.setLocation(20, 20);

        help.setSize(80, 20);
        help.setLocation(10, 18);

        jbp.setSize(80, 20);
        jbp.setLocation(jp3.getWidth() - 90, 18);

        aboutdev.setSize(100, 20);
        aboutdev.setLocation(jp3.getWidth() / 2 - aboutdev.getWidth() / 2, 18);
    }

    // ======== panel Properties
    void panelproperties() {
        jp1.setBackground(backgroundcolor);
        jp1.setLocation(jf1.getWidth() / 14, jf1.getHeight() / 6);
        jp1.setSize(110, 180);
        TitledBorder p1border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(foregroundcolor),
                "Choose Level");
        p1border.setTitleJustification(TitledBorder.CENTER);
        p1border.setTitleFont(new Font("Courier", Font.BOLD, 12));
        p1border.setTitleColor(foregroundcolor);
        jp1.setBorder(p1border);
        jp1.setLayout(null);

        jp2.setBackground(backgroundcolor);
        jp2.setLocation(jf1.getWidth() / 3, jf1.getHeight() / 6 + 10);
        jp2.setSize(110, 160);
        TitledBorder p2border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(foregroundcolor),
                "Choose Color");
        p2border.setTitleJustification(TitledBorder.CENTER);
        p2border.setTitleFont(new Font("Courier", Font.BOLD, 12));
        p2border.setTitleColor(foregroundcolor);
        jp2.setBorder(p2border);
        jp2.setLayout(null);

        jp3.setBackground(backgroundcolor);
        jp3.setLocation(jf1.getWidth() / 40, jf1.getHeight() - 100);
        jp3.setSize(jf1.getWidth() - 40, 50);
        TitledBorder p3border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(foregroundcolor),
                "OPTIONS");
        p3border.setTitleJustification(TitledBorder.CENTER);
        p3border.setTitleFont(new Font("Ariel", Font.BOLD, 12));
        p3border.setTitleColor(foregroundcolor);
        jp3.setBorder(p3border);
        jp3.setLayout(null);

        theme.setBackground(backgroundcolor);
        theme.setSize(jf1.getWidth() - 350, 80);
        theme.setLocation(jf1.getWidth() / 3 + 125, jf1.getHeight() - 300);
        TitledBorder tborder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(foregroundcolor),
                "THEMES");
        tborder.setTitleJustification(TitledBorder.CENTER);
        tborder.setTitleFont(new Font("Ariel", Font.BOLD, 12));
        tborder.setTitleColor(foregroundcolor);
        theme.setBorder(tborder);
        theme.setLayout(null);

        jpp.setBackground(backgroundcolor);
        jpp.setLocation(0, 0);
        jpp.setSize(jf1.getWidth(), box * 2);
    }

    // ======== checkbox Properties
    void checkboxproperties() {
        jc1.setLocation(jp1.getWidth() / 2 - 35, 25);
        jc1.setForeground(foregroundcolor);
        jc1.setBackground(backgroundcolor);
        if (reoccur) {
            jc3.setSelected(true);
        }

        jc2.setLocation(jp1.getWidth() / 2 - 35, 50);
        jc2.setForeground(foregroundcolor);
        jc2.setBackground(backgroundcolor);

        jc3.setLocation(jp1.getWidth() / 2 - 35, 75);
        jc3.setForeground(foregroundcolor);
        jc3.setBackground(backgroundcolor);

        jc4.setLocation(jp1.getWidth() / 2 - 35, 100);
        jc4.setForeground(foregroundcolor);
        jc4.setBackground(backgroundcolor);

        jc5.setLocation(jp1.getWidth() / 2 - 35, 125);
        jc5.setForeground(foregroundcolor);
        jc5.setBackground(backgroundcolor);

        jc6.setLocation(jp1.getWidth() / 2 - 35, 150);
        jc6.setForeground(foregroundcolor);
        jc6.setBackground(backgroundcolor);

        head.setLocation(jp2.getWidth() / 2 - 30, jp2.getHeight() - 65);
        head.setForeground(foregroundcolor);
        head.setBackground(backgroundcolor);
        if (reoccur) {
            head.setSelected(true);
        }

        body.setLocation(jp2.getWidth() / 2 - 30, jp2.getHeight() - 40);
        body.setForeground(foregroundcolor);
        body.setBackground(backgroundcolor);

        day.setLocation(theme.getWidth() / 2 - 50, 20);
        day.setForeground(foregroundcolor);
        day.setBackground(backgroundcolor);

        dark.setLocation(theme.getWidth() / 2, 20);
        dark.setForeground(foregroundcolor);
        dark.setBackground(backgroundcolor);

        black.setLocation(theme.getWidth() / 2 + 5, 45);
        black.setForeground(foregroundcolor);
        black.setBackground(backgroundcolor);

        white.setLocation(theme.getWidth() / 2 - 55, 45);
        white.setForeground(foregroundcolor);
        white.setBackground(backgroundcolor);
    }

    // ======== Properties method
    void properties() {

        obj.FrameProperties();
        obj.panelproperties();
        obj.ButtonProperties();
        obj.checkboxproperties();
    }

    // ======== Painting whole
    public void paint(Graphics g) {
        if (playing) {
            obj.snakerun(g);
        } else {
            g.setColor(foregroundcolor);
            g.setFont(new Font("SansSerif", Font.PLAIN, 40));
            g.drawString("Welcome", jf1.getWidth() / 2 - 80, jf1.getHeight() / 8);
            g.setColor(Color.red);
            g.setFont(new Font("Ariel", Font.PLAIN, 12));
            g.drawString("Created-By : Nikhil Singh", jf1.getWidth() / 2 - 80, jf1.getHeight() - 40);
            obj.snakeshow(g);
        }
        g.dispose();
    }

    // ======== snake representation
    void snakeshow(Graphics g) {

        g.setColor(headcolor);
        g.fillOval(jf1.getWidth() - 200, jf1.getHeight() / 6 + 120, 25, 25);

        g.setColor(bodycolor);
        g.fillOval(jf1.getWidth() - 175, jf1.getHeight() / 6 + 120, 25, 25);
        g.setColor(bodycolor);
        g.fillOval(jf1.getWidth() - 150, jf1.getHeight() / 6 + 120, 25, 25);
        g.setColor(bodycolor);
        g.fillOval(jf1.getWidth() - 125, jf1.getHeight() / 6 + 120, 25, 25);
        g.setColor(bodycolor);
        g.fillOval(jf1.getWidth() - 100, jf1.getHeight() / 6 + 120, 25, 25);

    }

    // ======== making snake run
    void snakerun(Graphics g) {
        if (moves) {
            boxx[0] = box * 4;
            boxy[0] = box * 3;
            boxx[1] = box * 3;
            boxy[1] = box * 3;
            boxx[2] = box * 2;
            boxy[2] = box * 3;
            boxx[3] = box * 1;
            boxy[3] = box * 3;
        }

        g.setColor(backgroundcolor);
        g.fillRect(0, box * 2, jf1.getWidth(), jf1.getHeight());
        g.setColor(foregroundcolor);
        g.drawLine(0, box * 2, jf1.getWidth(), box * 2);

        obj.scoreprinting();
        obj.hscoreprinting();
        for (int i = 0; i < boxlength; i++) {
            g.setColor(Color.red);
            g.fillOval(foodx, foody, box, box);
            if (boxx[0] == foodx && boxy[0] == foody) {
                boxlength++;
                score++;
                if (score >= hscore) {
                    hscore = score;
                }
                foodlocationUpdater();
            }
            if (i == 0) {
                g.setColor(headcolor);
            } else {
                g.setColor(bodycolor);
            }
            g.fillOval(boxx[i], boxy[i], box, box);
            if (i != 0) {
                if (boxx[0] == boxx[i] && boxy[0] == boxy[i]) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    death();
                    g.setColor(backgroundcolor);
                    g.fillRect(0, box * 2, jf1.getWidth(), jf1.getHeight());
                    g.setColor(Color.RED);
                    g.setFont(new Font("Segoe UI", Font.BOLD, 50));
                    g.drawString("Game Over", jf1.getWidth() / 2 - 140, jf1.getHeight() / 2);
                    break;
                }
            }
        }
    }

    // ======== Printing the score
    void scoreprinting() {
        scoreprint.setForeground(foregroundcolor);
        Font font0 = new Font("SansSerif", Font.BOLD, 30);
        scoreprint.setHorizontalAlignment(SwingConstants.CENTER);
        scoreprint.setFont(font0);
        scoreprint.setSize(jpp.getWidth(), 50);
        scoreprint.setLocation(0, 0);
        scoreprint.setText(String.valueOf(score));
    }

    void hscoreprinting() {
        hscoreprint.setForeground(foregroundcolor);
        Font font0 = new Font("SansSerif", Font.BOLD, 30);
        hscoreprint.setHorizontalAlignment(SwingConstants.CENTER);
        hscoreprint.setFont(font0);
        hscoreprint.setSize(jphs.getWidth(), 50);
        hscoreprint.setLocation(0, 0);
        hscoreprint.setText(String.valueOf(hscore));
    }

    // ======== adding all components to JFrame
    void add() {
        if (playing) {
            jf1.add(jppb);
            jppb.add(jpb);
            jpb.add(restart);
            jpb.add(home);
            jpb.add(replay);
            jppb.add(lreset);
            jppb.add(reseticon);
            jppb.add(lhome);
            jppb.add(homeicon);
            jppb.add(lrestart);
            jppb.add(restarticon);
            jppb.add(jpp);
            jppb.add(jphs);
            jpp.add(scoreprint);
            jphs.add(hscoreprint);
            jpb.add(lspace);

        } else {
            jf1.add(jp1);
            jf1.add(jp2);
            jf1.add(theme);
            jp1.add(jc1);
            jp1.add(jc2);
            jp1.add(jc3);
            jp1.add(jc4);
            jp1.add(jc5);
            jp1.add(jc6);
            jp2.add(head);
            jp2.add(body);
            jp2.add(color);
            jf1.add(jp3);
            jp3.add(help);
            jp3.add(jbp);
            theme.add(day);
            theme.add(dark);
            theme.add(black);
            theme.add(white);
            jp3.add(aboutdev);

        }

        jf1.add(obj);
    }

    // ======== Keyboard input
    @Override
    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_UP) {
            left = false;
            right = false;
            if (down) {
                down = true;
                up = false;
            } else {
                up = true;
                down = false;
            }
            space = false;
            timer.start();
            moves = false;
        }
        if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
            up = false;
            down = false;
            if (left) {
                left = true;
                right = false;
            } else {
                right = true;
                left = false;
            }
            space = false;
            timer.start();
            moves = false;
        }
        if (key.getKeyCode() == KeyEvent.VK_LEFT) {
            up = false;
            down = false;
            if (right) {
                left = false;
                right = true;
            } else {
                right = false;
                left = true;
            }
            space = false;
            timer.start();
            moves = false;
        }
        if (key.getKeyCode() == KeyEvent.VK_DOWN) {
            left = false;
            right = false;
            if (up) {
                up = true;
                down = false;
            } else {
                down = true;
                up = false;
            }
            space = false;
            timer.start();
            moves = false;
        }
        if (key.getKeyCode() == KeyEvent.VK_SPACE) {
            space = true;
        }
        jf1.repaint();
    }

    // =======Pausing
    void pause() {
        timer.stop();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // ======== Button press input
    @Override
    public void actionPerformed(ActionEvent key) {
        if (playing) {
            timer.start();
            if (left) {
                for (int i = boxlength - 1; i >= 0; i--) {
                    boxy[i + 1] = boxy[i];
                }
                for (int i = boxlength; i >= 0; i--) {
                    if (i != 0) {
                        boxx[i] = boxx[i - 1];
                    } else {
                        boxx[i] -= box;
                    }
                    if (boxx[i] < 0) {
                        boxx[i] = jf1.getWidth() - box;
                    }
                }
            }
            if (right) {
                for (int i = boxlength - 1; i >= 0; i--) {
                    boxy[i + 1] = boxy[i];
                }
                for (int i = boxlength; i >= 0; i--) {
                    if (i != 0) {
                        boxx[i] = boxx[i - 1];
                    } else {
                        boxx[i] += box;
                    }
                    if (boxx[i] > jf1.getWidth() - box) {
                        boxx[i] = 0;
                    }
                }
            }
            if (up) {
                for (int i = boxlength - 1; i >= 0; i--) {
                    boxx[i + 1] = boxx[i];
                }
                for (int i = boxlength; i >= 0; i--) {
                    if (i != 0) {
                        boxy[i] = boxy[i - 1];
                    } else {
                        boxy[i] -= box;
                    }
                    if (boxy[i] < box * 2) {
                        boxy[i] = jf1.getHeight() - box * 2;
                    }
                }
            }
            if (down) {
                for (int i = boxlength - 1; i >= 0; i--) {
                    boxx[i + 1] = boxx[i];
                }
                for (int i = boxlength; i >= 0; i--) {
                    if (i != 0) {
                        boxy[i] = boxy[i - 1];
                    } else {
                        boxy[i] += box;
                    }
                    if (boxy[i] > jf1.getHeight() - box * 2) {
                        boxy[i] = box * 2;
                    }
                }
            }
            if (space) {
                obj.pause();
            }
            jf1.repaint();
        } else {

            if (key.getSource() == day) {
                if (day.isSelected()) {
                    dark.setSelected(false);
                    black.setSelected(false);
                    white.setSelected(false);
                    backgroundcolor = Color.lightGray;
                    foregroundcolor = Color.BLACK;
                    color.removeActionListener(this);
                    jbp.removeActionListener(this);
                    obj.run();
                    timer.stop();
                }
            }
            if (key.getSource() == dark) {
                if (dark.isSelected()) {
                    day.setSelected(false);
                    black.setSelected(false);
                    white.setSelected(false);
                    backgroundcolor = Color.DARK_GRAY;
                    foregroundcolor = Color.black;
                    color.removeActionListener(this);
                    jbp.removeActionListener(this);
                    obj.run();
                    timer.stop();
                }
            }
            if (key.getSource() == black) {
                if (black.isSelected()) {
                    dark.setSelected(false);
                    day.setSelected(false);
                    white.setSelected(false);
                    backgroundcolor = Color.black;
                    foregroundcolor = Color.white;
                    color.removeActionListener(this);
                    jbp.removeActionListener(this);
                    obj.run();
                    timer.stop();
                }
            }
            if (key.getSource() == white) {
                if (white.isSelected()) {
                    dark.setSelected(false);
                    day.setSelected(false);
                    black.setSelected(false);
                    backgroundcolor = Color.WHITE;
                    foregroundcolor = Color.BLACK;
                    color.removeActionListener(this);
                    jbp.removeActionListener(this);
                    obj.run();
                    timer.stop();
                }
            }

            if (key.getSource() == dark || key.getSource() == day || key.getSource() == black
                    || key.getSource() == white) {
                if (!dark.isSelected() && !day.isSelected() && !black.isSelected() && !white.isSelected()) {
                    dark.setSelected(true);
                    black.setSelected(false);
                    day.setSelected(false);
                    backgroundcolor = Color.DARK_GRAY;
                    foregroundcolor = Color.BLACK;
                    color.removeActionListener(this);
                    jbp.removeActionListener(this);
                    obj.run();
                }
            }

            if (key.getSource() == jc1) {
                if (jc1.isSelected()) {
                    jc2.setSelected(false);
                    jc3.setSelected(false);
                    jc4.setSelected(false);
                    jc5.setSelected(false);
                    jc6.setSelected(false);
                    speed = speedconstant * 3;
                }
            }
            if (key.getSource() == jc2) {
                if (jc2.isSelected()) {
                    jc1.setSelected(false);
                    jc3.setSelected(false);
                    jc4.setSelected(false);
                    jc5.setSelected(false);
                    jc6.setSelected(false);
                    speed = speedconstant * 2;
                }
            }
            if (key.getSource() == jc3) {
                if (jc3.isSelected()) {
                    jc1.setSelected(false);
                    jc2.setSelected(false);
                    jc4.setSelected(false);
                    jc5.setSelected(false);
                    jc6.setSelected(false);
                    speed = speedconstant;
                }
            }
            if (key.getSource() == jc4) {
                if (jc4.isSelected()) {
                    jc2.setSelected(false);
                    jc3.setSelected(false);
                    jc1.setSelected(false);
                    jc5.setSelected(false);
                    jc6.setSelected(false);
                    speed = speedconstant / 2;
                }
            }
            if (key.getSource() == jc5) {
                if (jc5.isSelected()) {
                    jc2.setSelected(false);
                    jc3.setSelected(false);
                    jc4.setSelected(false);
                    jc1.setSelected(false);
                    jc6.setSelected(false);
                    speed = speedconstant / 3;
                }
            }
            if (key.getSource() == jc6) {
                if (jc6.isSelected()) {
                    jc2.setSelected(false);
                    jc3.setSelected(false);
                    jc4.setSelected(false);
                    jc5.setSelected(false);
                    jc1.setSelected(false);
                    speed = speedconstant / 10;
                }
            }

            if (key.getSource() == jc1 || key.getSource() == jc2 || key.getSource() == jc3 || key.getSource() == jc4
                    || key.getSource() == jc5 || key.getSource() == jc6) {
                if (!jc1.isSelected() && !jc2.isSelected() && !jc3.isSelected() && !jc4.isSelected()
                        && !jc5.isSelected() && !jc6.isSelected()) {
                    jc3.setSelected(true);
                    speed = speedconstant;
                }
            }

            if (key.getSource() == head) {
                if (head.isSelected()) {
                    body.setSelected(false);
                    h = true;
                    b = false;
                }
            }
            if (key.getSource() == body) {
                if (body.isSelected()) {
                    head.setSelected(false);
                    h = false;
                    b = true;
                }
            }
            if (key.getSource() == head || key.getSource() == body) {
                if (!head.isSelected() && !body.isSelected()) {
                    head.setSelected(true);
                    h = true;
                    b = false;
                }
            }

            if (key.getSource() == color) {
                if (h) {
                    headcolor = JColorChooser.showDialog(this, "Select a color", headcolor);
                }
                if (b) {

                    bodycolor = JColorChooser.showDialog(this, "Select a color", bodycolor);
                }
                reoccur = false;
                jf1.repaint();
                obj.properties();
            }
            if (key.getSource() == help) {
                obj.Help();
            }
            if (key.getSource() == jbd) {
                jfd.dispose();
            }
            if (key.getSource() == jbh) {
                jfh.dispose();
            }
            if (key.getSource() == aboutdev) {
                obj.aboutDev();
            }
            if (key.getSource() == jbp) {
                if (headcolor == backgroundcolor || bodycolor == backgroundcolor) {

                    UIManager.put("OptionPane.background", backgroundcolor);
                    UIManager.put("Panel.background", backgroundcolor);
                    UIManager.put("OptionPane.messageForeground", foregroundcolor);
                    JOptionPane.showMessageDialog(null,
                            "<html> <center> Please Change the Color of Snake Body and Head. <br> Otherwise it will be not Visible on Background. </center> <html>",
                            "Change Color!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    playing = true;
                    obj.stopvisible();
                    obj.run();
                }
            }
        }

        if (key.getSource() == restart) {
            obj.restartgame();
        }
        if (key.getSource() == home) {
            obj.menuagain();
        }
        if (key.getSource() == replay) {
            hscore = 0;
            score = 0;
            obj.restartgame();
        }
    }

    void restartgame() {
        playing = true;
        score = 0;
        moves = true;
        left = false;
        right = false;
        up = false;
        down = false;
        boxlength = 4;
        jf1.repaint();
    }

    // ======== replaying from start
    void menuagain() {
        playing = false;
        score = 0;
        moves = true;
        left = false;
        right = false;
        up = false;
        down = false;
        reoccur = true;
        space = false;
        boxlength = 4;
        jf1.getContentPane().removeAll();
        obj.reinitilizer();
        timer.stop();
        obj.run();
    }

    // ======== restarting without going to menu
    void reinitilizer() {

        jc1.setSelected(false);
        jc2.setSelected(false);
        jc3.setSelected(true);
        jc4.setSelected(false);
        jc5.setSelected(false);
        jc6.setSelected(false);
        head.setSelected(true);
        body.setSelected(false);

        h = true;
        b = false;
        color.removeActionListener(this);
        jbp.removeActionListener(this);
        speed = speedconstant;

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        theme = new JPanel();
        temp = new JPanel();
    }

    // ======== Help menu
    void Help() {

        jfh.setIconImage(infoicon);
        jfh.setSize(300, 380);
        jfh.setLocation((int) (dm.getWidth() / 2 - jfh.getWidth() / 2),
                (int) (dm.getHeight() / 2 - jfh.getHeight() / 2));
        jfh.setVisible(true);
        jfh.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jfh.setResizable(false);

        jph.setLocation(0, 0);
        jph.setSize(jfh.getWidth(), jfh.getHeight());
        jph.setBackground(backgroundcolor);
        TitledBorder pborder = BorderFactory.createTitledBorder("Help");
        pborder.setTitleJustification(TitledBorder.CENTER);
        pborder.setTitleFont(new Font("Ariel", Font.BOLD, 12));
        pborder.setTitleColor(foregroundcolor);
        jph.setBorder(pborder);
        jph.setLayout(null);

        jbh.setSize(60, 30);
        jbh.setLocation(jph.getWidth() / 2 - 30, jph.getHeight() - 70);

        jlh1.setForeground(foregroundcolor);
        Font font0 = new Font("SansSerif", Font.BOLD, 20);
        jlh1.setFont(font0);
        jlh1.setSize(jph.getWidth(), 25);
        jlh1.setLocation(10, 25);
        jlh1.setText("How To Play :");

        String text = "<html> <p> .Simple Use Arrow Keys <br> . &emsp (for Left) <br> . &emsp (for Up) <br> . &emsp (for Down) <br> . &emsp (for Right) <br> . To Change Snake Head & Body Color <br> Tick the head or box and click the icon to choose Color. <br> . Use Theme for Better Experience.  </p> </html>";

        jlh2.setForeground(foregroundcolor);
        Font font1 = new Font("Ariel", Font.PLAIN, 15);
        jlh2.setFont(font1);
        jlh2.setSize(jph.getWidth(), jph.getHeight() - 70);
        jlh2.setLocation(10, 25);
        jlh2.setText(text);

        temp.setBackground(backgroundcolor);
        temp.setLocation(5, 85);
        temp.setSize(20, jlh2.getHeight() - 230);
        temp.setVisible(true);

        JLabel left = new JLabel(new ImageIcon(lefticon));
        left.setLocation(0, 0);
        left.setSize(15, 15);

        JLabel up = new JLabel(new ImageIcon(upicon));
        up.setLocation(0, 15);
        up.setSize(15, 15);

        JLabel down = new JLabel(new ImageIcon(downicon));
        down.setLocation(0, 30);
        down.setSize(15, 15);

        JLabel right = new JLabel(new ImageIcon(righticon));
        right.setLocation(0, 45);
        right.setSize(15, 15);

        jfh.add(jph);
        jph.add(jbh);
        jph.add(jlh1);
        jph.add(jlh2);
        jlh2.add(temp);
        temp.add(left);
        temp.add(up);
        temp.add(down);
        temp.add(right);

        jbh.addActionListener(this);
    }

    // ======== info menu
    void aboutDev() {

        jfd.setIconImage(abouticon);
        jfd.setSize(350, 350);
        jfd.setLocation((int) (dm.getWidth() / 2 - jfd.getWidth() / 2),
                (int) (dm.getHeight() / 2 - jfd.getHeight() / 2));
        jfd.setVisible(true);
        jfd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jfd.setResizable(false);

        jpd.setLocation(0, 0);
        jpd.setSize(jfd.getWidth(), jfd.getHeight());
        jpd.setBackground(backgroundcolor);
        TitledBorder pdborder = BorderFactory.createTitledBorder("About me!");
        pdborder.setTitleJustification(TitledBorder.CENTER);
        pdborder.setTitleFont(new Font("Ariel", Font.BOLD, 12));
        pdborder.setTitleColor(foregroundcolor);
        jpd.setBorder(pdborder);
        jpd.setLayout(null);

        jbd.setSize(60, 30);
        jbd.setLocation(jpd.getWidth() / 2 - 30, jpd.getHeight() - 70);

        jld1.setForeground(foregroundcolor);
        Font font0 = new Font("SansSerif", Font.BOLD, 20);
        jld1.setFont(font0);
        jld1.setSize(jpd.getWidth(), 25);
        jld1.setLocation(10, 25);
        jld1.setText("<html> &emsp&emsp&emsp&emsp&emsp About Me  </html>");
        String center = "&emsp&nbsp&nbsp&emsp&nbsp&nbsp&emsp&nbsp&nbsp&emsp";
        String text1 = "<html> <p>  <center> Mail => nik.singh710@gmail.com  <br> Mail => nik.singh710@outlook.com <br> Telegram => https://t.me/niksingh710 <br> Github => https://github.com/niksingh710<br> Paypal => paypal.me/niksingh710 <br> On Paypal u can use Gmail id to. </center> </p> </html>";
        String text2 = "<html> <p> <center> " + center + " Created By : <br> " + center + " Nikhil Singh <br> " + center
                + " :: niksingh710:: </center> </p> </html>";
        jld2.setForeground(foregroundcolor);
        Font font1 = new Font("Ariel", Font.PLAIN, 17);
        jld2.setFont(font1);
        jld2.setSize(jpd.getWidth(), jpd.getHeight() - 120);
        jld2.setLocation(10, 0);
        jld2.setText(text1);

        jld3.setForeground(foregroundcolor);
        Font font2 = new Font("Ariel", Font.PLAIN, 18);
        jld3.setFont(font2);
        jld3.setSize(jpd.getWidth(), jpd.getHeight() + 110);
        jld3.setLocation(10, 0);
        jld3.setText(text2);

        jfd.add(jpd);
        jpd.add(jbd);
        jpd.add(jld1);
        jpd.add(jld2);
        jpd.add(jld3);

        jbd.addActionListener(this);
    }

    // ======== foodlocation Modification
    void foodlocationUpdater() {

        if (moves) {
            boxx[0] = box * 4;
            boxy[0] = box * 3;
            boxx[1] = box * 3;
            boxy[1] = box * 3;
            boxx[2] = box * 2;
            boxy[2] = box * 3;
            boxx[3] = box * 1;
            boxy[3] = box * 3;
        }
        int column = (int) (jf1.getHeight() - box * 2) / box;
        int row = (int) (jf1.getWidth() - box) / box;
        double x = Math.random() * (row - 0);
        x = (int) x;
        double y = Math.random() * (column - 0);
        y = (int) y;
        if (y < 3) {
            y += 3;
        }
        foodx = (int) x * box;
        foody = (int) y * box;
        for (int i = 1; i < boxlength; i++) {
            if (boxx[i] == foodx && boxy[i] == foody) {
                foodlocationUpdater();
            }
        }
    }

    // ======== Death
    void death() {
        timer.stop();
    }

    // ======== Controlling restart game bugs
    void stopvisible() {
        jp1.setVisible(false);
        jp2.setVisible(false);
        jp3.setVisible(false);
        theme.setVisible(false);
    }

    void exitwork() throws IOException {
        FileWriter fw = new FileWriter("High_Score/HighScore.dat", false);
        fw.write(String.valueOf(hscore));
        fw.close();
        jf1.dispose();
        System.exit(0);
    }
}
