<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法洲后台管理系统</title>
    <link rel="stylesheet" href="${app}/statics/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="${app}/statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="${app}/statics/boot/js/jquery-3.3.1.min.js"></script>
    <script src="${app}/statics/boot/js/bootstrap.min.js"></script>
    <script src="${app}/statics/jqgrid/js/trirand/jquery.jqGrid.min(1).js"></script>
    <script src="${app}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script>

    </script>
</head>
<body>
<!--生成导航条开始-->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">

        <!--生成导航标题-->
        <div class="navbar-header">
            <a href="" class="navbar-brand">持明法洲后台管理管理系统V1.0</a>
        </div>
        <!--生成导航内容-->
        <ul class="nav navbar-nav navbar-right">
            <li>
                <a href=""> 退出登录 <span class="glyphicon glyphicon-share"></span> </a>
            </li>
        </ul>
        <p class="navbar-text navbar-right">欢迎：<span id="hei">${loginAdmin.name}</span></p>
    </div>
</nav>

<%--栅栏系统开始--%>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
            <!--创建手风琴实例-->
            <div class="panel-group" id="panelgroup">

                <!--创建面板-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <!--使用连接完成折叠效果-->
                            <a href="#aa" data-toggle="collapse" data-parent="#panelgroup" ><h5>轮播图模块</h5></a>
                        </div>
                    </div>

                    <div class="panel-collapse collapse" id="aa">
                        <ul class="list-group">
                            <li class="list-group-item"><a href="javascript:$('#xxx').load('${app}/turn.jsp')">轮播图列表</a></li>
                            <li class="list-group-item"><a href="javascript:$('#xxx').load('./users01.html')">轮播图添加</a></li>
                        </ul>
                    </div>
                </div>

                <!--创建另一个面板-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <!--使用连接完成折叠效果-->
                            <a href="#jj" data-toggle="collapse" data-parent="#panelgroup" ><h5>专辑模块</h5></a>
                        </div>
                    </div>

                    <div class="panel-collapse collapse" id="jj">
                        <ul class="list-group">
                            <li class="list-group-item"><a href="javascript:$('#xxx').load('${app}/album.jsp')">专辑列表</a></li>
                            <li class="list-group-item"><a href="javascript:$('#xxx').load('${app}/chapter.jsp')">章节列表</a></li>
                        </ul>
                    </div>
                </div>


                <!--创建另一个面板-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <!--使用连接完成折叠效果-->
                            <a href="#bb" data-toggle="collapse" data-parent="#panelgroup" ><h5>文章模块</h5></a>
                        </div>
                    </div>

                    <div class="panel-collapse collapse" id="bb">
                        <ul class="list-group">
                            <li class="list-group-item"><a href="javascript:$('#xxx').load('./users01.html')">章节列表</a></li>
                            <li class="list-group-item"><a href="javascript:$('#xxx').load('./users01.html')">章节添加</a></li>
                        </ul>
                    </div>
                </div>


                <!--创建另一个面板-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <!--使用连接完成折叠效果-->
                            <a href="#cc" data-toggle="collapse" data-parent="#panelgroup" ><h5>用户模块</h5></a>
                        </div>
                    </div>

                    <div class="panel-collapse collapse" id="cc">
                        <ul class="list-group">
                            <li class="list-group-item"><a href="javascript:$('#xxx').load('${app}/user.jsp')">用户列表</a></li>
                            <li class="list-group-item"><a href="javascript:$('#xxx').load('./users01.html')">用户添加</a></li>
                        </ul>
                    </div>
                </div>


                <!--创建另一个面板-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <!--使用连接完成折叠效果-->
                            <a href="#dd" data-toggle="collapse" data-parent="#panelgroup" ><h5>日志模块</h5></a>
                        </div>
                    </div>

                    <div class="panel-collapse collapse" id="dd">
                        <ul class="list-group">
                            <li class="list-group-item">类别列表</li>
                            <li class="list-group-item">添加类别</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <%--中心屏展示开始--%>
        <div class="col-sm-10" id="xxx">
            <%--首页开始--%>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <h5>欢迎登录持明法州后台管理系统</h5>
                    </div>
                </div>
            </div>
            <%--首页结束--%>
            <%--巨幕展示--%>
            <div class="jumbotron">
                <img src="${pageContext.request.contextPath}/img/jingjing.jpg" class="img-responsive" alt="Cinque Terre">
            </div>
        </div>
        <%--中心屏展示结束--%>
        <div class="panel-footer">
            <div class="panel-title">
                <h5 style="text-align: center">@18595585841.163.cn</h5>
            </div>
        </div>
    </div>
</div>
<%--栅栏系统结束--%>

</body>
</html>
