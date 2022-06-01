package com.example.ngoprojectbackend.controller;

import com.example.ngoprojectbackend.Security.JwtUtils;
import com.example.ngoprojectbackend.model.ERole;
import com.example.ngoprojectbackend.model.Event;
import com.example.ngoprojectbackend.model.Role;
import com.example.ngoprojectbackend.model.User;
import com.example.ngoprojectbackend.repo.RoleRepo;
import com.example.ngoprojectbackend.repo.UserRepo;
import com.example.ngoprojectbackend.request.SignUpRequest;
import com.example.ngoprojectbackend.response.MessageResponse;
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
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/addUser")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepo.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepo.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        User user = new User(signUpRequest.getLastName(),
                signUpRequest.getFirstName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepo.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepo.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepo.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/getAllUsers")
    public List<User> EventList() {
        return userService.getUsers();
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> UserByIdList(@PathVariable int id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User userInfo) {
        User user = userService.getUserById(id);

        user.setEmail(userInfo.getEmail());
        user.setUsername(userInfo.getUsername());
        user.setFirstName(userInfo.getFirstName());
        user.setLastName(userInfo.getLastName());
        user.setRoles(userInfo.getRoles());
        user.setPassword(userInfo.getPassword());

        User updatedUser = userService.createUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/patchUser/{id}")
    public ResponseEntity<User> patchUser(@PathVariable int id, @RequestBody User userInfo) {
        User user = userService.getUserById(id);

        user.setFirstName(userInfo.getFirstName());
        user.setLastName(userInfo.getLastName());

        User patchUser = userService.patchUser(user);
        return ResponseEntity.ok(patchUser);
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEvent(@PathVariable int id) {
        User user = userService.getUserById(id);
        userService.deleteUser(user.getId());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
