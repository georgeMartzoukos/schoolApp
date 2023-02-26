package gr.aueb.cf.schoolapp.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.DAO.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.DAO.usersDAO.IUserDAO;
import gr.aueb.cf.schoolapp.DAO.usersDAO.UserDAOImpl;
import gr.aueb.cf.schoolapp.DTO.UserDTO;
import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;
import gr.aueb.cf.schoolapp.service.util.DBUtil;
import org.mindrot.jbcrypt.BCrypt;


import javax.swing.JPasswordField;

public class UpdateDeleteFormUsername extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtUsername;
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement p;
	private ResultSet rs;
	private JPasswordField txtPassword;

	 IUserDAO  userDAO = new UserDAOImpl();
	 IUserService userService = new UserServiceImpl(userDAO);
	private int listPosition;
	private int listSize;
	List<User> users = new ArrayList<>();
	
	public UpdateDeleteFormUsername() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					users = userService.getUserByUsername(Main.getSearchUser().getInputUsername());
					listSize = users.size();
					listPosition = 0;

					if (listSize == 0) {
						txtId.setText("");
						txtUsername.setText("");
						txtPassword.setText("");
					}

					txtId.setText(Integer.toString(users.get(listPosition).getID()));
					txtUsername.setText(users.get(listPosition).getUsername());
					//txtPassword.setText(users.get(listPosition).getUsername());

				} catch (UserDAOException  e1) {
					String message = e1.getMessage();
					JOptionPane.showMessageDialog(null, message,"Error while getting users",JOptionPane.ERROR_MESSAGE);
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {

			}
		});
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
		setTitle("Ενημέρωση / Διαγραφή User");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 423, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setForeground(new Color(165, 42, 42));
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(22, 55, 88, 20);
		contentPane.add(lblUsername);
		
		txtId = new JTextField();
		txtId.setBackground(new Color(245, 245, 220));
		txtId.setEditable(false);
		txtId.setBounds(100, 21, 38, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(120, 55, 180, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(50);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 146, 370, 1);
		contentPane.add(separator);
		
		JButton btnFirst = new JButton("");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listPosition > 0) {
					listPosition = 0;
					txtId.setText(Integer.toString(users.get(listPosition).getID()));
					txtUsername.setText(users.get(listPosition).getUsername());
					//txtPassword.setText(users.get(0).getPassword());
				}
			}
		});
		btnFirst.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("First record.png"))));
		btnFirst.setBounds(22, 158, 46, 23);
		contentPane.add(btnFirst);
		
		JButton btnPrevious = new JButton("");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listPosition > 0 ) {
					listPosition--;
					txtId.setText(Integer.toString(users.get(listPosition).getID()));
					txtUsername.setText(users.get(listPosition).getUsername());
					//txtPassword.setText(users.get(listPosition).getPassword());
				}
			}
		});
		btnPrevious.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Previous_record.png"))));
		btnPrevious.setBounds(69, 158, 46, 23);
		contentPane.add(btnPrevious);
		
		JButton btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listPosition < listSize - 1) {
					listPosition++;
					txtId.setText(Integer.toString(users.get(listPosition).getID()));
					txtUsername.setText(users.get(listPosition).getUsername());
					//txtPassword.setText(users.get(listPosition).getPassword());
				}
			}
		});
		btnNext.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Next_track.png"))));
		btnNext.setBounds(116, 158, 46, 23);
		contentPane.add(btnNext);
		
		JButton btnLast = new JButton("");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( listPosition < listSize - 1) {
					listPosition = listSize - 1;
					txtId.setText(Integer.toString(users.get(listPosition).getID()));
					txtUsername.setText(users.get(listPosition).getUsername());
					//txtPassword.setText(users.get(listPosition).getPassword());
				}
			}
		});
		btnLast.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Last_Record.png"))));
		btnLast.setBounds(163, 158, 46, 23);
		contentPane.add(btnLast);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response;
				try {
					int id;

					if (txtId.getText().equals("")) return;
					id = Integer.parseInt(txtId.getText());
					response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος", "Delete",
							JOptionPane.YES_NO_OPTION );

					if (response == JOptionPane.YES_OPTION) {
						userService.deleteUser(id);
						JOptionPane.showMessageDialog(null,  " Successfully deleted",
								"Delete", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (UserNotFoundException | UserDAOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setForeground(new Color(0, 0, 255));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(22, 202, 98, 36);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputUsername;
				String inputPassword;
				int id;
				try {
					id = Integer.parseInt(txtId.getText().trim());
					inputUsername = txtUsername.getText().trim();
					inputPassword = String.valueOf(txtPassword.getPassword()).trim();
					
					if (inputPassword.equals("") || (inputUsername.equals(""))) {
						throw new UserDAOException("password or username is null");
					}
					UserDTO userDTO = new UserDTO();
					userDTO.setUsername(inputUsername);
					userDTO.setPassword(inputPassword);
					userDTO.setId(id);
					User user = userService.updateUser(userDTO);

					txtPassword.setText("");
					JOptionPane.showMessageDialog(null, " record updated",
							"UPDATE", JOptionPane.PLAIN_MESSAGE);
				} catch (UserDAOException | UserNotFoundException e1) {
					String message = e1.getMessage();
					JOptionPane.showMessageDialog(null,message,"Update error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnUpdate.setForeground(new Color(0, 0, 255));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(121, 202, 98, 36);
		contentPane.add(btnUpdate);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchUser().setEnabled(true);
				Main.getUpdateDeleteFormUsername().setVisible(false);
			}
			
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setForeground(new Color(0, 0, 255));
		btnClose.setBounds(220, 202, 98, 36);
		contentPane.add(btnClose);
		
		JLabel lblId = new JLabel("Κωδικός");
		lblId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblId.setForeground(new Color(165, 42, 42));
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId.setBounds(22, 19, 68, 20);
		contentPane.add(lblId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setForeground(new Color(165, 42, 42));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(42, 88, 68, 20);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(120, 91, 180, 19);
		contentPane.add(txtPassword);
	}
	

}
