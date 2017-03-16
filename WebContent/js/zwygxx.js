Ext.onReady(function() {

	// 定义列
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

	// 转换原始数据为EXT可以显示的数据
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
		}
	});
	// 加载数据
	store.load({
		params : {
			start : 0,
			limit : 25
		}
	});
	var grid = new Ext.grid.GridPanel({
		renderTo : 'grid', // 渲染位置
		store : store, // 转换后的数据
		id : 'gridd',
		columns : columns, // 显示列
		stripeRows : true, // 斑马线效果
		loadMask : true, // 显示遮罩和提示功能,即加载Loading……
		 listeners: {//双击表格任意行弹框显示本行信息
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
							 hidden :true,
							 modal:true,
							 iconCls: "Applicationformedit",
							 x: 350,
							 y: 10,
							 bodyStyle: "background:#ffffff",
							 items: [{
						        	width:'100%',
						        	html:'<iframe id=cenIF src=map.action scrolling=yes height=600 width=100%></iframe>'
						        }]	
						 });
						 win.show(this);
					 }else{
						 Ext.Msg.alert(
								 "提示",
								 "请选择一行记录进行查看")
					 }
				 }
			 }
		 },
		bbar : new Ext.PagingToolbar({
			pageSize : 10,
			store : store,
			displayInfo : true,
			displayMsg : '显示第{0}条到{1}条记录，一共 {2}条',
			emptyMsg : "没有记录"
		})
	});
});
