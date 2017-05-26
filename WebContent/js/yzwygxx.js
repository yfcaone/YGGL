Ext.onReady(function () {


    var columns = [Ext.create('Ext.grid.RowNumberer',
        {
            text: '序号',
            align: 'center',
            width: "5%"
        }), {
        header: '姓名',
        width: "10%",
        dataIndex: 'ACCOUNT',
        align: 'center'
    }, {
        header: '项目名称',
        width: "25%",
        dataIndex: 'AFFAIR',
        align: 'center'
    }, {
        header: '地点',
        width: "10%",
        dataIndex: 'LACALE',
        align: 'center'
    }, {
        header: '开始时间',
        width: "15%",
        dataIndex: 'STARTTIME',
        align: 'center'
    }, {
        header: '结束时间',
        width: "15%",
        dataIndex: 'ENDTIME',
        align: 'center'
    }, {
        header: '项目经理',
        width: "10%",
        dataIndex: 'MANAGER',
        align: 'center'
    }, {
        header: '是否完成',
        width: "10%",
        dataIndex: 'ISCOMPLETE',
        align: 'center',
        flex: 1
    }];


    var store = new Ext.data.ArrayStore({

        id: 'selected',
        fields: [{
            name: 'ACCOUNT'
        }, {
            name: 'AFFAIR'
        }, {
            name: 'LACALE'
        }, {
            name: 'STARTTIME'
        }, {
            name: 'ENDTIME'
        }, {
            name: 'DETAIL'
        }, {
            name: 'MANAGER'
        }, {
            name: 'ISDEAL'
        }, {
            name: 'SCORE'
        }, {
            name: 'ISCOMPLETE'
        }],
        proxy: {
            type: 'ajax',
            url: 'getZwygInfo.action',
            reader: {
                type: 'json',
                root: 'list'
            }
        },
        listeners: {

            'beforeload': function (store) {
                var params = {
                    // codeDesc: Ext.getCmp('code_desc').getValue()
                };
                Ext.apply(store.proxy.extraParams, params);
            }
        },

        pageSize: 10,
        autoLoad: true
    });


    var grid = new Ext.grid.GridPanel({
        renderTo: 'grid',
        store: store,
        id: 'gridd',
        columnLines: true,
        columns: columns,
        stripeRows: true,
        loadMask: true,
        listeners: {
            dblclick: {
                element: 'body',
                fn: function () {
                    var selectedI = Ext.getCmp("gridd").getSelectionModel();
                    if (selectedI.selected.items && selectedI.selected.items.length > 0) {
                        var rec = selectedI.selected.items[0]["data"];
                        win = Ext.create('widget.window', {
                            titleCollapse: true,
                            closable: false,
                            resizable: false,//是否可以改变大小
                            draggable: false,//是否可以拖动
                            width: 1000,
                            height: 500,
                            border: 0,
                            title: rec['ACCOUNT'] + '的位置',
                            hidden: true,
                            modal: true,
                            iconCls: "Applicationformedit",
                            x: 80,
                            y: 20,
                            bodyStyle: "background:#ffffff",
                            buttons: [{
                                xtype: "button",
                                text: "返回",
                                handler: function () {
                                    win.close();
                                }
                            }],
                            items: [{
                                width: '100%',

                                html: '<iframe id=cenIF src=map.action?account=' + rec["ACCOUNT"] + '  scrolling=yes height=600 width=100%></iframe>'
                            }]
                        });
                        win.show(this);
                    } else {
                        Ext.Msg.alert(
                            "提示", "请选择一行记录进行查看")
                    }
                }
            }
        },

        bbar: new Ext.PagingToolbar({
            pageSize: 3,
            store: store,
            displayInfo: true,
            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',
            emptyMsg: "没有数据"
        })
    });
});
