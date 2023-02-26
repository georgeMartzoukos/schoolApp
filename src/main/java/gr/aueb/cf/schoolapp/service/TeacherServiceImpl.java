package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.DAO.teachersDAO.ITeacherDAO;
import gr.aueb.cf.schoolapp.DAO.exceptions.TeacherDaoException;
import gr.aueb.cf.schoolapp.DTO.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class TeacherServiceImpl implements ITeacherService{
    private final ITeacherDAO teacherDAO;

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher insertTeacher(TeacherDTO teacherToInsert) throws TeacherDaoException {
        if (teacherToInsert == null) return null;

        try{
            Teacher teacher = mapTeacher(teacherToInsert);
            return teacherDAO.insert(teacher);

        } catch (TeacherDaoException  e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Teacher mapTeacher(TeacherDTO dto) {
        return new Teacher(dto.getId(), dto.getFirstname(),dto.getLastname());
    }

    @Override
    public Teacher updateTeacher(TeacherDTO teacherToUpdate) throws TeacherDaoException, TeacherDaoException, TeacherNotFoundException {
        if (teacherToUpdate == null) return null;

        try{
            if (teacherDAO.getById(teacherToUpdate.getId()) == null) {
                throw new TeacherNotFoundException("Teacher with id " + teacherToUpdate.getId() + " not found");
            }
            Teacher teacher = mapTeacher(teacherToUpdate);
            return teacherDAO.update(teacher);

        } catch (TeacherDaoException | TeacherNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteTeacher(int id) throws TeacherDaoException, TeacherNotFoundException {

        try{
            if (teacherDAO.getById(id) == null) {
                throw new TeacherNotFoundException("Teacher with id " + id + " not found");
            }
            teacherDAO.delete(id);
        } catch (TeacherDaoException | TeacherNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Teacher> getTeacherByLastName(String lastname) throws TeacherDaoException  {
        List<Teacher> teachers = new ArrayList<>();
        if (lastname == null) return teachers;
        try {
            teachers = teacherDAO.getByLastname(lastname);
            //if (teachers.size() == 0) throw new TeacherNotFoundException("Not teacher found with lastname starting with " + lastname);
            return teachers;
         } catch (TeacherDaoException  e) {
           e.printStackTrace();
            throw e;
        }
    }


}
