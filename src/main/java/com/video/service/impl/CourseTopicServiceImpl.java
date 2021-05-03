package com.video.service.impl;

import com.github.pagehelper.PageInfo;
import com.video.dao.CourseTopicDao;
import com.video.domain.CourseTopic;
import com.video.service.CourseTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程主题业务逻辑类
 */
@Service
public class CourseTopicServiceImpl implements CourseTopicService {
    @Autowired
    CourseTopicDao courseTopicDao;


    /**
     * 获取对应type类型的课程
     * @param typeId
     * @return
     */
    @Override
    public PageInfo<CourseTopic> getCourseTopicList(int typeId) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("courseTypeId",typeId);
        params.put("flag",1);
        List<CourseTopic> topicList = courseTopicDao.findCourseTopicByCondition(params);
        PageInfo<CourseTopic> pageInfo = new PageInfo<>(topicList);
        return pageInfo;
    }


    /**
     * 获取所有类型的课程
     * @return
     */
    @Override
    public PageInfo<CourseTopic> getCourseTopicAll() {
        HashMap<String,Object> params = new HashMap<>();
        params.put("flag",1);
        List<CourseTopic> topicList = courseTopicDao.findCourseTopicByCondition(params);
        PageInfo<CourseTopic> pageInfo = new PageInfo<>(topicList);
        return pageInfo;
    }


    /**
     * 根据topicId获取CourseTopic
     * @param topicId
     * @return
     */
    @Override
    public CourseTopic getCourseTopic(int topicId) {
        List<Integer> ids = new ArrayList<>();
        ids.add(topicId);
        List<CourseTopic> topicList = courseTopicDao.findCourseTopicByIds(ids);
        if (topicList == null) return null;
        return topicList.get(0);
    }


    /**
     * 根据keyword搜索topic
     * @param keyword
     * @return
     */
    @Override
    public PageInfo<CourseTopic> searchTopic(String keyword) {
        Map<String,Object> map = new HashMap<>();
        map.put("topicName",keyword);
        map.put("flag",1);
        List<CourseTopic> topicList = courseTopicDao.findCourseTopicByCondition(map);
        PageInfo<CourseTopic> pageInfo = new PageInfo<>(topicList);
        return pageInfo;
    }

}
