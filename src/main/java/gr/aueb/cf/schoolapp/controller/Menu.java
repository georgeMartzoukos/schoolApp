package gr.aueb.cf.schoolapp.controller;


import gr.aueb.cf.schoolapp.Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Menu extends JFrame {
    private static final long serialVersionUID = 1L;


    public Menu() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setTitle("Διαχείριση Εκπαιδευτικού Συστήματος");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 451, 256);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblQuality1 = new JLabel("Ποιότητα στην Εκπαίδευση");
        lblQuality1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblQuality1.setBounds(82, 11, 244, 46);
        contentPane.add(lblQuality1);

        JSeparator separator = new JSeparator();
        separator.setBounds(40, 68, 345, 1);
        contentPane.add(separator);

        JButton btnTeachers = new JButton("");
        btnTeachers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getMenu().setEnabled(false);
                Main.getSearchForm().setVisible(true);
            }
        });
        btnTeachers.setBounds(27, 94, 32, 32);
        contentPane.add(btnTeachers);

        JLabel lblTeachers = new JLabel("Εκπαιδευτές");
        lblTeachers.setForeground(new Color(165, 42, 42));
        lblTeachers.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTeachers.setBounds(66, 97, 95, 27);
        contentPane.add(lblTeachers);

        JButton btnStudents = new JButton("");
        btnStudents.setBounds(27, 134, 32, 32);
        contentPane.add(btnStudents);

        JLabel lblStudents = new JLabel("Εκπαιδευόμενοι");
        lblStudents.setForeground(new Color(165, 42, 42));
        lblStudents.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblStudents.setBounds(66, 137, 115, 27);
        contentPane.add(lblStudents);

        JLabel lblQuality2 = new JLabel("Ποιότητα στην Εκπαίδευση");
        lblQuality2.setForeground(new Color(0, 128, 0));
        lblQuality2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblQuality2.setBounds(84, 13, 244, 46);
        contentPane.add(lblQuality2);
    }


}
