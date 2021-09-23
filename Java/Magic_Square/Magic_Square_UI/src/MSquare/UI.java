import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class UI implements ActionListener {
    static UI obj = new UI();
    static Magic_Square mm = new Magic_Square();
    static JFrame jf0 = new JFrame();
    static JFrame jf1 = new JFrame();
    JLabel output = new JLabel();
    JLabel user_info = new JLabel("<html> Enter an odd No. <br> &emsp &emsp   0-9 </html>");
    JTextField user_input = new JTextField("  ");
    JButton button = new JButton("Done");
    JLabel img = new JLabel(new ImageIcon("img/img.jpeg"));
    JLabel back_img = new JLabel(new ImageIcon("img/back_img.gif"));
    ImageIcon logoicon=new ImageIcon("img/icon.png");
    Image icon=logoicon.getImage();
    static int input;
    static int array[][];
    static String out;
    static Dimension dm=Toolkit.getDefaultToolkit().getScreenSize();

    public static void main(String[] args) {
        obj.properties();
        obj.add();
        jf0.setVisible(true);
    }

    public void properties() {
        jf0.setTitle("User Input");
        jf0.setLayout(null);
        jf1.setLayout(null);
        jf1.setTitle("Magic Square");
        back_img.setBounds(0, 0, 300, 230);
        jf0.setSize( 250, 180);
        img.setBounds(0, 0, 250, 180);
        jf1.setSize( 300, 230);
        jf1.setLocation((dm.width-jf1.getSize().width)/2, (dm.height-jf1 .getSize().height)/2);
        jf0.setLocation((dm.width-jf0.getSize().width)/2, (dm.height-jf0.getSize().height)/8);
        jf0.setResizable(false);
        jf0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf1.setResizable(false);
        jf0.setIconImage(icon);
        jf1.setIconImage(icon);
        user_info.setBounds(70, 10, 150, 40);
        user_info.setForeground(Color.yellow);
        user_input.setBounds(95, 60, 25, 25);
        user_input.setBackground(Color.black);
        user_input.setForeground(Color.white);
        user_input.setCaretColor(Color.WHITE);
        user_input.setCaretPosition(2);
        button.setBounds(75, 90, 65, 25);
        button.addActionListener(this);
    }

    public void add() {
        jf0.add(user_info);
        jf0.add(user_input);
        jf0.add(button);
        jf0.add(img);
        jf1.add(output);
        jf1.add(back_img);
    }

    @Override
    public void actionPerformed(ActionEvent key) {
        if (key.getSource() == button) {
            obj.work();
        }
    }
    void work(){
                input = Integer.parseInt(user_input.getText().trim());
            if (input % 2 == 0) {
                JOptionPane.showMessageDialog(null,
                        "<html> &emsp &emsp &emsp ERROR!! <br> &emsp Enter an Odd Number </html>", "ERROR",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                array = mm.array(input);
                out = obj.display(array);
                output.setText("<html> " + out + " </html>");
                output.setForeground(Color.white);
                output.setBounds((10 - input) * 15, -120, 400, 400);
                jf1.setVisible(true);
            }
    }

    String display(int[][] array) {
        String txt = "";
        String p = "";
        for (int i = 0; i < input; i++) {
            for (int j = 0; j < input; j++) {
                p = String.valueOf(array[i][j]);
                p = p.trim();
                while (p.length() < Integer.toString(input * input).length()) {
                    p = p + " &nbsp ";
                }
                txt = txt + p + " &nbsp &nbsp ";
            }
            txt = txt + " <br> ";
        }
        return txt;
    }
}
