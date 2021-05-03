package com.video.dao;

import com.github.pagehelper.PageInfo;
import com.video.domain.CourseTopic;
import com.video.domain.CourseType;

import java.util.List;
import java.util.Map;

public interface CourseTopicDao {

    //插入数据
    int insertCourseTopic(CourseTopic courseTopic);

    //分页
    List<CourseTopic> findCourseTopicByCondition(Map<String,Object> params);

    //根据topicId获取CourseTopic
    List<CourseTopic> findCourseTopicByIds(List ids);


}
