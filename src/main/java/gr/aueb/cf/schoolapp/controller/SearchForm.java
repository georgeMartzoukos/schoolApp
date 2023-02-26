package gr.aueb.cf.schoolapp.controller;


import gr.aueb.cf.schoolapp.Main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class SearchForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtLastname;
    private String inputLastname;

    public SearchForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                txtLastname.setText("");
            }
        });
        setTitle("Αναζήτηση Εκπαιδευτών");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 428, 354);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLastname = new JLabel("Επώνυμο");
        lblLastname.setForeground(new Color(165, 42, 42));
        lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblLastname.setBounds(154, 30, 80, 20);
        contentPane.add(lblLastname);

        txtLastname = new JTextField();
        txtLastname.setBounds(87, 57, 214, 25);
        contentPane.add(txtLastname);
        txtLastname.setColumns(50);

        JButton btnSearch = new JButton("Αναζήτηση");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputLastname = txtLastname.getText().trim();
                Main.getSearchForm().setEnabled(false);
                Main.getUpdateDeleteForm().setVisible(true);
            }
        });
        btnSearch.setForeground(new Color(0, 0, 255));
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSearch.setBounds(122, 89, 144, 44);
        contentPane.add(btnSearch);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getMenu().setEnabled(true);
                Main.getSearchForm().setVisible(false);
            }
        });
        btnClose.setForeground(new Color(0, 0, 255));
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnClose.setBounds(248, 255, 96, 49);
        contentPane.add(btnClose);

        JButton btnInsert = new JButton("Εισαγωγή");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchForm().setEnabled(false);
                Main.getInsertForm().setVisible(true);
            }
        });
        btnInsert.setForeground(Color.BLUE);
        btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnInsert.setBounds(122, 179, 144, 44);
        contentPane.add(btnInsert);

        JPanel panel = new JPanel();
        panel.setBounds(44, 15, 300, 134);
        contentPane.add(panel);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(44, 161, 300, 81);
        contentPane.add(panel_1);
    }

    public String getInputLastname() {
        return inputLastname;
    }

}

