package com.video.service.impl;

import com.video.dao.CourseTypeDao;
import com.video.domain.CourseType;
import com.video.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程类型业务逻辑类
 */
@Service
public class CourseTypeServiceImpl implements CourseTypeService {
    @Autowired
    CourseTypeDao courseTypeDao;

    /**
     * 查找所有的课程类别
     * @return
     */
    @Override
    public List<CourseType> getAllCourseType() {
        return courseTypeDao.findCourseTypeAll();
    }
}
