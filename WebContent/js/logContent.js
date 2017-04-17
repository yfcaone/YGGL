Ext.onReady(function() {

	// 瀹氫箟鍒�
	var columns = [ {
		header : '姓名',
		width : "10%",
		dataIndex : 'LNAME',
		align : 'center'
	}, {
		header : '项目名称',
		width : "30%",
		dataIndex : 'LAFFAIR',
		align : 'center'
	}, {
		header : '日志时间',
		width : "10%",
		dataIndex : 'LDATE',
		align : 'center'
	}, {
		header : '日志内容',
		width : "50%",
		dataIndex : 'LOG',
		align : 'center',
		flex : 1
	} ];

	// 杞崲鍘熷鏁版嵁涓篍XT鍙互鏄剧ず鐨勬暟鎹�
	var store = new Ext.data.ArrayStore({

		id : 'selected',
		fields : [ {
			name : 'LNAME'
		}, {
			name : 'LAFFAIR'
		}, {
			name : 'LDATE'
		}, {
			name : 'LOG'
		} ],
		proxy : {
			type : 'ajax',
			url : 'getLogContent.action',
			reader : {
				type : 'json',
				root : 'list'
			}
		},
		listeners:{

			  'beforeload': function (store, op, options) {
                var params = {
                		lname:lname,
                		laffair:laffair
                
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
		renderTo :Ext.getBody(), // 娓叉煋浣嶇疆
		store : store, // 杞崲鍚庣殑鏁版嵁
		id : 'gridd',
		columnLines: true,
		columns : columns, // 鏄剧ず鍒�
		stripeRows : true, // 鏂戦┈绾挎晥鏋�
		loadMask : true, // 鏄剧ず閬僵鍜屾彁绀哄姛鑳�,鍗冲姞杞絃oading鈥︹��
		
		
			
	});
});
