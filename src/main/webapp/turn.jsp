<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>

    <script>
        /*添加*/
        $(function () {
            $("#sub").click(function () {
                var formData = new FormData($("#form")[0]);
                $.ajax({
                    type:"POST",
                    url:"${app}/turn/uploadOrUpdate",
                    data:formData,
                    contentType : false,
                    processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
                    success : function () {
                        /*表单重置*/
                        document.getElementById("form").reset();
                        $("#myModal").modal("hide");
                        $("#grouptable").jqGrid().trigger("reloadGrid");
                    }

                })
            });


            /*初始化表格*/
            $("#grouptable").jqGrid({
                styleUI : 'Bootstrap',
                url:"turn/findTurnsByPage",
                datatype:"JSON",
                autowidth:true,//自适应宽度
                rowNum:2,//设置固定展示条数
                rowList:[2,5,10],//设置可选每页展示条数
                editurl:"${app}/turn/edit",//指定编辑地址
                cellEdit:true,//设置编辑单元格
                hidegrid:true,//设置展示按钮
                //caption:"小组管理",//设置表格名称
                pager:"#pager",//设置页码
                viewrecords:true,
                colNames:["图片名称","图片路径","图片描述","图片状态","上传时间","图片超链接","操作"],
                colModel:[
                    {name:"title",align:"center"},
                    {name:"picPath",align:"center",editable:true},
                    {name:"picDescription",align:"center",editable:true},
                    {name:"status",editable:true,edittype:"select",
                        editoptions:{value:"1:激活;2:未激活"}
                    },
                    {name:"uploadTime",align:"center",editable:true},
                    {name:"hyperlink",align:"center",editable:true},
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
        /*查看*/
        function see(id) {
            $.ajax({
                type:"POST",
                url:"${app}/turn/findTurnById",
                data:{"id":id},
                success : function (result) {
                    //重置表单
                    console.log($("#pic"));
                    /*$("#pic")[0].reset();*/
                    document.getElementById("pic").reset();
                    $("#img").attr("src","${app}/upload/"+result.title);
                    $("#picModal").modal("show");
                }
            });
        }
        function edit(id){
            $.ajax({
                type:"POST",
                url:"${app}/turn/findTurnById",
                data:{"id":id},
                success : function (result) {
                    //重置表单
                    console.log($("#form"));
                    document.getElementById("form").reset();
                    $("#id").val(result.id);
                    $("#picDescription").val(result.picDescription);
                    $("#sta").val(result.status);
                    $("#myModal").modal("show");
                }
            });
        }

        function del(id){
            $.ajax({
                type:"POST",
                url:"${app}/turn/deleteTurn",
                data:{"id":id},
                success : function (result) {
                    /*刷新模态框*/
                    $("#grouptable").jqGrid().trigger("reloadGrid");
                }
            });
        }



    </script>
    <style>
        .navbar-nav>li>a{
            line-height:0px;
        }
        #hei{
            color: blue;
        }
    </style>

        <!--class="col-sm-10"开始-->
        <div class="col-sm-10">
            <div class="page-header">
                <h2>轮播图管理</h2>
            </div>
            <!--标签开始-->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">轮播图管理</a></li>
                    <li role="presentation"><a href="#myModal" id="add" data-toggle="modal" data-target="#myModal">轮播图添加</a></li>
                </ul>

            </ul>

            <!--标签内容组-->
            <div class="tab-content">
                <!--标签内容面板-->
                <div class="tab-pane active" id="home">
                    <table id="grouptable"></table>
                    <div id="pager" style="height: 50px;width: auto"></div>
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

<!--模态框的创建开始-->
<div class="modal fade" id="myModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <!--模态框标题-->
            <div class="modal-header">
                <!--
                    用来关闭模态框的属性:data-dismiss="modal"
                -->
                <button type="button" class="close" data-dismiss="modal" ><span >&times;</span></button>
                <h4 class="modal-title">编辑轮播图信息</h4>
            </div>

            <!--模态框内容体-->
            <div class="modal-body">

                <form id="form" action="${pageContext.request.contextPath}/turn/uploadOrUpdate" class="form-horizontal">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">文件</label>
                        <div class="col-sm-10">
                            <input type="hidden"  id="id" name="id"/>
                            <input type="file" name="file" id="file" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">图片描述</label>
                        <div class="col-sm-10">
                            <input type="text" name="picDescription" id="picDescription"  placeholder="请输入图片相关描述信息" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">图片状态</label>
                        <div class="col-sm-10">
                            <select name="status" id="sta" class="form-control">
                                <option id="已激活" value="已激活">已激活</option>
                                <option id="未激活" value="未激活">未激活</option>
                            </select>
                        </div>
                    </div>

                    <!--模态页脚-->
                    <div class="modal-footer">
                        <button type="button" id="sub" class="btn btn-primary">确认</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
                    </div>

                </form>

            </div>



        </div>
    </div>
</div>
<!--模态框的创建结束-->

<!--模态框的创建开始-->
<div class="modal fade" id="picModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <!--模态框标题-->
            <div class="modal-header">
                <!--
                    用来关闭模态框的属性:data-dismiss="modal"
                -->
                <button type="button" class="close" data-dismiss="modal" ><span >&times;</span></button>
                <h4 class="modal-title">编辑轮播图信息</h4>
            </div>

            <!--模态框内容体-->
            <div class="modal-body">

                <form id="pic" action="${pageContext.request.contextPath}/turn/uploadOrUpdate" class="form-horizontal">

                    <div class="form-group">
                        <%--<label class="col-sm-2 control-label"></label>--%>
                        <div class="col-sm-12">
                            <img src="#" id="img" style="width: 570px;height: 400px">
                        </div>
                    </div>

                    <!--模态页脚-->
                    <div class="modal-footer">
                        <button type="button" id="subm" class="btn btn-primary" data-dismiss="modal">确认</button>
                        <%--<button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>--%>
                    </div>

                </form>

            </div>



        </div>
    </div>
</div>
<!--模态框的创建结束-->


