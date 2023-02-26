package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.DAO.exceptions.TeacherDaoException;
import gr.aueb.cf.schoolapp.DTO.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import java.util.List;

public interface ITeacherService {
    Teacher insertTeacher(TeacherDTO teacherToInsert) throws TeacherDaoException ;
    Teacher updateTeacher(TeacherDTO teacherToUpdate)
            throws TeacherDaoException
            , TeacherNotFoundException;
    void deleteTeacher(int id) throws TeacherDaoException, TeacherNotFoundException;
    List<Teacher> getTeacherByLastName(String lastname) throws TeacherDaoException;



}
