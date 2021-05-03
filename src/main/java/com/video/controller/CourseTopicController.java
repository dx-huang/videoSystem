package com.video.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.domain.CourseTopic;
import com.video.domain.CourseType;
import com.video.service.CourseTopicService;
import com.video.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

/**
 * 课程专题控制类
 */
@Controller
public class CourseTopicController {
    @Autowired
    CourseTopicService courseTopicService;
    @Autowired
    CourseTypeService courseTypeService;

    /**
     * 根据类型来获取课程
     * @param typeId
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping("/course/{typeId}")
    public String getTopicListByTypeId(@PathVariable Integer typeId, Model model,Integer pageNum){
        List<CourseType> courseTypeList = courseTypeService.getAllCourseType();
        if (pageNum == null || pageNum <= 0){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,16);
        PageInfo<CourseTopic> topicList = null;
        if (typeId == 0){
            topicList = courseTopicService.getCourseTopicAll();
        }else {
            topicList = courseTopicService.getCourseTopicList(typeId);
        }
        model.addAttribute("courseTypeList",courseTypeList);
        model.addAttribute("topicList",topicList);
        model.addAttribute("clickType",typeId);
        model.addAttribute("clickNavType",1);
        return "course";
    }

    /**
     * 模糊搜索专题
     * @param keyword
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping("/search")
    public String searchTopic(String keyword,Model model,Integer pageNum){
        if (pageNum == null || pageNum <= 0){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,16);
        PageInfo<CourseTopic> topicList = courseTopicService.searchTopic(keyword);
        model.addAttribute("topicList",topicList);
        model.addAttribute("keyword",keyword);
        return "search";
    }





}
