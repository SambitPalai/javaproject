package notepad;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {

    JMenuItem newdoc,open,save,print,exit,copy,cut,paste,select_all,about;
    JTextArea area;
    StringBuilder text;

    Notepad(){

        setTitle("My Notepad");
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("icons/notepad.png"));
        Image i1 = notepadIcon.getImage();
        setIconImage(i1);

        // Menubar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);

        // Menus in a Menubar ----> File
        JMenu file = new JMenu("File");
        file.setFont(new Font("System",Font.PLAIN,14));

        newdoc = new JMenuItem("New"); // option in a menu i.e "New"
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newdoc.addActionListener(this);
        file.add(newdoc);
        open = new JMenuItem("Open"); // option in a menu i.e "Open"
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);
        file.add(open);
        save = new JMenuItem("Save"); // option in a menu i.e "Save"
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);
        file.add(save);
        print = new JMenuItem("Print"); // option in a menu i.e "Print"
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);
        file.add(print);
        exit = new JMenuItem("Exit"); // option in a menu i.e "Exit"
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        exit.addActionListener(this);
        file.add(exit);
        menuBar.add(file);

        // Menus in a Menubar ----> Edit
        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("System",Font.PLAIN,14));

        copy = new JMenuItem("Copy"); // option in a menu i.e "Copy"
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);
        edit.add(copy);
        paste = new JMenuItem("Paste"); // option in a menu i.e "Paste"
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);
        edit.add(paste);
        cut = new JMenuItem("Cut"); // option in a menu i.e "Cut"
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);
        edit.add(cut);
        select_all = new JMenuItem("Select All"); // option in a menu i.e "Select All"
        select_all.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        select_all.addActionListener(this);
        edit.add(select_all);
        menuBar.add(edit);

        // Menus in a Menubar ----> Help
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setFont(new Font("System",Font.PLAIN,14));

        about = new JMenuItem("About"); // option in a menu i.e "About"
        helpMenu.add(about);
        about.addActionListener(this);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);//I built a menu bar and menus now I'm attaching it to the window so the user can see it.

        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
        area.setLineWrap(true); // automatically goes to next line when the line is full
        area.setWrapStyleWord(true); // let's say word IceCream here Ice and Cream got SEPARATED as the line gets full but IceCream will go to next line instead of splitting .
        add(area);

        JScrollPane pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae){
        // EXIT
        if (ae.getActionCommand().equals("Exit") ){
            System.exit(0);
        }
        // ABOUT
        else if (ae.getActionCommand().equals("About")) {
            new About().setVisible(true);
        // NEW
        } else if (ae.getActionCommand().equals("New") ){
            area.setText("");
        // OPEN
        } else if (ae.getActionCommand().equals("Open")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files","txt");
            chooser.addChoosableFileFilter(restrict);
            int action = chooser.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION){
                return;
            }
            // It helps to read the selected file
            File file = chooser.getSelectedFile();
            try{
                BufferedReader reader = new BufferedReader(new FileReader(file)); // Reads the file
                area.read(reader,null); // Sets the text in the JTextArea i.e. area the way it is not in ascending or descending order.
            }catch(Exception e) {
                e.printStackTrace();
            }
        // SAVE
        } else if (ae.getActionCommand().equals("Save")) {
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");
            int action = saveas.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File filename = new File(saveas.getSelectedFile()+".txt");
            BufferedWriter outFile = null;
            try{
                outFile = new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            }catch (Exception e){
                e.printStackTrace();
            }
        // SAVE
        } else if (ae.getActionCommand().equals("Print")){
            try {
                area.print();
            }catch (Exception e){
              e.printStackTrace();
            }
        // COPY
        }else if (ae.getActionCommand().equals("Copy")) {
            text = new StringBuilder(area.getSelectedText());
        // PASTE
        }else if (ae.getActionCommand().equals("Paste")) {
            area.insert(String.valueOf(text),area.getCaretPosition());
        // CUT
        } else if (ae.getActionCommand().equals("Cut")) {
            text = new StringBuilder(area.getSelectedText());
            area.replaceRange(String.valueOf(""),area.getSelectionStart(),area.getSelectionEnd());
        // SELECT ALL
        }else if (ae.getActionCommand().equals("Select All")) {
            area.selectAll();
        }
    }

    public static void main(String[] args) {
        new Notepad();
    }
}
