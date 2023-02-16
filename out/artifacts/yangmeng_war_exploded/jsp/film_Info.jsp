<%--
  Created by IntelliJ IDEA.
  User: 25684
  Date: 2023/2/15
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../static/js/jquery-3.1.1.js"></script>
    <link href="../static/layui/css/layui.css" rel="stylesheet"/>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>学生管理</legend>
</fieldset>

<table id="tb" lay-filter="tbFilter"></table>

<template id="tbToolbal">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">添加</button>
</template>

<script type="text/html" id="rowtool">
    <div class="layui-btn-group">
        <button type="button" class="layui-btn layui-btn-sm" lay-event="edit">
            <i class="layui-icon">&#xe642;</i>
        </button>
        <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del">
            <i class="layui-icon">&#xe640;</i>
        </button>
    </div>
</script>

<form class="layui-form" id="fm" hidden lay-filter="fmFilter">
    <div class="layui-form-item" hidden>
        <label class="layui-form-label">id</label>
        <div class="layui-input-block">
            <input type="text" name="filmId" required lay-verify=""
                   placeholder="请输入姓名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">电影名字</label>
        <div class="layui-input-block">
            <input type="text" name="filmName" required lay-verify="required"
                   placeholder="请输入电影名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">电影类型</label>
        <div class="layui-input-inline">
            <select name="filmType" lay-verify="required" lay-search="">
                <option value="">直接选择或搜索选择</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">播放时长</label>
        <div class="layui-input-block">
            <input type="text" name="filmDuration" required lay-verify="required"
                   placeholder="请输入播放时长" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">上映时间</label>
        <div class="layui-input-inline">
            <input type="text" name="releaseDate" id="date" lay-verify="datetime" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">电影备注</label>
        <div class="layui-input-block">
            <input type="text" name="filmRemark" required lay-verify="required"
                   placeholder="请输入电影备注" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="doSubmit">提交</button>
            <button class="layui-btn" type="reset">重置</button>
        </div>
    </div>
</form>
</body>
</html>
<script src="../static/layui/layui.all.js"></script>
<script>
    layui.use(['table', 'layer', 'form','laydate'], function () {
        let table = layui.table
            , layer = layui.layer
            , form = layui.form
            , index = 0 // 弹出窗的下标
            , url = '' //表单提交的路径
            ,laydate=layui.laydate;

        laydate.render({
            elem: '#date',
            type: 'datetime'
        });

        // 展示已知数据
        table.render({
            elem: '#tb'   //指定原始表格元素选择器（推荐id选择器）
            , url: '/film/list'//异步数据接口,会自动访问url路径，把返回值作为表格数据，返回值[{},{}]
            , cols: [[ // 设置表头。值是一个二维数组
                // title:列名;width:列宽;minWidth：最小列宽;sort: 是否允许排序（默认：false）;,
                // hide: true隐藏   // field：设定字段名，加载数据，数据的属性名
                {field: 'filmId', title: 'ID', sort: true}
                , {field: 'filmName', title: '电影名字'}
                , {field: 'typeName', title: '电影类型'}
                , {field: 'filmDuration', title: '电影时长'}
                , {field: 'releaseDate', title: '上映时间'}
                , {field: 'filmRemark', title: '备注'}
                , {title: '操作', toolbar: '#rowtool'} // 绑定工具条模板
            ]]
            , even: true
            , toolbar: '#tbToolbal'
        });

        $.ajax({
            url:'/film/lists',
            async:false,
            success:function (data) {
                for (let i = 0; i < data.count; i++) {
                    $("#fm [name='filmType']").append(`<option value="\${data.data[i].type_id}">\${data.data[i].type_name}</option>`)
                }
            }
        });
        form.render(null,'fmFilter');

        // 监听头部工具条
        table.on('toolbar(tbFilter)', function (obj) {
            switch (obj.event) {
                case 'add':
                    url = '/film/add';
                    index = layer.open({
                        type: 1,
                        content: $("#fm"), //这里content是一个普通的String
                        title: '新增',
                        area: ['600px', '600px']
                    });
                    $("#fm")[0].reset();    // 重置表单
                    break;
                case 'reset':
                    $("#fm")[0].reset();
                    break;
            }
        })

        // 工具条事件:行内工具条
        table.on('tool(tbFilter)', function (obj) {
            let row = obj.data;// 当前行的数据
            switch (obj.event) {
                case 'edit':
                    url = '/film/update';
                    index = layer.open({
                        type: 1,
                        content: $("#fm"), //这里content是一个普通的String
                        title: '修改',
                        area: ['600px', '600px'],
                        success: function () {
                            // 回显数据
                            form.val('fmFilter', row);// 按name值匹配
                        }
                    });
                    break;
                case 'del':
                    $.post('/film/delete', {id: row.filmId}, function (data) {
                        if (data == 1) {
                            layer.msg('删除成功', {icon: 1,time: 500},function () {
                                table.reload('tb');
                            });
                        } else {
                            layer.msg('删除失败', {icon: 5});
                        }
                    })
                    break;
            }
        })

        // 表单的提交事件
        form.on('submit(doSubmit)', function (obj) {
            $.post(url, obj.field, function (data) {
                if (data == 1) {
                    layer.msg('操作成功', {icon: 1,time: 500}, function () {
                        layer.close(index);     // 关闭弹出层
                        $("#fm")[0].reset();    // 重置表单
                        table.reload('tb');     // 刷新表格
                    });
                } else {
                    layer.msg('操作失败', {icon: 5});
                }
            })
            return false;
        })
    })
</script>
