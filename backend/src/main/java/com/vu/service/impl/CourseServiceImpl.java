package com.vu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vu.Mapper.CourseMapper;
import com.vu.Mapper.UserMapper;
import com.vu.domain.Course;
import com.vu.domain.User;
import com.vu.service.CourseService;
import com.vu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseMapper courseMapper;
    @Override
    public Page<Course> findAllCoursesByPage(Page<Course> page) {
        Page<Course> pageData = courseMapper.selectPage(page, new QueryWrapper<Course>());
        return pageData;
    }

    @Override
    public String getCourseNameById(int id) {
        LambdaQueryWrapper<Course> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Course::getCourseId, id);
        lqw.last(" limit 1");
        return courseMapper.selectOne(lqw).getCourseName();
    }
}
