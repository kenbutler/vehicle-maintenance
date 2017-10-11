/**
 * Created by kenbutler on 6/20/17.
 */
package garage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import java.io.*;
import java.text.ParseException;

public class Garage {

    private JFrame frame;
    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    /**
     * Launch the application.
     */
    /*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Garage window = new Garage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    */

    /**
     * Create the application.
     */
    public Garage() throws IOException, ParseException {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() throws IOException, ParseException {
        frame = new JFrame();
        frame.setBounds(100, 100, 1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Overview
        //Overview menu = new Overview();
        CurrentState myState = new CurrentState();

        //tabbedPane.addTab("Overview",  null, menu, null);
        tabbedPane.addTab("History", new JPanel());


    }

}

