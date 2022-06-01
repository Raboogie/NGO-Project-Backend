package com.example.ngoprojectbackend.controller;

import com.example.ngoprojectbackend.Security.JwtUtils;
import com.example.ngoprojectbackend.Security.UserDetailsImpl;
import com.example.ngoprojectbackend.model.ERole;
import com.example.ngoprojectbackend.model.Event;
import com.example.ngoprojectbackend.model.Role;
import com.example.ngoprojectbackend.model.User;
import com.example.ngoprojectbackend.repo.RoleRepo;
import com.example.ngoprojectbackend.repo.UserRepo;
import com.example.ngoprojectbackend.request.LoginRequest;
import com.example.ngoprojectbackend.request.SignUpRequest;
import com.example.ngoprojectbackend.response.MessageResponse;
import com.example.ngoprojectbackend.response.UserInfoResponse;
import com.example.ngoprojectbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/test")
public class TestAuthController {

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

    @Autowired
    UserService userService;


    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }



    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
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
