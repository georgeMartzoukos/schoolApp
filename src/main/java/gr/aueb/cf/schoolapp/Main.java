package gr.aueb.cf.schoolapp;

import gr.aueb.cf.schoolapp.DAO.usersDAO.IUserDAO;
import gr.aueb.cf.schoolapp.DAO.usersDAO.UserDAOImpl;
import gr.aueb.cf.schoolapp.controller.*;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Menu menu;
    private static SearchForm searchForm;
    private static InsertForm insertForm;
    private static UpdateDeleteForm updateDeleteForm;

    private static LoginPage loginPage;
    private static InsertUser insertUser;
    private static SearchUser searchUser;
    private static UpdateDeleteFormUsername updateDeleteFormUsername;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    menu = new Menu();
                    menu.setLocationRelativeTo(null);
                    menu.setVisible(false);

                    searchForm = new SearchForm();
                    searchForm.setLocationRelativeTo(null);
                    searchForm.setVisible(false);

                    insertForm = new InsertForm();
                    insertForm.setLocationRelativeTo(null);
                    insertForm.setVisible(false);

                    updateDeleteForm = new UpdateDeleteForm();
                    updateDeleteForm.setLocationRelativeTo(null);
                    updateDeleteForm.setVisible(false);

                    loginPage = new LoginPage();
                    loginPage.setLocationRelativeTo(null);
                    loginPage.setVisible(true);

                    insertUser = new InsertUser();
                    insertUser.setLocationRelativeTo(null);
                    insertUser.setVisible(false);

                    searchUser = new SearchUser();
                    searchUser.setLocationRelativeTo(null);
                    searchUser.setVisible(false);

                    updateDeleteFormUsername = new UpdateDeleteFormUsername();
                    updateDeleteFormUsername.setLocationRelativeTo(null);
                    updateDeleteFormUsername.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static Menu getMenu() {
        return menu;
    }

    public static SearchForm getSearchForm() {
        return searchForm;
    }

    public static InsertForm getInsertForm() {
        return insertForm;
    }

    public static UpdateDeleteForm getUpdateDeleteForm() {
        return updateDeleteForm;
    }

    public static LoginPage getLoginPage() {
        return loginPage;
    }

    public static InsertUser getInsertUser() {
        return insertUser;
    }

    public static SearchUser getSearchUser() {
        return searchUser;
    }

    public static UpdateDeleteFormUsername getUpdateDeleteFormUsername() {
        return updateDeleteFormUsername;
    }

}
