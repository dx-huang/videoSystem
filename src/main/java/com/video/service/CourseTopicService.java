package com.video.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.video.domain.CourseTopic;

import java.util.List;

public interface CourseTopicService {
    //分页
    PageInfo<CourseTopic> getCourseTopicList(int typeId);

    //获取所有的课程主题
    PageInfo<CourseTopic> getCourseTopicAll();

    //根据topicId获取CourseTopic
    CourseTopic getCourseTopic(int topicId);

    //根据keyword搜索topic
    PageInfo<CourseTopic> searchTopic(String keyword);

}