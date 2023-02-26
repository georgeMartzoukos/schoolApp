package gr.aueb.cf.schoolapp.DAO.teachersDAO;

import gr.aueb.cf.schoolapp.DAO.exceptions.TeacherDaoException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO{
    @Override
    public Teacher insert(Teacher teacher) throws TeacherDaoException {
        String sql = "INSERT INTO TEACHERS (FIRSTNAME, LASTNAME) VALUES (?, ?)";

        try(Connection conn = DBUtil.getConnection();
        PreparedStatement p = conn.prepareStatement(sql)) {
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            if (firstname.equals("") || lastname.equals("") ){
                return null;
            }

            p.setString(1, firstname);
            p.setString(2, lastname);
            p.executeUpdate();
            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDaoException("sql error in teacher " + teacher + "insertion");
        }
    }



    @Override
    public Teacher update(Teacher teacher) throws TeacherDaoException {
          String sql = "UPDATE TEACHERS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ? ";

        try(Connection conn = DBUtil.getConnection();
            PreparedStatement p = conn.prepareStatement(sql)) {

            int id = teacher.getId();
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();



            if (firstname.equals("") || lastname.equals("") ){
                return null;
            }

            p.setString(1, firstname);
            p.setString(2, lastname);
            p.setInt(3,id);
            p.executeUpdate();
            return teacher;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDaoException("sql error in teacher " + teacher.getLastname());
        }
    }

    @Override
    public void delete(int id) throws TeacherDaoException {
        String sql = "DELETE FROM TEACHERS WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setInt(1, id);
            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDaoException("sql error in teacher " + id + "deleted");
        }
    }
    @Override
    public List<Teacher> getByLastname(String lastname) throws TeacherDaoException {
        String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WHERE LASTNAME LIKE ?";
        ResultSet rs;
        List<Teacher> teachers = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, lastname + '%');
            rs = p.executeQuery();

            while (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME")
                );
                teachers.add(teacher);
            }

            return teachers;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDaoException("sql error in teacher with lastname = " + lastname);
        }
    }

    @Override
    public Teacher getById(int id) throws TeacherDaoException {
        Teacher teacher = null;
        ResultSet rs;
        String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WHERE ID = ?";


        try(Connection conn = DBUtil.getConnection();
            PreparedStatement p = conn.prepareStatement(sql)) {
            p.setInt(1, id);
            rs = p.executeQuery();

            while (rs.next()) {
                 teacher = new Teacher(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME")
                );

            }
                return teacher;
        }catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDaoException("sql error in teacher with id = " + id);
        }
    }
}
