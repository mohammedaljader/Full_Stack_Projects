package org.project.joboffers.DAL;

import org.project.joboffers.DALInterfaces.IUserDAL;
import org.project.joboffers.JPARepository.UserRepo;
import org.project.joboffers.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAL implements IUserDAL {
    private final UserRepo userRepo;

    @Autowired
    public UserDAL(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(User user) {
         userRepo.delete(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User getUserByUserId(String userId) {
        return userRepo.findByUserId(userId);
    }

    @Override
    public void editUser(User user) {
//        userRepo.updateUser(user.getUsername(),
//                user.getName(),
//                user.getPassword(),
//                user.getAddress());
        userRepo.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public void editPassword(String username, String password) {
        userRepo.updatePassword(username, password);
    }
}
