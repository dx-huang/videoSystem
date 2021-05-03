<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!doctype html>
<html lang="en" >
<head>    <!-- Required meta tags -->
    <meta charset="GBK">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <title>首页</title>
    <style>
        .selected:hover{
            cursor: pointer;
            color: crimson;
            box-shadow: 2px 3px 3px 2px #888888;
        }
    </style>
</head>
<body>
<%--引入导航栏--%>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>

<%--内容--%>
<%--轮播图--%>
<div class="container">
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="/static/imgs/ssm框架.jpg" class="d-block w-100" alt="...">
            </div>
            <div class="carousel-item">
                <img src="/static/imgs/ssm框架.jpg" class="d-block w-100" alt="...">
            </div>
            <div class="carousel-item">
                <img src="/static/imgs/ssm框架.jpg" class="d-block w-100" alt="...">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div><br>
<%--卡片--%>
<%--框架--%>
<div class="container">
    <a href="#" class="float-right">更多>></a>
    <h4 class="text-center">框架</h4>
    <hr class="btn-secondary">
    <div class="row">
        <c:forEach items="${courseType1.list}" var="CourseTopic">
            <div class="col-sm-3 ">
                <a href="/topicVideo/${CourseTopic.id}">
                    <div class="card selected" style="width: 16rem;">
                        <img src="${CourseTopic.imgUrl}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">${CourseTopic.topicName}</h5>
                            <p class="card-text">${CourseTopic.views}人学习</p>
                            <c:choose>
                                <c:when test="${CourseTopic.vipFlag==0}">
                                    <span class="badge badge-success">免费</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-danger">会员</span>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div><br>

<%--数据库--%>
<div class="container">
    <a href="#" class="float-right">更多>></a>
    <h4 class="text-center">数据库</h4>
    <hr class="btn-secondary">
    <div class="row">
        <c:forEach items="${courseType3.list}" var="CourseTopic">
            <div class="col-sm-3 ">
                <a href="/topicVideo/${CourseTopic.id}">
                    <div class="card selected" style="width: 16rem;">
                        <img src="${CourseTopic.imgUrl}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">${CourseTopic.topicName}</h5>
                            <p class="card-text">${CourseTopic.views}人学习</p>
                            <c:choose>
                                <c:when test="${CourseTopic.vipFlag==0}">
                                    <span class="badge badge-success">免费</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-danger">会员</span>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>

</div><br><br>

<%--引入页尾--%>
<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>


<%--JQuery--%>
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
<%--BootStrap JS--%>
<%--<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>--%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

</body>
</html>