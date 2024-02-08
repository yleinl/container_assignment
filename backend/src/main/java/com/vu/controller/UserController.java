package com.vu.controller;

import com.vu.domain.User;
import com.vu.model.R;
import com.vu.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "User Controller")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R<User> login(@RequestParam String vunetId, @RequestParam String password) {
        User user = userService.login(vunetId, password);
        if (user != null) {
            return R.ok(user);
        } else {
            return R.fail("Login failed");
        }
    }

    @PostMapping("/register")
    public R<String> register(@RequestBody User user) {
        boolean success = userService.register(user);
        if (success) {
            return R.ok("User registered successfully");
        } else {
            return R.fail("Failed to register user");
        }
    }

    @PostMapping("/registerCourse")
    public R<String> registerCourse(@RequestParam String vunetId, @RequestParam int courseId) {
        boolean success = userService.registerCourse(vunetId, courseId);
        if (success) {
            return R.ok("Course registered successfully");
        } else {
            return R.fail("Failed to register course");
        }
    }

    @GetMapping("/getCourseName")
    public R<List<String>> getCourseName(@RequestParam String vunetId) {
        List<String> courseNames = userService.getCourseName(vunetId);
        return R.ok(courseNames);
    }
}
