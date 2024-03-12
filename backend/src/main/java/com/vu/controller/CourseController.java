package com.vu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vu.domain.Course;
import com.vu.model.R;
import com.vu.service.CourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/courses")
@Api(tags = "Course Controller")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/page")
    public R<Page<Course>> findAllCoursesByPage(
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<Course> page = new Page<>(pageNum, pageSize);
        Page<Course> pageData = courseService.findAllCoursesByPage(page);
        return R.ok(pageData);
    }
}