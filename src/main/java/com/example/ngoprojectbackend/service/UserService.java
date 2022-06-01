package com.example.ngoprojectbackend.service;

import com.example.ngoprojectbackend.model.Event;
import com.example.ngoprojectbackend.model.User;
import com.example.ngoprojectbackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class UserService {
    @Autowired
    UserRepo userRepo;
    public List<User> getUsers() {
        return userRepo.findAll();
    }
    public User getUserById(int id) {
        return userRepo.findById(id).get();
    }
    public User createUser(User user) { userRepo.save(user);return user;}
    public User patchUser(User user) { userRepo.save(user);return user;}
    public void deleteUser(int id) { userRepo.deleteById(id);}


}
