Ext.onReady(function() {

	// 瀹氫箟鍒�
	var columns = [ {
		header : '姓名',
		width : 85,
		dataIndex : 'account',
		align : 'center'
	}, {
		header : '项目名称',
		width : 150,
		dataIndex : 'affair',
		align : 'center'
	}, {
		header : '地点',
		width : 70,
		dataIndex : 'lacale',
		align : 'center'
	}, {
		header : '开始时间',
		width : 100,
		dataIndex : 'startime',
		align : 'center'
	}, {
		header : '结束时间',
		width : 100,
		dataIndex : 'endtime',
		align : 'center'
	}, {
		header : '工作日志',
		width : 390,
		dataIndex : 'detail',
		align : 'center'
	}, {
		header : '管理员',
		width : 70,
		dataIndex : 'manager',
		align : 'center'
	}, {
		header : '是否评分',
		width : 70,
		dataIndex : 'isdeal',
		align : 'center'
	}, {
		header : '分数',
		width : 100,
		dataIndex : 'score',
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
							 width: 500,
							 height: 450,
							 border:0,
							 title : '员工位置',
							 hidden :true,
							 modal:true,
							 iconCls: "Applicationformedit",
							 x: 350,
							 y: 10,
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
			pageSize : 10,
			store : store,
			displayInfo : true,
			displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
			emptyMsg : "没有数据"
		}),
			tbar:[{
			xtype:'button',
			text: "编辑",
            iconCls: "Applicationformedit",
            handler: function () {
            }
		}],
	});
});
