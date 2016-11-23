//easyui 公用配置
var EsCommonOptions = {
    /**
     * 扩展src对象到obj上(继承)
     * @param src
     * @param obj
     * @returns {*}
     */
    extendTo:function(parent,obj) {
        if(null === parent) {
            return obj ;
        }
        for(var item in parent) {
            obj[item] = parent[item] ;
        }
        return obj ;
    },
    helper:{
        grid:{
            //getloader
            getLoader:function(queryFormId,reqUrl) {
                var loader = function(param,success,error) {
                    var otherPar = $('#' + queryFormId).serializeObject();
                    for (var item in otherPar) {
                        param[item] = otherPar[item];
                    }
                    $.ajax({
                        url: reqUrl,
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
                return loader ;
            }
        }
    },
    OPTIONS:{
        //linkbutton
        BTN:{
            plain:true,
            size:'small'
        },
        //datagrid
        GRID:{
            rownumbers:true,
            singleSelect:false,
            pageSize:10,
            pagination:true,
            loadMsg:'加载中...',
            fitColumns:true,
            showHeader:true,
            showFooter:true,
            fit:true
        },
        //dialog
        DIALOG:{
            collapsible:false,
            minimizable:false,
            maximizable:false,
            resizable:false,
            resizable:false,
            shadow:true,
            inline:false,
            modal:true
        },
        //textbox
        TEXTBOX:{
            width:150
        },
        //combobox
        COMBOBOX:{
            width:150,
            panelHeight:'auto',
            panelMaxHeight:200,
            selectOnNavigation:true,
            editable:false
        }
    }

};