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

public class SearchUser extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private String inputUsername;

	public SearchUser() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtUsername.setText("");
			}
		});
		setTitle("Αναζήτηση Εκπαιδευτών");
		//setIconImage(Toolkit.getDefaultToolkit().getImage(SearchUser.class.getResource("/resources/eduv2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(87, 57, 214, 25);
		contentPane.add(txtUsername);
		txtUsername.setColumns(50);
		
		JButton btnSearch = new JButton("Αναζήτηση");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputUsername = txtUsername.getText().trim();
				Main.getSearchUser().setEnabled(false);
				Main.getUpdateDeleteFormUsername().setVisible(true);
			}
		});
		btnSearch.setForeground(new Color(0, 0, 255));
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(122, 89, 144, 44);
		contentPane.add(btnSearch);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setForeground(new Color(0, 0, 255));
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(248, 255, 96, 49);
		contentPane.add(btnClose);
		
		JButton btnInsert = new JButton("Εισαγωγή");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchUser().setEnabled(false);
				Main.getInsertUser().setVisible(true);
			}
		});
		btnInsert.setForeground(Color.BLUE);
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInsert.setBounds(122, 179, 144, 44);
		contentPane.add(btnInsert);
		
		JPanel panel = new JPanel();
		panel.setBounds(44, 15, 300, 134);
		contentPane.add(panel);
		
		JLabel lblLastname = new JLabel("Username");
		panel.add(lblLastname);
		lblLastname.setForeground(new Color(165, 42, 42));
		lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(44, 161, 300, 81);
		contentPane.add(panel_1);
	}

	public String getInputUsername() {
		return inputUsername;
	}


}
