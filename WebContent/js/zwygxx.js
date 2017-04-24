Ext.onReady(function() {

	// 杞崲鍘熷鏁版嵁涓篍XT鍙互鏄剧ず鐨勬暟鎹�
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
			url : 'emplinfor.action',
			reader : {
				type : 'json',
				root : 'list'
			}
		},
		listeners:{

			  'beforeload': function (store, op, options) {
                var params = {
                   name: Ext.getCmp('username').getValue()
                };
                Ext.apply(store.proxy.extraParams, params);
            }
		},
		pageSize : 10,
		autoLoad : true
	});
	
	// 瀹氫箟鍒�
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

	
	var grid = new Ext.grid.GridPanel({
		renderTo : 'grid', // 娓叉煋浣嶇疆
		
		id : 'gridd',
		columnLines: true,
		columns : columns, // 鏄剧ず鍒�
		stripeRows : true, // 鏂戦┈绾挎晥鏋�
		loadMask : true, // 鏄剧ず閬僵鍜屾彁绀哄姛鑳�,鍗冲姞杞絃oading鈥︹��
		store : store, // 杞崲鍚庣殑鏁版嵁
		 listeners: {
			 
			 dblclick:{
				 element:'body',
				 fn:function(){
					 var selectedI = Ext.getCmp("gridd").getSelectionModel();
					 if(selectedI.selected.items && selectedI.selected.items.length>0){
						 var rec = selectedI.selected.items[0]["data"];
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
					 }else{
						 Ext.Msg.alert(
								 "提示", "请选择一行记录进行查看")
					 }
				 }
			 }
		 },
		
		
			tbar:[{
			xtype:'button',
			text: "评分",
            iconCls: "Applicationformedit",
            handler: function () {

            	var selectedI = Ext.getCmp("gridd").getSelectionModel();
            		if (selectedI.selected.items && selectedI.selected.items.length > 0) {
            			var rec = selectedI.selected.items[0]["data"];
            			if(rec['ISCOMPLETE']=="完成"){
            				//弹窗显示信息
                        	var win=Ext.create("widget.window",{
                         	title : '查看公告',
                         	closable:false,
                         	resizable:false,//是否可以改变大小
                         	draggable:false,//是否可以拖动
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
            								url : "addPcInfo.action?account="+rec["ACCOUNT"]+"&affair="+rec["AFFAIR"]+"",
            								data : JSON.stringify(formData),
            								dataType : "json",
            								contentType : "application/json; charset=UTF-8",
            								success : function(form,options) {
            									Ext.Msg.alert("提示", "评测成功",function() {
            											var store = Ext.getStore("selected");
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
                        					xtype:'panel',
                        					border:0,
                        					id:'account',
                        					value:rec["account"],
                        					html:'<h3 align="center" >姓名：'+rec["ACCOUNT"]+'</h3>'
                        				},{
                        					xtype:'panel',
                        					border:0,
                        					id:'affair',
                        					value:rec["affair"],
                        					margin:'-10 0 0 0',
                        					id:'attachmentFiled',
                        					html:'<h3 align="center" >项目名称：'+rec["AFFAIR"]+'</h3>'
                        				},{
                        					xtype:'panel',
                        					border:0,
                        					margin:'-10 0 0 0',
                        					html:'<h3 align="center" >考勤情况：'+'没有缺勤'+'</h3>'
                        				},{
                        					xtype:'panel',
                        					border:0,
                        					margin:'-10 0 0 0',
                        					html:'<h3 align="center" >日志情况：'+'整体可以'+'</h3>'
                        				},{
                        					xtype:'panel',
                        					border:0,
                        					margin:'-10 0 0 0',
                        					html:'<h3 align="center" >项目结果情况：'+'正常完成任务'+'</h3>'
                        				},{
                        					xtype:'label',
                        					border:0,
                        					width: '100%',
                        					border:0,
                        					width:4,
                        					margin:'-10 0 0 0',
                        					html:'<hr style="color: white;">'
                        				},{
                        	                // 拖动组件
                        	                xtype : 'sliderfield', // 15
                        	                id:'kqpc',
                        	                fieldLabel : '考勤评测',
                        	                margin:'25 10 10 10',
                        	                labelWidth:70,
                        	                value : 5,
                        	                increment : 1,
                        	                minValue : 0,
                        	                maxValue : 10
                        	            }, {
                        	                // 拖动组件
                        	                xtype : 'sliderfield', // 15
                        	                id:'rzpc',
                        	                margin:'0 10 10 10',
                        	                labelWidth:70,
                        	                fieldLabel : '日志评测',
                        	                value : 5,
                        	                increment : 1,
                        	                minValue : 0,
                        	                maxValue : 10
                        	            },{
                        	                // 拖动组件
                        	                xtype : 'sliderfield', // 15
                        	                id:'jgpc',
                        	                margin:'0 10 0 10',
                        	                labelWidth:70,
                        	                fieldLabel : '结果评测',
                        	                value : 5,
                        	                increment : 1,
                        	                minValue : 0,
                        	                maxValue : 10
                        	            }]
                        				
                        				}]
                        			});		
                        			win.show(this);
            			}else{
            				Ext.Msg.alert("提示","此项目未完成！");
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
		},'->',{
			xtype : 'textfield',
			name : 'username',
			id : 'username',
			margin:'0 10 0 0',
			labelWidth : 30,
			fieldLabel : '姓名'
		}, {
			xtype : 'button',
			text : '查询',
			iconCls : "Bulletmagnify",
			handler : function() {
				var store = Ext.getStore("selected");
				store.reload();
			}
		}],
		bbar : new Ext.PagingToolbar({
			pageSize : 3,
			store : store,
			displayInfo : true,
			displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
			emptyMsg : "没有数据"
		}),
	});
	
	
});
