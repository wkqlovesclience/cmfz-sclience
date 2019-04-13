<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
    <script>
        $(function () {
            $("#saveChapterBtn").click(function () {
                //var chapter = $("#chapterForm").serialize();
                var formData = new FormData($("#chapterForm")[0]);
                $.ajax({
                    type:"POST",
                    url:"${app}/chapter/upload",
                    data:formData,
                    contentType : false,
                    processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
                    success:function(request){
                        /*表单重置*/
                        document.getElementById("chapterForm").reset();
                        $("#chapterModal").modal("hide");
                        $("#grouptable").jqGrid().trigger("reloadGrid");
                    }
                });
            });

            //章节上传
            $("#add").click(function () {


                $.ajax({
                    type:"POST",
                    url:"${app}/album/findAlbumById",
                    data:{"id":id},
                    success : function (result) {
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
            });

            /*初始化表格*/
            $("#grouptable").jqGrid({
                styleUI : 'Bootstrap',
                url:"${app}/chapter/findAllChaptersByPage",
                datatype:"JSON",
                autowidth:true,//自适应宽度
                rowNum:10,//设置固定展示条数
                rowList:[10,15,20],//设置可选每页展示条数
                editurl:"${app}/chapter/edit",//指定编辑地址
                cellEdit:true,//设置编辑单元格
                hidegrid:true,//设置展示按钮
                //caption:"小组管理",//设置表格名称
                pager:"#pager",//设置页码
                viewrecords:true,//展示信息总记录数
                colNames:["name","audioPath","audioSize","audioTime","playNum","uploadTime","downloadNum","album","Options"],
                colModel:[
                    {name:"name",align:"center"},
                    {name:"audioPath",align:"center",editable:true},
                    {name:"audioSize",align:"center",editable:true},
                    {name:"audioTime",align:"center",editable:true},
                    {name:"playNum",align:"center",editable:true},
                    {name:"uploadTime",align:"center",editable:true},
                    {name:"downloadNum",align:"center",editable:true},
                    {name:"album.name",align:"center",editable:true},
                    {name:"options",width:"230px",formatter:function (value,options,row) {
                            console.log(options);
                            console.log(row.id);
                            var content="<a onclick=\"javascript:listen('"+row.id+"')\"><span class='glyphicon glyphicon-play'></span></a> &nbsp;&nbsp;&nbsp;&nbsp;"+
                                "<a href=\"${app}/chapter/download?id="+row.id+"\"><span class='glyphicon glyphicon-save'></span></a> &nbsp;&nbsp;&nbsp;"
                                +"<a onclick=\"javascript:listen('"+row.id+"')\"><span class='glyphicon glyphicon-remove'></span></a>";
                            return content;
                        }}
                ],
                grouping:true,
                groupingView : {
                    groupField: ['album.name'],
                    groupColumnShow: [true],
                    groupText: ['<b>{0} - {1} </b>'],
                    groupCollapse: true,
                    groupOrder: ['desc']
                }
            }).jqGrid("navGrid","#pager",{edit:true,add:true,del:true,search:true,refresh:true});
        });

        function edit(id){
            //根据rowid获取当前行的方法
            $("#poetryTab").jqGrid('editGridRow', id, {
                height : 300,
                reloadAfterSubmit : true
            });
        }


        function del(id){
            $("#poetryTab").jqGrid('delGridRow', id, {
                reloadAfterSubmit : true
            });
        }

        function abc123(){
            $('#qqq').modal('show');
        }
    </script>
        <!--class="col-sm-10"开始-->
        <div class="col-sm-10">
            <div class="page-header">
                <h2>章节管理</h2>
            </div>
            <!--标签开始-->
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">章节列表</a></li>
                <li role="presentation"><a href="#" onclick="abc123();" id="add">章节添加</a></li>
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
                </div>
                <div class="tab-pane" id="saveInfo">B</div>
                <div class="tab-pane" id="SpringMvc">SpringMvc</div>
                <div class="tab-pane" id="SpringBoot">SpringBoot</div>
            </div>
            </div>
            <!--class="col-sm-10"结束-->
    <!--第一层栅格系统结束-->


<%--模态框开始--%>
<div class="modal fade" id="qqq" tabindex="-1">
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

            <!--模态框内容体-->
            <div class="modal-body">

                <form id="chapterForm" class="form-horizontal" enctype="multipart/form-data">
                    <input type="text" id="id" name="id" hidden/>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">专辑名称</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="album.name" id="albumName">
                                <option value="1">激活</option>
                                <option value="0">冻结</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">章节上传</label>
                        <div class="col-sm-10">
                            <input type="file" name="file"  placeholder="请选择上传章节" class="form-control">
                        </div>
                    </div>
                    <!--模态页脚-->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="saveChapterBtn">保存</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%--模态框结束--%>