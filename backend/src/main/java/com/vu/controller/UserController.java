package com.vu.controller;

import com.vu.domain.Course;
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
    public R<String> register(@RequestParam String vunetId, @RequestParam String studentName, @RequestParam String password, @RequestParam String email) {
        User user = new User(vunetId, studentName, password, email);
        boolean success = userService.register(user);
        if (success) {
            return R.ok("User registered successfully");
        } else {
            return R.fail("Failed to register user");
        }
    }

    @PostMapping("/changePassword")
    public R<String> changePassword(@RequestParam String vunetId, @RequestParam String oldPassword, @RequestParam String newPassword) {
        boolean success = userService.changePassword(vunetId, oldPassword, newPassword);
        if (success) {
            return R.ok("Password changed successfully");
        } else {
            return R.fail("Failed to change password");
        }
    }

    @PostMapping("/registerCourse")
    public R<String> registerCourse(@RequestParam String vunetId, @RequestParam String courseId) {
        boolean success = userService.registerCourse(vunetId, courseId);
        if (success) {
            return R.ok("Course registered successfully");
        } else {
            return R.fail("Failed to register course");
        }
    }

    @GetMapping("/getCourseName")
    public R<List<Course>> getCourseName(@RequestParam String vunetId) {
        List<Course> courses = userService.getSelectedCourse(vunetId);
        return R.ok(courses);
    }
}
