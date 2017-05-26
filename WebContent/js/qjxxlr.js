Ext.onReady(function () {

    var store = new Ext.data.ArrayStore({

        id: 'selected',
        fields: [{
            name: 'LID'
        }, {
            name: 'P_NUMBER'
        },{
            name: 'LOG_NUMBER'
        }, {
            name: 'LNAME'
        }, {
            name: 'START_TIME'
        }, {
            name: 'END_TIME'
        }, {
            name: 'DAYS'
        }],
        proxy: {
            type: 'ajax',
            url: 'getLeaveInfo.action',
            reader: {
                type: 'json',
                totalProperty : 'totalCount',
                root: 'list'
            }
        },
        listeners: {

            'beforeload': function (store) {
                var params = {
                    P_NUMBER: Ext.getCmp('P_NUMBER').getValue()
                };
                Ext.apply(store.proxy.extraParams, params);
            }
        },
        pageSize: 13,
        autoLoad: true
    });

    /**
     * 项目编号
     */
    var xmbh_store=Ext.create('Ext.data.Store',{
        storeId:'xmbhStore',
        fields:['PID','P_NUMBER'],
        proxy: {
            type: 'ajax',
            url: 'getXmbh.action'
        },
        autoLoad: true
    });

    /**
     * 工号
     */
    var gh_store=Ext.create('Ext.data.Store',{
        storeId:'dqtzStore',
        fields:['TNAME','JOB_NUMBER'],
        proxy: {
            type: 'ajax',
            url: 'getGhData.action'
        },
        listeners: {
            'beforeload': function (store, op, options) {
                var params = {
                    p_number:Ext.getCmp('p_number').getValue()
                };
                Ext.apply(store.proxy.extraParams, params);
            }
        } ,
        autoLoad: false
    });
    
    Ext.create('Ext.form.Panel', {
        id: 'ccluFrom',
        bodyPadding: 10,
        renderTo: Ext.getBody(),
        border: 0,
        items: [{
            xtype: 'container',
            anchor: '100%',
            margin: '10 0 0 0',
            layout: 'hbox',
            border: 0,
            items: [{
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
                items: [{
                    xtype:'combobox',
                    fieldLabel: '项目编号',
                    id:'p_number',
                    labelWidth: 60,
                    store:xmbh_store,
                    valueField:'PID',
                    displayField:'P_NUMBER',
                    emptyText : '--请选择项目编号--',
                    name: 'p_number',
                    editable : false,
                    anchor:'96%',
                    listeners:{
                        scope: this,
                        'select': function(){
                            Ext.getCmp("log_number").clearValue();
                            gh_store.load();}
                    }
                }]
            },{
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
                items: [{
                    xtype:'combobox',
                    fieldLabel: '工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号',
                    id:'log_number',
                    labelWidth: 60,
                    store:gh_store,
                    valueField:'TNAME',
                    displayField:'JOB_NUMBER',
                    emptyText : '--请选择工号--',
                    name: 'log_number',
                    editable : false,
                    anchor:'96%'

                }]
            }, {
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
                items: [{
                    xtype: 'datefield',
                    fieldLabel: '开始时间',
                    labelWidth: 60,
                    //afterLabelTextTpl: required,
                    allowBlank: false,
                    name: 'start_date',
                    id: 'start_date',
                    format: 'Y-m-d',
                    msgTarget: 'side',
                    anchor: '96%'
                }]
            }, {
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
                items: [{
                    xtype: 'datefield',
                    fieldLabel: '结束时间',
                    labelWidth: 60,
                    //afterLabelTextTpl: required,
                    allowBlank: false,
                    name: 'end_date',
                    id: 'end_date',
                    format: 'Y-m-d',
                    msgTarget: 'side',
                    anchor: '96%'
                }]
            }]
        }, {
            xtype: 'container',
            anchor: '100%',
            layout: 'hbox',
            margin: '10 0 0 0',
            border: 0,
            items: [{
                xtype: 'container',
                flex: .84,
                layout: 'anchor',
                items: [{
                    xtype: 'textareafield',
                    fieldLabel: '请假事由',
                    id: 'leave_cause',
                    labelWidth: 60,
                    //store:specialtyStore,
                    name: 'leave_cause',
                    editable: false,
                    anchor: '98.5%'
                }]
            }]
        }, {
            xtype: 'container',
            anchor: '6%',
            layout: 'hbox',
            margin: '10 0 10 0',
            border: 0,
            items: [{
                xtype: 'container',
                flex: .14,
                layout: 'anchor',
                items: [{
                    xtype: 'button',
                    text: '确&nbsp;&nbsp;定',
                    id: 'create',
                    name: 'create',
                    anchor: '96%',
                    handler: function () {
                        var log_number = Ext.getCmp('log_number').getValue();
                        var p_number = Ext.getCmp('p_number').getValue();
                        var start_date = Ext.getCmp('start_date').getValue();
                        var end_date = Ext.getCmp('end_date').getValue();
                        var leave_cause = Ext.getCmp('leave_cause').getValue();
                        Ext.Ajax.request({
                            url: 'addLeaveInfo.action',
                            params: {
                                log_number: log_number,
                                start_date: start_date,
                                end_date: end_date,
                                leave_cause: leave_cause,
                                p_number:p_number
                            },
                            success: function () {
                                var store2 = Ext.getStore('selected');
                                store2.reload();
                                Ext.Msg.alert("提示", "提交成功。");
                            }, failure: function () {
                                Ext.Msg.alert("错误", "请录入相应信息。");
                            }
                        })
                    }
                }]
            }]
        },{
            xtype: "form",
            border: false,
            id: "gridform",
            items: [{
                xtype: 'grid',
                columnLines: true,
                id: "gridd",
                width: "100%",
                store: store,
                tbar: ['->', {
                    xtype : 'textfield',
                    name : 'P_NUMBER',
                    id : 'P_NUMBER',
                    margin:'0 10 0 0',
                    labelWidth : 60,
                    fieldLabel : '项目编号'
                }, {
                    xtype : 'button',
                    text : '查询',
                    iconCls : "Bulletmagnify",
                    handler : function() {
                        var store = Ext.getStore("selected");
                        store.reload();
                    }
                },{
                    xtype: 'button',
                    text: '导出',
                    iconCls: "Applicationgo",
                    handler: function () {
                    }
                }],
                columns: [{
                    header: '序号',
                    width: '10%',
                    menuDisabled: true,// 去掉表格下拉排序
                    dataIndex: 'LID',
                    align: "center"
                }, {
                    header: '项目编号',
                    dataIndex: 'P_NUMBER',
                    menuDisabled: true,
                    width: '15%',
                    align: "center"
                },{
                    header: '工号',
                    dataIndex: 'LOG_NUMBER',
                    menuDisabled: true,
                    width: '15%',
                    align: "center"
                }, {
                    header: '姓名',
                    dataIndex: 'LNAME',
                    menuDisabled: true,
                    width: '15%',
                    align: "center"
                },
                    // 可设置是否为该列进行排序
                    {
                        header: '开始时间',
                        dataIndex: 'START_TIME',
                        width: '15%',
                        menuDisabled: true,
                        align: "center",
                        format: 'Y-m-d'
                    },
                    {
                        header: '结束时间',
                        dataIndex: 'END_TIME',
                        width: '15%',
                        menuDisabled: true,
                        align: "center",
                        format: 'Y-m-d'
                    }, {
                        header: '天数',
                        dataIndex: 'DAYS',
                        width: '15%',
                        menuDisabled: true,
                        align: "center",
                        flex: 1
                    }],
                bbar : new Ext.PagingToolbar({
                    store : store,
                    displayInfo : true,
                    displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
                    emptyMsg : "没有数据"
                })
            }]
        }]
    })
});