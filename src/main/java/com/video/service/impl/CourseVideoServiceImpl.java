package com.video.service.impl;

import com.video.dao.CourseVideoDao;
import com.video.domain.CourseVideo;
import com.video.service.CourseVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程视频业务逻辑类
 */
@Service
public class CourseVideoServiceImpl implements CourseVideoService {
    @Autowired
    CourseVideoDao courseVideoDao;


    /**
     * 根据courseTopic的id获取视频信息
     * @param courseTopicId
     * @return
     */
    @Override
    public List<CourseVideo> getCourseVideoById(Integer courseTopicId) {
        Map<String,Object> map = new HashMap<>();
        map.put("courseTopicId",courseTopicId);
        map.put("flag",1);
        return courseVideoDao.findCourseVideoByCondition(map);
    }
}
