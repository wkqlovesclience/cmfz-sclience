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
                url:"log/findAllLogs",
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
                colNames:["Id","操作","操作时间","操作状态","操作人员"],
                colModel:[
                    {name:"id",align:"center"},
                    {name:"operation",align:"center",editable:true},
                    {name:"createDate",align:"center",editable:true},
                    {name:"sign",align:"center",editable:true},
                    {name:"adminName",align:"center",editable:true}
                ]
            }).jqGrid("navGrid","#pager",{edit:true,add:true,del:true,search:true,refresh:true});
        });


    </script>

        <!--class="col-sm-10"开始-->
        <div class="col-sm-10">
            <div class="page-header">
                <h2>专辑管理</h2>
            </div>
            <!--标签开始-->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">专辑列表</a></li>
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
<div class="modal fade" id="logModal" tabindex="-1">
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

                <form id="logForm" class="form-horizontal" enctype="multipart/form-data">

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