package gr.aueb.cf.schoolapp.DAO.exceptions;

import gr.aueb.cf.schoolapp.model.Teacher;

public class TeacherDaoException extends Exception{
    private final static long serialVersionUID = 1L;

    public TeacherDaoException(String s) {
        super(s);
    }
}
