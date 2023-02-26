package gr.aueb.cf.schoolapp.DAO.usersDAO;

import gr.aueb.cf.schoolapp.DAO.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.User;

import java.util.List;

public interface IUserDAO {
    User insert(User user) throws UserDAOException;
    User update(User user) throws UserDAOException;
    void delete(int ID) throws UserDAOException;
    List<User> getByUsername(String username) throws UserDAOException;
    User getByID(int ID) throws UserDAOException;
}
