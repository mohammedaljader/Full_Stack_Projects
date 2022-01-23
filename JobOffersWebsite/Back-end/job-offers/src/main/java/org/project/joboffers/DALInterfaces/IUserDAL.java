package org.project.joboffers.DALInterfaces;

import org.project.joboffers.Model.User;

import java.util.List;

public interface IUserDAL {
    List<User> getAllUsers();
    User addUser(User user);
    void deleteUser(User user);
    User getUserByUsername(String username);
    User getUserByUserId(String userId);
    void editUser(User user);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    void editPassword(String username, String password);
}
