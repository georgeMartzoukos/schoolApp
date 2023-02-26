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
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UpdateDeleteForm extends JFrame {

   // private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtLastname;
    private JTextField txtFirstname;
    private static final long serialVersionUID = 1L;


    private ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);
    private int listPosition;
    private int listSize;
    List<Teacher> teachers = new ArrayList<>();



    public UpdateDeleteForm() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                try {
                    teachers = teacherService.getTeacherByLastName(Main.getSearchForm().getInputLastname());

                    listSize = teachers.size();
                    listPosition = 0;

                    if (listSize == 0) {
                        txtId.setText("");
                        txtFirstname.setText("");
                        txtLastname.setText("");
                    }
                    txtId.setText(Integer.toString(teachers.get(listPosition).getId()));
                    txtFirstname.setText(teachers.get(listPosition).getFirstname());
                    txtLastname.setText(teachers.get(listPosition).getLastname());

                } catch ( TeacherDaoException ex) {
                    String message = ex.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error in getting teachers", JOptionPane.ERROR_MESSAGE);
                }

            }
            @Override
            public void windowClosed(WindowEvent e) {

            }
        });
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setTitle("Ενημέρωση / Διαγραφή Εκπαιδευτή");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 423, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 255, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLastname = new JLabel("Επώνυμο");
        lblLastname.setHorizontalAlignment(SwingConstants.TRAILING);
        lblLastname.setForeground(new Color(165, 42, 42));
        lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblLastname.setBounds(22, 55, 68, 20);
        contentPane.add(lblLastname);

        txtId = new JTextField();
        txtId.setBackground(new Color(245, 245, 220));
        txtId.setEditable(false);
        txtId.setBounds(100, 21, 38, 20);
        contentPane.add(txtId);
        txtId.setColumns(10);

        txtLastname = new JTextField();
        txtLastname.setBounds(100, 55, 170, 20);
        contentPane.add(txtLastname);
        txtLastname.setColumns(50);

        txtFirstname = new JTextField();
        txtFirstname.setBounds(100, 91, 170, 20);
        contentPane.add(txtFirstname);
        txtFirstname.setColumns(50);

        JSeparator separator = new JSeparator();
        separator.setBounds(22, 146, 370, 1);
        contentPane.add(separator);

        JButton btnFirst = new JButton("");
        btnFirst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listSize > 0) {
                    listPosition = 0;
                    //txtId.setText(String.format("%s", teachers.get(listPosition).getId()));
                    txtId.setText(Integer.toString(teachers.get(listPosition).getId()));
                    txtLastname.setText(teachers.get(listPosition).getLastname());
                    txtFirstname.setText(teachers.get(listPosition).getFirstname());
                }
            }
        });
        btnFirst.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("First record.png"))));
        btnFirst.setBounds(22, 158, 46, 23);
        contentPane.add(btnFirst);

        JButton btnPrevious = new JButton("");
        btnPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (listPosition > 0) {
                    listPosition--;
                    txtId.setText(Integer.toString(teachers.get(listPosition).getId()));
                    txtLastname.setText(teachers.get(listPosition).getLastname());
                    txtFirstname.setText(teachers.get(listPosition).getFirstname());
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
                    txtId.setText(Integer.toString(teachers.get(listPosition).getId()));
                    txtLastname.setText(teachers.get(listPosition).getLastname());
                    txtFirstname.setText(teachers.get(listPosition).getFirstname());
                }
            }
        });
        btnNext.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Next_track.png"))));
        btnNext.setBounds(116, 158, 46, 23);
        contentPane.add(btnNext);

        JButton btnLast = new JButton("");
        btnLast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                    if (listPosition < listSize - 1) {
                        listPosition = listSize - 1;

                        txtId.setText(Integer.toString(teachers.get(listPosition).getId()));
                        txtLastname.setText(teachers.get(listPosition).getLastname());
                        txtFirstname.setText(teachers.get(listPosition).getFirstname());
                    }
            }
        });
        btnLast.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Last_Record.png"))));
        btnLast.setBounds(163, 158, 46, 23);
        contentPane.add(btnLast);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    int response;
                    String idStr = txtId.getText();
                    int id;

                    if (idStr.equals("")) return;
                    id = Integer.parseInt(txtId.getText());
                    response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος", "Delete",
                            JOptionPane.YES_NO_OPTION );

                    if (response == JOptionPane.YES_OPTION) {
                        teacherService.deleteTeacher(id);

                        JOptionPane.showMessageDialog(null,   " Teacher was deleted successfully",
                                "Delete", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (TeacherDaoException | TeacherNotFoundException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "DELETE", JOptionPane.ERROR_MESSAGE);
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

                try  {
                    String inputLastname = txtLastname.getText().trim();
                    String inputFirstname = txtFirstname.getText().trim();
                    String id = txtId.getText().trim();

                    if (inputLastname.equals("") || (inputFirstname.equals(""))) {
                        JOptionPane.showMessageDialog(null,  " Not valid Input", "INSERT ERROR", JOptionPane.PLAIN_MESSAGE);
                    }

                    TeacherDTO teacherDTO = new TeacherDTO();
                    teacherDTO.setId(Integer.parseInt(id));
                    teacherDTO.setFirstname(inputFirstname);
                    teacherDTO.setLastname(inputLastname);

                    Teacher teacher = teacherService.updateTeacher(teacherDTO);
                    JOptionPane.showMessageDialog(null,"Teacher: " + teacher.getLastname()
                            + " was updated", "update", JOptionPane.PLAIN_MESSAGE);


                } catch(TeacherDaoException | TeacherNotFoundException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
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
                Main.getSearchForm().setEnabled(true);
                Main.getUpdateDeleteForm().setVisible(false);
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

        JLabel lblFirstname = new JLabel("Όνομα");
        lblFirstname.setHorizontalAlignment(SwingConstants.TRAILING);
        lblFirstname.setForeground(new Color(165, 42, 42));
        lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFirstname.setBounds(22, 91, 68, 20);
        contentPane.add(lblFirstname);
    }

}
