package lk.ijse.userservice.controller;

import lk.ijse.userservice.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Dasun Wijayathilaka
 * @created 6/20/2025
 * @github https://github.com/dasunwijayathilaka
 * @project Smart-Parking-Management-System-Microservice-Based-Application
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

    private  List<User> users = new ArrayList<>();

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        user.setUserId(UUID.randomUUID().toString());
        users.add(user);
        return user;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable String userId, @RequestBody User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId)) {
                updatedUser.setUserId(userId);
                users.set(i, updatedUser);
                return updatedUser;
            }
        }
        return null;
    }
}