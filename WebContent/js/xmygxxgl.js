Ext.onReady(function() {
    var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
        clicksToEdit : 1,
        id : 'cellEditing'
    });

    var columns = [ Ext.create('Ext.grid.RowNumberer', {
        text : '序号',
        align : 'center',
        width : "5%"
    }), {
        header : '项目编号',
        width : "10%",
        dataIndex : 'P_NUMBER',
        align : 'center'
    }, {
        header : '项目名称',
        width : "25%",
        dataIndex : 'P_NAME',
        align : 'center'
    }, {
        header : '地点',
        width : "15%",
        dataIndex : 'P_CITY',
        align : 'center'
    }, {
        header : '出发时间',
        width : "15%",
        dataIndex : 'P_DATE',
        align : 'center'
    }, {
        header : '创建人',
        width : "15%",
        dataIndex : 'P_USERNAME',
        align : 'center'
    }, {
        header : '创建日期',
        width : "15%",
        dataIndex : 'P_CREATE_DATE',
        align : 'center',
        flex:1
    } ];
    
    var store = new Ext.data.ArrayStore({

        id : 'selected',
        fields : [ {
            name : 'P_NUMBER'
        }, {
            name : 'P_NAME'
        }, {
            name : 'P_CITY'
        }, {
            name : 'P_DATE'
        }, {
            name : 'P_USERNAME'
        }, {
            name : 'P_CREATE_DATE'
        } ],
        proxy : {
            type : 'ajax',
            url : 'getXmygxxInfo.action',
            reader : {
                type : 'json',
                root : 'list'
            }
        },
        listeners : {

            'beforeload' : function(store, op, options) {
                var params = {
                    // codeDesc: Ext.getCmp('code_desc').getValue()
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
            'beforeload': function (store, op, options) {
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
            'beforeload': function (store, op, options) {
                var params = {
                    post_name:Ext.getCmp('post_name').getValue()
                };
                Ext.apply(store.proxy.extraParams, params);
            }
        } ,
        autoLoad: false
    });

    var grid = new Ext.grid.GridPanel({
        renderTo : Ext.getBody(),
        store : store,
        id : 'gridd',
        plugins : [ cellEditing ],
        columnLines : true,
        columns : columns,
        stripeRows : true,
        loadMask : true,

        bbar : new Ext.PagingToolbar({
            pageSize : 3,
            store : store,
            displayInfo : true,
            displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
            emptyMsg : "没有数据"
        }),
        tbar : [{
            xtype : 'button',
            text : "员工管理",
            iconCls : "Applicationformedit",
            handler : function() {
                var selectedI = Ext.getCmp("gridd").getSelectionModel();
                if(selectedI.selected.items && selectedI.selected.items.length > 0){
                    var rec = selectedI.selected.items[0]["data"];
                    P_NUMBER = rec['P_NUMBER'];
                    P_CITY = rec['P_CITY'];
                    P_NAME = rec['P_NAME'];
                    Ext.Ajax.request({
                        url:'getP_NUMBER.action',
                        params:{
                            P_NUMBER:P_NUMBER
                        },
                        success:function(response){

                        },failure:function(){
                        }
                    });
                    var ygstore = new Ext.data.ArrayStore({

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
                        }, {
                            name : 'P_EVACUATE_DATE'
                        }],
                        proxy : {
                            type : 'ajax',
                            url : 'getTravelInfos.action',
                            reader : {
                                type : 'json',
                                root : 'list'
                            }
                        },
                        listeners:{
                            'beforeload': function (store, op, options) {
                                var params = {
                                };
                                Ext.apply(store.proxy.extraParams, params);
                            }
                        },
                        pageSize : 10,
                        autoLoad : true
                    });

                    win =Ext.create('widget.window',{
                        titleCollapse: true,
                        closable: false,
                        resizabl: false,
                        width:1000,
                        height:500,
                        border:0,
                        title:'项目员工管理',
                        hidden: true,
                        modal: true,
                        iconCls:"Applicationformedit",
                        x:80,
                        y:20,
                        bodyStyle:"background:#ffffff",

                        items:[{
                            xtype:'form',
                            id:'addForm',
                            items:[{

                                xtype:'container',
                                anchor:'100%',
                                margin:'10 0 0 10',
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
                                        //store:specialtyStore,
                                        name: 'job_number',
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
                                },{
                                    xtype: 'container',
                                    flex: .25,
                                    layout: 'anchor',
                                    items: [{
                                        xtype:'button',
                                        text:'添&nbsp;&nbsp;加',
                                        margin:'0 10 10 0',
                                        id:'create',
                                        labelWidth: 60,
                                        name: 'create',
                                        anchor:'96%',
                                        handler:function(){
                                            var go_date = Ext.getCmp('go_date').getValue();
                                            var post_name = Ext.getCmp('post_name').getValue();
                                            var job_number = Ext.getCmp('job_number').getValue();
                                            Ext.Ajax.request({
                                                url:'addTravelInfos.action',
                                                params:{
                                                    P_NUMBER : P_NUMBER,
                                                    P_CITY : P_CITY,
                                                    P_NAME : P_NAME,
                                                    go_date:go_date,
                                                    post_name:post_name,
                                                    job_number:job_number
                                                },
                                                success:function(response){
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
                                xtype : "form",

                                border : false,
                                id : "gridform",
                                items : [{
                                    xtype : 'grid',
                                    columnLines : true,
                                    id : "gridd1",
                                    width : "100%",
                                    store : ygstore,
                                    plugins:cellEditing,
                                    tbar:[{
                                        xtype : "button",
                                        text : "保存",
                                        iconCls : "Applicationformmagnify",
                                        handler : function() {
                                            var selectedI = Ext.getCmp("gridd1").getSelectionModel();
                                            if(selectedI.selected.items && selectedI.selected.items.length>0){
                                                var rec_cl = selectedI.selected.items[0]["data"];
                                                P_NUMBER = rec_cl['P_NUMBER'];
                                                JOB_NUMBER = rec_cl['JOB_NUMBER'];
                                                P_EVACUATE_DATE = rec_cl['P_EVACUATE_DATE'];
                                                Ext.MessageBox.confirm("提示", "确定要撤离此员工吗？", goReset);
                                                function goReset(btn){
                                                    if(btn == "yes"){
                                                        Ext.Ajax.request({
                                                            url:'addTravelYgclInfo.action',
                                                            params:{
                                                                P_NUMBER : rec_cl['P_NUMBER'],
                                                                JOB_NUMBER : rec_cl['JOB_NUMBER'],
                                                                P_EVACUATE_DATE : rec_cl['P_EVACUATE_DATE']
                                                            },
                                                            success:function(response){
                                                                var store2 = Ext.getStore('selected');
                                                                store2.reload();

                                                                Ext.Msg.alert("提示","撤离成功！");
                                                            },failure:function(){
                                                                Ext.Msg.alert("提示","撤离失败！");
                                                            }
                                                        })
                                                    }
                                                }
                                            }else{
                                                Ext.Msg.alert(
                                                    "提示", "请选择一名员工进行操作！")
                                            }
                                        }
                                    }],
                                    columns : [	Ext.create('Ext.grid.RowNumberer',
                                        {text : '序号',
                                            align:'center',
                                            width:"5%"
                                        }),{
                                        header : '项目编号',
                                        width : '10%',
                                        menuDisabled : true,// 去掉表格下拉排序
                                        dataIndex : 'P_NUMBER',
                                        align : "center"
                                    }, {
                                        header : '项目名称',
                                        dataIndex : 'TAFFAIR',
                                        menuDisabled : true,
                                        width : '20%',
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
                                            width : '10%',
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
                                            header : '加入时间',
                                            dataIndex : 'TDATE',
                                            width : '13%',
                                            menuDisabled : true,
                                            align : "center",
                                            format : 'Y-m-d'
                                        },{
                                            header : '撤离时间',
                                            dataIndex : 'P_EVACUATE_DATE',
                                            width : '13%',
                                            menuDisabled : true,
                                            align : "center",
                                            format : 'Y-m-d',
                                            renderer: Ext.util.Format.dateRenderer('Y-m-d'),
                                            editor:{
                                                xtype:'datefield',
                                                name:'cldate',
                                                format:"Y-m-d",

                                                id:'cldate'
                                            },
                                            flex : 1
                                        }]
                                }]
                            }]
                        }],
                        buttons: [{
                            xtype: "button",
                            text: "返回",
                            handler: function () {
                                var store1 = Ext.getStore('selected');
                                store1.reload();
                                win.close();
                            }
                        }]
                    });
                    win.show(this);
                }else{
                    Ext.Msg.alert(
                        "提示", "请选择一个项目进行管理！")
                }
            }
        }]
    });
});
