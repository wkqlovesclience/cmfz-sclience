<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>

    <script>
        function showPicture(cellvalue, options, rowObject){
            return "<img src='" +cellvalue  + "' height='50' width='50' />";
        }
        $(function () {
            $('#myButton').on('click', function () {

                $.ajax({
                    type:"POST",
                    url:"${app}/user/export",
                    success : function () {
                        $("#grouptable").jqGrid().trigger("reloadGrid");
                    }
                });
            })

            /*初始化表格*/
            $("#grouptable").jqGrid({
                styleUI : 'Bootstrap',
                url:"user/findAllUsersByPage",
                datatype:"JSON",
                autowidth:true,//自适应宽度
                rowNum:2,//设置固定展示条数
                rowList:[2,5,10],//设置可选每页展示条数
                editurl:"${app}/student/edit",//指定编辑地址
                cellEdit:true,//设置编辑单元格
                hidegrid:true,//设置展示按钮
                //caption:"小组管理",//设置表格名称
                pager:"#pager",//设置页码
                viewrecords:true,
                colNames:["用户名","手机号","性别","昵称","真实姓名","所在省份","所在城市","个人签名","头像","盐","注册时间","最后登录时间","专辑状态","操作"],
                colModel:[
                    {name:"userName",align:"center"},
                    {name:"phone",align:"center",editable:true},
                    {name:"sex",align:"center",editable:true},
                    {name:"nickName",align:"center",editable:true},
                    {name:"name",align:"center",editable:true},
                    {name:"province",align:"center",editable:true},
                    {name:"city",align:"center",editable:true},
                    {name:"autograph",align:"center",editable:true},
                    {name : "headPic",
                        align:"center",
                        index : "url",
                        formatter : showPicture,
                        editable : true,
                        edittype : 'file',
                        editoptions:{enctype:"multipart/form-data"},
                        width : 40},
                    {name:"salt",align:"center",editable:true},
                    {name:"registTime",align:"center",editable:true},
                    {name:"lastloginTime",align:"center",editable:true},
                    {name:"status",editable:true,edittype:"select",
                        editoptions:{value:"1:正常;2:冻结"}
                    },
                    {name:"options",width:"230px",formatter:function (value,options,row) {
                            console.log(options);
                            console.log(row.id);
                            var content="<a onclick=\"javascript:see('"+row.id+"')\"> <span class='glyphicon glyphicon-search'></span></a>  " +
                                "<a onclick=\"javascript:edit('"+row.id+"')\"><span class='glyphicon glyphicon-pencil'></span></a>  " +
                                "<a onclick=\"javascript:del('"+row.id+"')\"><span class='glyphicon glyphicon-remove'></span></a>  ";
                            return content;
                        }}
                ]
            }).jqGrid("navGrid","#pager",{edit:true,add:true,del:true,search:true,refresh:true});
        });


    </script>


        <!--class="col-sm-10"开始-->
        <div class="col-sm-10">
            <div class="page-header">
                <h2>用户管理</h2>
            </div>
            <!--标签开始-->
            <ul class="nav nav-tabs">
                <li class="active"><a href="#list" data-toggle="tab">用户列表</a></li>
                <li><a href="#saveInfo" data-toggle="tab" class="addOrUpdate" id="add">用户添加</a></li>
                <li class="dropdown">
                    <!--触发器-->
                    <a href="" class="dropdown-toggle" data-toggle="dropdown">
                        下拉菜单 <span class="caret"></span>
                    </a>
                    <!--下拉菜单-->
                    <ul class="dropdown-menu">
                        <li><a href="#SpringMvc" data-toggle="tab">SpringMVC</a></li>
                        <li><a href="#SpringBoot" data-toggle="tab">SpringBOOT</a></li>
                    </ul>
                </li>
            </ul>

            <!--标签内容组-->
            <div class="tab-content">
                <!--标签内容面板-->
                <div class="tab-pane active" id="list">
                    <table id="grouptable"></table>
                    <div id="pager" style="width: auto;height: 50px"></div>
                    <button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off">
                        导出用户信息
                    </button>
                </div>
                <div class="tab-pane" id="saveInfo">B</div>
                <div class="tab-pane" id="SpringMvc">SpringMvc</div>
                <div class="tab-pane" id="SpringBoot">SpringBoot</div>
            </div>
            </div>
            <!--class="col-sm-10"结束-->
        </div>
    </div>
    <!--第一层栅格系统结束-->
