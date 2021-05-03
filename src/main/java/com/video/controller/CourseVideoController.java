package com.video.controller;

import com.video.domain.CourseTopic;
import com.video.domain.CourseVideo;
import com.video.service.CourseTopicService;
import com.video.service.CourseVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

/**
 * 课程视频控制类
 */
@Controller
public class CourseVideoController {
    @Autowired
    CourseTopicService courseTopicService;
    @Autowired
    CourseVideoService courseVideoService;

    /**
     * 根据CourseTopic的id号获取视频信息 播放第一个视频
     * @param topicId
     * @param model
     * @return
     */
    @RequestMapping("/topicVideo/{topicId}")
    public String getTopicVideo(@PathVariable Integer topicId, Model model){
        CourseTopic courseTopic = courseTopicService.getCourseTopic(topicId);
        List<CourseVideo> courseVideoList = courseVideoService.getCourseVideoById(topicId);
        CourseVideo courseVideo = courseVideoList.get(0);
        model.addAttribute("courseTopic",courseTopic);
        model.addAttribute("courseVideoList",courseVideoList);
        model.addAttribute("courseVideo",courseVideo);
        return "video";
    }


    /**
     * 在专题里根据视频id搜索
     * @param topicId
     * @param videoId
     * @param model
     * @return
     */
    @RequestMapping("/topicVideo/{topicId}/{videoId}")
    public String getTopicVideo(@PathVariable Integer topicId,@PathVariable Integer videoId ,Model model){
        CourseTopic courseTopic = courseTopicService.getCourseTopic(topicId);
        List<CourseVideo> courseVideoList = courseVideoService.getCourseVideoById(topicId);
        CourseVideo courseVideo = null;
        for (CourseVideo video: courseVideoList){
            if (video.getId() == videoId){
                courseVideo = video;
            }
        }
        model.addAttribute("courseTopic",courseTopic);
        model.addAttribute("courseVideoList",courseVideoList);
        model.addAttribute("courseVideo",courseVideo);
        return "video";
    }


}
