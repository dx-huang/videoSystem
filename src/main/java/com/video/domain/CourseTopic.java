package com.video.domain;

import java.util.Date;

/**
 * course_topic
 * 
 * @author bianj
 * @version 1.0.0 2021-01-26
 */
public class CourseTopic implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -1946951694147224078L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** id */
    private Integer id;

    /** topicName */
    private String topicName;

    /** views */
    private Integer views;

    /** vipFlag */
    private Integer vipFlag;

    /** courseTypeId */
    private Integer courseTypeId;

    /** topicIntro */
    private String topicIntro;

    /** flag */
    private Integer flag;

    /** pptUrl */
    private String pptUrl;

    /** createTime */
    private Date createTime;

    private String imgUrl;

    /* This code was generated by TableGo tools, mark 1 end. */

    /* This code was generated by TableGo tools, mark 2 begin. */

    /**
     * 获取id
     * 
     * @return id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     * 
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取topicName
     * 
     * @return topicName
     */
    public String getTopicName() {
        return this.topicName;
    }

    /**
     * 设置topicName
     * 
     * @param topicName
     */
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    /**
     * 获取views
     * 
     * @return views
     */
    public Integer getViews() {
        return this.views;
    }

    /**
     * 设置views
     * 
     * @param views
     */
    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     * 获取vipFlag
     * 
     * @return vipFlag
     */
    public Integer getVipFlag() {
        return this.vipFlag;
    }

    /**
     * 设置vipFlag
     * 
     * @param vipFlag
     */
    public void setVipFlag(Integer vipFlag) {
        this.vipFlag = vipFlag;
    }

    /**
     * 获取courseTypeId
     * 
     * @return courseTypeId
     */
    public Integer getCourseTypeId() {
        return this.courseTypeId;
    }

    /**
     * 设置courseTypeId
     * 
     * @param courseTypeId
     */
    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    /**
     * 获取topicIntro
     * 
     * @return topicIntro
     */
    public String getTopicIntro() {
        return this.topicIntro;
    }

    /**
     * 设置topicIntro
     * 
     * @param topicIntro
     */
    public void setTopicIntro(String topicIntro) {
        this.topicIntro = topicIntro;
    }

    /**
     * 获取flag
     * 
     * @return flag
     */
    public Integer getFlag() {
        return this.flag;
    }

    /**
     * 设置flag
     * 
     * @param flag
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * 获取pptUrl
     * 
     * @return pptUrl
     */
    public String getPptUrl() {
        return this.pptUrl;
    }

    /**
     * 设置pptUrl
     * 
     * @param pptUrl
     */
    public void setPptUrl(String pptUrl) {
        this.pptUrl = pptUrl;
    }

    /**
     * 获取createTime
     * 
     * @return createTime
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置createTime
     * 
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "CourseTopic{" +
                "id=" + id +
                ", topicName='" + topicName + '\'' +
                ", views=" + views +
                ", vipFlag=" + vipFlag +
                ", courseTypeId=" + courseTypeId +
                ", topicIntro='" + topicIntro + '\'' +
                ", flag=" + flag +
                ", pptUrl='" + pptUrl + '\'' +
                ", createTime=" + createTime +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}