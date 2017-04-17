Ext.onReady(function() {

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
		width : "20%",
		dataIndex : 'STARTTIME',
		align : 'center'
	}, {
		header : '结束时间',
		width : "20%",
		dataIndex : 'ENDTIME',
		align : 'center'
	}, {
		header : '管理员',
		width : "10%",
		dataIndex : 'MANAGER',
		align : 'center',
		flex : 1
	} ];

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
		} ],
		proxy : {
			type : 'ajax',
			url : 'getYpzaygInfo.action',
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

		bbar : new Ext.PagingToolbar({
			pageSize : 10,
			store : store,
			displayInfo : true,
			displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
			emptyMsg : "没有数据"
		}),
			tbar:[{
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
