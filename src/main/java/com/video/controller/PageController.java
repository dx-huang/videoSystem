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
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

/**
 * 页面跳转控制类
 */
@Controller
public class PageController {
    @Autowired
    CourseTopicService courseTopicService;
    @Autowired
    CourseTypeService courseTypeService;


    /**
     * 请求“/“时，跳转到首页
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String indexPage(Model model){
        //数据库
        PageHelper.startPage(1,4);
        PageInfo<CourseTopic> courseType1 = courseTopicService.getCourseTopicList(1);
        //框架
        PageHelper.startPage(1,4);
        PageInfo<CourseTopic> courseType3 = courseTopicService.getCourseTopicList(3);
        model.addAttribute("courseType1",courseType1);
        model.addAttribute("courseType3",courseType3);
        model.addAttribute("clickNavType",0);
        return "index";
    }


    /**
     * 请求“/forgetPsd"时，跳转到找回密码的页面
     * @return
     */
    @RequestMapping("/forgetPsd")
    public String forgetPsd(){
        return "forgetPsd";
    }


    /**
     * 请求“/course”时，跳转到课程的页面
     * @param model
     * @return
     */
    @RequestMapping("/course")
    public String course(Model model){
        //获取所有的类型
        List<CourseType> courseTypeList = courseTypeService.getAllCourseType();
        //获取所有类型的课程
        PageHelper.startPage(1,16);
        PageInfo<CourseTopic> topicList = courseTopicService.getCourseTopicAll();
        model.addAttribute("courseTypeList",courseTypeList);
        model.addAttribute("topicList",topicList);
        model.addAttribute("clickType",0);
        model.addAttribute("clickNavType",1);
        return "course";
    }


    /**
     * 跳转到vip页面
     * @param model
     * @return
     */
    @RequestMapping("/vip")
    public String vip(Model model){
        model.addAttribute("clickNavType",2);
        return "vip";
    }

}
