Ext.onReady(function() {

	
	var columns = [ {
		header : '姓名',
		width : "10%",
		dataIndex : 'ACCOUNT',
		align : 'center'
	}, {
		header : '项目名称',
		width : "30%",
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
		header : '管理员',
		width : "10%",
		dataIndex : 'MANAGER',
		align : 'center',
	}, {
		header : '是否完成',
		width : "10%",
		dataIndex : 'ISCOMPLETE',
		align : 'center',
		flex : 1
	} ];

	
	var store = new Ext.data.ArrayStore({

		id : 'selected',
		fields : [ {
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

			  'beforeload': function (store, op, options) {
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
	var grid = new Ext.grid.GridPanel({
		renderTo : 'grid', 
		store : store, 
		id : 'gridd',
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
					win = Ext.create('widget.window', {
						title : '员工在外信息',
						closable : false,
						width : 500,
						height : 280,
						bodyPadding : 10,
						iconCls : "Applicationformedit",
						bodyStyle : "background:#ffffff",
						resizable : false,
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
										success : function(form,
												options) {
											Ext.Msg.alert("提示", "数据保存成功",
													function() {
														var store = Ext.getStore("gridd");
														store.reload();
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
								id : 'account',
								name : 'account'
							},{
								xtype : 'textfield',
								fieldLabel : '项目名称',
								id : 'affair',
								name : 'affair'
							},{
								xtype : 'textfield',
								fieldLabel : '地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点&nbsp;',
								id : 'lacale',
								name : 'lacale'
							},{
								xtype : 'datefield',
								fieldLabel : '开始时间',
								name : 'starttime',
								id:'starttime',
								format : 'Y-m-d'
							}, {
								xtype : 'datefield',
								fieldLabel : '结束时间',
								name : 'endtime',
								id : 'endtime',
								format : 'Y-m-d'
							},{
								xtype : 'textfield',
								fieldLabel : '管&nbsp;理&nbsp;员&nbsp;',
								id : 'manager',
								name : 'manager'
							}]
						}]
					});
					win.show(this);
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
            						
            						console.log(rec["account"]+rec["affair"]);
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
            								success : function(form,options) {
            									Ext.Msg.alert("提示", "评测成功",function() {
            											var store = Ext.getStore("gridd");
            											store.reload();
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
                        				     enableColors: false,
                        				     enableAlignments: false,
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
                        				}/*,{
                        					xtype: 'extkindeditor',
                        					allowBlank: false,
                        					name: 'Responsibilities',
                        					height: 100,
                        					Controller:'yfcaone',
                        					width:200,
                        					labelWidth:70,
                        					margins:'0,0,0,10',
                        					id: 'Responsibilities',
                        					fieldLabel: '日志内容'
                        				}*/]
                        				
                        				}]
                        			});		
                        			win.show(this);
   					 }else{
   						 Ext.Msg.alert("提示","该项目已完成，不可进行编辑！");
   					 }
            	
            			}else{
            			Ext.Msg.alert("提示","请选择一条记录进行评测！");
            			return;
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
		}],
	});
});
