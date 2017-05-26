Ext.onReady(function() {
    var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
        clicksToEdit: 1,
        id:'cellEditing'
    });
    var combostore = new Ext.data.ArrayStore({
        fields: ['code_desc', 'code_desc'],
        data: [['完成', '完成'], ['未完成', '未完成']]
    });

    var columns = [ Ext.create('Ext.grid.RowNumberer',
        {text : '序号',
            align:'center',
            width:"5%"
        }),{
        header : '姓名',
        width : "10%",
        dataIndex : 'ACCOUNT',
        align : 'center'
    }, {
        header : '项目名称',
        width : "25%",
        dataIndex : 'AFFAIR',
        align : 'center'
    }, {
        header : '地点',
        width : "10%",
        dataIndex : 'LACALE',
        align : 'center'
    }, {
        header : '开始时间',
        width : "15%",
        dataIndex : 'STARTTIME',
        align : 'center'
    }, {
        header : '结束时间',
        width : "15%",
        dataIndex : 'ENDTIME',
        align : 'center'
    }, {
        header : '项目经理',
        width : "10%",
        dataIndex : 'MANAGER',
        align : 'center'
    }, {
        header : '是否完成',
        width : "10%",
        dataIndex : 'ISCOMPLETE',
        editor: {
            xtype:'combobox',
            store:combostore,
            valueField:'code_desc',
            displayField:'code_desc',
            allowBlank: false,
            editable :false
        },
        align : 'center',
        flex : 1
    } ];
    
    var store = new Ext.data.ArrayStore({
        id : 'selected',
        fields : [{
            name : 'ID'
        },{
            name : 'ACCOUNT'
        }, {
            name : 'AFFAIR'
        }, {
            name : 'LACALE'
        }, {
            name : 'STARTTIME'
        }, {
            name : 'ENDTIME'
        }, {
            name : 'DETAIL'
        }, {
            name : 'MANAGER'
        }, {
            name : 'ISDEAL'
        }, {
            name : 'SCORE'
        } , {
            name : 'ISCOMPLETE'
        } ],
        proxy : {
            type : 'ajax',
            url : 'getSelfInfo.action',
            reader : {
                type : 'json',
                root : 'list'
            }
        },
        listeners:{
            'beforeload': function (store) {
                var params = {
                };
                Ext.apply(store.proxy.extraParams, params);
            }
        }
    });
    store.load({
        params : {
            start : 0,
            limit : 25
        }
    });
    var grid = new Ext.grid.GridPanel({
        renderTo : 'grid',
        store : store,
        id : 'gridd',
        plugins: [cellEditing],
        columnLines: true,
        columns : columns,
        stripeRows : true,
        loadMask : true,
        listeners: {
            dblclick:{
                element:'body',
                fn:function(){
                    var selectedI = Ext.getCmp("gridd").getSelectionModel();
                    if(selectedI.selected.items && selectedI.selected.items.length>0){
                        var rec = selectedI.selected.items[0]["data"];
                        if(rec['ISCOMPLETE']=="未完成"){
                            win = Ext.create('widget.window',{
                                titleCollapse :true,
                                closable:false,
                                resizable:false,//是否可以改变大小
                                draggable:false,//是否可以拖动
                                width: 1000,
                                height: 500,
                                border:0,
                                title : rec['ACCOUNT']+'的位置',
                                hidden :true,
                                modal:true,
                                iconCls: "Applicationformedit",
                                x: 80,
                                y: 20,
                                bodyStyle: "background:#ffffff",
                                buttons : [ {
                                    xtype : "button",
                                    text : "返回",
                                    handler : function() {
                                        win.close();
                                    }
                                }],
                                items: [{
                                    width:'100%',
                                    html:'<iframe id=cenIF src=map.action?account='+rec["ACCOUNT"]+'  scrolling=yes height=600 width=100%></iframe>'
                                }]
                            });
                            win.show(this);
                        }
                    }else{
                        Ext.Msg.alert(
                            "提示", "请选择一行记录进行查看")
                    }
                }
            }
        },
        bbar : new Ext.PagingToolbar({
            pageSize : 3,
            store : store,
            displayInfo : true,
            displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
            emptyMsg : "没有数据"
        }),
        tbar:[{
            xtype : "button",
            text : "新建",
            iconCls : "Add",
            handler : function() {
                var win1 = Ext.create('widget.window', {
                    title : '员工在外信息',
                    closable : false,
                    resizable:false,//是否可以改变大小
                    draggable:false,//是否可以拖动
                    width : 500,
                    height : 280,
                    bodyPadding : 10,
                    iconCls : "Applicationformedit",
                    bodyStyle : "background:#ffffff",
                    constrain : true,
                    buttons : [{
                        xtype : "button",
                        text : "保存",
                        handler : function() {
                            var form = Ext.getCmp("addnr").getForm();
                            if (form.isValid()) {
                                var formData = form.getValues();
                                $.ajax({
                                    async : false,
                                    type : "post",
                                    url : "addData.action",
                                    data : JSON.stringify(formData),
                                    dataType : "json",
                                    contentType : "application/json; charset=UTF-8",
                                    success : function() {
                                        Ext.Msg.alert("提示", "数据保存成功",
                                            function() {
                                                var store = Ext.getStore("selected");
                                                store.reload();
                                                win1.close();
                                            });
                                    }
                                });
                            }
                        }
                    }, {
                        xtype : "button",
                        text : "取消",
                        handler : function() {
                            win1.close();
                        }
                    }],
                    modal : true,
                    items : [{
                        xtype : 'form',
                        id : 'addnr',
                        layout : 'anchor',
                        fieldDefaults : {
                            labelAlign : 'left',
                            labelWidth : 65,
                            anchor : '100%'
                        },
                        border : 0,
                        items : [{
                            xtype : 'textfield',
                            margin:'20 0 7 0 ',
                            fieldLabel : '姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;',
                            regex:/^[\u4E00-\u9FA5]/,
                            value:username,
                            readOnly:true,
                            fieldStyle:'color:#999999',
                            id : 'account',
                            name : 'account',
                            allowBlank: false
                        },{
                            xtype : 'textfield',
                            fieldLabel : '项目名称',
                            blankText:"项目名称不能为空，请填写！",
                            id : 'affair',
                            name : 'affair',
                            allowBlank: false
                        },{
                            xtype : 'textfield',
                            fieldLabel : '地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点&nbsp;',
                            blankText:"地点不能为空，请填写！",
                            id : 'lacale',
                            name : 'lacale',
                            allowBlank: false
                        },{
                            xtype : 'datefield',
                            fieldLabel : '开始时间',
                            name : 'starttime',
                            id:'starttime',
                            format : 'Y-m-d',
                            allowBlank: false
                        }, {
                            xtype : 'datefield',
                            fieldLabel : '结束时间',
                            name : 'endtime',
                            id : 'endtime',
                            format : 'Y-m-d',
                            allowBlank: false
                        },{
                            xtype : 'textfield',
                            fieldLabel : '项目经理',
                            blankText:"项目经理不能为空，请填写！",
                            id : 'manager',
                            regex:/^[\u4E00-\u9FA5]/,
                            regexText:"请正确输入项目经理名称！",
                            name : 'manager',
                            allowBlank: false
                        }]
                    }]
                });
                win1.show(this);
            }
        },{
            xtype:'button',
            text: "编辑",
            iconCls: "Applicationformedit",
            handler: function () {
                var selectedI = Ext.getCmp("gridd").getSelectionModel();
                if (selectedI.selected.items && selectedI.selected.items.length > 0) {
                    var rec = selectedI.selected.items[0]["data"];
                    if(rec['ISCOMPLETE']=='未完成'){
                        //弹窗显示信息
                        var win=Ext.create("widget.window",{
                            title : '编辑日志',
                            closable:false,
                            resizable:false,//是否可以改变大小
                            draggable:false,//是否可以拖动
                            iconCls: "Applicationformedit",
                            id:'lookupWin',
                            width : 700,
                            height : 400,
                            modal:true,
                            bodyStyle : "background:#ffffff",
                            x : 200,
                            y : 80,
                            buttons : [{
                                xtype : "button",
                                text : "提交",
                                handler : function() {
                                    var form = Ext.getCmp("pcInfo").getForm();
                                    if (form.isValid()) {
                                        var formData = form.getValues();
                                        $.ajax({
                                            async : false,
                                            type : "post",
                                            url : "addLogInfo.action?account="+rec["ACCOUNT"]+"&affair="+rec["AFFAIR"]+"",
                                            data : JSON.stringify(formData),
                                            dataType : "json",
                                            contentType : "application/json; charset=UTF-8",
                                            success : function() {
                                                Ext.Msg.alert("提示", "日志添加成功！",function() {
                                                    win.close();
                                                });
                                            }
                                        });
                                    }
                                }
                            }, {
                                xtype : "button",
                                text : "取消",
                                handler : function() {
                                    win.close();
                                }
                            }],
                            items : [ {
                                xtype : "form",
                                id : "pcInfo",
                                closeable:true,
                                overflowY:'auto',
                                layout: {
                                    type: 'vbox',
                                    align: 'stretch'
                                },
                                border : 0,
                                items : [{
                                    xtype: 'htmleditor',
                                    border:0,
                                    id:'log',
                                    name:'log',
                                    height:340,
                                    enableAlignments: true,//是否启用对齐按钮，包括左中右三个按钮 
                                    enableColors: true,//是否启用前景色背景色按钮，默认为true
                                    enableFont: true,//是否启用字体选择按钮 默认为true
                                    enableFontSize: true,//是否启用字体加大缩小按钮 
                                    enableFormat: true,//是否启用加粗斜体下划线按钮
                                    enableLists: true,//是否启用列表按钮
                                    enableSourceEdit: true,//是否启用代码编辑按钮
                                    fontFamilies: ["宋体", "隶书", "黑体"],
                                    width:'100%'
                                }]
                            }]
                        });
                        win.show(this);
                    }else{
                        Ext.Msg.alert("提示","该项目已完成，不可进行编辑！");
                    }
                }else{
                    Ext.Msg.alert("提示","请选择一条记录进行评测！");
                }

            }
        },{
            xtype:'button',
            text: "查看",
            iconCls: "Applicationformmagnify",
            handler: function () {
                var selectedI = Ext.getCmp("gridd").getSelectionModel();
                if(selectedI.selected.items && selectedI.selected.items.length>0){
                    var rec = selectedI.selected.items[0]["data"];
                    window.location.href='logContent.action?account='+rec["ACCOUNT"]+'&affair='+rec["AFFAIR"]+' '
                }else{
                    Ext.Msg.alert(
                        "提示", "请选择一行记录进行查看")
                }
            }
        },{
            xtype:'button',
            text: "保存",
            iconCls: "Applicationformmagnify",
            handler: function () {
                var selectedI = Ext.getCmp("gridd").getSelectionModel();
                if(selectedI.selected.items && selectedI.selected.items.length>0){
                    var rec = selectedI.selected.items[0]["data"];
                    Ext.Ajax.request({
                        url:'updateInfo.action',
                        params:{
                            ID:rec['ID'],
                            ACCOUNT:rec['ACCOUNT'],
                            AFFAIR:rec['AFFAIR'],
                            ISCOMPLETE:rec['ISCOMPLETE']
                        },
                        success: function() {
                            Ext.Msg.alert('提示','保存成功！');
                        },
                        failure: function() {
                            Ext.Msg.alert('错误', respText.error);
                        }
                    });
                }else{
                    Ext.Msg.alert(
                        "提示", "请选择一行记录进行保存")
                }
            }
        }]
    });
});
