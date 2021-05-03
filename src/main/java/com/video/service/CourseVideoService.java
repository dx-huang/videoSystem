package com.video.service;

import com.video.domain.CourseVideo;

import java.util.List;

public interface CourseVideoService {

    List<CourseVideo> getCourseVideoById(Integer courseTopicId);
}
