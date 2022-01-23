package org.project.joboffers.JPARepository;

import org.project.joboffers.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUserId(String userId);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    @Modifying
    @Transactional
    @Query("Update User u SET u.name=:name, u.password=:password, u.address=:address WHERE u.username =:username ")
    void updateUser(@Param("username") String username,
                    @Param("name") String name,
                    @Param("password") String password,
                    @Param("address") String address);

    @Modifying
    @Transactional
    @Query("Update User u SET u.password=:password WHERE u.username =:username ")
    void updatePassword(@Param("username") String username,
                    @Param("password") String password);

}
