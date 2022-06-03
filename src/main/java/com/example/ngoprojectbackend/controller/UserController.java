package com.example.ngoprojectbackend.controller;


import com.example.ngoprojectbackend.Security.JwtUtils;
import com.example.ngoprojectbackend.model.ERole;
import com.example.ngoprojectbackend.model.Role;
import com.example.ngoprojectbackend.model.User;
import com.example.ngoprojectbackend.repo.RoleRepository;
import com.example.ngoprojectbackend.repo.UserRepository;
import com.example.ngoprojectbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RequestMapping("/api/users")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserService userService;

  @Autowired
  RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;



    @GetMapping("/getAllUsers")
    public List<User> EventList() {

        System.out.println("Getting...");
        return userService.getUsers();
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEvent(@PathVariable Long id) {
        User user = userService.getUserById(id);
        userService.deleteUser(user.getId());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> UserByIdList(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userInfo) {
        User user = userService.getUserById(id);

        user.setEmail(userInfo.getEmail());
        user.setUsername(userInfo.getUsername());
        user.setFirstname(userInfo.getFirstname());
        user.setLastname(userInfo.getLastname());
        user.setRolename(userInfo.getRolename());
        user.setPassword(userInfo.getPassword());
        Set<Role> roles = new HashSet<>();
      if (user.getRolename() == null) {
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
      } else {
        System.out.println(user.getRolename());
        switch(user.getRolename()){

          case"admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }

      };

      user.setRoles(roles);

        User updatedUser = userService.createUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/patchUser/{id}")
    public ResponseEntity<User> patchUser(@PathVariable Long id, @RequestBody User userInfo) {
        User user = userService.getUserById(id);

        user.setFirstname(userInfo.getFirstname());
        user.setLastname(userInfo.getLastname());

        User patchUser = userService.patchUser(user);
        return ResponseEntity.ok(patchUser);
    }

@GetMapping("/getRoles/{id}")
  public String listRoles(Long id){
      return userService.getRoleById(id);

    }






}
