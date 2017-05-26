Ext.onReady(function(){

    var store = new Ext.data.ArrayStore({
        id : 'selected',
        fields : [ {
            name : 'TID'
        }, {
            name : 'P_NUMBER'
        }, {
            name : 'TAFFAIR'
        }, {
            name : 'JOB_NUMBER'
        }, {
            name : 'TNAME'
        }, {
            name : 'DPOST'
        }, {
            name : 'TDATE'
        }],
        proxy : {
            type : 'ajax',
            url : 'getTravelInfo.action',
            reader : {
                type : 'json',
                totalProperty : 'totalCount',
                root : 'list'
            }
        },
        listeners:{
            'beforeload': function (store) {
                var params = {
                    P_NUMBER: Ext.getCmp('P_NUMBER').getValue()
                };
                Ext.apply(store.proxy.extraParams, params);
            }
        },
        pageSize : 16,
        autoLoad : true
    });

    /**
     * 城市等级
     */
    var city_grade=Ext.create('Ext.data.Store',{
        storeId:'specialtyStore',
        fields:['T_CLEVEL_CODE','T_CLEVEL'],
        proxy: {
            type: 'ajax',
            url: 'getCityGrade.action'
        },
        autoLoad: true
    });
    /**
     * 城市
     */
    var city=Ext.create('Ext.data.Store',{
        storeId:'dqtzStore',
        fields:['CI_CODE','CITY'],
        proxy: {
            type: 'ajax',
            url: 'getCity.action'
        },
        listeners: {
            'beforeload': function (store) {
                var params = {
                    city:Ext.getCmp('city_grade').getValue()
                };
                Ext.apply(store.proxy.extraParams, params);
            }
        } ,
        autoLoad: false
    });

    /**
     *部门
     */
    var department=Ext.create('Ext.data.Store',{
        storeId:'specialtyStore',
        fields:['DEPARTMENT_CODE','DEPARTMENT'],
        proxy: {
            type: 'ajax',
            url: 'getDepartment.action'
        },
        autoLoad: true
    });
    /**
     * 职位
     */
    var post_name=Ext.create('Ext.data.Store',{
        storeId:'dqtzStore',
        fields:['DPOST_CODE','DPOST'],
        proxy: {
            type: 'ajax',
            url: 'getPost.action'
        },
        listeners: {
            'beforeload': function (store) {
                var params = {
                    department:Ext.getCmp('department_name').getValue()
                };
                Ext.apply(store.proxy.extraParams, params);
            }
        } ,
        autoLoad: false
    });

    /**
     * 工号
     */
    var job_number=Ext.create('Ext.data.Store',{
        storeId:'dqtzStore',
        fields:['JOB_NUMBER','JOB_NUMBER'],
        proxy: {
            type: 'ajax',
            url: 'getNumber.action'
        },
        listeners: {
            'beforeload': function (store ) {
                var params = {
                    post_name:Ext.getCmp('post_name').getValue()
                };
                Ext.apply(store.proxy.extraParams, params);
            }
        } ,
        autoLoad: false
    });
    Ext.create('Ext.form.Panel',{
        id:'ccluFrom',
        bodyPadding:10,
        renderTo:Ext.getBody(),
        border:0,
        items:[{
            xtype:'container',
            anchor:'100%',
            layout:'hbox',
            border:0,
            items:[{
                xtype:'container',
                flex:1,
                layout:'anchor',
                items:[{
                    xtype:'textfield',
                    fieldLabel: '项目名称',
                    id:'affair_name',
                    labelWidth: 60,
                    name: 'affair_name',
                    editable : false,
                    anchor:'96%'
                }]
            },{
                xtype:'container',
                flex:1,
                layout:'anchor',
                items:[{
                    xtype:'combobox',
                    fieldLabel: '城市等级',
                    id:'city_grade',
                    labelWidth: 60,
                    store:city_grade,
                    valueField:'T_CLEVEL_CODE',
                    displayField:'T_CLEVEL',
                    emptyText : '--请选择城市等级--',
                    name: 'city_grade',
                    editable : false,
                    anchor:'96%',
                    listeners:{
                        scope: this,
                        'select': function(){
                            Ext.getCmp("city").clearValue();
                            city.load();}
                    }
                }]
            },{
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
                items: [{
                    xtype:'combobox',
                    fieldLabel: '城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市',
                    id:'city',
                    labelWidth: 60,
                    store:city,
                    valueField:'CI_CODE',
                    displayField:'CITY',
                    emptyText : '--请选择城市--',
                    name: 'city',
                    editable : false,
                    anchor:'96%'
                }]
            },{
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
                items: [{
                    xtype:'datefield',
                    fieldLabel: '出发时间',
                    labelWidth: 60,
                    //afterLabelTextTpl: required,
                    allowBlank: false,
                    name: 'go_date',
                    id: 'go_date',
                    format:'Y-m-d',
                    msgTarget: 'side',
                    anchor:'96%'
                }]
            }]
        },{
            xtype:'container',
            anchor:'30%',
            flex: 0.2,
            margin:'10 10 0 0',
            layout:'hbox',
            border:0,
            items:[{
                xtype:'button',
                text:'确&nbsp;&nbsp;定',
                id:'addCreate',
                width:60,
                name: 'create',
                margin:'0 10 0 0',
                anchor:'96%',
                handler:function(){
                    var add_city = Ext.getCmp('city').getValue();
                    var add_affair_name = Ext.getCmp('affair_name').getValue();
                    var add_go_date = Ext.getCmp('go_date').getValue();
                    Ext.Ajax.request({
                        url:'addCreateTravelInfo.action',
                        params:{
                            add_city:add_city,
                            add_affair_name:add_affair_name,
                            add_go_date:add_go_date
                        },
                        success:function(response){
                            var result = Ext.decode(response.responseText);
                            $.each(result,function(i,comm){
                                console.log(comm);
                                P_NUMBER = comm['P_NUMBER'];
                            });
                            Ext.getCmp('p_number').setValue(P_NUMBER);
                            Ext.Msg.alert("提示","创建成功。");
                        },failure:function(){
                            Ext.Msg.alert("错误","创建失败。");
                        }
                    })
                }
            }]
        },{
            xtype:'container',
            anchor:'25%',
            margin:'10 0 0 0',
            layout:'hbox',
            border:0,
            items:[{
                xtype: 'container',
                flex: 0.8,
                layout: 'anchor',
                items: [{
                    xtype:'textfield',
                    fieldLabel: '项目编号',
                    readOnly:true,
                    fieldStyle:'color:#999999',
                    id:'p_number',
                    labelWidth: 60,
                    name: 'p_number',
                    editable : false,
                    anchor:'96%'
                }]
            }]
        },{
            xtype:'container',
            anchor:'80%',
            margin:'10 0 0 0',
            layout:'hbox',
            border:0,
            items:[{
                xtype:'container',
                flex:1,
                layout:'anchor',
                items:[{
                    xtype:'combobox',
                    fieldLabel: '部门名称',
                    id:'department_name',
                    labelWidth: 60,
                    store:department,
                    valueField:'DEPARTMENT_CODE',
                    displayField:'DEPARTMENT',
                    emptyText : '--请选择部门--',
                    name: 'department_name',
                    editable : false,
                    anchor:'96%',
                    listeners:{
                        scope: this,
                        'select': function(){
                            Ext.getCmp("post_name").clearValue();
                            post_name.load();}
                    }
                }]
            },{
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
                items: [{
                    xtype:'combobox',
                    fieldLabel: '职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务',
                    id:'post_name',
                    labelWidth: 60,
                    store:post_name,
                    valueField:'DPOST_CODE',
                    displayField:'DPOST',
                    emptyText : '--请选择职务--',
                    name: 'post_name',
                    editable : false,
                    anchor:'96%',
                    listeners:{
                        scope: this,
                        'select': function(){
                            Ext.getCmp("job_number").clearValue();
                            job_number.load();}
                    }
                }]
            },{
                xtype:'container',
                flex:1,
                layout:'anchor',
                items:[{
                    xtype:'combobox',
                    fieldLabel: '工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号',
                    store:job_number,
                    valueField:'JOB_NUMBER',
                    displayField:'JOB_NUMBER',
                    emptyText : '--请选择工号--',
                    id:'job_number',
                    labelWidth: 60,
                    name: 'job_number',
                    editable : false,
                    anchor:'96%'
                }]
            },{
                xtype: 'container',
                flex: .20,
                layout: 'anchor',
                items: [{
                    xtype:'button',
                    text:'确&nbsp;&nbsp;定',
                    id:'create',
                    labelWidth: 60,
                    name: 'create',
                    anchor:'96%',
                    handler:function(){
                        var city = Ext.getCmp('city').getValue();
                        var p_number = Ext.getCmp('p_number').getValue();
                        var affair_name = Ext.getCmp('affair_name').getValue();
                        var go_date = Ext.getCmp('go_date').getValue();
                        var post_name = Ext.getCmp('post_name').getValue();
                        var job_number = Ext.getCmp('job_number').getValue();
                        Ext.Ajax.request({
                            url:'addTravelInfo.action',
                            params:{
                                city:city,
                                affair_name:affair_name,
                                p_number:p_number,
                                go_date:go_date,
                                post_name:post_name,
                                job_number:job_number
                            },
                            success:function(){
                                var store2 = Ext.getStore('selected');
                                store2.reload();
                                Ext.Msg.alert("提示","提交成功。");
                            },failure:function(){
                                Ext.Msg.alert("错误","请输入相应信息！");
                            }
                        })
                    }
                }]
            }]
        },{
            xtype:'container',
            anchor:'30%',
            layout:'hbox',
            margin:'10 0 0 0',
            border:0,
            items:[]
        },{
            xtype : "form",
            border : false,
            id : "gridform",
            items : [{
                xtype : 'grid',
                columnLines : true,
                id : "gridd",
                width : "100%",
                store : store,
                tbar : [{
                    xtype : "button",
                    text : "删除",
                    iconCls : "Delete",
                    handler : function() {
                        var selectedI = Ext.getCmp("gridd").getSelectionModel();
                        if(selectedI.selected.items && selectedI.selected.items.length>0){
                            var rec = selectedI.selected.items[0]["data"];
                            Ext.Ajax.request({
                                url:'delTravelInfo.action',
                                params:{
                                    TID:rec['TID']
                                },
                                success:function(){
                                    var store2 = Ext.getStore('selected');
                                    store2.reload();
                                    Ext.Msg.alert("提示","删除成功。");
                                },failure:function(){
                                    Ext.Msg.alert("提示","删除失败！。");
                                }
                            })
                        }else{
                            Ext.Msg.alert(
                                "提示", "请选择一行记录进行删除")
                        }
                    }
                }, '->',{
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
                    xtype : 'button',
                    text : '导出',
                    iconCls : "Applicationgo",
                    handler : function() {
                        Ext.Ajax.request({
                            url:'DaoChu.action',
                            success:function(){
                                Ext.Msg.alert("提示","导出成功。");
                            },failure:function(){
                                Ext.Msg.alert("错误","与后台联系的时候出了问题。");
                            }
                        })
                    }
                }],
                columns : [	Ext.create('Ext.grid.RowNumberer',
                    {text : '序号',
                        align:'center',
                        width:"7%"
                    }),{
                    header : '项目编号',
                    width : '14%',
                    menuDisabled : true,// 去掉表格下拉排序
                    dataIndex : 'P_NUMBER',
                    align : "center"
                }, {
                    header : '项目名称',
                    dataIndex : 'TAFFAIR',
                    menuDisabled : true,
                    width : '21%',
                    align : "center"
                },{
                    header : '工号',
                    dataIndex : 'JOB_NUMBER',
                    menuDisabled : true,
                    width : '14%',
                    align : "center"
                },
                    // 可设置是否为该列进行排序
                    {
                        header : '姓名',
                        dataIndex : 'TNAME',
                        width : '14%',
                        menuDisabled : true,
                        align : "center"
                    },
                    {
                        header : '职务',
                        dataIndex : 'DPOST',
                        width : '15%',
                        menuDisabled : true,
                        align : "center"
                    },{
                        header : '出发时间',
                        dataIndex : 'TDATE',
                        width : '15%',
                        menuDisabled : true,
                        align : "center",
                        format : 'Y-m-d',
                        flex : 1
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

