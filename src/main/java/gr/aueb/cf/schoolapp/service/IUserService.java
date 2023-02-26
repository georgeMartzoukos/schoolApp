package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.DAO.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.DTO.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

import java.util.List;

public interface IUserService {
    User insertUser(UserDTO userDTO) throws UserDAOException;
    User updateUser(UserDTO userDTO) throws UserNotFoundException, UserDAOException;
    void deleteUser(int ID) throws UserDAOException, UserNotFoundException;
    List<User> getUserByUsername(String username) throws UserDAOException;
   // boolean userAuthentication(String username, String Password) throws UserDAOException, UserNotFoundException;
}
