package gr.aueb.cf.schoolapp.DAO.teachersDAO;

import gr.aueb.cf.schoolapp.DAO.exceptions.TeacherDaoException;
import gr.aueb.cf.schoolapp.model.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface ITeacherDAO {
    Teacher insert(Teacher teacher) throws TeacherDaoException;
    Teacher update(Teacher teacher) throws TeacherDaoException;
    void delete(int id) throws  TeacherDaoException;
    List<Teacher> getByLastname(String lastname) throws TeacherDaoException;

    Teacher getById(int id) throws TeacherDaoException;
}
