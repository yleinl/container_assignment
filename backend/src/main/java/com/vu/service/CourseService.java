package com.vu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vu.domain.Course;

public interface CourseService extends IService<Course> {
    /**
     * All course information
     * @param page
     * @return
     */
    Page<Course> findAllCoursesByPage(Page<Course> page);

    String getCourseNameById(int id);
}
