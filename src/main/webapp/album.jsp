<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>


    <script>
        function showPicture(cellvalue, options, rowObject){
            return "<img src='" +cellvalue  + "' height='50' width='50' />";
        }
        $(function () {

            /*初始化表格*/
            $("#grouptable").jqGrid({
                styleUI : 'Bootstrap',
                url:"album/findAllAlbumsByPage",
                datatype:"JSON",
                autowidth:true,//自适应宽度
                rowNum:2,//设置固定展示条数
                rowList:[2,5,10],//设置可选每页展示条数
                editurl:"${app}/album/edit",//指定编辑地址
                cellEdit:true,//设置编辑单元格
                hidegrid:true,//设置展示按钮
                //caption:"小组管理",//设置表格名称
                pager:"#pager",//设置页码
                viewrecords:true,
                colNames:["专辑名称","评分","播音","专辑作者","专辑描述","上传时间","章节数量","albumPic","cover","专辑状态","操作"],
                colModel:[
                    {name:"name",align:"center"},
                    {name:"score",align:"center",editable:true},
                    {name:"beam",align:"center",editable:true},
                    {name:"author",align:"center",editable:true},
                    {name:"des",align:"center",editable:true},
                    {name:"publishDate",align:"center",editable:true},
                    {name:"episodes",align:"center",editable:true},
                    {name:"albumPic",
                        align:"center",
                        index : "url",
                        formatter : showPicture,
                        editable : true,
                        edittype : 'file',
                        editoptions:{enctype:"multipart/form-data"},
                        width : 40},
                    {name:"cover",align:"center",editable:true},
                    {name:"status",editable:true,edittype:"select",
                        editoptions:{value:"1:正常;2:冻结"},
                        formatter:function(value,options,row){
                            if(row.status == 0){
                                return "正常";
                            }
                            return "冻结";
                        }
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
        $("#savebtn").click(function () {

            var formData = new FormData($("#albumForm")[0]);
            $.ajax({
                type:"POST",
                url:"${app}/album/addOrUpdate",
                data:formData,
                contentType : false,
                processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
                success : function () {
                    //移除表单中不可编辑属性
                    $(':input', '#albumForm').removeAttr('disabled');
                    /*表单重置*/
                    document.getElementById("albumForm").reset();
                    $("#albumModal").modal("hide");
                    $("#grouptable").jqGrid().trigger("reloadGrid");
                }

            })
        });
        /*修改*/
        function edit(id){

            $.ajax({
                type:"POST",
                url:"${app}/album/findAlbumById",
                data:{"id":id},
                success : function (result) {
                    //移除表单中不可编辑属性
                    $(':input', '#albumForm').removeAttr('disabled');
                    //重置表单
                    console.log($("#albumForm"));
                    document.getElementById("albumForm").reset();
                    $("#id").val(result.id);
                    $("#name").val(result.name);
                    $("#score").val(result.score);
                    $("#author").val(result.author);
                    $("#reader").val(result.beam);
                    $("#count").val(result.episodes);
                    $("#des").val(result.des);
                    $("#pic").val(result.file);
                    $("#createdate").val(result.createDate);
                    $("#status").val(result.status);
                }
            });
            $("#albumModal").modal("show");
        }
        /*删除*/
        function del(id){
            $.ajax({
                type:"POST",
                url:"${app}/album/deleteAlbum",
                data:{"id":id},
                success : function (result) {
                    /*刷新模态框*/
                    $("#grouptable").jqGrid().trigger("reloadGrid");
                }
            });
        }


        function see(id) {

            $.ajax({
                type:"POST",
                url: "${app}/album/findAlbumById",
                dataType:"JSON",
                data:{"id":id},
                success:function (result) {
                    //重置表单
                    console.log($("#albumForm"));
                    document.getElementById("albumForm").reset();
                    $("#id").val(result.id).attr("disabled","disabled");
                    $("#name").val(result.name).attr("disabled","disabled");
                    $("#score").val(result.score).attr("disabled","disabled");
                    $("#author").val(result.author).attr("disabled","disabled");
                    $("#reader").val(result.beam).attr("disabled","disabled");
                    $("#count").val(result.episodes).attr("disabled","disabled");
                    $("#des").val(result.des).attr("disabled","disabled");
                    $("#pic").val(result.file).attr("disabled","disabled");
                    $("#createdate").val(result.createDate).attr("disabled","disabled");
                    $("#status").val(result.status).attr("disabled","disabled");
                    $("#img").attr("src","${app}/upload/"+result.cover)

                }
            });
            $("#albumModal").modal("show");
        }

    </script>

        <!--class="col-sm-10"开始-->
        <div class="col-sm-10">
            <div class="page-header">
                <h2>专辑管理</h2>
            </div>
            <!--标签开始-->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">专辑列表</a></li>
                <li role="presentation"><a href="#albumModal" id="add" data-toggle="modal" data-target="#albumModal">专辑添加</a></li>
            </ul>
            <!--标签内容组-->
            <div class="tab-content">
                <!--标签内容面板-->
                <div class="tab-pane active" id="list">
                    <table id="grouptable"></table>
                    <div id="pager" style="height: 50px;width: auto"></div>
                </div>
                <div class="tab-pane" id="saveInfo">B</div>
                <div class="tab-pane" id="SpringMvc">SpringMvc</div>
                <div class="tab-pane" id="SpringBoot">SpringBoot</div>
            </div>
        </div>
        <!--class="col-sm-10"结束-->
    <!--第一层栅格系统结束-->


<%--模态框开始--%>
<div class="modal fade" id="albumModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <!--模态框标题-->
            <div class="modal-header">
                <!--
                    用来关闭模态框的属性:data-dismiss="modal"
                -->
                <button type="button" class="close" data-dismiss="modal" ><span >&times;</span></button>
                <h4 class="modal-title">编辑专辑信息</h4>
            </div>

            <!--模态框内容体开始-->
            <div class="modal-body">

                <form id="albumForm" class="form-horizontal" enctype="multipart/form-data">

                    <input type="text" id="id" name="id" hidden/>

                    <input type="text" id="createdate" name="createDate" hidden/>

                    <input type="text" id="count" name="episodes" hidden/>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">专辑名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="name" id="name" placeholder="请输入专辑名称" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">专辑评分</label>
                        <div class="col-sm-10">
                            <input type="text" name="score" id="score" placeholder="请输入评分" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">专辑作者</label>
                        <div class="col-sm-10">
                            <input type="text" name="author" id="author" placeholder="请输入作者" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">专辑播音</label>
                        <div class="col-sm-10">
                            <input type="text" name="beam" id="reader" placeholder="请输入播音" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">专辑封面</label>
                        <div class="col-sm-10">
                            <input type="file" name="file"  placeholder="请上传专辑封面" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">专辑描述</label>
                        <div class="col-sm-10">
                            <%--<input type="text" name="des" id="des" placeholder="请输入描述" class="form-control">--%>
                                <!-- 加载编辑器的容器 -->
                                <script id="des" name="des" type="text/plain">
                                    这里写你的初始化内容
                                </script>
                                <!-- 实例化编辑器 -->
                                <script type="text/javascript">
                                    UE.delEditor('des');
                                    var ue = UE.getEditor('des');

                                </script>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">激活状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="status" id="status">
                                <option value="1">激活</option>
                                <option value="0">冻结</option>
                            </select>
                        </div>
                    </div>

                    <div class="modal-body">
                        <img  id="img" class="img-responsive" >
                    </div>


                </form>

            </div>

            <!--模态页脚-->
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="savebtn">保存</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>

        </div>
    </div>
</div>
<%--模态框结束--%>