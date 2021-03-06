<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>课程列表</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" crossorigin="anonymous">
    <style>
        .selected:hover{
            cursor: pointer;
            color: crimson;
            box-shadow: 2px 3px 3px 2px #888888;
        }
    </style>
</head>
<body>

<!--引入导航栏-->
<jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
<%--首页内容区域--%>
<div class="container">

    <%-- 导航--%>
    <ul class="nav nav-pills">
        <li class="nav-item">
            <a class="nav-link  ${ clickType == 0? "active" :""}" href="/course">全部</a>
        </li>
        <c:forEach items="${courseTypeList}" var="type">
            <li class="nav-item">
                <a class="nav-link ${ type.id == clickType ? "active" :""} "
                   href="/course/${type.id}">${type.name}</a>
            </li>
        </c:forEach>
    </ul>

    <%-- - 0[row] 1 2 3[row] - --%>
    <%-- - 4[row] 5 6 7[row] - --%>
    <%-- - 8[row] 9 10 11[row] - --%>

    <%-- - 12[row] 13[row] - --%>

    <%-- - 12[row] 13 14 15[row] - --%>
    <%-- - PageInfo<CourseTopic> topicList - --%>
    <c:forEach items="${topicList.list}" var="topic" varStatus="idx" begin="0">
        <c:if test="${idx.index % 4 == 0 }">
            <div class="row row-cols-1 row-cols-md-4 mt-2">
        </c:if>
        <div class="col mb-3">
            <a href="/topicVideo/${topic.id}">
                <div class="card selected">
                    <img src="${topic.imgUrl}" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">${topic.topicName}</p>
                        <p class="card-text">${topic.views}人学习</p>
                        <c:choose>
                            <c:when test="${topic.vipFlag == 0}">
                                <span class="badge badge-pill badge-success ">免费</span>
                            </c:when>

                            <c:otherwise>
                                <span class="badge badge-pill badge-danger ">会员</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </a>
        </div>
        <c:if test="${idx.index % 4 == 3 || idx.last}">
            </div>
        </c:if>
    </c:forEach>

    <%-- 分页--%>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item ${topicList.hasPreviousPage? "" :"disabled"} ">
                <a class="page-link" href="/course/${clickType}?pageNum=${topicList.prePage}">上一页</a>
            </li>
            <%--            //导航条上的第一页--%>
            <%--            private int navigateFirstPage;--%>
            <%--            //导航条上的最后一页--%>
            <%--            private int navigateLastPage;--%>
            <c:forEach var="i" begin="${topicList.navigateFirstPage}" end="${topicList.navigateLastPage}">
                <li class=" page-item
                ${topicList.pageNum == i ? "active" : "" } ">
                    <a class="page-link" href="/course/${clickType}?pageNum=${i}">${i}</a>
                </li>
            </c:forEach>
            <li class="page-item ${topicList.hasNextPage? "" :"disabled"} ">
                <a class="page-link" href="/course/${clickType}?pageNum=${topicList.nextPage}">下一页</a>
            </li>
        </ul>
    </nav>
</div>
<br>
<br>
<%--网站的尾部--%>
<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
</body>
</html>
