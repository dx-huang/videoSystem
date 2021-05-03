package com.video.service;

import com.video.domain.CourseType;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseTypeService {
   //获取所有的课程类型
   List<CourseType> getAllCourseType();
}
