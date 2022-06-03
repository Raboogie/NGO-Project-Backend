package com.example.ngoprojectbackend.service;

import com.example.ngoprojectbackend.model.Role;
import com.example.ngoprojectbackend.model.User;
import com.example.ngoprojectbackend.repo.RoleRepository;
import com.example.ngoprojectbackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepo;
    RoleRepository roleRepo;
    public List<User> getUsers() {
        return userRepo.findAll();
    }
    public User getUserById(Long id) {
       return userRepo.findById(id).get();
    }


    public User createUser(User user) { userRepo.save(user);return user;}
    public User patchUser(User user) { userRepo.save(user);return user;}
    public void deleteUser(Long id) { userRepo.deleteById(id);}

  public String getRoleById(Long id){return userRepo.findById(id).get().getRoles().toString();}


}
