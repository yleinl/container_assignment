package com.vu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vu.domain.User;

import java.util.List;

public interface UserService extends IService<User> {

    /**
     * Login
     * @param vunetId
     * @param password
     * return
     */
    User login(String vunetId, String password);

    /**
     * @param vunetId
     * @return
     */
    User getByVunetId(String vunetId);

    /**
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * @param vunetId
     * @param courseId
     * @return
     */
    boolean registerCourse(String vunetId, int courseId);

    /**
     * @param vunetId
     * @return
     */
    List<String> getCourseName(String vunetId);
}
