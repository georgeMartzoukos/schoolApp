package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.DAO.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.DAO.teachersDAO.ITeacherDAO;
import gr.aueb.cf.schoolapp.DAO.teachersDAO.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.DAO.usersDAO.IUserDAO;
import gr.aueb.cf.schoolapp.DAO.usersDAO.UserDAOImpl;
import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends JFrame {
	IUserDAO userDAO = new UserDAOImpl();
	IUserService userService = new UserServiceImpl(userDAO);
	List<User> users = new ArrayList<>();
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;


	public LoginPage() {
		setTitle("Είσοδος Χρήστη");
		//setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/resources/eduv2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setForeground(new Color(165, 42, 42));
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setBounds(64, 32, 110, 36);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setForeground(new Color(165, 42, 42));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(45, 77, 130, 36);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(184, 37, 162, 27);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(45, 159, 330, 1);
		contentPane.add(separator);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputUsername = txtUsername.getText().trim();
				String inputPassword = String.valueOf(txtPassword.getPassword()).trim();
				String password = System.getenv("TS_ADMIN_PASSWORD");
				String hashedPassword;
				
				if (inputUsername.equals("") || inputPassword.equals("")) {
					return;
				}
				
				if (inputUsername.equals("george") && (inputPassword.equals(password))) {
					Main.getLoginPage().setVisible(false);
					Main.getSearchUser().setVisible(true);
				} else  {
					try {
						hashedPassword = userService.getUserByUsername(inputUsername).get(0).getPassword();
						if (BCrypt.checkpw(inputPassword, hashedPassword)) {
							Main.getLoginPage().setVisible(false);
							Main.getSearchForm().setVisible(true);
						}
					}catch (UserDAOException e1)  {
						String message = e1.getMessage();
						JOptionPane.showMessageDialog(null,message, "User not found", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnLogin.setForeground(new Color(0, 0, 255));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.setBounds(211, 188, 135, 54);
		contentPane.add(btnLogin);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(185, 82, 162, 27);
		contentPane.add(txtPassword);
	}
}
