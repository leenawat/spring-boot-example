package com.punyadev.springcruduserrole.user.controller;

import com.punyadev.springcruduserrole.user.model.User;
import com.punyadev.springcruduserrole.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create
    @PostMapping()
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    // Read all
    @GetMapping()
    public List<User> readAll() {
        return userService.readAll();
    }

    // Read By id
    @GetMapping("/{id}")
    public Optional<User> readById(@PathVariable Long id) {
        return userService.read(id);
    }

    // Update
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) throws Exception {
        return userService.update(id, user);
    }

    // Update partial
    @PatchMapping("/{id}")
    public User updatePatial(@PathVariable Long id, @RequestBody User user) throws Exception {
        return userService.updatePatial(id, user);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
