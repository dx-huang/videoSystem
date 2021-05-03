
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.controller.PageController;
import com.video.dao.CourseTopicDao;
import com.video.dao.CourseTypeDao;
import com.video.dao.CourseVideoDao;
import com.video.dao.UserDao;
import com.video.domain.CourseTopic;
import com.video.domain.CourseType;
import com.video.domain.CourseVideo;
import com.video.domain.User;
import com.video.exception.UserException;
import com.video.service.CourseTopicService;
import com.video.service.impl.CourseTopicServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static cn.hutool.core.date.DateUtil.now;
import static cn.hutool.core.date.DateUtil.rangeToList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class DaoTest {


    @Autowired
    UserDao userDao;

    @Autowired
    CourseTopicDao courseTopicDao;

    @Autowired
    CourseTypeDao courseTypeDao;

    @Autowired
    CourseVideoDao courseVideoDao;


//    @Test
//    public void test1(){
//        User user = new User();
//        user.setId(4);
//        user.setEmail("12134");
//        user.setPassword("666");
//        int result = userDao.insertUser(user);
//        System.out.println(result);
//
//    }
//
//    @Test
//    public void test2(){
//        int result = userDao.deleteUserById(3);
//        System.out.println(result);
//    }
//
//    @Test
//    public void test3(){
//        Map<String,Object> map = new HashMap<>();
//        map.put("id",1);
//        map.put("email","1633758068@qq.com");
//        map.put("vipFlag",1);
//        map.put("createTime",now());
//
//        int result = userDao.updateUser(map);
//        System.out.println(result);
//    }
//
//    @Test
//    public void test4(){
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        List<User> users = userDao.findUserByIds(list);
//        System.out.println("================================");
//        for (User user:users) {
//            System.out.println(user);
//        }
//        System.out.println("=================================");
//    }
//
//    @Test
//    public void test5(){
//        List<User> users = userDao.findUserAll();
//        System.out.println("==========================");
//        for(User user:users){
//            System.out.println(user);
//        };
//        System.out.println("===========================");
//    }
//
//    @Test
//    public void test6(){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("vipFlag",0);
//        System.out.println("======================");
//        List<User> users = userDao.findUserByCondition(map);
//        for(User user:users){
//            System.out.println(user);
//        }
//        System.out.println("=======================");
//    }
//
    @Test
    public void test7(){
        // id, topic_name, views, vip_flag, course_type_id, topic_intro, flag, ppt_url,
        //            create_time
        for (int i = 1; i <= 20; i++){
            CourseTopic courseTopic = new CourseTopic();
            courseTopic.setTopicName("Spring"+i);
            courseTopic.setViews(RandomUtil.randomInt(2000,8000));
            courseTopic.setVipFlag(new Random().nextInt(2));
            courseTopic.setCourseTypeId(1);
            courseTopic.setTopicIntro("about Spring");
            courseTopic.setFlag(1);
            courseTopic.setPptUrl("www.baidu.com");
            courseTopic.setCreateTime(new Date());
            courseTopic.setImgUrl("http://localhost:8080/static/imgs/456.jpg");
            int result = courseTopicDao.insertCourseTopic(courseTopic);
            System.out.print(result);
        }
        for (int i = 1; i <= 20; i++){
            CourseTopic courseTopic = new CourseTopic();
            courseTopic.setTopicName("springCloud"+i);
            courseTopic.setViews(RandomUtil.randomInt(2000,8000));
            courseTopic.setVipFlag(new Random().nextInt(2));
            courseTopic.setCourseTypeId(2);
            courseTopic.setTopicIntro("about springCloud");
            courseTopic.setFlag(1);
            courseTopic.setPptUrl("www.baidu.com");
            courseTopic.setCreateTime(new Date());
            courseTopic.setImgUrl("http://localhost:8080/static/imgs/456.jpg");
            int result = courseTopicDao.insertCourseTopic(courseTopic);
            System.out.print(result);
        }
        for (int i = 1; i <= 20; i++){
            CourseTopic courseTopic = new CourseTopic();
            courseTopic.setTopicName("MySQL"+i);
            courseTopic.setViews(RandomUtil.randomInt(2000,8000));
            courseTopic.setVipFlag(new Random().nextInt(2));
            courseTopic.setCourseTypeId(3);
            courseTopic.setTopicIntro("about MySQL");
            courseTopic.setFlag(1);
            courseTopic.setPptUrl("www.baidu.com");
            courseTopic.setCreateTime(new Date());
            courseTopic.setImgUrl("http://localhost:8080/static/imgs/456.jpg");
            int result = courseTopicDao.insertCourseTopic(courseTopic);
            System.out.print(result);
        }

    }

    @Test
    public void test8(){
        for (int i = 1; i <= 20; i++){
           // id, video_name, flag, free_flag, course_topic_id, play_url, create_time
            CourseVideo courseVideo = new CourseVideo();
            courseVideo.setVideoName("Spring5最新完整教程IDEA版通俗易懂 (1)");
            courseVideo.setFlag(1);
            courseVideo.setFreeFlag(0);
            courseVideo.setCourseTopicId(i);
            courseVideo.setPlayUrl("//player.bilibili.com/player.html?aid=71110355&bvid=BV1WE411d7Dv&cid=123206878&page=1\" scrolling=\"no\" border=\"0\" frameborder=\"no\" framespacing=\"0\" allowfullscreen=\"true\"");
            courseVideo.setCreateTime(new Date());
            int result = courseVideoDao.insertCourseVideo(courseVideo);
            System.out.print(result);
        }
        for (int i = 1; i <= 20; i++){
            // id, video_name, flag, free_flag, course_topic_id, play_url, create_time
            CourseVideo courseVideo = new CourseVideo();
            courseVideo.setVideoName("SpringCloud最新教程IDEA版 (1)");
            courseVideo.setFlag(1);
            courseVideo.setFreeFlag(0);
            courseVideo.setCourseTopicId(i+20);
            courseVideo.setPlayUrl("//player.bilibili.com/player.html?aid=76020761&bvid=BV1jJ411S7xr&cid=130029724&page=1\" scrolling=\"no\" border=\"0\" frameborder=\"no\" framespacing=\"0\" allowfullscreen=\"true\"");
            courseVideo.setCreateTime(new Date());
            int result = courseVideoDao.insertCourseVideo(courseVideo);
            System.out.print(result);
        }
        for (int i = 1; i <= 20; i++){
            // id, video_name, flag, free_flag, course_topic_id, play_url, create_time
            CourseVideo courseVideo = new CourseVideo();
            courseVideo.setVideoName("MySQL最新教程通俗易懂 (1))");
            courseVideo.setFlag(1);
            courseVideo.setFreeFlag(0);
            courseVideo.setCourseTopicId(i+40);
            courseVideo.setPlayUrl("//player.bilibili.com/player.html?aid=83395424&bvid=BV1NJ411J79W&cid=142661155&page=1\" scrolling=\"no\" border=\"0\" frameborder=\"no\" framespacing=\"0\" allowfullscreen=\"true\"");
            courseVideo.setCreateTime(new Date());
            int result = courseVideoDao.insertCourseVideo(courseVideo);
            System.out.print(result);
        }
        for (int i = 1; i <= 20; i++){
            // id, video_name, flag, free_flag, course_topic_id, play_url, create_time
            CourseVideo courseVideo = new CourseVideo();
            courseVideo.setVideoName("Spring5最新完整教程IDEA版通俗易懂 (2)");
            courseVideo.setFlag(1);
            courseVideo.setFreeFlag(1);
            courseVideo.setCourseTopicId(i);
            courseVideo.setPlayUrl("//player.bilibili.com/player.html?aid=71110355&bvid=BV1WE411d7Dv&cid=123207194&page=2\" scrolling=\"no\" border=\"0\" frameborder=\"no\" framespacing=\"0\" allowfullscreen=\"true\"");
            courseVideo.setCreateTime(new Date());
            int result = courseVideoDao.insertCourseVideo(courseVideo);
            System.out.print(result);
        }
        for (int i = 1; i <= 20; i++){
            // id, video_name, flag, free_flag, course_topic_id, play_url, create_time
            CourseVideo courseVideo = new CourseVideo();
            courseVideo.setVideoName("SpringCloud最新教程IDEA版 (2)");
            courseVideo.setFlag(1);
            courseVideo.setFreeFlag(1);
            courseVideo.setCourseTopicId(i+20);
            courseVideo.setPlayUrl("//player.bilibili.com/player.html?aid=76020761&bvid=BV1jJ411S7xr&cid=130029853&page=2\" scrolling=\"no\" border=\"0\" frameborder=\"no\" framespacing=\"0\" allowfullscreen=\"true\"");
            courseVideo.setCreateTime(new Date());
            int result = courseVideoDao.insertCourseVideo(courseVideo);
            System.out.print(result);
        }
        for (int i = 1; i <= 20; i++){
            // id, video_name, flag, free_flag, course_topic_id, play_url, create_time
            CourseVideo courseVideo = new CourseVideo();
            courseVideo.setVideoName("MySQL最新教程通俗易懂 (2))");
            courseVideo.setFlag(1);
            courseVideo.setFreeFlag(1);
            courseVideo.setCourseTopicId(i+40);
            courseVideo.setPlayUrl("//player.bilibili.com/player.html?aid=83395424&bvid=BV1NJ411J79W&cid=142661277&page=2\" scrolling=\"no\" border=\"0\" frameborder=\"no\" framespacing=\"0\" allowfullscreen=\"true\"");
            courseVideo.setCreateTime(new Date());
            int result = courseVideoDao.insertCourseVideo(courseVideo);
            System.out.print(result);
        }



    }

    @Test
    public void test9(){
        List<CourseType> list = courseTypeDao.findCourseTypeAll();
        for (CourseType c : list){
            System.out.println(c);
        }
    }




}