package gr.aueb.cf.schoolapp.controller;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.DAO.teachersDAO.ITeacherDAO;
import gr.aueb.cf.schoolapp.DAO.teachersDAO.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.DAO.exceptions.TeacherDaoException;
import gr.aueb.cf.schoolapp.DTO.TeacherDTO;
import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertForm extends JFrame {
    private static final long serialVersionUID = 1L;

    private ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    private JPanel contentPane;
    private JTextField txtLastname;
    private JTextArea txtFirstname;
    private JButton btnInsert;
    private JButton btnClose;
    private final JPanel panel = new JPanel();
    private JSeparator separator;



    public InsertForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                txtLastname.setText("");
                txtFirstname.setText("");
            }
        });

        setForeground(new Color(51, 0, 255));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLastname = new JLabel("Επώνυμο");
        lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblLastname.setBounds(107, 46, 78, 13);
        contentPane.add(lblLastname);

        txtLastname = new JTextField();
        txtLastname.setBounds(202, 41, 181, 19);
        contentPane.add(txtLastname);
        txtLastname.setColumns(50);

        JLabel lblFirstname = new JLabel("Όνομα");
        lblFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFirstname.setBounds(96, 71, 68, 13);
        contentPane.add(lblFirstname);

        txtFirstname = new JTextArea();
        txtFirstname.setBounds(202, 70, 180, 16);
        txtFirstname.setColumns(50);
        contentPane.add(txtFirstname);

        JButton btnInsert = new JButton("Insert");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try  {
                    String inputLastname = txtLastname.getText().trim();
                    String inputFirstname = txtFirstname.getText().trim();

                    if (inputLastname.equals("") || (inputFirstname.equals(""))) {
                        JOptionPane.showMessageDialog(null,  " Not valid Input", "INSERT ERROR", JOptionPane.PLAIN_MESSAGE);
                    }

                    TeacherDTO teacherDTO = new TeacherDTO();
                    teacherDTO.setFirstname(inputFirstname);
                    teacherDTO.setLastname(inputLastname);

                    Teacher teacher = teacherService.insertTeacher(teacherDTO);
                    JOptionPane.showMessageDialog(null,"Teacher-" + teacher
                    + " was inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);


                } catch(TeacherDaoException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnInsert.setBounds(82, 209, 159, 44);
        contentPane.add(btnInsert);

        JButton btnClose = new JButton("CLOSE");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchForm().setEnabled(true);
                Main.getInsertForm().setVisible(false);

            }
        });

        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnClose.setBounds(283, 210, 140, 42);
        contentPane.add(btnClose);
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBounds(39, 17, 385, 113);
        contentPane.add(panel);

        separator = new JSeparator();
        separator.setBounds(46, 157, 380, 25);
        contentPane.add(separator);
    }

    private static class __Tmp {
        private static void __tmp() {
            javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
        }
    }
}
