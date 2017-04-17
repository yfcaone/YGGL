Ext.onReady(function() {

	// 瀹氫箟鍒�
	var columns = [ {
		header : '姓名',
		width : "10%",
		dataIndex : 'account',
		align : 'center'
	}, {
		header : '项目名称',
		width : "30%",
		dataIndex : 'affair',
		align : 'center'
	}, {
		header : '地点',
		width : "10%",
		dataIndex : 'lacale',
		align : 'center'
	}, {
		header : '开始时间',
		width : "20%",
		dataIndex : 'startime',
		align : 'center'
	}, {
		header : '结束时间',
		width : "20%",
		dataIndex : 'endtime',
		align : 'center'
	}, {
		header : '管理员',
		width : "10%",
		dataIndex : 'manager',
		align : 'center',
		flex : 1
	} ];

	// 杞崲鍘熷鏁版嵁涓篍XT鍙互鏄剧ず鐨勬暟鎹�
	var store = new Ext.data.ArrayStore({

		id : 'selected',
		fields : [ {
			name : 'account'
		}, {
			name : 'affair'
		}, {
			name : 'lacale'
		}, {
			name : 'startime'
		}, {
			name : 'endtime'
		}, {
			name : 'detail'
		}, {
			name : 'manager'
		}, {
			name : 'isdeal'
		}, {
			name : 'score'
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
                   // codeDesc: Ext.getCmp('code_desc').getValue()
                };
                Ext.apply(store.proxy.extraParams, params);
            }
		}
	});
	// 鍔犺浇鏁版嵁
	store.load({
		params : {
			start : 0,
			limit : 25
		}
	});
	var grid = new Ext.grid.GridPanel({
		renderTo : 'grid', // 娓叉煋浣嶇疆
		store : store, // 杞崲鍚庣殑鏁版嵁
		id : 'gridd',
		columnLines: true,
		columns : columns, // 鏄剧ず鍒�
		stripeRows : true, // 鏂戦┈绾挎晥鏋�
		loadMask : true, // 鏄剧ず閬僵鍜屾彁绀哄姛鑳�,鍗冲姞杞絃oading鈥︹��
		
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
							 title : rec['account']+'的位置',
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
						        	
						        	html:'<iframe id=cenIF src=map.action?account='+rec["account"]+'  scrolling=yes height=600 width=100%></iframe>'
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
		
		bbar : new Ext.PagingToolbar({
			pageSize : 3,
			store : store,
			displayInfo : true,
			displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
			emptyMsg : "没有数据"
		}),
			tbar:[{
			xtype:'button',
			text: "评分",
            iconCls: "Applicationformedit",
            handler: function () {

            	var selectedI = Ext.getCmp("gridd").getSelectionModel();
            		if (selectedI.selected.items && selectedI.selected.items.length > 0) {
            			var rec = selectedI.selected.items[0]["data"];
            			//弹窗显示信息
            			var win=Ext.create("widget.window",{
             	title : '查看公告',
             	closable:false,
            	id:'lookupWin',
            	
            	width : 700,
            	height : 400,
            	modal:true,
            	bodyStyle : "background:#ffffff",
            	x : 80,
            	y : 20,
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
								url : "addPcInfo.action?account="+rec["account"]+"&affair="+rec["affair"]+"",
								data : JSON.stringify(formData),
								dataType : "json",
								contentType : "application/json; charset=UTF-8",
								success : function(form,
										options) {
									Ext.Msg.alert("提示", "评测成功",
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
            					html:'<h3 align="center" >姓名：'+rec["account"]+'</h3>'
            				},{
            					xtype:'panel',
            					border:0,
            					id:'affair',
            					value:rec["affair"],
            					margin:'-10 0 0 0',
            					id:'attachmentFiled',
            					html:'<h3 align="center" >项目名称：'+rec["affair"]+'</h3>'
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
            	                margin:'0 10 0 10',
            	                labelWidth:70,
            	                value : 5,
            	                increment : 1,
            	                minValue : 0,
            	                maxValue : 10
            	            }, {
            	                // 拖动组件
            	                xtype : 'sliderfield', // 15
            	                id:'rzpc',
            	                margin:'0 10 0 10',
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
					 window.location.href='logContent.action?account='+rec["account"]+'&affair='+rec["affair"]+' '
				 }else{
					 Ext.Msg.alert(
							 "提示", "请选择一行记录进行查看")
				 }
			 
            }
		}],
	});
});
