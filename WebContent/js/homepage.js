Ext.onReady(function () {
     //使用Viewport布局
	var user = username;
     var mypanel = new Ext.container.Viewport({
         layout: 'border',//表格布局
         items: [
              north,
              west,
              centerWin,
              south
            ]
        }); 
   });
   
var genderStore = Ext.create("Ext.data.Store", {
    fields: ["Name", "Value"],
    data: [
        { Name: "男", Value: "男" },
        { Name: "女", Value: "女" }
    ]
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
//编写 各个模块
	//编写north panel
	var north = new Ext.panel.Panel({
	    region: 'north',
	    title: '员工后台管理系统',
	   
	    bbar: [{
	        text: '行政办公'
	    }, '-', {
	        text: '电话通信'
	    }, '-', {
	        text: '客户管理'
	    }, '-', {
	        text: '人力资源管理'
	    }, '-', {
	        text: '进销存管理'
	    }, '-', {
	        text: 'VIP客户管理'
	    }, '-', {
	        text: '业绩管理',
	    }, {
	        xtype: "tbfill"
	    }, {
	        pressed: false, text: username,
	        menu:new Ext.menu.Menu({
                ignoreParentCilcks:true,
                items:[{
                    text:'注销',
                    handler:function(){
                    	Ext.MessageBox.confirm("提示", "确定注销该用户？");
                    	window.location.href="../login/login.action";
                    }
                },{
                    text:'退出',
                    handler:function(){
                    	Ext.MessageBox.confirm("提示", "确定要退出吗？");
                    	CloseWebPage()
                    }
                }]
            })
	    }, {
	        pressed: false, text: '帮助'
	    }]
	});//north panel 编写

    //编写 west panel
    var west = new Ext.panel.Panel({
        title: '信息管理栏',
        region: 'west',
        collapsible: true,
        split: true,
        width: 200,
        margins: 1,
        //添加两块面板
        layout: 'auto',
        items: [{
            title: "员工管理",
            height: 285,
            collapsible: true,
            layout: 'accordion',//使用手风琴进行布局
            items: [{
                title: '在外员工信息管理',
                items: [{
                    xtype: 'treepanel',
                    border: 0,
                    rootVisible: false,//根节点是否可见
                    root: {
                        children: [
                            {text: '未评在外员工信息', leaf: true},
                            {text: '已评在外员工信息', leaf: true}
                        ]
                    },
                    listeners:{
                        //如果项被点击的话
                        itemclick:function(view,record,item){
                            //如果是叶子，对应上面的leaf==true
                            if(record.data.leaf){
                                //如果没有与当前点击项目id相同的的标签页的话
                            	if(record.data.text=="未评在外员工信息"){
                            		$('#cenIF').attr("src","zwygxx.action");
                            	}else if(record.data.text=="已评在外员工信息"){
                            		$('#cenIF').attr("src","ypzwygxx.action");
                            	}
                            }
                        }
                    },
                }],
            }, {
                title: '出差及费用报销管理',
                items: [{
                    xtype: 'treepanel',
                    border: 0,
                    rootVisible: false,//根节点是否可见
                    root: {
                        children: [
                            {text: '出差信息录入', leaf: true},
                            {text: '请假信息录入', leaf: true},
                            {text: '报销借款信息录入', leaf: true},
                            {text: '出差费用结算', leaf: true},
                          
                        ]
                    },
                    listeners:{
                        //如果项被点击的话
                        itemclick:function(view,record,item){
                            //如果是叶子，对应上面的leaf==true
                            if(record.data.leaf){
                                //如果没有与当前点击项目id相同的的标签页的话
                            	if(record.data.text=="出差信息录入"){
                            		$('#cenIF').attr("src","ccxxlr.action");
                            	}else if(record.data.text=="请假信息录入"){
                            		$('#cenIF').attr("src","qjxxlr.action");
                            	}else if(record.data.text=="报销借款信息录入"){
                            		$('#cenIF').attr("src","bxxxlr.action");
                            	}else if(record.data.text=="出差费用结算"){
                            		$('#cenIF').attr("src","ccfyjs.action");
                            	}
                            }
                        }
                    },
                }]
            }, {
                title: '商品信息管理系统',
                items: [{
                    xtype: 'treepanel',
                    border: 0,
                    rootVisible: false,//根节点是否可见
                    root: {
                        children: [
                            {text: '收购信息', leaf: true},
                            {text: '订单信息', leaf: true},
                            {text: '产品信息', leaf: true},
                            {text: '客户信息', leaf: true},
                            {text: 'VIP客户信息', leaf: true},
                            {text: '销售信息', leaf: true}
                        ]
                    }
                }]
            }],
        }, {
            collapsible: true,
            style: 'margin-top: 3px;',
            height: 205,
            title: "未读信息",
            items: [{
                xtype: 'treepanel',
                border: 0,
                rootVisible: false,//根节点是否可见
                root: {
                    children: [
                        {text: 'xxx信息消费记录', leaf: true},
                        {text: '财政支出信息', leaf: true},
                        {text: '产品信息', leaf: true},
                        {text: '客户建议信息', leaf: true}
                    ]
                }
            }]
        }]
    });
    //编写 west panel
        //编写 center panel
var centerWin = Ext.create('Ext.panel.Panel', {
    	id:'centerWin',
	 //   title: 'Hello',
	    region:'center',
	    height: '100%',
	    x:275,
	    y:0,
	    width: '80%',
	    layout: 'fit',
	    items: [{
        	width:'100%',
        	html:'<iframe id=cenIF frameborder=0 src=zwygxx.action scrolling=yes height="100%" width=100%></iframe>'
        }]	
	});

   
    var south = new Ext.panel.Panel({
        id:'south',
        region: 'south',
        height: 30,
        bbar: [{
            text:'工具栏',
            menu:new Ext.menu.Menu({
                ignoreParentCilcks:true,
                items:[{
                    text:'创建用户',
                    handler:function(){
                    	var win = Ext.create('widget.window', {
                    		title : '创建用户',
                    		maximizable : false,  
                            draggable : false,  
                            closable : false,  
                            resizable : false,  
                    		width : 700,
                    		height : 450,
                    		iconCls : "Add",
                    		bodyStyle : "background:#ffffff",
                    		constrain : true,
                    		buttons : [{
                    			xtype : "button",
                    			text : "返回",
                    			handler : function() {
                    				win.close();
                    			}
                    		}],
                    		modal : true,
                    		items : [{
                    			xtype:'form',
                    			id:'form',
                    			border:0,
                    			items:[{
                    			xtype : 'form',
                    			id : 'form1',
                    			layout : 'hbox',
                    			border : 0,
                    			items : [{
                    				xtype : 'textfield',
                    				margin:'10 20 10 10 ',
                    				labelWidth : 60,
                    				
                    				labelAlign : 'left',
                    				fieldLabel : '姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名',
                    				id : 'account',
                    				name : 'account'
                    			},{
                    				xtype: "combobox",
                    	            name: "Gender",
                    	            margin:'10 20 10 10 ',
                    	            fieldLabel: "性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别",
                    	            labelWidth : 60,
                    	            store: genderStore,
                    	            editable: false,
                    	            displayField: "Name",
                    	            valueField: "Value",
                    	            emptyText: "--请选择--",
                    	            queryMode: "local"
                    			}]
                    		},{
                    			xtype:'form',
                    			id:'bmform',
                    			width:'90%',
                    			layout:'hbox',
                    			border:0,
                    			items:[{
                					xtype:'combobox',
                                    fieldLabel: '部门名称',
                                    id:'department_name',
                                    labelWidth: 60,
                                    margin:'10 20 10 10 ',
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
                				},{
                                    xtype:'combobox',
                                    fieldLabel: '职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务',
                                    id:'post_name',
                                    labelWidth: 60,
                                    margin:'10 20 10 10 ',
                                    store:post_name,
                                    valueField:'DPOST_CODE',
                                    displayField:'DPOST',
                                    emptyText : '--请选择职务--',
                                    name: 'post_name',
                                    editable : false,
                                    anchor:'96%',
                                   
                                }]
                    		}]
                    		},{
                    			xtype:'form',
                    			id:'addform',
                    			border:0,
                    			items:[{
                    				xtype : 'button',
                    				labelWidth : 50,
                    				labelAlign : 'left',
                    				text:'创&nbsp;&nbsp;建',
                    				width:60,
                    				margin:'10 20 10 10 ',
                    				id : 'create',
                    				name : 'create',
                    				handler : function() {
                    					
                						var form = Ext.getCmp("form").getForm();
                						if (form.isValid()) {
                							var formData = form.getValues();
                							$.ajax({
                								async : false,
                								type : "post",
                								url : "createUser.action",
                								data : JSON.stringify(formData),
                								dataType : "json",
                								contentType : "application/json; charset=UTF-8",
                								success : function(form,options) {
                									Ext.Msg.alert("提示", "添加成功",function() {
                											var store = Ext.getStore("gridd1");
                											store.reload();
                									});
                								}
                							});
                						}
                					}
                    			}]
                    		},{
                    			xtype : "form",
                    			layout : "vbox",
                    			border : false,
                    			id : "gridform",
                    			items : [{
                    				xtype : 'grid',
                    				columnLines : true,
                    				overflowY:'auto',
                    				id : "gridd",
                    				height:263,
                    				width : "100%",
                    				store : store1,
                    				tbar : [{
                    					xtype : "button",
                    					text : "删除",
                    					iconCls : "Delete",
                    					handler : function() {}
                    				}, '->',{
                    					xtype : 'textfield',
                    					name : 'name',
                    					id : 'name',
                    					margin:'0 10 0 0',
                    					labelWidth : 30,
                        				fieldLabel : '姓名'
                    				},{
                    					xtype : 'datetimefield',
                    					name : 'date',
                    					fieldLabel : "时间",
                    					labelWidth : 30,
                    					id : 'date',
                    					value:new Date() ,  
                    					format : 'Y-m-d h:i:s'
                    				}, {
                    					xtype : 'button',
                    					text : '查询',
                    					iconCls : "Bulletmagnify",
                    					handler : function() {
                    						var store = Ext.getStore("gridd1");
                    						store.reload();
                    					}
                    				}],
                    				columns : [{
                    							header : '工号',
                    							width : '20%',
                    							menuDisabled : true,// 去掉表格下拉排序
                    							dataIndex : 'VID',
                    							align : "center"
                    						}, {
                    							header : '姓名',
                    							dataIndex : 'VNAME',
                    							menuDisabled : true,
                    							width : '20%',
                    							align : "center"
                    						},{
                    							header : '用户名',
                    							dataIndex : 'VACCOUNT',
                    							menuDisabled : true,
                    							width : '20%',
                    							align : "center"
                    						},
                    						// 可设置是否为该列进行排序
                    						{
                    							header : '密码',
                    							dataIndex : 'VPASSWORD',
                    							width : '20%',
                    							menuDisabled : true,
                    							align : "center"
                    						},
                    						{
                    							header : '时间',
                    							dataIndex : 'VDATE',
                    							width : '25%',
                    							menuDisabled : true,
                    							align : "center",
                    							format : 'Y-m-d',
                    							flex : 1
                    						}]
                    			}]

                    		}]
                    	});
                    	win.show(this);
                    }
                },{
                    text:'保存'
                },{
                    text:'退出'
                }]
            })
        }, '-', {
            xtype: "tbfill"
        }, '-', {
            text: 'XXX后台管理系统'
        }, '-', {
            text: "XXX信息技术有限公司"
        }, '-', {
            text: '与我们练习'
        }]
    });//south panel
    function CloseWebPage() {
        if (navigator.userAgent.indexOf("MSIE") > 0) {      //IE浏览器
            if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
                window.opener = null; 
                window.close();
            }
            else {
                window.open('', '_top'); window.top.close();
            }
        }
        else if (navigator.userAgent.indexOf("Firefox") > 0) {     //火狐浏览器
            window.location.href = 'https://www.baidu.com/ ';
            //window.history.go(-2);
        }
        else {                      //其他浏览器
            window.opener = null; 
            window.open('', '_self', '');
            window.close();
        }
    }  
   
   