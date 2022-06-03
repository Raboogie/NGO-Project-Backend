package com.example.ngoprojectbackend;

import com.example.ngoprojectbackend.model.User;
import com.example.ngoprojectbackend.repo.UserRepository;
import com.example.ngoprojectbackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testusers {
  @Autowired
  UserRepository userRepo;

  @Autowired
  @InjectMocks
  UserService userService;

  @Test
  void contextLoads() {
  }

  @Test
  public void getEvent() {
    Long id = Long.valueOf(1);
    User user = userRepo.getOne(id);
    System.out.println("Event eventId is: " + user.getId());
  }

  @Test
  public void getAllEvents() {
    System.out.println(userService.getUsers().toString());
  }

  @Test
  public void createEvent() {
    User user = new User("uname", "epass", "pass", "fname","lname","rolename");

    user.setUsername("uname");
    user.setEmail("epass");
    user.setPassword("pass");
    user.setFirstname("fname");
    user.setLastname("lname");
    user.setRolename("rolename");

    //eventRepo.save(event);
    System.out.println("saved one record in db table...");
    System.out.println(user);

  }


}
