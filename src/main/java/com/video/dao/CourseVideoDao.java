package com.video.dao;

import com.video.domain.CourseVideo;

import java.util.List;
import java.util.Map;

public interface CourseVideoDao {
    List<CourseVideo> findCourseVideoByCondition(Map<String,Object> map);

    int insertCourseVideo(CourseVideo courseVideo);

}
