/**
 * Created by wang'ao on 2016/11/14 0014.
 */
/*****************************************************
 *
 * 以下模块用来公共的CRUD的es实现,id请勿更改,
 * 如不需要,请自定义
 *
 * @type {{save: Server.save, update: Server.update, del: Server.del}}
 ******************************************************/
var AbCRUD = {
    /**
     * 自动_init下所有的方法
     * @param model
     */
    init2:function(model){
        var obj = model._init ;
        for(var field in obj) {
            var inObj = obj[field] ;
            for(var fuc in inObj) {
                eval('inObj.' + fuc + '()');
            }
        }

    },
    //初始化模块
    init: function (CONSTANT) {
        var grid = function () {
            $('#' + CONSTANT._ID._GRID).datagrid({
                rownumbers: true,
                singleSelect: false,
                pageSize: 10,
                pagination: true,
                loadMsg: '加载中...',
                fitColumns: true,
                showHeader: true,
                showFooter: true,
                fit: true,
                idField: CONSTANT._GRID._IDFIELD,
                columns: CONSTANT._GRID._COLUMNS,
                loader: function (param, success, error) {
                    param.page = param.page === 0 ? 1 : param.page ;
                    var otherPar = $('#' + CONSTANT._ID._QUERYFORM).serializeObject();
                    for (var item in otherPar) {
                        param[item] = otherPar[item];
                    }
                    $.ajax({
                        url: CONSTANT._URL._LIST,
                        type: 'post',
                        data: param,
                        dataType: 'json',
                        success: function (data) {
                            if (data.success) {
                                success(data.data);
                            }
                        }
                    });
                }
            });
        };
        var search = function () {
            $('#' + CONSTANT._ID._BTNSEARCH).linkbutton({
                iconCls: "icon-search",
                lain: true,
                size: 'small',
                plain:true,
                onClick: function () {
                    $('#' + CONSTANT._ID._GRID).datagrid('reload');
                }
            });
        };
        var reset = function () {
            $('#' + CONSTANT._ID._BTNRESET).linkbutton({
                iconCls: "icon-clear",
                plain:true,
                lain: true,
                size: 'small',
                onClick: function () {
                    $('#' + CONSTANT._ID._QUERYFORM).form('reset');
                }
            });
        };
        var dialog = function () {
            $('#' + CONSTANT._ID._FORMDIALOG).dialog({
                closed: true,
                iconCls: 'icon-save',
                closable: false,
                shadow: true,
                inline: false,
                modal: true
            });
        };
        var btnAdd = function() {
            $('#' + CONSTANT._ID._BTNADD).linkbutton({
                iconCls: "icon-add",
                plain:true,
                lain: true,
                size: 'small',
                onClick: function () {
                    $('#' + CONSTANT._ID._FILLFORM).form('reset');
                    $('#' + CONSTANT._ID._FORMDIALOG).dialog({
                        closed: false,
                        title: "新增",
                        buttons: [{
                            text: '确定',
                            iconCls: 'icon-ok',
                            handler: function () {
                                var valid = $('#' + CONSTANT._ID._FILLFORM).form('validate');
                                if (!valid) return;
                                $('#' + CONSTANT._ID._FILLFORM).form('submit', {
                                    url: CONSTANT._URL._SAVE,
                                    success: function (data) {
                                        data = jQuery.parseJSON(data);
                                        if (data.success) {
                                            $.messager.show({
                                                title: "信息提示",
                                                msg: data.msg,
                                                timeout: 1500,
                                                showType: 'fade'
                                            });
                                            $('#' + CONSTANT._ID._FORMDIALOG).dialog('close');
                                            $('#' + CONSTANT._ID._GRID).datagrid('reload');
                                        }
                                        else {
                                            $.messager.alert('信息提示', data.msg, 'error');
                                        }
                                    }
                                });
                            }
                        }, {
                            text: '取消',
                            iconCls: 'icon-cancel',
                            handler: function () {
                                $('#' + CONSTANT._ID._FORMDIALOG).dialog('close');
                            }
                        }]
                    });
                }
            });
        };
        var btnDel = function () {
            $('#' + CONSTANT._ID._BTNDEL).linkbutton({
                iconCls: "icon-remove",
                plain:true,
                lain: true,
                size: 'small',
                onClick: function () {
                    if($('#' + CONSTANT._ID._GRID).datagrid('getSelections').length === 0) {
                        $.messager.alert('信息提示', "请选择一条数据进行操作.", 'error');
                        return ;
                    }
                    $.messager.confirm('信息提示', '确定要删除该记录？', function (result) {
                        if (result) {
                            var items = $('#' + CONSTANT._ID._GRID).datagrid('getSelections');
                            var ids = [];
                            $(items).each(function () {
                                ids.push(this[CONSTANT._GRID._IDFIELD]);
                            });
                            var param = {};
                            param[CONSTANT._GRID._IDFIELD] = ids;
                            $.ajax({
                                url: CONSTANT._URL._DEL,
                                data: param,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.success) {
                                        $.messager.show({
                                            title: "信息提示",
                                            msg: data.msg,
                                            timeout: 1500,
                                            showType: 'fade'
                                        });
                                        $('#' + CONSTANT._ID._FORMDIALOG).dialog('close');
                                        $('#' + CONSTANT._ID._GRID).datagrid('reload');
                                    }
                                    else {
                                        $.messager.alert('信息提示', data.msg, 'error');
                                    }
                                }
                            });
                        }
                    });
                }
            });
        };
        /*var linkEdit = function() {
            $('#' + CONSTANT._ID._LINKEDIT).click(function(){

            }) ;
        }*/
        //invoke
        grid();
        search();
        reset();
        dialog();
        btnAdd() ;
        btnDel() ;
    },
    edit: function (id) {
        $('#' + CONSTANT._ID._FILLFORM).form('reset');
        var param = {};
        param[CONSTANT._GRID._IDFIELD] = id;
        $.ajax({
            url: CONSTANT._URL._EDIT,
            data: param,
            success: function (data) {
                if (data.success) {
                    $('#' + CONSTANT._ID._FILLFORM).form('load', data.data)
                }
                else {
                    $.messager.alert('信息提示', data.msg, 'error');
                }
            }
        });
        $('#' + CONSTANT._ID._FORMDIALOG).dialog({
            closed: false,
            title: "修改",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function () {
                    var valid = $('#' + CONSTANT._ID._FILLFORM).form('validate');
                    if (!valid) return;
                    $('#' + CONSTANT._ID._FILLFORM).form('submit', {
                        url: CONSTANT._URL._UPDATE,
                        success: function (data) {
                            data = jQuery.parseJSON(data);
                            if (data.success) {
                                $.messager.show({
                                    title: "信息提示",
                                    msg: data.msg,
                                    timeout: 1500,
                                    showType: 'fade'
                                });
                                $('#' + CONSTANT._ID._FORMDIALOG).dialog('close');
                                $('#' + CONSTANT._ID._GRID).datagrid('reload');
                            }
                            else {
                                $.messager.alert('信息提示', data.msg, 'error');
                            }
                        }
                    });
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#' + CONSTANT._ID._FORMDIALOG).dialog('close');
                }
            }]
        });
    }
} ;

