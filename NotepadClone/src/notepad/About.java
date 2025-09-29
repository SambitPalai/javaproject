package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class About extends JFrame implements ActionListener {

    JButton close;

    About(){

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Windows_11_.png"));
        Image i2 = i1.getImage().getScaledInstance(400,75,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label1 = new JLabel(i3);
        label1.setBounds(150,40,400,75);
        add(label1);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/notepad.png"));
        Image i5 = i4.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel label2 = new JLabel(i6);
        label2.setBounds(80,150,90,90);
        add(label2);

        JLabel text = new JLabel("<html>"  +
                        "<h2 style='color:blue;'>My Notepad Project</h2>"  +
                        "<p><b>Built with Java</b></p>"  +
                        "<p style='color:green;'>On 30-09-2025</p>"  +
                        "<p>All rights of the project reserved to me . </p>"  +
                        "</html>");
        text.setBounds(220,50,300,300);
        add(text);

        close = new JButton("Close");
        close.setBackground(Color.WHITE);
        close.setForeground(Color.BLACK);
        close.setBounds(300,550,100,20);
        close.addActionListener(this);
        add(close);

        setLayout(null);
        setUndecorated(true);
        setLocation(400,50);
        getContentPane().setBackground(Color.WHITE);
        setSize(700,600);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("Close")){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new About();
    }
}
