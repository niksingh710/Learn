package app.screen;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * MainFrame
 */
public class MainFrame extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Panel panel;
    private JButton start_btn;
    private JLabel size_label;
    private JLabel drop_label;
    private JLabel sort;
    private JLabel time;
    private JLabel slider_label;
    private JSlider slider;
    private JTextField size_inp;
    private JRadioButton ascending, descending;
    private JRadioButton ms, s;
    private JComboBox<String> drop_down;
    private String[] algorithm = { "Bubble Sort", "Selection Sort", "Insertion Sort" };
    private ArrayList<String> algorithm_avail;
    private int w, h, size;
    private char todo;
    private ButtonGroup bg, sbg;
    private String text;
    private char t;
    private int speed;

    public MainFrame(int w, int h) {
        super("Visualizer By Nikhil Singh");
        setSize(w, h);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        this.w = w;
        this.h = h;

        panel = new Panel(w, h, 0, 0, Color.black);
        ascending = new JRadioButton("Ascending");
        descending = new JRadioButton("Descending");
        slider = new JSlider();
        slider_label = new JLabel("Speed");
        ms = new JRadioButton("MilliSeconds");
        s = new JRadioButton("Seconds");
        bg = new ButtonGroup();
        sbg = new ButtonGroup();
        start_btn = new JButton("Start");
        size_label = new JLabel("Size :- ");
        drop_label = new JLabel("Algorithm :- ");
        size_inp = new JTextField();
        sort = new JLabel("Sort :- ");
        time = new JLabel("Time :- ");
        drop_down = new JComboBox<String>(algorithm);

        this.algorithm_avail = new ArrayList<String>();

        avail_algo();

        btn_properties();
        label_properties();
        text_inp_properties();
        drop_down_properties();
        radio_Property();
        slider_Property();

        add();
    }

    private void avail_algo() {
        this.algorithm_avail.add("Bubble Sort");
        this.algorithm_avail.add("Selection Sort");
    }

    private void slider_Property() {
        slider.setOrientation(JSlider.HORIZONTAL);
        slider.setMinimum(0);
        slider.setMaximum(10);
        slider.setSize(200, 30);
        slider.setLocation(20, 90);
        slider.setMajorTickSpacing(1);
        slider.setPaintLabels(false);
        slider.setOpaque(false);
        slider.setToolTipText("Slow->Fast->Fastest");
        slider.setPaintTicks(true);
        slider.setValue(5);
        slider.setForeground(Color.white);
    }

    private void radio_Property() {
        ascending.setSize(100, 20);
        ascending.setLocation(60, 40);
        ascending.setBorder(null);
        ascending.setForeground(Color.white);
        ascending.setBackground(Color.black);
        ascending.setOpaque(false);
        ascending.setSelected(true);

        descending.setSize(100, 20);
        descending.setLocation(150, 40);
        descending.setBorder(null);
        descending.setForeground(Color.white);
        descending.setBackground(Color.black);
        descending.setOpaque(false);

        s.setSize(100, 20);
        s.setLocation(60, 60);
        s.setBorder(null);
        s.setForeground(Color.white);
        s.setBackground(Color.black);
        s.setOpaque(false);
        s.setSelected(true);

        ms.setSize(100, 20);
        ms.setLocation(140, 60);
        ms.setBorder(null);
        ms.setForeground(Color.white);
        ms.setBackground(Color.black);
        ms.setOpaque(false);
    }

    private void drop_down_properties() {
        drop_down.setSize(140, 20);
        drop_down.setLocation(90, 10);
    }

    private void text_inp_properties() {
        size_inp.setSize(70, 20);
        size_inp.setLocation(w - 180, h - 60);
        size_inp.setToolTipText("Enter The Size of Canvas.");
    }

    private void label_properties() {
        size_label.setSize(70, 20);
        size_label.setLocation(w - 220, h - 60);
        size_label.setForeground(Color.white);

        drop_label.setSize(80, 20);
        drop_label.setLocation(10, 10);
        drop_label.setForeground(Color.white);

        sort.setSize(70, 20);
        sort.setLocation(20, 40);
        sort.setForeground(Color.white);

        time.setSize(60, 20);
        time.setLocation(10, 60);
        time.setForeground(Color.white);

        slider_label.setSize(70, 20);
        slider_label.setLocation(100, 120);
        slider_label.setForeground(Color.white);
    }

    private void btn_properties() {
        start_btn.setSize(70, 20);
        start_btn.setBorderPainted(false);
        start_btn.setLocation(w - 90, h - 60);
        start_btn.addActionListener(this);
    }

    private void add() {
        add(panel);
        panel.add(start_btn);
        panel.add(size_label);
        panel.add(size_inp);
        panel.add(drop_down);
        panel.add(drop_label);
        panel.add(ascending);
        panel.add(descending);
        panel.add(sort);
        panel.add(time);
        panel.add(ms);
        panel.add(s);
        panel.add(slider);
        panel.add(slider_label);

        bg.add(ascending);
        bg.add(descending);
        sbg.add(ms);
        sbg.add(s);
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        try {
            text = String.valueOf(drop_down.getSelectedItem());
            if (Integer.parseInt(size_inp.getText()) >= 100 && Integer.parseInt(size_inp.getText()) <= 1000) {
                this.size = Integer.parseInt(size_inp.getText());
                this.speed = slider.getValue();
                if (ascending.isSelected()) {
                    this.todo = 'a';
                } else if (descending.isSelected()) {
                    this.todo = 'd';
                }
                if (ms.isSelected()) {
                    this.t = 'm';
                } else if (s.isSelected()) {
                    this.t = 's';
                }
                if (algorithm_avail.contains(drop_down.getSelectedItem())) {
                    new Frame(size, (String) drop_down.getSelectedItem(), todo, text, t, speed);
                } else {
                    JOptionPane.showMessageDialog(null, "This Algotithm Coming Soon.", "Dialog",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please Enter Value Between 99 and 1001.", "Dialog",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please Enter a positive Integer Value.", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}