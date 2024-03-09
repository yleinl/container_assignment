package com.vu.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vu.Mapper.CourseMapper;
import com.vu.Mapper.UserMapper;
import com.vu.domain.Course;
import com.vu.domain.User;
import com.vu.service.CourseService;
import com.vu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseService courseService;

    private final ObjectMapper objectMapper = new ObjectMapper(); // JSON处理

    @Override
    public User login(String vunetId, String password) {
        if (StringUtils.isEmpty(password)) {
            throw new RuntimeException("password can not be null.");
        }

        User user = userService.getByVunetId(vunetId);
        if (!password.equals(user.getPassword()) || !vunetId.equals(user.getVunetId()) ){
            throw new RuntimeException("login information is incorrect.");
        }

        return user;
    }

    @Override
    public User getByVunetId(String vunetId) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getVunetId, vunetId);
        lqw.last(" limit 1");
        return userMapper.selectOne(lqw);
    }

    @Override
    public boolean register(User user) {
        String studentName = user.getStudentName();
        String password = user.getPassword();
        String email = user.getEmail();
        if (StringUtils.isEmpty(studentName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email)){
            throw new IllegalArgumentException("studentName, password and email can not be null.");
        }
        int count = count(new LambdaQueryWrapper<User>()
                .eq(!StringUtils.isEmpty(email), User::getEmail, email));

        if (count > 0) {
            throw new IllegalArgumentException("email has been registered.");
        }
        return save(user);
    }

    @Override
    public boolean registerCourse(String vunetId, String courseId) {
        User user = getByVunetId(vunetId);
        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }

        String currentCourses = user.getCourses();
        String updatedCourses;

        if (currentCourses == null || currentCourses.isEmpty()) {
            updatedCourses = String.valueOf(courseId);
        } else {
            updatedCourses = currentCourses + "," + courseId;
        }

        user.setCourses(updatedCourses);

        return userMapper.updateById(user) > 0;
    }


    @Override
    public List<Course> getSelectedCourse(String vunetId) {
        User user = getByVunetId(vunetId);

        if (user == null) {
            return null;
        }
        
        String courses = user.getCourses();
        String[] ids = courses.split(",");

        List<Course> selectedCourses = new ArrayList<>();

        for (String id : ids) {
            Course course = courseService.getById(id);
            if (course != null) {
                selectedCourses.add(course);
            }
        }

        return selectedCourses;
    }

//    @Override
//    public User getByVunetId(String vunetId) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("vunetid", vunetId);
//        return userMapper.selectOne(queryWrapper);
//    }

    @Override
    public boolean changePassword(String vunetId, String oldPassword, String newPassword) {
        User user = userMapper.selectById(vunetId);
        if (user != null && user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            userMapper.updateById(user);
            return true;
        }
        return false;
    }

}
